package com.cmcc.android.ats.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cmcc.android.ats.model.ResultViewDO;
import com.cmcc.android.ats.model.TestResultDO;
import com.cmcc.android.ats.runner.StartTest;
import com.cmcc.android.ats.tools.StringTools;
import com.cmcc.android.ats.view.ReflushDisplayView;
import com.cmcc.android.ats.view.listview.ListViewAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ClickListenerUtil {
	private Context ctx;
	private ResultViewDO rv = null;
	private TestResultDO tr = null;

	public ClickListenerUtil(Context ctx, ResultViewDO rv, TestResultDO tr) {
		this.ctx = ctx;
		this.rv = rv;
		this.tr = tr;
	}

	public OnClickListener getClickListener() {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] testArray = null;
				int resource = android.R.layout.simple_list_item_1;
				switch (v.getId()) {
				case ConstantsUtil.RUN_BTN_ID:
					DialogUtil.showWaitingDialog(ctx, ConstantsUtil.RUNNING);
					rv.setListViewAdapter(new ArrayAdapter<String>(ctx, resource));
					changeTitleColor(ConstantsUtil.RUN_BTN_ID);
					StartTest.run(rv);
					break;

				case ConstantsUtil.RUN_VIEW_ID:
					tr = StartTest.testResult;
					changeTitleColor(ConstantsUtil.RUN_VIEW_ID);
					if (null != tr) {
						testArray = StringTools.listToStrArray(tr.getTestCaseList());
						rv.getListView().setOnItemClickListener(getItemClickListener(true, true));
					} else {
						testArray = new String[] { ConstantsUtil.NO_TEST_RAN_RESULT };
					}
					rv.setListViewAdapter(new ArrayAdapter<String>(ctx,
							resource, testArray));
					ReflushDisplayView.reflushResultList(rv, tr);
					break;
					
				case ConstantsUtil.FAIL_VIEW_ID:
					tr = StartTest.testResult;
					changeTitleColor(ConstantsUtil.FAIL_VIEW_ID);
					if (null != tr) {
						testArray = StringTools.getMapKeys(tr.getFailCaseMap());
						rv.getListView().setOnItemClickListener(getItemClickListener(true, false));
					} else {
						testArray = new String[] { ConstantsUtil.NO_TEST_RAN_RESULT };
					}
					rv.setListViewAdapter(new ArrayAdapter<String>(ctx,
							resource, testArray));
					ReflushDisplayView.reflushResultList(rv, tr);
					break;
					
				case ConstantsUtil.ERR_VIEW_ID:
					tr = StartTest.testResult;
					changeTitleColor(ConstantsUtil.ERR_VIEW_ID);
					if (null != tr) {
						testArray = StringTools.getMapKeys(tr.getErrCaseMap());
						rv.getListView().setOnItemClickListener(getItemClickListener(false, true));
					} else {
						testArray = new String[] { ConstantsUtil.NO_TEST_RAN_RESULT };
					}
					rv.setListViewAdapter(new ArrayAdapter<String>(ctx,
							resource, testArray));
					ReflushDisplayView.reflushResultList(rv, tr);
					break;
					
				default:
					break;
				}
			}

		};
	}
	
	private ListViewAdapter getAdapter(Map<String, String> map){
		int resource = android.R.layout.simple_list_item_1;
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()){
			Map<String, String> itemMap = new HashMap<String, String>();
			itemMap.put("key", it.next());
			data.add(itemMap);
		}
		return new ListViewAdapter(ctx, data, resource, new String[] { "key" }, new int[] { android.R.id.text1 });
	}
	
//	private void addItem() {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("title", "标题");
//		map.put("text", "要显示的内容");
//		data.add(map);
//		listItemAdapter.notifyDataSetChanged();
//	}
//
//	private void deleteItem() {
//		int size = data.size();
//		if (size > 0) {
//			data.remove(data.size() - 1);
//			listItemAdapter.notifyDataSetChanged();
//		}
//	}
	
	public AdapterView.OnItemClickListener getItemClickListener(final boolean isFail, final boolean isErr){
		return new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterview, View view,
					int position, long id) {
				String key = ((TextView) view).getText().toString();
				String content = "detail info is null";
				int conColor = Color.BLUE;
				if(isFail && isErr){
					if(tr.getFailCaseMap().containsKey(key)){
						content = tr.getFailCaseMap().get(key);
					}else if(tr.getErrCaseMap().containsKey(key)){
						content = tr.getErrCaseMap().get(key);
						conColor = Color.RED;
					}else{
						return;
					}
				}else if(isFail){
					content = tr.getFailCaseMap().get(key);
				}else if(isErr){
					content = tr.getErrCaseMap().get(key);
					conColor = Color.RED;
				}else{
					return;
				}
				DialogUtil.detailDialog(ctx, key, content, conColor);
			}
		};
	}

	// 标题背景颜色变更
	private void changeTitleColor(int id) {
		switch (id) {
		case ConstantsUtil.ERR_VIEW_ID:
			setTitleViewBackgroundColor(Color.GRAY, Color.GRAY, Color.WHITE);
			break;
		case ConstantsUtil.FAIL_VIEW_ID:
			setTitleViewBackgroundColor(Color.GRAY, Color.WHITE, Color.GRAY);
			break;
		default:
			setTitleViewBackgroundColor(Color.WHITE, Color.GRAY, Color.GRAY);
			break;
		}
	}

	private void setTitleViewBackgroundColor(int runColor, int failColor,
			int errColor) {
		rv.getRunTestCountView().setBackgroundColor(runColor);
		rv.getFailTestCountView().setBackgroundColor(failColor);
		rv.getErrTestCountView().setBackgroundColor(errColor);
	}
}
