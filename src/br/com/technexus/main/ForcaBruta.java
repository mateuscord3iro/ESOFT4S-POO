package br.com.technexus.main;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class ForcaBruta {

    public static void main(String[] args) {
        String encryptedB64 = "U2FsdGVkX189CvKETNa+k2wHIMpbCwNk7HKB3nBRz0D9bBaPt2nFMCdElvKoRfTmmqVv41Txh370RXFWRVNOX3vpgPHULkkaoyh9DfmzzGBXkGnu/SJfQkGuU08zbgMQNSwCGTwoIHkUMzRFQELNOQ==";
        encryptedB64 = encryptedB64.replaceAll("\\s", "");

        byte[] fullBytes = Base64.getDecoder().decode(encryptedB64);
        byte[] salt = Arrays.copyOfRange(fullBytes, 8, 16);
        byte[] cipherText = Arrays.copyOfRange(fullBytes, 16, fullBytes.length);

        String charset = "abcdefghijklmnopqrstuvwxyz0123456789";
        System.out.println("Iniciando ataque de força bruta no banco da TechNexus...");
        long startTime = System.currentTimeMillis();

        for (char c1 : charset.toCharArray()) {
            for (char c2 : charset.toCharArray()) {
                for (char c3 : charset.toCharArray()) {
                    String testPass = "lam" + c1 + c2 + c3;
                    try {
                        PBEKeySpec spec = new PBEKeySpec(testPass.toCharArray(), salt, 1000, 384);
                        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                        byte[] keyAndIv = skf.generateSecret(spec).getEncoded();

                        byte[] key = Arrays.copyOfRange(keyAndIv, 0, 32);
                        byte[] iv = Arrays.copyOfRange(keyAndIv, 32, 48);

                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
                        IvParameterSpec ivSpec = new IvParameterSpec(iv);

                        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
                        byte[] decryptedBytes = cipher.doFinal(cipherText);
                        String result = new String(decryptedBytes, StandardCharsets.UTF_8);

                        if (result.contains("http")) {
                            long endTime = System.currentTimeMillis();
                            System.out.println("\nSUCESSO! A criptografia foi quebrada!");
                            System.out.println("Senha encontrada: " + testPass);
                            System.out.println("URL Secreta: " + result.trim());
                            System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
                            return;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        System.out.println("\nForça bruta concluída. Senha não encontrada.");
    }
}