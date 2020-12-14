package fr.gboissinot.kernel.java.annotation.ddd;

import fr.gboissinot.kernel.java.annotation.Pattern;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Gregory Boissinot
 */
public @interface DDD {

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @Pattern.ValueClass
    @Inherited
    @interface ValueObject {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @Inherited
    @interface Entity {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @interface Service {
    }

    @Documented
    @Retention(RUNTIME)
    @Target({TYPE, FIELD})
    @ValueObject
    @Inherited
    @interface ValueObjectId {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(PACKAGE)
    @interface Aggregate {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @Entity
    @Inherited
    @interface AggregateRoot {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @ValueObjectId
    @interface AggregateRootId {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @Service
    @interface DomainService {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @Inherited
    @interface DomainEvent {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @Inherited
    @InfrastructureService
    @interface Repository {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @interface RepositoryImpl {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @Service
    @interface InfrastructureService {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @interface InfrastructureServiceImpl {
    }

    @Documented
    @Retention(RUNTIME)
    @Target(TYPE)
    @Service
    @interface ApplicationService {
    }
}
