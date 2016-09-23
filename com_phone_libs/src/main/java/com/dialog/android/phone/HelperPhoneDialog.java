package com.dialog.android.phone;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import com.mitake.android.phone.R;
import com.dialog.android.phone.app.PhoneDialog;

public class HelperPhoneDialog extends HelperBasePhoneDialog {

//	提示訊息
	public static final String DEFAULT_TITLE = "";

	public static PhoneDialog createFinshActivityDialog(Activity act, String msg) {
		String positiveButtonText;
		positiveButtonText = getPositiveButtonText(act);
		
		return createFinshActivityDialog(act, DEFAULT_TITLE, msg, positiveButtonText);
	}

	public static PhoneDialog createFinshActivityDialog(final Activity act, String title, String msg, String pbt) {
		DialogInterface.OnClickListener pbl;

		pbl = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				act.finish();
			}
		};
		return createDialog(act, title, msg, pbt, pbl);
	}

	public static PhoneDialog createDialog(Context ctx, String msg, DialogInterface.OnClickListener pbl) {
		String positiveButtonText;
		positiveButtonText = getPositiveButtonText(ctx);
		
		return createDialog(ctx, DEFAULT_TITLE, msg, positiveButtonText, pbl);
	}

	public static PhoneDialog createDialog(Context ctx, String title, String msg, DialogInterface.OnClickListener pbl) {
		String positiveButtonText;
		positiveButtonText = getPositiveButtonText(ctx);
		
		return createDialog(ctx, title, msg, positiveButtonText, pbl);
	}
	
	//For F5 Block
	public static PhoneDialog createDialog(Context ctx, String title, String msg, DialogInterface.OnClickListener pbl, boolean isF5Block) {
		String positiveButtonText;
		positiveButtonText = getPositiveButtonText(ctx);
		
		return createDialog(ctx, title, msg, positiveButtonText, pbl, isF5Block);
	}

	public static PhoneDialog createDialog(Context ctx, String msg, DialogInterface.OnClickListener pbl, DialogInterface.OnClickListener nbl) {
		String positiveButtonText;
		positiveButtonText = getPositiveButtonText(ctx);
		
		return createDialog(ctx, DEFAULT_TITLE, msg, positiveButtonText, pbl, ctx.getString(R.string.dialog_no), nbl);
	}
	
	public static String getPositiveButtonText(Context ctx) {
		return ctx.getString(R.string.dialog_yes);
	}

	public static void popOutDialog(Context ctx, Dialog dialog) {
		popOutDialog(ctx, dialog, null);
	}

	public static void popOutDialog(Context ctx, Dialog dialog, String title) {
		String aTitle = title;
		if (null == title) {
			aTitle = ctx.getString(R.string.dialog_select);
		}
		showDialogWithBottomPopOut(dialog, aTitle, 14, Color.WHITE);
	}

	/**
	 * Create a Progress Dialog.
	 * 
	 * @param ctx
	 *            dialog context
	 * @param title
	 *            text of title
	 * @return progress dialog with screen lock
	 */
	public static PhoneDialog createProgressDialog(Context ctx, String title) {
		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// Progress Bar.
		View pgBar = inflater.inflate(R.layout.dialog_content_process, null);
		pgBar.findViewById(R.id.phone_progress).setVisibility(View.VISIBLE);

		// Progress Dialog.
		PhoneDialog.Builder builder = new PhoneDialog.Builder(ctx);
		PhoneDialog pgDialog = builder.setTitle(title).setContentView(pgBar).setCancelable(false).create();
		pgDialog.setCancelable(false);
		return pgDialog;
	}

	public static PhoneDialog createProgressDialog(Context ctx) {
		return createProgressDialog(ctx, ctx.getString(R.string.dialog_loading));
	}
	
	public static void createNullPointerExceptionDialog(Context ctx){
		if(HelperBasePhoneDialog.isF5Block == false)
			createDialog( ctx, ctx.getString(R.string.dialog_error)).show();
	}

}
