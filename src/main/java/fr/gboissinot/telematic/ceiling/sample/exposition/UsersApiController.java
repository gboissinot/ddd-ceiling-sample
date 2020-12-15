package fr.gboissinot.telematic.ceiling.sample.exposition;

import fr.gboissinot.kernel.cqs.command.CommandBus;
import fr.gboissinot.kernel.cqs.command.CommandPreconditionsFailureException;
import fr.gboissinot.telematic.ceiling.sample.application.gauge.modify.ModifyUserGaugeCommand;
import fr.gboissinot.telematic.ceiling.sample.application.gauge.reset.ResetUserGaugeCommand;
import fr.gboissinot.telematic.ceiling.sample.domain.model.AmountExceededException;
import fr.gboissinot.telematic.ceiling.sample.domain.model.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
class UsersApiController {

    private final CommandBus commandBus;

    @Autowired
    public UsersApiController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PutMapping(path = "/{userId}/gauge", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> changeGaugeValue(@PathVariable long userId,
                                                 @RequestBody @Valid ModifyUserGaugeRequest modifyClientGaugeRequest) {
        ModifyUserGaugeCommand modifyUserGaugeCommand =
                new ModifyUserGaugeCommand(
                        UserId.of(userId),
                        modifyClientGaugeRequest.amount);
        commandBus.post(modifyUserGaugeCommand);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{userId}/gauge", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> resetGaugeValue(@PathVariable long userId) {
        ResetUserGaugeCommand resetUserGaugeCommand = new ResetUserGaugeCommand(UserId.of(userId));
        commandBus.post(resetUserGaugeCommand);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({AmountExceededException.class, CommandPreconditionsFailureException.class})
    public ResponseEntity<Void> handleException() {
        return ResponseEntity.badRequest().build();
    }
}
