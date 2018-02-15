package com.solutionsdelivery.RPG.dao;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor {

    //private RpgCredentials rpgCredentials = new RpgCredentials();

    // Parameters needed for Two-Way Encryption using Encryption algorithms - in this case AES (requiring 16
    // character-length key
    // size)
    private final String ENCODING = "UTF-8";

    private final String CIPHER_STRING = "AES/CBC/PKCS5PADDING";

    private final String ALGORITHM = "AES";

    // The block size required in AES is 16, therefore ensure that the ENC_KEY and ENC_INIT_VECTOR are 16 in length.
    private final String ENC_KEY = "zksuwrmthnqifcpa";

    private final String ENC_INIT_VECTOR = "pdftmiwqyhvzengc";

    public String encrypt(String plainText) {
        String encryptedText = "";
        try {

            IvParameterSpec iv = new IvParameterSpec(ENC_INIT_VECTOR.getBytes(ENCODING));
            SecretKeySpec secretKeySpec = new SecretKeySpec(ENC_KEY.getBytes(ENCODING), ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_STRING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            byte[] encrypted = cipher.doFinal(plainText.getBytes(ENCODING));
            encryptedText = new String(Base64.encodeBase64(encrypted), ENCODING);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return encryptedText;
    }


    public String decrypt(String cipherText) {
        String decryptedString = "";
        try {
            IvParameterSpec iv = new IvParameterSpec(ENC_INIT_VECTOR.getBytes(ENCODING));
            SecretKeySpec secretKeySpec = new SecretKeySpec(ENC_KEY.getBytes(ENCODING), ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_STRING);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            decryptedString = new String(cipher.doFinal(Base64.decodeBase64(cipherText.getBytes(ENCODING))), ENCODING);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return decryptedString;
    }
}

