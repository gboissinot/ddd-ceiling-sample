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
import org.springframework.http.HttpStatus;
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
public class UsersApiGaugeModificationTest {

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
    public void should_change_gauge_without_exceed_ceiling() throws Exception {
        UserId userId = createOrGetUser();
        double accumulator = 0;
        sendHttpChangeGaugeValue(userId, USER_AMOUNT_VALUE, HttpStatus.NO_CONTENT);
        accumulator += USER_AMOUNT_VALUE;
        checkChangeGaugeWithValue(accumulator);
    }

    @Test
    public void should_change_gauge_on_exceed_ceiling() throws Exception {
        UserId userId = createOrGetUser();
        sendHttpChangeGaugeValue(userId, DEFAULT_CEILING_VALUE + USER_AMOUNT_VALUE, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void should_throw_wrong_command_validation_on_wrong_amount() throws Exception {
        UserId userId = createOrGetUser();
        sendHttpChangeGaugeValue(userId, -USER_AMOUNT_VALUE, HttpStatus.BAD_REQUEST);
    }

    private UserId createOrGetUser() {
        List<User> users = retrieveUserQueryHandler.handle(new RetrieveUserQuery());
        if (users.isEmpty()) {
            return createUserCommandHandler.handle(new CreateUserCommand(DEFAULT_CEILING_VALUE));
        } else {
            //Get the first userId
            return users.get(0).id();
        }
    }

    private void checkChangeGaugeWithValue(double amount) {
        List<User> userList = retrieveUserQueryHandler.handle(new RetrieveUserQuery());
        Assert.assertThat(userList.size(), is(1));
        User user = userList.get(0);
        Assert.assertThat(user.ceilingValue(), is(DEFAULT_CEILING_VALUE));
        Assert.assertThat(user.gaugeValue(), is(amount));
    }

    private void sendHttpChangeGaugeValue(UserId userId, double amount, HttpStatus expectedStatus) throws Exception {
        ModifyUserGaugeRequest modifyClientGaugeRequest = new ModifyUserGaugeRequest();
        modifyClientGaugeRequest.amount = amount;
        StringWriter content = new StringWriter();
        objectMapper.writeValue(content, modifyClientGaugeRequest);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mvc.perform(MockMvcRequestBuilders.put("/users/{userId}/gauge", userId.value())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(content.toString()))
                .andExpect(status().is(expectedStatus.value()));
    }
}