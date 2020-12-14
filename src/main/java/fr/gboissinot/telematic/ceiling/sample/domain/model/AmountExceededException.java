package fr.gboissinot.telematic.ceiling.sample.domain.model;

public class AmountExceededException extends RuntimeException {
    AmountExceededException() {
        super("The operation amount exceed the ceiling.");
    }
}
