package fr.gboissinot.kernel.ddd;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * Aggregate ID Provider
 * <p>
 * Provides an unique identifier
 *
 * @author Gregory Boissinot
 */
public class IDProvider implements Supplier<String> {

    private static final IDProvider INSTANCE = new IDProvider();

    private IDProvider() {
    }

    public static IDProvider getInstance() {
        return INSTANCE;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    /**
     * Get the UUID in version 4
     *
     * @return an uuid v4
     */
    @Override
    public String get() {
        MessageDigest salt;
        try {
            salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
            return bytesToHex(salt.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
