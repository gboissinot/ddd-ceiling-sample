package fr.gboissinot.kernel.ddd;

import fr.gboissinot.kernel.java.annotation.ddd.DDD;

/**
 * Represents a DDD Value Object.
 * <p>
 * Value objects contains attributes but has no conceptual identity and must be immutable.
 *
 * @author Gregory Boissinot
 */
@DDD.ValueObject
public interface ValueObject {
}
