package com.grupobeta.styleportal.component;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;

	public abstract class StylePortalEditableLabelV2<T> extends AjaxEditableLabel<T> {
		private static final long serialVersionUID = 1L;
		protected FeedbackPanel feedback;

		protected FeedbackPanel getFeedback() {
			return feedback;
		}

		protected void setFeedback(FeedbackPanel feedback) {
			this.feedback = feedback;
		}

		public StylePortalEditableLabelV2(String id, FeedbackPanel feedback) {
			super(id);
			setFeedback(feedback);
		}

		public StylePortalEditableLabelV2(String id, FeedbackPanel feedback, IModel<T> model) {
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

	}

