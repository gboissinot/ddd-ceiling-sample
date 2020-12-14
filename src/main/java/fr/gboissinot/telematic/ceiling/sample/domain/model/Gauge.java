package fr.gboissinot.telematic.ceiling.sample.domain.model;

import fr.gboissinot.kernel.java.annotation.ddd.DDD;

import java.math.BigDecimal;
import java.util.Objects;

@DDD.ValueObject
class Gauge {

    private final BigDecimal value;

    Gauge(double value) {
        this.value = BigDecimal.valueOf(value);
    }

    private Gauge(BigDecimal value) {
        this.value = value;
    }

    double value() {
        return value.doubleValue();
    }

    Gauge addValue(double amount) {
        return new Gauge(value.add(BigDecimal.valueOf(amount)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gauge gauge = (Gauge) o;
        return Objects.equals(value, gauge.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Gauge{" +
                "value=" + value +
                '}';
    }
}
