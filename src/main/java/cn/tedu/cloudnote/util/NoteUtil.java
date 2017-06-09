package cn.tedu.cloudnote.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

public class NoteUtil {
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	public static String md5(String password) throws NoSuchAlgorithmException{
		MessageDigest md=MessageDigest.getInstance("md5");
		byte[] md5=md.digest(password.getBytes());
		String s=Base64.encodeBase64String(md5);
		return s;
	}
}
