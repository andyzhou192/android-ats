package net.andy.android.ats.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DialogUtil {
	private static ProgressDialog mProDiag = null;
	private static boolean showing = false;
	private static Object locker = new Object();

	public static void detailDialog(Context ctx, String title, String content, int conColor) {
		final TextView detailView = new TextView(ctx);
		initDetailTextView(detailView, conColor, content);
		AlertDialog displayDialog = new AlertDialog.Builder(ctx)
				.setTitle(title)
				.setView(detailView)
				.setNegativeButton("REBACK",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).create();
		displayDialog.show();
	}

	public static void showWaitingDialog(Context contex, String text) {
		synchronized (locker) {
			if (showing && mProDiag != null) {
				dismissDialog();
			}
			showing = true;
			mProDiag = ProgressDialog.show(contex, null, text, true, false,
					new DialogInterface.OnCancelListener() {
						public void onCancel(DialogInterface d) {
							return;
						}
					});
			mProDiag.show();
		}
	}

	public static void dismissDialog() {
		synchronized (locker) {
			if (showing == false) {
				return;
			}
			if (mProDiag != null) {
				mProDiag.dismiss();
				mProDiag = null;
				showing = false;
			}
		}
	}

	private static void initDetailTextView(TextView tv, int textColor, String content) {
		tv.setTextColor(textColor);
		tv.setText(content);
		tv.setTextSize(20);
		tv.setMovementMethod(new ScrollingMovementMethod());
		tv.setPadding(2, 1, 2, 1);
		tv.setBackgroundColor(Color.WHITE);
	}

}
