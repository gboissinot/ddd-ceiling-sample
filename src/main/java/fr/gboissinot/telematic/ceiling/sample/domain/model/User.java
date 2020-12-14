package fr.gboissinot.telematic.ceiling.sample.domain.model;

import fr.gboissinot.kernel.ddd.BaseAggregateRoot;
import fr.gboissinot.kernel.java.annotation.ddd.DDD;

import java.util.Objects;

@DDD.AggregateRoot
public class User extends BaseAggregateRoot<UserId> {

    private final Ceiling ceiling;
    private Gauge gauge;

    private User(UserId userId, long version, Gauge gauge, Ceiling ceiling) {
        super(userId, version);
        this.gauge = gauge;
        this.ceiling = ceiling;
    }

    public static UserBuilder withUserId(UserId userId) {
        return new UserBuilder(userId);
    }

    public void increaseGaugeValue(double amount) {
        Gauge newGauge = gauge.addValue(amount);
        if (isExceedCeiling(newGauge)) {
            throw new AmountExceededException();
        }
        this.gauge = newGauge;
        raiseEvent(new UserGaugeModifiedEvent(id(), amount));
    }

    public void resetGauge() {
        this.gauge = new Gauge(0);
        raiseEvent(new UserGaugeResetEvent(id()));
    }

    private boolean isExceedCeiling(Gauge newGauge) {
        return newGauge.value() > ceiling.value();
    }

    public double gaugeValue() {
        return gauge.value();
    }

    public long ceilingValue() {
        return ceiling.value();
    }

    public static class UserBuilder {

        private static final long DEFAULT_VERSION_VALUE = 0L;
        private static final Gauge DEFAULT_GAUGE_VALUE = new Gauge(0);
        private static final Ceiling DEFAULT_CEILING_VALUE = new Ceiling(0L);

        private final UserId userId;
        private long version;
        private Ceiling ceiling;
        private Gauge gauge;

        UserBuilder(UserId userId) {
            this.userId = Objects.requireNonNull(userId);
            this.version = DEFAULT_VERSION_VALUE;
            this.gauge = DEFAULT_GAUGE_VALUE;
            this.ceiling = DEFAULT_CEILING_VALUE;
        }

        public UserBuilder withVersion(long version) {
            this.version = version;
            return this;
        }

        public UserBuilder withCeiling(long ceilingValue) {
            this.ceiling = new Ceiling(ceilingValue);
            return this;
        }

        public UserBuilder withGauge(double gaugeValue) {
            this.gauge = new Gauge(gaugeValue);
            return this;
        }

        public User build() {
            return new User(userId, version, gauge, ceiling);
        }
    }
}
