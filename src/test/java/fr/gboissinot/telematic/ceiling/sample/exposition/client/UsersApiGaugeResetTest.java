package fr.gboissinot.telematic.ceiling.sample.exposition.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.gboissinot.telematic.ceiling.sample.TelematicCellingApplication;
import fr.gboissinot.telematic.ceiling.sample.application.create.CreateUserCommand;
import fr.gboissinot.telematic.ceiling.sample.application.create.CreateUserCommandHandler;
import fr.gboissinot.telematic.ceiling.sample.application.retrieve.RetrieveUserQuery;
import fr.gboissinot.telematic.ceiling.sample.application.retrieve.RetrieveUserQueryHandler;
import fr.gboissinot.telematic.ceiling.sample.domain.model.User;
import fr.gboissinot.telematic.ceiling.sample.domain.model.UserId;
import fr.gboissinot.telematic.ceiling.sample.exposition.ModifyUserGaugeRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.StringWriter;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TelematicCellingApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles({"local", "test"})
@Transactional
public class UsersApiGaugeResetTest {

    private static final double USER_AMOUNT_VALUE = 34.0;
    private static final long DEFAULT_CEILING_VALUE = 1500L;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CreateUserCommandHandler createUserCommandHandler;

    @Autowired
    private RetrieveUserQueryHandler retrieveUserQueryHandler;


    @Test
    public void should_change_gauge() throws Exception {
        UserId userId = createUser();
        sendHttpChangeGaugeValue(userId);
        sendHttpResetGaugeValue(userId);
        checkGaugeZero();
    }

    private UserId createUser() {
        return createUserCommandHandler.handle(new CreateUserCommand(DEFAULT_CEILING_VALUE));
    }

    private void checkGaugeZero() {
        List<User> userList = retrieveUserQueryHandler.handle(new RetrieveUserQuery());
        Assert.assertThat(userList.size(), is(1));
        User user = userList.get(0);
        Assert.assertThat(user.ceilingValue(), is(DEFAULT_CEILING_VALUE));
        Assert.assertThat(user.gaugeValue(), is(0.0));
    }

    private void sendHttpChangeGaugeValue(UserId userId) throws Exception {
        ModifyUserGaugeRequest modifyClientGaugeRequest = new ModifyUserGaugeRequest();
        modifyClientGaugeRequest.amount = USER_AMOUNT_VALUE;
        StringWriter content = new StringWriter();
        objectMapper.writeValue(content, modifyClientGaugeRequest);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mvc.perform(MockMvcRequestBuilders.put("/users/{userId}/gauge", userId.value())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(content.toString()))
                .andExpect(status().isNoContent());
    }

    private void sendHttpResetGaugeValue(UserId userId) throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/users/{userId}/gauge", userId.value())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNoContent());
    }
}