package com.grupobeta.wicket;

import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;


public class GBSubmitButton extends AjaxButton {
	private static final long serialVersionUID = 1L;

	public GBSubmitButton(String id) {
		super(id);

	}

	@Override
	protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
	{
		 super.updateAjaxAttributes(attributes);
         attributes.getAjaxCallListeners().add(new AjaxCallListener()
             .onBefore("$('#" + getMarkupId() + "').prop('disabled',true);")
             .onComplete("$('#" + getMarkupId() + "').prop('disabled',false);"));
	}



}
