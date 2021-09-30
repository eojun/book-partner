package com.bookpartner.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA256Util {
	
	// MD5
    public static String MD5HASH(String str){
    	String MD5 = ""; 
    	try{

    		MessageDigest md = MessageDigest.getInstance("MD5"); 
    		md.update(str.getBytes()); 
    		byte byteData[] = md.digest();
    		StringBuffer sb = new StringBuffer(); 
    		for(int i = 0 ; i < byteData.length ; i++){
    			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
    		}
    		MD5 = sb.toString();
    	}catch(NoSuchAlgorithmException e){
    		e.printStackTrace(); 
    		MD5 = null; 
    	}

    	return MD5;
    }

    // SHA-256
    public static String SHA256(String str){
    	String SHA = ""; 
    	try{

    		MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
    		sh.update(str.getBytes()); 
    		byte byteData[] = sh.digest();
    		StringBuffer sb = new StringBuffer(); 
    		for(int i = 0 ; i < byteData.length ; i++){
    			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
    		}
    		SHA = sb.toString();
    	}catch(NoSuchAlgorithmException e){
    		e.printStackTrace(); 
    		SHA = null; 
    	}

    	return SHA;
    }
    
    
    // Get Password SHA-256(MD5(String str))
    public static String getMD5HashAndSHA256Password(String str){
    	String password = ""; 
    	try{

    		String md5 = MD5HASH(str);
	        password = SHA256(md5);
    	}catch(Exception e){
    		e.printStackTrace(); 
    		password = null; 
    	}

    	return password;
    }
}
