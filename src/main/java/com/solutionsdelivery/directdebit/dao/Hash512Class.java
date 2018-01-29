package com.solutionsdelivery.directdebit.dao;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;

public class Hash512Class {

    private static final String Algorithm = "SHA-512";

    public String getResponseHash(String pattern) {
        String responseHash = StringUtils.EMPTY;
        try {
            responseHash = generateHashedStringSHA512(MessageDigest.getInstance(Algorithm), pattern);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseHash;
    }


    private String generateHashedStringSHA512(MessageDigest messageDigest, String echoString) {
        messageDigest.update(echoString.getBytes());
        byte[] echoData = messageDigest.digest();
        String out = "";
        StringBuilder sb = new StringBuilder();
        for (byte element : echoData) {
            sb.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
        }
        out = sb.toString();
        return out;
    }

}
