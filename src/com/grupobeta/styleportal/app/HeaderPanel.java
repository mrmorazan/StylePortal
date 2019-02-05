package com.grupobeta.styleportal.app;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.basic.Label;
/*import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.image.NonCachingImage;*/
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
/*import org.apache.wicket.model.AbstractReadOnlyModel;*/
/*import org.apache.wicket.request.resource.DynamicImageResource;*/
import org.apache.wicket.model.Model;

import com.grupobeta.styleportal.domain.Rol;


public class HeaderPanel extends Panel {
	private static final long serialVersionUID = 1L;
	private boolean viewPanel;
	
	public HeaderPanel() {
		this(false);
	}
	
	public HeaderPanel(boolean viewPanel) {


		super("header");
		this.add(new Label("welcome", StylePortalSession.get().isSignedIn() ? StylePortalSession.get().getCodUsuario() : "Bienvenido" ));

		this.add(new Label("userName", StylePortalSession.get().getUsuario()!=null ? StylePortalSession.get().getUsuario().getCodUsuario() : StylePortalSession.get().isSignedIn() ? StylePortalSession.get().getCodUsuario() : "Bienvenido" ));

		this.add(new Label("title", Model.of("Product Development Portal")).setVisible(viewPanel));
		//Icono
	/*	if(StockManSession.get().isSignedIn() && StockManSession.get().getFotoBase64()!=null){
			NonCachingImage gbImageFromStringBase64 = new NonCachingImage("imagenIco", new AbstractReadOnlyModel<DynamicImageResource>() {
				private static final long serialVersionUID = 1L;

				@Override
				public DynamicImageResource getObject() {
					DynamicImageResource dir = new DynamicImageResource() {
						private static final long serialVersionUID = 1L;

						@Override
						protected byte[] getImageData(Attributes attributes) {
							return StockManSession.get().getFotoBase64();
						}
					};
					dir.setFormat("image/png");
					return dir;
				}

			});

			gbImageFromStringBase64.setMarkupId("imagenIco");
			gbImageFromStringBase64.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
			this.add(gbImageFromStringBase64);
		} else {
			ContextImage image = new ContextImage("imagenIco",
					"img/userIcon.png");
			image.setMarkupId("imagenIco");
			image.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
			this.add(image);
		}


		//Imagen
		if(StockManSession.get().isSignedIn() && StockManSession.get().getFotoBase64()!=null){
			NonCachingImage gbImageFromStringBase64 = new NonCachingImage("imagen", new AbstractReadOnlyModel<DynamicImageResource>() {
				private static final long serialVersionUID = 1L;

				@Override
				public DynamicImageResource getObject() {
					DynamicImageResource dir = new DynamicImageResource() {
						private static final long serialVersionUID = 1L;

						@Override
						protected byte[] getImageData(Attributes attributes) {
							return StockManSession.get().getFotoBase64();
						}
					};
					dir.setFormat("image/png");
					return dir;
				}

			});

			gbImageFromStringBase64.setMarkupId("image");
			gbImageFromStringBase64.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
			this.add(gbImageFromStringBase64);
		} else {
			ContextImage image = new ContextImage("imagen",
					"img/userIcon.png");
			image.setMarkupId("image");
			image.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
			this.add(image);
		}*/

		Roles rol = StylePortalSession.get().getRoles();
		final boolean noHasUserInApp = rol.hasRole(Rol.USUARIO);

		this.add(new Link<Void>("login"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(LogInPage.class);
			}

			@Override
			public boolean isVisible() {
				return super.isVisible() && !StylePortalSession.get().isSignedIn();
			}
		});

		this.add(new AjaxLink<Void>("logout"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				StylePortalSession.get().signOut();
				setResponsePage(getApplication().getHomePage());
			}

			@Override
			public boolean isVisible() {
				return super.isVisible() && StylePortalSession.get().isSignedIn();
			}
		});

		this.add(new Link<Void>("profile"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new UserPage(false));
			}

			@Override
			public boolean isVisible(){
				return super.isVisible() && !noHasUserInApp;
			}
		});

		this.add(new Link<Void>("password"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new UserPage(true));
			}

			@Override
			public boolean isVisible(){
				return super.isVisible() &&!noHasUserInApp && false;
			}
		});
/*
		HashMap<Tabs, Link<Void>> links = new HashMap<HeaderPanel.Tabs, Link<Void>>();
		links.put(Tabs.HOME, new Link<Void>("home") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(getApplication().getHomePage());
			}
		});
		links.put(Tabs.SOLICITUDES, new Link<Void>("solicitudes") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(SolicitudPage.class);
			}
		});

		links.put(Tabs.REPORTS, new Link<Void>("reportes") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
			//	setResponsePage(SearchPage.class);
			}
		});

		links.put(Tabs.SETTINGS, new Link<Void>("settings") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				//setResponsePage(MantPage.class);
			}
		});
		links.put(Tabs.ADMIN, new Link<Void>("admin") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(UsersPage.class);
			}
		});

		for(Tabs tab: links.keySet()) {
			this.add(links.get(tab));
			if (tab == selectedTab)
				links.get(tab).add(new AttributeAppender("class", Model.of("active"), " "));
		} */
	}
}
