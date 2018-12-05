package com.grupobeta.styleportal.service.hibernate;

import java.io.Serializable;
import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.grupobeta.styleportal.dao.GenericDao;
import com.grupobeta.styleportal.domain.DomainObject;
import com.grupobeta.styleportal.service.Service;

public abstract class AbstractHibernateServiceImpl implements Service {
	private GenericDao genericDao;
	private MailSender mailSender;
	private SimpleMailMessage templateMessage;
	private SimpleMailMessage mailMessage;

	@Override
	public <T extends DomainObject> T load(Class<T> type, Serializable id) {
		return getGenericDao().load(type, id);
	}

	@Override
	public <T extends DomainObject> List<T> loadAll(Class<T> type) {
		return getGenericDao().loadAll(type);
	}

	@Override
	public void delete(DomainObject object) {
		getGenericDao().delete(object);
	}

	@Override
	public void refresh(DomainObject object) {
		getGenericDao().refresh(object);
	}

	@Override
	public void save(DomainObject object) {
		getGenericDao().save(object);
	}

	@Override
	public <T extends DomainObject> int countAll(Class<T> type) {
		return getGenericDao().countAll(type);
	}

	public void setGenericDao(GenericDao genericDao) {
		this.genericDao = genericDao;
	}

	public GenericDao getGenericDao() {
		return genericDao;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getTemplateMessage() {
		return templateMessage;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public SimpleMailMessage getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	protected SimpleMailMessage newMessage() {
		return new SimpleMailMessage(getTemplateMessage());
	}

	protected void sendMessage(final SimpleMailMessage message) {
		Thread t = new Thread() {


			@Override
			public void run() {
		        try {
		            getMailSender().send(message);
		        }
		        catch(MailException ex) {
		            System.out.println(ex.getMessage());
		        }
			}
		};
		t.start();
	}
	
}
