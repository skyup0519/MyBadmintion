package com.dialog.android.phone;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mitake.android.phone.R;
import com.dialog.android.phone.app.PhoneDialog;
import com.dialog.android.phone.util.Android;

/**
 * @author Joseph
 */
public class HelperBasePhoneDialog {
	
	public static boolean isF5Block = false;
	
	/**
	 * Create a Dialog with Title and Message.
	 * 
	 * @param ctx dialog context
	 * @param title text of title
	 * @param msg text of message
	 * @return dialog
	 */
	public static PhoneDialog createDialog(Context ctx, String title, String msg) {
		return createDialog(ctx, title, msg, "確定");
	}
	
	/**
	 * Create a Dialog with Title and Message.
	 * 
	 * @param ctx dialog context
	 * @param title text of title
	 * @param msg text of message
	 * @return dialog
	 */
	public static PhoneDialog createDialog(Context ctx, String title, String msg, boolean isHtml) {
		return createDialog(ctx, title, msg, "確定", isHtml);
	}

	/**
	 * Create a Dialog with Message.
	 * 
	 * @param ctx dialog context
	 * @param msg text of message
	 * @return dialog
	 */
	public static PhoneDialog createDialog(Context ctx, String msg) {
		return createDialog(ctx, "訊息", msg, "確定");
	}

	/**
	 * Create a Dialog with Title, Message and Positive Button Text.
	 * 
	 * @param ctx dialog context
	 * @param title text of title
	 * @param msg text of message
	 * @param pbt positive button text
	 * @return dialog
	 */
	public static PhoneDialog createDialog(Context ctx, String title, String msg, String pbt) {
		OnClickListener pbl = new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		};
		return createDialog(ctx, title, msg, pbt, pbl);
	}
	
	/**
	 * Create a Dialog with Title, Message and Positive Button Text.
	 * 
	 * @param ctx dialog context
	 * @param title text of title
	 * @param msg text of message
	 * @param pbt positive button text
	 * @return dialog
	 */
	public static PhoneDialog createDialog(Context ctx, String title, String msg, String pbt, boolean isHtml) {
		OnClickListener pbl = new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		};
		return createDialog(ctx, title, msg, pbt, pbl, false, isHtml);
	}

	/**
	 * Create a Dialog with Title, Message and Positive Button Setup.
	 * 
	 * @param ctx dialog context
	 * @param title text of title
	 * @param msg text of message
	 * @param pbt positive button text
	 * @param pbl positive button listener
	 * @return dialog
	 */
	public static PhoneDialog createDialog(Context ctx, String title, String msg, String pbt, OnClickListener pbl) {
		return createDialog(ctx, title, msg, pbt, pbl, null, null);
	}
	
	/**
	 * Create a Dialog with Title, Message and Positive Button Setup.
	 * 
	 * @param ctx dialog context
	 * @param title text of title
	 * @param msg text of message
	 * @param pbt positive button text
	 * @param pbl positive button listener
	 * @return dialog
	 */
	public static PhoneDialog createDialog(Context ctx, String title, String msg, String pbt, OnClickListener pbl, boolean isF5Block, boolean isHtml) {
		return createDialog(ctx, title, msg, pbt, pbl, null, null, isHtml);
	}

	/**
	 * For F5 Block
	 */
	public static PhoneDialog createDialog(Context ctx, String title, String msg, String pbt, OnClickListener pbl, boolean isF5Block) {
		HelperBasePhoneDialog.isF5Block = isF5Block;
		return createDialog(ctx, title, msg, pbt, pbl, null, null);
	}
	
	/**
	 * Create a Dialog with Title, Message, Positive Button Setup and Negative Button Setup.
	 * 
	 * @param ctx dialog context
	 * @param title text of title
	 * @param msg text of message
	 * @param pbt positive button text
	 * @param pbl positive button listener
	 * @param nbt negative button text
	 * @param nbl negative button listener
	 * @return dialog
	 */
	public static PhoneDialog createDialog(Context ctx, String title, String msg, String pbt, OnClickListener pbl, String nbt, OnClickListener nbl) {
		PhoneDialog.Builder builder = new PhoneDialog.Builder(ctx);
		builder.setTitle(title).setMessage(msg);
		if (pbt != null) {
			builder.setPositiveButton(pbt, pbl);
		}
		if (nbt != null) {
			builder.setNegativeButton(nbt, nbl);
		}
		PhoneDialog dialog = builder.create();
		return dialog;
	}
	
	/**
	 * Create a Dialog with Title, Message, Positive Button Setup and Negative Button Setup.
	 * 
	 * @param ctx dialog context
	 * @param title text of title
	 * @param msg text of message
	 * @param pbt positive button text
	 * @param pbl positive button listener
	 * @param nbt negative button text
	 * @param nbl negative button listener
	 * @return dialog
	 */
	public static PhoneDialog createDialog(Context ctx, String title, String msg, String pbt, OnClickListener pbl, String nbt, OnClickListener nbl, boolean isHtml) {
		PhoneDialog.Builder builder = new PhoneDialog.Builder(ctx);
		builder.setIsHtml(isHtml);
		builder.setTitle(title).setMessage(msg);
		if (pbt != null) {
			builder.setPositiveButton(pbt, pbl);
		}
		if (nbt != null) {
			builder.setNegativeButton(nbt, nbl);
		}
		PhoneDialog dialog = builder.create();
		return dialog;
	}

	/**
	 * Create a Progress Dialog.
	 * 
	 * @param ctx dialog context
	 * @return progress dialog with screen lock
	 */
	public static PhoneDialog createProgressDialog(Context ctx) {
		return createProgressDialog(ctx, "資料讀取中");
	}

	/**
	 * Create a Progress Dialog.
	 * 
	 * @param ctx dialog context
	 * @param title text of title
	 * @return progress dialog with screen lock
	 */
	public static PhoneDialog createProgressDialog(Context ctx, String title) {
		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// Progress Bar.
		final View pgBar = inflater.inflate(R.layout.dialog_content_process, null);
		pgBar.findViewById(R.id.phone_progress).setVisibility(View.VISIBLE);
		// Progress Dialog.
		PhoneDialog.Builder builder = new PhoneDialog.Builder(ctx);
		PhoneDialog pgDialog = builder.setTitle(title).setContentView(pgBar).setCancelable(false).create();
		pgDialog.setCancelable(false);

		return pgDialog;
	}
	public static void unbindDrawables(View view) {
		try{
		if(view!=null){
		    if (view.getBackground() != null) {  	
		        view.getBackground().setCallback(null);
		    }
		    else if(view instanceof ListView){
		    	for (int i = 0; i < ((ListView) view).getChildCount(); i++) {
		            unbindDrawables(((ListView) view).getChildAt(i));
		        }
		    }else if(view instanceof GridView){
		    	for (int i = 0; i < ((GridView) view).getChildCount(); i++) {
		            unbindDrawables(((GridView) view).getChildAt(i));
		        }
		    }
		    else if (view instanceof ViewGroup) {
		        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
		            unbindDrawables(((ViewGroup) view).getChildAt(i));
		        }
		        ((ViewGroup) view).removeAllViews();
		    }
	    }
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	}
	/**
	 * Shake animation with view.
	 * 
	 * @param context The context.
	 * @param view Be shake view.
	 */
	public static void shake(Context context, View view) {
		Animation shake = AnimationUtils.loadAnimation(context, R.anim.dialog_shake_horizontal_500);
		view.startAnimation(shake);
	}
	
	/**
	 * Show dialog with Bottom pop out.
	 * 
	 * @param dialog
	 * @param title
	 * @param titleTextSize
	 * @param titleTextColor
	 */
	public static void showDialogWithBottomPopOut(Dialog dialog, String title, int titleTextSize, int titleTextColor) {
		PhoneDialog.DialogHelper.showBottomInBottomOutDialog(dialog);
		ViewGroup g = (ViewGroup) ((ViewGroup) dialog.findViewById(android.R.id.content)).getChildAt(0);
		float textSize = Android.computeDIPtoPixel(dialog.getContext(), titleTextSize);
		
		TextView tv = new TextView(dialog.getContext());
		tv.setText(title);
		tv.setTextSize(textSize);
		tv.setTextColor(titleTextColor);
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
		
		LinearLayout.LayoutParams params;
		int margin = (int) Android.computeDIPtoPixel(dialog.getContext(), 10);
		params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		params.topMargin = margin;
		params.bottomMargin = margin;
		params.leftMargin = margin;
		params.rightMargin = margin;
		g.addView(tv, 0, params);
	}
	
	/**
	 * The default show dialog with Bottom pop out method.
	 * 
	 * @param dialog
	 */
	public static void showDialogWithBottomPopOut(Dialog dialog , Context ctx) {
		showDialogWithBottomPopOut(dialog, ctx.getString(R.string.dialog_select), 16, Color.WHITE);
	}
}
