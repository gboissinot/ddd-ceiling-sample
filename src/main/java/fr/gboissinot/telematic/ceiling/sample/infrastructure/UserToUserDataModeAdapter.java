package fr.gboissinot.telematic.ceiling.sample.infrastructure;

import fr.gboissinot.telematic.ceiling.sample.domain.model.User;

import java.util.function.Function;

class UserToUserDataModeAdapter implements Function<User, UserDataModel> {

    static final UserToUserDataModeAdapter INSTANCE = new UserToUserDataModeAdapter();

    private UserToUserDataModeAdapter() {
    }

    @Override
    public UserDataModel apply(User user) {
        UserDataModel userDataModel = new UserDataModel();
        userDataModel.setId(user.id().value());
        userDataModel.setGauge(user.gaugeValue());
        userDataModel.setCelling(user.ceilingValue());
        return userDataModel;
    }
}
