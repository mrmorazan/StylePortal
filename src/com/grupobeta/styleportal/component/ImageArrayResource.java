package com.grupobeta.styleportal.component;

import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.util.time.Duration;

import com.grupobeta.styleportal.domain.ImagenBam;

public class ImageArrayResource extends ByteArrayResource {
	private static final long serialVersionUID = 1L;

	public ImageArrayResource(ImagenBam imagenBam) {
		super("image/png", imagenBam.getImagen(), imagenBam.getImagenBamID()+imagenBam.getUbicacion());
	}
	
	@Override
	protected void configureResponse(ResourceResponse response, Attributes attributes) {
		super.configureResponse(response, attributes);
		response.setCacheDuration(Duration.NONE);
	}

}
