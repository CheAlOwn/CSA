package com.chealown.csa.Entities;

import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SecurityUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256; //Бит
    private static final int IV_SIZE = 12; // Байт (96 бит) - стандарт для gcm
    private static final int TAG_LENGTH_BIT = 128; //бит

    // сохраняем ключ в файл
    // TODO: изменить на переменные окружения
    //  *при этом не забыть перевести в base64
    public static void saveKeyToFile(SecretKey key, String fileName) throws IOException {
        byte[] keyBytes = key.getEncoded();
        java.nio.file.Files.write(java.nio.file.Paths.get(fileName), keyBytes);
    }

    // загрузка ключа из файла
    public static SecretKey loadKeyFromFile(String fileName) throws IOException {
        byte[] keyBytes = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(fileName));
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    // загрузка ключа из переменных окружения
    public static SecretKey loadKeyFromEnv(String envVarName) {
        String keyBase64 = System.getenv(envVarName);
        if (keyBase64 == null || keyBase64.isEmpty()) {
            throw new IllegalStateException("Ключ шифрования не найден в переменной окружения: " + envVarName);
        }
        byte[] keyBytes = Base64.getDecoder().decode(keyBase64);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    // шифрование
    public static String encrypt(String data, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);

        // генерируем случайный iv для этой операции
        byte[] iv = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);

        byte[] cipherText = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // объединяем iv и зашифрованный текст, чтобы потом знать, как расшифровать
        byte[] combined = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(cipherText, 0, combined, iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    // дешифрование
    public static String decrypt(String base64Data, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] combined = Base64.getDecoder().decode(base64Data);

        // Разделяем iv и ciphertext
        byte[] iv = new byte[IV_SIZE];
        byte[] cipherText = new byte[combined.length - iv.length];

        System.arraycopy(combined, 0, iv, 0, iv.length);
        System.arraycopy(combined, iv.length, cipherText, 0, cipherText.length);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);

        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText, StandardCharsets.UTF_8);
    }

    public static String encryptSafe(String data, SecretKey key) {
        if (data == null || data.trim().isEmpty()) {
            return null; // или пустую строку, в зависимости от логики БД
        }
        try {
            return encrypt(data, key);
        } catch (Exception e) {
            e.printStackTrace(); // В продакшене лучше использовать логгер
            return null;
        }
    }

    // Дешифрование с обработкой null
    public static String decryptSafe(String base64Data, SecretKey key) {
        if (base64Data == null || base64Data.trim().isEmpty()) {
            return null;
        }
        try {
            return decrypt(base64Data, key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Хеширование пароля с использованием BCrypt
     * @param plainPassword пароль в открытом виде
     * @return хеш, готовый к сохранению в БД
     */
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            return null;
        }
        // logRounds = 12: баланс между безопасностью и производительностью
        // Можно вынести в константу или переменную окружения
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    /**
     * Проверка пароля: сравнивает ввод с хешем из БД
     * @param plainPassword пароль, введённый пользователем
     * @param hashedPassword хеш из базы данных
     * @return true, если пароль верный
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
