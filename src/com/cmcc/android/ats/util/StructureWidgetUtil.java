package com.cmcc.android.ats.util;

import com.cmcc.android.ats.view.textview.BorderTextView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class StructureWidgetUtil {
	
	public static int MATCH = LayoutParams.MATCH_PARENT;
	public static int WRAP = LayoutParams.WRAP_CONTENT;
	public static LayoutParams MATCH_LPS = new LayoutParams(MATCH, MATCH);
	public static LayoutParams WRAP_LPS = new LayoutParams(WRAP, WRAP);
	public static LayoutParams MATCH_WRAP_LPS = new LayoutParams(MATCH, WRAP);
	public static LayoutParams WRAP_MATCH_LPS = new LayoutParams(WRAP, MATCH);

	public static Button strucButton(Context ctx, int id, String text, OnClickListener ocl){
		Button btn = new Button(ctx);
		btn.setLayoutParams(MATCH_WRAP_LPS);
		btn.setId(id);
		btn.setText(text);
		btn.setOnClickListener(ocl);
		return btn;
	}
	
	public static BorderTextView strucBorderTextView(Context ctx, String content, int textColor, int backgroundColor, int width){
		BorderTextView tv = new BorderTextView(ctx, null);
		tv.setText(content);
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
		tv.setWidth(width);
		tv.setPadding(2, 15, 2, 15); 
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        tv.setTextSize(16);//…Ë÷√◊÷ÃÂ£ª
		if(0 != textColor)
			tv.setTextColor(textColor);
		if(0 != backgroundColor)
			tv.setBackgroundColor(backgroundColor);
		return tv;
	}
	
	public static TextView strucDivideLine(Context ctx, int color){
		TextView tv = new TextView(ctx);
		tv.setPadding(1, 5, 1, 5); 
		tv.setBackgroundColor(color);
		return tv;
	}
	
	public static LinearLayout strucLinearLayout(Context ctx, LayoutParams lps, int orientation, View...children){
		LinearLayout layout = new LinearLayout(ctx);
		layout.setGravity(Gravity.CENTER_HORIZONTAL);
		if(null == lps)
			lps = WRAP_LPS;
		layout.setLayoutParams(lps);
		if(0 == orientation)
			orientation = LinearLayout.VERTICAL;  //HORIZONTAL(0)°¢VERTICAL(1)
		layout.setOrientation(orientation);
		for(View v:children){
			layout.addView(v);
		}
		return layout;
	}
	
	public static FrameLayout strucFrameLayout(Context ctx, View...children){
		FrameLayout layout = new FrameLayout(ctx);
		layout.setBackgroundColor(Color.GRAY);
		layout.setLayoutParams(MATCH_LPS);
		for(View v:children){
			layout.addView(v);
		}
		return layout;
	}
	
	public static TableLayout strucTableLayout(Context ctx, TableRow...rows){
		TableLayout layout = new TableLayout(ctx);
		layout.setLayoutParams(MATCH_WRAP_LPS);
		layout.setBackgroundColor(Color.GRAY);
		for(int i = 0; i < rows.length; i++){
			layout.addView(rows[i]);
		}
		return layout;
	}
	
	public static TableRow strucTableRow(Context ctx, View...children){
		TableRow row = new TableRow(ctx);
		row.setGravity(Gravity.CENTER_HORIZONTAL);
		row.setLayoutParams(MATCH_WRAP_LPS);
		for(int j = 0; j < children.length; j++){
			row.addView(children[j]);
		}
		return row;
	}
	
}
