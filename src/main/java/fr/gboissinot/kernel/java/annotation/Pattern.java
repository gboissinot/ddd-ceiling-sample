package fr.gboissinot.kernel.java.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Gregory Boissinot
 */
public @interface Pattern {

    @Documented
    @Retention(RUNTIME)
    @Target({TYPE})
    @Module(Layer.APPLICATION)
    @interface Command {
    }

    @Documented
    @Retention(RUNTIME)
    @Target({TYPE})
    @Module(Layer.APPLICATION)
    @interface Query {
    }

    @Documented
    @Retention(RUNTIME)
    @Target({TYPE})
    @Module(Layer.APPLICATION)
    @interface QueryResult {
    }

    @Documented
    @Retention(RUNTIME)
    @Target({TYPE})
    @Module(Layer.EXPOSITION)
    @interface Representation {
    }

    @Documented
    @Retention(RUNTIME)
    @Target({TYPE, ANNOTATION_TYPE})
    @Module(Layer.EXPOSITION)
    @Representation
    @interface DTORequest {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @Module(Layer.EXPOSITION)
    @Representation
    @interface DTOResponse {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @interface DataModel {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @interface ValueClass {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface StaticFactoryMethod {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @interface Factory {
    }
}
