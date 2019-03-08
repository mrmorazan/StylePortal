package com.grupobeta.styleportal.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.ResourceLink;

import com.grupobeta.styleportal.domain.ImagenBam;
import com.grupobeta.wicket.slimbox.SlimboxBehavior;

public class ImageLink extends ResourceLink<Void> {
	private static final long serialVersionUID = 1L;

	public ImageLink(String id, ImagenBam imagenBam) {
		super(id, new ImageArrayResource(imagenBam));
		
		this.add(new SlimboxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("rel", "lightbox");
			}
		});
	}
	

}
