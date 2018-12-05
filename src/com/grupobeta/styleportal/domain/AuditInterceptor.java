package com.grupobeta.styleportal.domain;

import java.io.Serializable;
import java.util.Arrays;


import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;



public class AuditInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 1L;

	private static class ThreadLocalUser extends ThreadLocal<String> {
		@Override
		protected String initialValue() {
			return "Style Portal";
		}
	}

	private static ThreadLocalUser currentUser = new ThreadLocalUser();

	public static String getCurrentUser() {
		return currentUser.get();
	}

	public static void setCurrentUser(String currentUser) {
		AuditInterceptor.currentUser.set(currentUser);
	}

	public static void destroy(){
		AuditInterceptor.currentUser.remove();
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState,
              Object[] previousState, String[] propertyNames,
              Type[] types) {
		  if (entity instanceof AuditableObject) {
			 setValue(currentState, propertyNames, "userChange", getCurrentUser());
			 setValue(currentState, propertyNames, "dateChange", new Date());
			  return true;
		  } else
			  return false;
	  }

	@Override
	  public boolean onSave(Object entity, Serializable id, Object[] state,
			  String[] propertyNames, Type[] types) {
		  if (entity instanceof AuditableObject) {
			  setValue(state, propertyNames, "userCreate", getCurrentUser());
			 setValue(state, propertyNames, "dateCreate", new Date());
			  return true;
		  } else
			  return false;
	  }

	  private void setValue(Object[] currentState, String[] propertyNames,
                String propertyToSet, Object value) {
		  int index = Arrays.asList(propertyNames).indexOf(propertyToSet);
		  if (index >= 0)
			  currentState[index] = value;
	  }

}
