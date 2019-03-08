package com.grupobeta.styleportal.app.styleportal.spec.spec;

import java.io.File;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebResponse;

import com.grupobeta.styleportal.app.StylePortalRequestCycle;
import com.grupobeta.styleportal.domain.DocumentPolyPm;
import com.grupobeta.styleportal.domain.InstructionPolyPm;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.wicket.PDFResource;

public class SpecPage extends SpecBasePage<InstructionPolyPm> {
	private static final long serialVersionUID = 1L;

	DocumentPolyPm specDocument;

	public SpecPage(StylePolyPm style) {
		super(style);

		setSpecDocument(getTransService().loadDocumentPolyPmSpec(style));
		
		String urlSpec = "";
		String pathSpec="";
		String filename= "";
		if(getSpecDocument()!=null) {
			urlSpec = getSpecDocument().getUrlComponent();
			filename = getSpecDocument().getFileName()+".pdf";
			
			try {
				String pathOrigen = File.separator + getSpecDocument().getFilePath();
				
				pathOrigen = pathOrigen.replace("\\", File.separator);
				
				pathSpec = File.separator + "media" + File.separator + "WinFiles" + pathOrigen;
			} catch (Exception e) {
				
			}
			
		} else {
			filename = "pdf-not-found.jpg";
		}
		
		PDFResource pdfResource = new PDFResource(Model.of(urlSpec), pathSpec);
		
		WebResponse fileResponse = (WebResponse) getRequestCycle().getResponse();
		fileResponse.setContentType(pdfResource.getContent());
		fileResponse.setHeader("Content-Disposition", "inline; filename=\""+ filename+"\"");
		fileResponse.write(pdfResource.getURLData());
		
		fileResponse.disableCaching();
		
		StylePortalRequestCycle.get().setResponse(fileResponse);
		/*getRequestCycle().setResponse(fileResponse);*/
		
	}

	public DocumentPolyPm getSpecDocument() {
		return specDocument;
	}

	public void setSpecDocument(DocumentPolyPm specDocument) {
		this.specDocument = specDocument;
	}

}
