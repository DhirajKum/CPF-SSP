package com.techm.fci.cpf.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

// TODO: Implement 256-bit version like: http://securejava.wordpress.com/2012/10/25/aes-256/
public class AesUtil {
    private final int keySize = 256;
    private final int iterationCount=10000;
    private final Cipher cipher;
    public static final String IV = "F27D5C9927726BCEFE7510B1BDD3D137";
    public static final String SALT = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
    public static final String phase="MCIPORTAL";
    public static Map<String,String> authSaltMap=new HashMap<String,String>();
    
    public String encryptString(String text){
    	try {
            SecretKey key = generateKey(SALT, phase);
            System.out.println(key);
            byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, key, IV, text.getBytes("UTF-8"));
            return base64(encrypted);
        }
        catch (UnsupportedEncodingException e) {
            throw fail(e);
        } 
    }
    public String decryptString(String ciphertext) {
        try {
            SecretKey key = generateKey(SALT, phase);
            byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, IV, base64(ciphertext));
            return new String(decrypted, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            throw fail(e);
        }
    }
    public AesUtil() {
       
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        }
        catch (NoSuchAlgorithmException e) {
            throw fail(e);
        }catch(NoSuchPaddingException e){
        	throw fail(e);
        }
    }
    
    public String encrypt(String salt, String iv, String passphrase, String plaintext) {
        try {
            SecretKey key = generateKey(salt, passphrase);
            System.out.println(key);
            byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, key, iv, plaintext.getBytes("UTF-8"));
            return base64(encrypted);
        }
        catch (UnsupportedEncodingException e) {
            throw fail(e);
        }
    }
    
    public String decrypt(String salt, String iv, String passphrase, String ciphertext) {
        try {
            SecretKey key = generateKey(salt, passphrase);
            byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64(ciphertext));
            return new String(decrypted, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            throw fail(e);
        }
    }
    
    private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
        try {
            cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
            return cipher.doFinal(bytes);
        }
        catch (InvalidKeyException e) {
            throw fail(e);
        }catch(InvalidAlgorithmParameterException e){
        	throw fail(e);
        }catch(IllegalBlockSizeException e){
        	throw fail(e);
        }catch(BadPaddingException e){
        	throw fail(e);
        }
    }
    
    private SecretKey generateKey(String salt, String passphrase) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
            SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
            return key;
        }
        catch (NoSuchAlgorithmException e) {
            throw fail(e);
        }catch(InvalidKeySpecException e){
        	throw fail(e);
        }
    }
    
    public static String random(int length) {
        byte[] salt = new byte[length];
        new SecureRandom().nextBytes(salt);
        return hex(salt);
    }
    
    public static Map<String,String> getkeySaltMap(){
    	//String key = new Date().getTime()+"";
    	//key = Base64.encodeBase64URLSafeString(key.getBytes());
    	String key = random(5);
    	Map<String,String> map= new HashMap<String,String>();
    	String salt = random(32);
    	authSaltMap.put(key, salt);
    	map.put("key", key);
    	map.put("salt", salt);
    	return map;
    }
    
    public static String base64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
    
    public static byte[] base64(String str) {
        return Base64.decodeBase64(str);
    }
    
    public static String hex(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }
    
    public static byte[] hex(String str) {
        try {
            return Hex.decodeHex(str.toCharArray());
        }
        catch (DecoderException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private IllegalStateException fail(Exception e) {
        return new IllegalStateException(e);
    }
    public static void main(String[] args) {
    	AesUtil aesUtil = new AesUtil();
    	String encToken = aesUtil.encryptString("SINGLESIGNONTOKEN|rajesh@htss.com|htss@123");
    	System.out.println(encToken);
    	String decToken = aesUtil.decryptString(encToken);
    	System.out.println(decToken);
    	
    	//String salt = random(5);
		//System.out.println(salt);
    	/*String s="784574545455";
    	System.out.println(Base64.encodeBase64URLSafeString(s.getBytes()));*/
    	/*AesUtil aesUtil = new AesUtil();
		String text="htss@123+1828718278";
		
		String salt = random(32);
		System.out.println(salt);
		System.out.println(salt.length());
		System.out.println(SALT.length());
		String encrypt = aesUtil.encrypt(salt, IV, phase, text);
		System.out.println(encrypt);
		String decrypt = aesUtil.decrypt(salt, IV, phase, encrypt);
		System.out.println(decrypt);
		System.out.println(decrypt.substring(0,decrypt.lastIndexOf("+")));
		System.out.println(decrypt.substring((decrypt.lastIndexOf("+")+1)));*/
		
	}
}
