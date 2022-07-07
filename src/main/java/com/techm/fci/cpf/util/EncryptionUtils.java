package com.techm.fci.cpf.util;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class EncryptionUtils {
    private static Random rand = new Random((new Date()).getTime());

    public static String encrypt(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] salt = new byte[] { 'T', 'h', 's', 'e', 'c', 'r', 'K', 'y'};
        rand.nextBytes(salt);
        return encoder.encode(salt) + encoder.encode(str.getBytes());
    }

    public static String decrypt(String encstr) {
        if (encstr.length() > 12) {
            String cipher = encstr.substring(12);
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                return new String(decoder.decodeBuffer(cipher));
            } catch (IOException e) {
                //  throw new InvalidImplementationException(
                //Fail
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String st = "1";
        String enc = encrypt(st);
        System.out.println("Encrypted string :" + enc);
        System.out.println("Decrypted string :" + decrypt(enc));
    }
}
