package com.grupobeta.styleportal.component;

import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;

public abstract class AjaxConfirmLink extends AjaxLink<Void> {
	private static final long serialVersionUID = 1L;

	private String verb;
	private String noun;

	public AjaxConfirmLink(String id, String verb, String noun) {
		super(id);
		this.verb = verb;
		this.noun = noun;
		//add(new ConfirmModifier("Are you sure you want to " + verb + " the " + noun + "?"));
	}

	@Override
    protected void updateAjaxAttributes( AjaxRequestAttributes attributes )
    {
        super.updateAjaxAttributes( attributes );

        AjaxCallListener ajaxCallListener = new AjaxCallListener();
        ajaxCallListener.onPrecondition( "return confirm('" + "Are you sure you want to " + verb + " the " + noun + "?" + "');" );
        attributes.getAjaxCallListeners().add( ajaxCallListener );
    }



}
