package com.grupobeta.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LoginLDAP {

	private static DirContext ldapContext;

	public LoginLDAP() {
	}

	public boolean IsAuthenticateUser(String host, int port, String username,
			String password) {
		try {
			return Authenticate(host, port, username, password);
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}

	public static boolean Authenticate(String host, int port, String username,
			String password) throws NamingException {

		DirContext ctx = getLdapContext(host, port, username, password);
		if (null != ctx) {
			ctx.close();
			return true;

		} else {
			return false;
		}

	}

	public static DirContext getLdapContext(String host, int port, String user,
			String password) throws NamingException {
		try {

			Hashtable<String, String> ldapEnv = new Hashtable<String, String>(
					11);
			ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			ldapEnv.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port);
			ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			ldapEnv.put(Context.SECURITY_PRINCIPAL, user + "@" + host);
			ldapEnv.put(Context.SECURITY_CREDENTIALS, password);
			ldapContext = new InitialDirContext(ldapEnv);

			ldapContext.close();
			return ldapContext;

		} catch (Exception ex) {
			return null;
		}
	}
}
