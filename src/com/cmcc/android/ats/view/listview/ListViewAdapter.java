package com.cmcc.android.ats.view.listview;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class ListViewAdapter extends SimpleAdapter {

	// ÑÕÉ«
	private int[] colors = { Color.CYAN, Color.LTGRAY };

	public ListViewAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		view.setBackgroundResource(colors[position % 2]);
		return view;
	}

}
