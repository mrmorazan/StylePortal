package com.grupobeta.wicket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;

public class RemoteImage extends Image {

	private static final long serialVersionUID = 1L;
	/**  */
	final IModel<String> urlModel;

	/**
	 * @param id
	 * @param urlModel
	 */
	public RemoteImage(String id, IModel<String> urlModel) {
		super(id);
		this.urlModel = urlModel;
	}

	/**
	 * @see org.apache.wicket.markup.html.image.Image#getImageResource()
	 */
	@Override
	protected IResource getImageResource() {
		return new DynamicImageResource() {
			private static final long serialVersionUID = 1L;

			@Override
			protected byte[] getImageData(Attributes attributes) {
				try {
			          URL url = new URL(urlModel.getObject());
			          URLConnection uc = (URLConnection) url.openConnection();


			          try {
			            final int size;

			            InputStream inputStream;
			            {
			              int tmp = uc.getContentLength();

			              if (tmp < 0 && uc.getContentType().equals("image/jpeg")) {
			            	// inputStream = url.openStream();
			                inputStream = new BufferedInputStream(uc.getInputStream());
			                inputStream.mark(Integer.MAX_VALUE);
			                while (0 <= inputStream.read()) {
			                  // read
			                }
			                inputStream.reset();
			                size = inputStream.available();
			              } else {
			                size = tmp;
			                inputStream = uc.getInputStream();
			              }


			            }

			            int filled = 0;
			            byte[] imageData = new byte[size];
			            do {
			              byte[] tmpData = new byte[size-filled];
			              int read = inputStream.read(tmpData);
			              System.arraycopy(tmpData, 0, imageData, filled, read);
			              filled += read;
			            } while (filled < size);
			            return imageData;
			          } finally {
			            uc.getInputStream().close();
			          }


			        } catch (IOException e) {
			        	System.out.println(e.getMessage());
			         // Util.errorLog(RemoteImage.class, "Failed to load remote image", e);
			        }

			        return new byte[] {};
			}



		};


		/*
		 * @Override protected void setHeaders(WebResponse response) {
		 * response.setHeader("Pragma", "no-cache");
		 * response.setHeader("Cache-Control", "no-cache");
		 * response.setDateHeader("Expires", 0); }
		 */
	};
}
