package edu.labIV.util;

import edu.labIV.cfg.Config;
import edu.labIV.cfg.ConfigKey;
import edu.labIV.cfg.ConfigSection;

import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncryptor {

    private final String FIXED_SALT;
    private final int ITERATIONS;
    private final int KEY_LENGTH;

    public PasswordEncryptor() {
        FIXED_SALT = Config.getInstance().getString(ConfigSection.ENCRYPTOR, ConfigKey.ENC_SALT);
        ITERATIONS = Config.getInstance().getInt(ConfigSection.ENCRYPTOR, ConfigKey.ENC_ITERATIONS);
        KEY_LENGTH = Config.getInstance().getInt(ConfigSection.ENCRYPTOR, ConfigKey.ENC_KEY_LENGTH);
    }

    private byte[] hash(char[] password) {
        PBEKeySpec spec = new PBEKeySpec(password, FIXED_SALT.getBytes(), ITERATIONS, KEY_LENGTH);
        byte[] hash = null;
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = skf.generateSecret(spec).getEncoded();
            spec.clearPassword();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        }
        return hash;
    }

    public String generateSecurePassword(String password) {
        byte[] securePassword = hash(password.toCharArray());
        return Base64.getEncoder().encodeToString(securePassword);
    }

    public String decodePassword(String password){
        return new String(Base64.getDecoder().decode(password));
    }

    public boolean verifyPassword(String encryptedPassword, String securedPassword) {
        String password = decodePassword(encryptedPassword);
        String newSecurePassword = generateSecurePassword(password);
        return newSecurePassword.equalsIgnoreCase(securedPassword);
    }
}
