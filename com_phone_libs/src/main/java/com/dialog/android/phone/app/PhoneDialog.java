package com.dialog.android.phone.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mitake.android.phone.R;

/**
 * 
 * Create custom Dialog windows for your application Custom dialogs rely on
 * custom layouts wich allow you to create and use your own look & feel.
 * 
 * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 * 
 * @author antoine vianey
 * 
 */
public class PhoneDialog extends Dialog {

	private TextView mTitleTextView;
	public static boolean isDialogStart = false;//因為PhoneDialog目前接為unCancelable，所以可用static
	
	public PhoneDialog(Context context, int theme) {
		super(context, theme);
		
	}

	public PhoneDialog(Context context) {
		super(context);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitleTextView.setText(title);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		isDialogStart = true;

	}
	
	@Override
	protected void onStop() {
		super.onStop();
		isDialogStart = false;

	}
	
	public void shake() {
		
	}

	/**
	 * Helper class for creating a custom dialog
	 */
	public static class Builder {

		private Context context;
		private String title;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private View contentView;
		private boolean mCancelable;
		private int mWindowSoftInputMode;
		private boolean mHtml = false;
		
		private DialogInterface.OnClickListener positiveButtonClickListener,
				negativeButtonClickListener;

		public Builder(Context context) {
			this.context = context;
			mWindowSoftInputMode = -1;
		}

		/**
		 * Set the Dialog message from String
		 * 
		 * @param title
		 * @return
		 */
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 * Set a custom content view for the Dialog. If a message is set, the
		 * contentView is not added to the Dialog...
		 * 
		 * @param v
		 * @return
		 */
		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * Set the positive button resource and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(int positiveButtonText,
				DialogInterface.OnClickListener listener) {
			this.positiveButtonText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the positive button text and it's listener
		 * 
		 * @param positiveButtonText
		 * @param listener
		 * @return
		 */
		public Builder setPositiveButton(String positiveButtonText,
				DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button resource and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(int negativeButtonText,
				DialogInterface.OnClickListener listener) {
			this.negativeButtonText = (String) context
					.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * Set the negative button text and it's listener
		 * 
		 * @param negativeButtonText
		 * @param listener
		 * @return
		 */
		public Builder setNegativeButton(String negativeButtonText,
				DialogInterface.OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}
		
		public Builder setCancelable(final boolean cancelable) {
			mCancelable = cancelable;
			return this;
		}
		
		public Builder setIsHtml(final boolean isHtml) {
			mHtml = isHtml;
			return this;
		}
		
		/**
		 * Set the Window soft input mode.
		 * @see WindowManager.LayoutParams
		 * @param type 
		 * @return Phone dialog builder
		 */
		public Builder setWindowSoftInpitMode(final int mode) {
			mWindowSoftInputMode = mode;
			return this;
		}

		/**
		 * Create the custom dialog
		 */
		public PhoneDialog create() {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final PhoneDialog dialog = new PhoneDialog(context, R.style.PhoneDialog);
			final View layout = inflater.inflate(R.layout.dialog, null);
			dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			dialog.setCancelable(mCancelable);
			//set the window soft input mode
			if (-1 != mWindowSoftInputMode) {
				dialog.getWindow().setSoftInputMode(mWindowSoftInputMode);
			}
			// set the dialog title
			dialog.mTitleTextView = (TextView) layout.findViewById(R.id.title);
			dialog.mTitleTextView.setText(title);
			// set the confirm button
			if (positiveButtonText != null) {
				((Button) layout.findViewById(R.id.positiveButton))
						.setText(positiveButtonText);
				if (positiveButtonClickListener != null) {
					((Button) layout.findViewById(R.id.positiveButton))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									positiveButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.positiveButton).setVisibility(
						View.GONE);
			}
			// set the cancel button
			if (negativeButtonText != null) {
				((Button) layout.findViewById(R.id.negativeButton))
						.setText(negativeButtonText);
				if (negativeButtonClickListener != null) {
					((Button) layout.findViewById(R.id.negativeButton))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									negativeButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.negativeButton).setVisibility(
						View.GONE);
			}
			// set the content message
			if (message != null) {
				((TextView) layout.findViewById(R.id.message)).setMovementMethod(ScrollingMovementMethod.getInstance());
				
				if (mHtml) {
					((TextView) layout.findViewById(R.id.message)).setText(Html.fromHtml(message));
				} else {
					((TextView) layout.findViewById(R.id.message)).setText(message);
				}

			} else if (contentView != null) {
				// if no message set
				// add the contentView to the dialog body
				((LinearLayout) layout.findViewById(R.id.content))
						.removeAllViews();
				((LinearLayout) layout.findViewById(R.id.content)).addView(
						contentView, new LayoutParams(
								LayoutParams.FILL_PARENT,
								LayoutParams.WRAP_CONTENT));
			}
			dialog.setContentView(layout);
			dialog.setOnKeyListener(new OnKeyListener() {
				
				@Override
				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
					if (KeyEvent.KEYCODE_SEARCH == keyCode) {
						return true;
					} else {
						return false;
					}
				}
			});

			return dialog;
		}
		
		public void showDialog() {
			create().show();
		}

	}

	public static class DialogHelper {
		/**
		 * Set and Show the Dialog with BottomIn BottomOut Animation.
		 *
		 * @param d
		 */
		public static void showBottomInBottomOutDialog(Dialog d) {
			d.show();
			Window w = d.getWindow();
			w.setGravity(Gravity.BOTTOM);
			w.setWindowAnimations(R.style.phone_Animation_Dialog_BottomIn300);
			w.setBackgroundDrawable(new ColorDrawable(0xAA000000));
			w.getDecorView().getLayoutParams().width = ViewGroup.LayoutParams.FILL_PARENT;
			w.getDecorView().getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
			WindowManager m = w.getWindowManager();
			m.updateViewLayout(w.getDecorView(), w.getDecorView().getLayoutParams());
		}

		/**
		 * Set and Show the Dialog with Full Size.
		 *
		 * @param d
		 */
		public static void showFullSizeDialog(Dialog d) {
			Window w = d.getWindow();
			d.show();
			w.getDecorView().getLayoutParams().width = ViewGroup.LayoutParams.FILL_PARENT;
			w.getDecorView().getLayoutParams().height = ViewGroup.LayoutParams.FILL_PARENT;
			WindowManager m = w.getWindowManager();
			m.updateViewLayout(w.getDecorView(), w.getDecorView().getLayoutParams());
		}
	}
	
}