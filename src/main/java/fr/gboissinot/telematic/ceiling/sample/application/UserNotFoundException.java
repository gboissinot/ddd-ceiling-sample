package fr.gboissinot.telematic.ceiling.sample.application;

import fr.gboissinot.telematic.ceiling.sample.domain.model.UserId;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UserId id) {
        super(String.format("No user found for the  id %s.", id.value()));
    }
}
