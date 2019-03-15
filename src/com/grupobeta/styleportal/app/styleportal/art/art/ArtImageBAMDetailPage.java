package com.grupobeta.styleportal.app.styleportal.art.art;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.IResource.Attributes;

import com.grupobeta.styleportal.component.ImageLink;
import com.grupobeta.styleportal.component.ImagenImage;
import com.grupobeta.styleportal.domain.ColorBam;
import com.grupobeta.styleportal.domain.ImagenBam;
import com.grupobeta.styleportal.domain.ImagenTallaBAM;
import com.grupobeta.styleportal.domain.StylePolyPm;
import com.grupobeta.styleportal.domain.TecnicaBam;
import com.grupobeta.wicket.PageablePropertyListView;

public class ArtImageBAMDetailPage extends ArtBasePage<ImagenBam> {
	private static final long serialVersionUID = 1L;
	
	WebMarkupContainer divImagen;
	WebMarkupContainer divTallas;
	List<ImagenTallaBAM> tallas = new ArrayList<ImagenTallaBAM>();
	
	WebMarkupContainer divColores;
	List<ColorBam> colores = new ArrayList<ColorBam>();
	
	WebMarkupContainer divTecnicas;
	List<TecnicaBam> tecnicas = new ArrayList<TecnicaBam>();

	public ArtImageBAMDetailPage(StylePolyPm style, ImagenBam imagenBam) {
		super(style);
		setSelectedObject(imagenBam);
		setTallas(getTransService().loadAllImagenesTallaBAM(getSelectedObject()));
		setColores(getTransService().loadAllColorFromImage(getSelectedObject()));
		setTecnicas(getTransService().loadAllTecnicasFromImage(getSelectedObject()));
		
		divImagen = new WebMarkupContainer("divImagen");
		divImagen.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		
		PageParameters parametrosModal = new PageParameters();
		parametrosModal.add("id", imagenBam.getImagenBamID());
		parametrosModal.add("thumbnail", false);
		parametrosModal.add("wicket:antiCache", System.currentTimeMillis());
		
		/*PageParameters parametrosDetalle = new PageParameters();
		parametrosModal.add("id", imagenBam.getImagenBamID());
		parametrosModal.add("thumbnail", true);
		parametrosModal.add("wicket:antiCache", System.currentTimeMillis());*/
		
		Link<Void> link = new ImageLink("view", getSelectedObject(), parametrosModal);
		link.add(new NonCachingImage("imagenBam") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected IResource getImageResource() {
				return new DynamicImageResource() {
					private static final long serialVersionUID = 1L;

					@Override
					protected byte[] getImageData(Attributes attributes) {
						return imagenBam.getThumbnail();
					}
				};
			}
			
		});
		divImagen.add(link);
		
		this.add(new Label("ubicacion", Model.of(getSelectedObject().getUbicacion())));
		this.add(new Label("printMode", Model.of(getSelectedObject().getPrintMode())));
		this.add(new Label("instructions", Model.of(getSelectedObject().getInstructions())));
		
		this.add(divImagen);
		
		divColores = new WebMarkupContainer("divColores");
		divColores.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		
		PageablePropertyListView<ColorBam> tableColores = new PageablePropertyListView<ColorBam>("tableColores") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<ColorBam> loadItems() {
				return getColores();
			}

			@Override
			protected void populateItem(Item<ColorBam> item) {
				item.add(new Label("colorCode"));
				item.add(new Label("colorName"));
				item.add(new Label("customerColorCode"));
				
			}
		};
		
		tableColores.setRowsPerPage(10);
		divColores.add(tableColores);
		
		divColores.add(new PagingNavigator("navigator", tableColores));
		this.add(divColores);
		
		
		divTecnicas = new WebMarkupContainer("divTecnicas");
		divTecnicas.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		
		PageablePropertyListView<TecnicaBam> tableTecnicas = new PageablePropertyListView<TecnicaBam>("tableTecnicas") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<TecnicaBam> item) {
				item.add(new Label("tecnicaName"));
				
			}
			
			@Override
			protected List<TecnicaBam> loadItems() {
				return getTecnicas();
			}
		};
		
		tableTecnicas.setRowsPerPage(10);
		divTecnicas.add(tableTecnicas);
		
		divTecnicas.add(new PagingNavigator("navigator", tableTecnicas));
		this.add(divTecnicas);
		
		divTallas = new WebMarkupContainer("divTallas");
		divTallas.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		
		PageablePropertyListView<ImagenTallaBAM> tableTallas = new PageablePropertyListView<ImagenTallaBAM>("tableTallas") {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<ImagenTallaBAM> loadItems() {
				return getTallas();
			}

			@Override
			protected void populateItem(Item<ImagenTallaBAM> item) {
				item.add(new Label("talla"));
				item.add(new Label("alto"));
				item.add(new Label("ancho"));
			}
			
		};
		
		tableTallas.setRowsPerPage(10);
		divTallas.add(tableTallas);
		
		divTallas.add(new PagingNavigator("navigator", tableTallas));
		this.add(divTallas);
		
		
		
	}

	public List<ImagenTallaBAM> getTallas() {
		return tallas;
	}

	public void setTallas(List<ImagenTallaBAM> tallas) {
		this.tallas = tallas;
	}

	public List<ColorBam> getColores() {
		return colores;
	}

	public void setColores(List<ColorBam> colores) {
		this.colores = colores;
	}

	public List<TecnicaBam> getTecnicas() {
		return tecnicas;
	}

	public void setTecnicas(List<TecnicaBam> tecnicas) {
		this.tecnicas = tecnicas;
	}
	
	

}
