package fr.gboissinot.telematic.ceiling.sample.infrastructure;

import fr.gboissinot.kernel.java.annotation.Pattern;
import fr.gboissinot.telematic.ceiling.sample.domain.model.User;
import fr.gboissinot.telematic.ceiling.sample.domain.model.UserId;

import javax.persistence.*;

@Pattern.DataModel
@Entity
@Table(name = "T_USER")
class UserDataModel {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private long id;

    @Version
    @Column(name = "USER_VERSION", nullable = false)
    private long version;

    @Column(name = "USER_GAUGE", nullable = false)
    private double gauge;

    @Column(name = "USER_CELLING", nullable = false)
    private long celling;

    User toUser() {
        return User.withUserId(UserId.of(id))
                .withVersion(version)
                .withCeiling(celling)
                .withGauge(gauge)
                .build();
    }

    public long getId() {
        return id;
    }

    @SuppressWarnings("WeakerAccess")
    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public double getGauge() {
        return gauge;
    }

    @SuppressWarnings("WeakerAccess")
    public void setGauge(double gauge) {
        this.gauge = gauge;
    }

    public long getCelling() {
        return celling;
    }

    @SuppressWarnings("WeakerAccess")
    public void setCelling(long celling) {
        this.celling = celling;
    }
}
