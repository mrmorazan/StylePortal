package com.grupobeta.wicket;

import java.io.BufferedInputStream;
import java.io.InputStream;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;

import com.grupobeta.styleportal.app.StylePortalSession;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

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
				InputStream in = null;
				
				String user = StylePortalSession.get().getCodUsuario();
				String password = StylePortalSession.get().getPassword();
				
				 try {
					SmbFile dir = new SmbFile(url);
					 SmbFile fileToGet=new SmbFile(url);
					 
					 in = new BufferedInputStream(new SmbFileInputStream(fileToGet));
					 
					 byte[] buffer = new byte[4096];
	                  int len = 0; //Read length
	                  while ((len = in.read(buffer, 0, buffer.length)) != -1) {
	                   //         out.write(buffer, 0, len);
	                  }
					 
				} catch (Exception e) {
					e.printStackTrace();
				}  finally {
	                  try {
	                    
	                      if(in != null) {
	                          in.close();
	                      }
	                  }
	                  catch (Exception e) {}
	              }
                
				
				return null;
			}
		};
	}

	
	

}
