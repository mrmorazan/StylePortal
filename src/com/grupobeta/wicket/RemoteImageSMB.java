package com.grupobeta.wicket;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;

import com.grupobeta.styleportal.app.StylePortalSession;

public class RemoteImageSMB extends Image {
	private static final long serialVersionUID = 1L;
	
	String url;
	
	public RemoteImageSMB(String id, String url) {
		super(id, url);
		this.url = url;
		
	}
	
	@Override
	protected IResource getImageResource() {
		return new DynamicImageResource() {
			private static final long serialVersionUID = 1L;

			@Override
			protected byte[] getImageData(Attributes attributes) {
				String user = StylePortalSession.get().getCodUsuario();
				String password = StylePortalSession.get().getPassword();
				
				return null;
			}
		};
	}

	
	

}
