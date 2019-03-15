package com.grupobeta.styleportal.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.util.time.Duration;

import com.grupobeta.styleportal.domain.ImagenBam;
import com.grupobeta.wicket.slimbox.SlimboxBehavior;

public class ImageLink extends ResourceLink<Void> {
	private static final long serialVersionUID = 1L;

	public ImageLink(String id, ImagenBam imagenBam, PageParameters pageParameters) {
		super(id , new DynamicImageResource() {
			private static final long serialVersionUID = 1L;

			@Override
			protected byte[] getImageData(Attributes attributes) {
				return imagenBam.getImagen();
			}
			
			@Override
			protected void configureCache(ResourceResponse data, Attributes attributes) {
				 Response response = attributes.getResponse(); 
	             ((WebResponse)response).disableCaching(); 
			}
			
			@Override
			protected void configureResponse(ResourceResponse response, Attributes attributes) {
				response.setCacheDuration(Duration.NONE);
				super.configureResponse(response, attributes);
			}
		});
		
		this.add(new SlimboxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("rel", "lightbox");
			}
		});
	}
	
	/*@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		 String url = tag.getAttributes().getString("href"); 
         url = url + "antiCache=" + System.currentTimeMillis(); 
         tag.put("href", url); 
	}*/
	

}
