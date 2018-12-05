package com.grupobeta.styleportal.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.image.NonCachingImage;

public class GBImageFromStringBase64 extends NonCachingImage {
	private static final long serialVersionUID = 1L;
	private String base64Resource;

	public GBImageFromStringBase64(String id, String base64Resource) {
		super(id);
		setBase64Resource(base64Resource);
	}

	@Override
	protected void onComponentTag(ComponentTag tag)
	{
		super.onComponentTag(tag);

		String url = getBase64Resource();
		tag.put("src", url);
	}

	public String getBase64Resource() {
		return base64Resource;
	}

	public void setBase64Resource(String base64Resource) {
		this.base64Resource = base64Resource;
	}


}
