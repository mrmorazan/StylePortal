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

import com.grupobeta.styleportal.app.StylePortalSession;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

public class RemoteImage extends Image {

	private static final long serialVersionUID = 1L;
	/**  */
	final IModel<String> urlModel;
	final String urlModel2;

	/**
	 * @param id
	 * @param urlModel
	 */
	public RemoteImage(String id, IModel<String> urlModel, String urlModel2) {
		super(id);
		this.urlModel = urlModel;
		this.urlModel2 = urlModel2;
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
							byte[] tmpData = new byte[size - filled];
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

					SmbFile file = null;
					byte[] imageData = new byte[9216];

					try {
						String url = urlModel2;
					//	NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null,
					//			StylePortalSession.get().getCodUsuario(), StylePortalSession.get().getPassword());
						file = new SmbFile(url);

						try (SmbFileInputStream in = new SmbFileInputStream(file)) {
							int size = in.available();
							int filled = 0;
							do {
								byte[] tmpData = new byte[size - filled];
								int read = in.read(tmpData);
								System.arraycopy(tmpData, 0, imageData, filled, read);
								filled += read;
							} while (filled < size);

							return imageData;
						}

					} catch (Exception e2) {

					}

				}

				return new byte[] {};
			}

		};

		/*
		 * @Override protected void setHeaders(WebResponse response) {
		 * response.setHeader("Pragma", "no-cache"); response.setHeader("Cache-Control",
		 * "no-cache"); response.setDateHeader("Expires", 0); }
		 */
	};
}
