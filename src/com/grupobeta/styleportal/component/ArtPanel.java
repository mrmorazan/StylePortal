package com.grupobeta.styleportal.component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.grupobeta.styleportal.app.styleportal.art.art.ArtImageBAMDetailPage;
import com.grupobeta.styleportal.domain.ArteBam;
import com.grupobeta.styleportal.domain.ImagenBam;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.styleportal.service.AdministracionService;
import com.grupobeta.styleportal.service.MantenimientoService;
import com.grupobeta.styleportal.service.TransaccionesService;
import com.grupobeta.wicket.PageablePropertyListView;
import com.grupobeta.wicket.RemoteImage;

public class ArtPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	protected MantenimientoService mantService;
	@SpringBean
	protected TransaccionesService transService;
	@SpringBean
	protected AdministracionService adminService;
	
	StylePolyPm stylePolyPm;
	ArteBam arteBam;
	List<ImagenBam> imagenesBAM = new ArrayList<ImagenBam>();
	WebMarkupContainer productCard;
	
	
	public ArtPanel(String id, ArteBam arteBam, StylePolyPm stylePolyPm) {
		super(id);
		this.setArteBam(arteBam);
		this.setStylePolyPm(stylePolyPm);
		
		String productID = "product-card-"+id;
		productCard = new WebMarkupContainer("productCard");
		productCard.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		productCard.setMarkupId(productID);
		
		
		
		setImagenesBAM(getTransService().loadAllImagenBamFromArte(getArteBam()));
		
		String pathFinal = "";
		try {
			String pathOrigen = File.separator + stylePolyPm.getUrlStyleImage2();
			
			pathOrigen = pathOrigen.replace("\\", File.separator);
			
			pathFinal = File.separator + "media" + File.separator + "WinFiles" + pathOrigen;
		} catch (Exception e) {
			
		}
		
		WebMarkupContainer productFront = new WebMarkupContainer("productFront");
		productFront.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		productFront.setMarkupId("product-front-"+id);
		productCard.add(productFront);
		
		String viewDetailID = "view-detail-"+id;
		WebMarkupContainer viewDetail = new WebMarkupContainer("viewDetail");
		viewDetail.setMarkupId(viewDetailID);
		productFront.add(viewDetail);
		
		productFront.add(new Label("arte", Model.of(getArteBam().getArte())));
		productFront.add(!getStylePolyPm().getUrlStyleImage().equals("0") ? new RemoteImage("remoteImage", Model.of(getStylePolyPm().getUrlStyleImage()), pathFinal) : new ContextImage("remoteImage", "img/notAvailableImage.png"));
		
		WebMarkupContainer productBack = new WebMarkupContainer("productBack");
		productBack.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		productBack.setMarkupId("product-back-"+id);
		productCard.add(productBack);
		
		WebMarkupContainer divCarousel = new WebMarkupContainer("divCarousel");
		divCarousel.setMarkupId("div-carousel-"+id);
		
		
		PageablePropertyListView<ImagenBam> carousel = new PageablePropertyListView<ImagenBam>("carousel") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<ImagenBam> loadItems() {
				return getImagenesBAM();
			}

			@Override
			protected void populateItem(Item<ImagenBam> item) {
				Link<Void> link = new Link<Void>("imageLink") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						 setResponsePage(new ArtImageBAMDetailPage(getStylePolyPm(), item.getModelObject()));
					}
				};
				
				link.add(new NonCachingImage("imagenBam") {
					private static final long serialVersionUID = 1L;
					
					@Override
					protected IResource getImageResource() {
						return new DynamicImageResource() {
							private static final long serialVersionUID = 1L;

							@Override
							protected byte[] getImageData(Attributes attributes) {
								return item.getModelObject().getThumbnail();
							}
						};
					}
				});
				
				link.add(new Label("ubicacion"));
				link.add(new Label("printMode"));
				link.add(new Label("alto"));
				link.add(new Label("ancho"));
				
				item.add(link);
				
			}
		};
		divCarousel.add(carousel);
		
		
		WebMarkupContainer arrowsPerspective = new WebMarkupContainer("arrowsPerspective");
		arrowsPerspective.setMarkupId("Arrows-Perspective-"+id);
		
		WebMarkupContainer carouselPrev = new WebMarkupContainer("carouselPrev");
		carouselPrev.setMarkupId("carousel-prev-"+id);
		arrowsPerspective.add(carouselPrev);
		
		WebMarkupContainer carouselNext = new WebMarkupContainer("carouselNext");
		carouselNext.setMarkupId("carousel-next-"+id);
		arrowsPerspective.add(carouselNext);
		
		divCarousel.add(arrowsPerspective);
		
		WebMarkupContainer flipBack = new WebMarkupContainer("flipBack");
		flipBack.setMarkupId("flip-back-"+id);
		
		WebMarkupContainer cx = new WebMarkupContainer("cx");
		cx.setMarkupId("cx-"+id);
		flipBack.add(cx);
		
		WebMarkupContainer cy = new WebMarkupContainer("cy");
		cy.setMarkupId("cy-"+id);
		flipBack.add(cy);
		
		productBack.add(flipBack);
		productBack.add(divCarousel);
		
		
		
		this.add(productCard);
		
		
	}

	public MantenimientoService getMantService() {
		return mantService;
	}

	public void setMantService(MantenimientoService mantService) {
		this.mantService = mantService;
	}

	public TransaccionesService getTransService() {
		return transService;
	}

	public void setTransService(TransaccionesService transService) {
		this.transService = transService;
	}

	public AdministracionService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdministracionService adminService) {
		this.adminService = adminService;
	}

	public StylePolyPm getStylePolyPm() {
		return stylePolyPm;
	}

	public void setStylePolyPm(StylePolyPm stylePolyPm) {
		this.stylePolyPm = stylePolyPm;
	}

	public ArteBam getArteBam() {
		return arteBam;
	}

	public void setArteBam(ArteBam arteBam) {
		this.arteBam = arteBam;
	}

	public List<ImagenBam> getImagenesBAM() {
		return imagenesBAM;
	}

	public void setImagenesBAM(List<ImagenBam> imagenesBAM) {
		this.imagenesBAM = imagenesBAM;
	}


	
	

}
