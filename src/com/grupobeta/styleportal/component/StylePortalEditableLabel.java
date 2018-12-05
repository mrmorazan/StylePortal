package com.grupobeta.styleportal.component;

	import java.text.DecimalFormat;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

	public abstract class StylePortalEditableLabel<T> extends AjaxEditableLabel<T> {
		private static final long serialVersionUID = 1L;
		protected FeedbackPanel feedback;

		protected FeedbackPanel getFeedback() {
			return feedback;
		}

		protected void setFeedback(FeedbackPanel feedback) {
			this.feedback = feedback;
		}

		public StylePortalEditableLabel(String id, FeedbackPanel feedback) {
			super(id);
			setFeedback(feedback);
		}

		public StylePortalEditableLabel(String id, FeedbackPanel feedback, IModel<T> model) {
			super(id,model);
			setFeedback(feedback);
		}

		@Override
		protected void onError(AjaxRequestTarget target) {
			super.onError(target);
			target.add(getFeedback());
		}

		@Override
		protected void onSubmit(AjaxRequestTarget target) {
			super.onSubmit(target);
			target.add(getFeedback());
			this.onSubmitHelper(target);
		}

		protected abstract void onSubmitHelper(AjaxRequestTarget target);

		@Override
		protected Component newLabel(final MarkupContainer parent, final String componentId,
				final IModel<T> model)
			{
				Label label = new Label(componentId, model)
				{
					private static final long serialVersionUID = 1L;

					@Override
					public <C> IConverter<C> getConverter(final Class<C> type)
					{
						IConverter<C> c = StylePortalEditableLabel.this.getConverter(type);
						return c != null ? c : super.getConverter(type);
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					public void onComponentTagBody(final MarkupStream markupStream,
						final ComponentTag openTag)
					{
						Object modelObject = getDefaultModelObject();
						DecimalFormat df = new DecimalFormat("#0.00");

						if ((modelObject == null) || "".equals(modelObject))
						{
							replaceComponentTagBody(markupStream, openTag, defaultNullLabel());
						}
						else
						{
							try {
								replaceComponentTagBody(markupStream, openTag, df.format(getDefaultModelObject()));
							} catch (Exception e) {
								super.onComponentTagBody(markupStream, openTag);
							}


						}
					}
				};
				label.setOutputMarkupId(true);
				label.add(new LabelAjaxBehavior(getLabelAjaxEvent()));
				return label;
			}

		@Override
		protected FormComponent<T> newEditor(final MarkupContainer parent, final String componentId,
				final IModel<T> model)
			{
				TextField<T> editor = new TextField<T>(componentId, model)
				{
					private static final long serialVersionUID = 1L;

					@Override
					public <C> IConverter<C> getConverter(final Class<C> type)
					{
						IConverter<C> c = StylePortalEditableLabel.this.getConverter(type);
						return c != null ? c : super.getConverter(type);
					}

					@Override
					protected void onModelChanged()
					{
						super.onModelChanged();
						StylePortalEditableLabel.this.onModelChanged();
					}

					@Override
					protected void onModelChanging()
					{
						super.onModelChanging();
						StylePortalEditableLabel.this.onModelChanging();
					}
				};
				editor.setOutputMarkupId(true);
				editor.setVisible(false);
				editor.add(new EditorAjaxBehavior()
				{
					private static final long serialVersionUID = 1L;

					@Override
					protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
					{
						super.updateAjaxAttributes(attributes);
						attributes.setEventNames("blur", "keyup", "keydown");

						// Note: preventDefault is handled selectively below
						attributes.setPreventDefault(false);

						// Note: escape can be detected on keyup, enter can be detected on keyup
						CharSequence precondition = "var kc=Wicket.Event.keyCode(attrs.event),"
							+ "evtType=attrs.event.type,"
							+ "ret=false;"
							+ "if (evtType==='blur' || (evtType==='keyup' && kc===27) || (evtType==='keydown' && kc===13)) {attrs.event.preventDefault(); ret = true;}"
							+ "return ret;";
						AjaxCallListener ajaxCallListener = new AjaxCallListener();
						ajaxCallListener.onPrecondition(precondition);

						CharSequence dynamicExtraParameters = "var result = [],"
							+ "evtType=attrs.event.type;"
							+ "if (evtType === 'keyup') { result.push( { name: 'save', value: false } ); }"
							+ "else { result = Wicket.Form.serializeElement(attrs.c); result.push( { name: 'save', value: true } ); }"
							+ "return result;";
						attributes.getDynamicExtraParameters().add(dynamicExtraParameters);

						attributes.getAjaxCallListeners().add(ajaxCallListener);

					}
				});
				return editor;
			}

	}

