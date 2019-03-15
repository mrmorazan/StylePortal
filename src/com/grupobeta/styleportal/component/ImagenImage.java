package com.grupobeta.styleportal.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

import com.grupobeta.styleportal.domain.ImagenBam;

public class ImagenImage extends NonCachingImage {
	private static final long serialVersionUID = 1L;

	public ImagenImage(String id, ImagenBam imagenBam, PageParameters pageParameters) {
		super(id , new ResourceReference("imagenBam") {
			private static final long serialVersionUID = 1L;

			@Override
			public IResource getResource() {
				return new ImageArrayResource(imagenBam);
			}
		}, pageParameters);
		
	}
	
	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		 String url = tag.getAttributes().getString("href"); 
         url = url + "antiCache=" + System.currentTimeMillis(); 
         tag.put("href", url); 
	}

}
