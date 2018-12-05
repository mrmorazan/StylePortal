package com.grupobeta.util;

import java.security.MessageDigest;

import com.grupobeta.errors.GBErrors;
import com.grupobeta.errors.GBException;


public class EncryptionUtils {
	private EncryptionUtils() {
	}

	public static String getHash(String password) throws GBException {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			byte[] input = digest.digest(password.getBytes("UTF-8"));
			return byteToBase64(input);
		} catch (Exception ex) {
			GBErrors.ENCRIPCION.throwException(ex);
		}
		return null;
	}


	protected static String byteToBase64(byte[] data) {
		String enc2 = new String(java.util.Base64.getMimeEncoder().encode(data),
		                         java.nio.charset.StandardCharsets.UTF_8);
		return enc2;
	}
}
