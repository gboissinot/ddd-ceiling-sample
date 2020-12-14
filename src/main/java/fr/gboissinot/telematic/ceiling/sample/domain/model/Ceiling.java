package fr.gboissinot.telematic.ceiling.sample.domain.model;

import fr.gboissinot.kernel.java.annotation.ddd.DDD;

import java.util.Objects;

@DDD.ValueObject
class Ceiling {

    private final long value;

    Ceiling(long value) {
        this.value = value;
    }

    long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ceiling ceiling = (Ceiling) o;
        return value == ceiling.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Ceiling{" +
                "value=" + value +
                '}';
    }
}
