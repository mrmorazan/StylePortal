package com.grupobeta.wicket;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.wicket.model.IModel;
import com.grupobeta.styleportal.app.BaseApplication;


public class PDFResource  {
		
	final IModel<String> urlModel;
	final String urlModel2;
	byte[] getPDFData;
	protected boolean isPDF;
	protected String content;
	
	public PDFResource(IModel<String> urlModel, String urlModel2) {
		this.urlModel=urlModel;
		this.urlModel2 = urlModel2;
		this.getPDFData = getURLData();
	}

	public byte[] getURLData() {
		try {
			if(urlModel.getObject().contains("%")) {
				urlModel.getObject().replace("%", "%25");
			}
			URL url = new URL(urlModel.getObject());
			URLConnection uc = (URLConnection) url.openConnection();

			try {
				final int size;

				InputStream inputStream;
				{
					int tmp = uc.getContentLength();

					if (tmp < 0 && uc.getContentType().equals("application/pdf")) {
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
				setContent("application/pdf");
				setPDF(true);
				uc.getInputStream().close();
			}

		} catch (Exception e) {
			InputStream targetStream = null;
			try {
				
				//	String pathOrigen = urlModel2.replace("Styles", "");
					
				//	String pathFinal = File.separator + "media" + File.separator + "WinFiles"+File.pathSeparator + pathOrigen;
					
				
					File file = new File(urlModel2);
					
					targetStream = new FileInputStream(file);
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();

					int nRead;
					byte[] data = new byte[16384];

					while ((nRead = targetStream.read(data, 0, data.length)) != -1) {
					  buffer.write(data, 0, nRead);
					}

					setContent("application/pdf");
					setPDF(true);
					return buffer.toByteArray();
				} catch (Exception e1) {
					try {
						String path ="";
						javax.servlet.ServletContext context = ((org.apache.wicket.protocol.http.WebApplication) BaseApplication.get()).getServletContext();
						path =  context.getRealPath("img"+File.separator+"pdf-not-found.jpg");
					
						
						File file = new File(path);
						
						targetStream = new FileInputStream(file);
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();

						int nRead;
						byte[] data = new byte[16384];

						while ((nRead = targetStream.read(data, 0, data.length)) != -1) {
						  buffer.write(data, 0, nRead);
						}

						setContent("image/jpeg");
						setPDF(false);
						return buffer.toByteArray();
					
					} catch (Exception e3) {
						
					}
				} finally {
					try {
						targetStream.close();
					} catch (IOException e1) {
						
					}
				}
		}
		
		
		return new byte[] {};
	}

	public boolean isPDF() {
		return isPDF;
	}

	public void setPDF(boolean isPDF) {
		this.isPDF = isPDF;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	

}
