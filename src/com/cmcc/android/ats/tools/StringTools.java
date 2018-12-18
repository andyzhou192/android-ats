package net.andy.android.ats.tools;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class StringTools {
	public final static String TAG = "StringHelper";

	/**
	 * ��ָ���ַ���ת��ΪJSON��
	 * @param jsonStr
	 * @return JSON����
	 */
	public static JSONObject convertToJson(String jsonStr){
		//contactList = "{\"result\":{\"contact_count\":\"1\",\"contact_list\":[{\"lastModifiedTime\":\"2014-06-30 13:43:40\",\"createTime\":\"2014-06-30 13:43:40\",\"status\":0,\"dataFromFlag\":\"1\",\"groupMap\":[],\"givenName\":\"\u738b\u4fca\u5b87\",\"contactUserId\":\"1031853202\",\"contactId\":\"6612128302\",\"lastContactTime\":null,\"userId\":1031853202,\"name\":\"\u738b\u4fca\u5b87\",\"syncMobileFlag\":\"1\",\"groups\":[],\"mobile\":[\"18701257471\"]}]},\"id\":\"1404194084187\",\"jsonrpc\":\"2.0\"}";
		JSONObject json = null;
		try {
			json = new JSONObject(jsonStr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
		
	}
	
	/**
	 * ��ȡJSON�ַ�����ָ�����ֶ�ֵ
	 * ע�⣺������ֻ����Լ򵥵���ṹ��JSON,��keysΪ��ʱ���������ַ���ת���ĵ���JSON����
	 * @param jsonStr: json�ַ���������ΪJSON��ʽ
	 * @param flag: ���ؽ�����͡�
	 * 			0-�������JSON�ַ���ת����JSON����
	 * 			1-����JSON�ַ�����ָ�����ֶ�ֵ��
	 * 			2-����JSON�ַ�����ָ����JSON����
	 * 			3-����JSON�ַ�����ָ����JSON�������顿
	 * @param keys
	 * @return
	 */
	public static Object fetchSpecifyFiledFromJsonStr(String jsonStr, int flag, String...keys){
		JSONObject obj = convertToJson(jsonStr);
		int count = keys.length;
		try {
			switch (flag){
			case 0:
				if(count == 0){
					return obj;
				}else{
					Log.e(TAG, "����������󣬵�flag=0ʱ��keysӦ��Ϊ�գ����������ò���.");
				}
				break;
				
			case 1:
				if(count == 1){
					return obj.get(keys[0]);
				}else if(count>1){
					for(int i=0; i<count-1; i++){
						obj = obj.getJSONObject(keys[i]);
					}
					return obj.get(keys[count-1]);
				}else{
					Log.e(TAG, "����������󣬵�flag=1ʱ��keysӦ�ò�Ϊ�գ�������һ��ֵ.");
				}
				break;
				
			case 2:
				if(count == 1){
					return obj.getJSONObject(keys[0]);
				}else if(count>1){
					for(int i=0; i<count; i++){
						obj = obj.getJSONObject(keys[i]);
					}
					return obj;
				}else{
					Log.e(TAG, "����������󣬵�flag=2ʱ��keysӦ�ò�Ϊ�գ�������һ��ֵ.");
				}
				break;
				
			case 3:
				if(count == 1){
					return obj.getJSONArray(keys[0]);
				}else if(count>1){
					for(int i=0; i<count-1; i++){
						obj = obj.getJSONObject(keys[i]);
					}
					return obj.getJSONArray(keys[count-1]);
				}else{
					Log.e(TAG, "����������󣬵�flag=2ʱ��keysӦ�ò�Ϊ�գ�������һ��ֵ.");
				}
				break;
				
			default:
				Log.e(TAG, "flag�����������.");
			}
					
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * ��ȡJSON�ַ�����ָ����JSON����
	 * ע�⣺������ֻ����Ը��ӽṹ��JSON
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static JSONObject fetchJSONObjectFromJsonStr(String jsonStr, String...keys){
		return (JSONObject) fetchSpecifyFiledFromJsonStr(jsonStr, 2, keys);
	}
	
	/**
	 * ��ȡJSON�ַ�����ָ����JSON��������
	 * ע�⣺������ֻ����Ը��ӽṹ��JSON
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static JSONArray fetchJSONArrayFromJsonStr(String jsonStr, String...keys){
		return (JSONArray) fetchSpecifyFiledFromJsonStr(jsonStr, 3, keys);
	}
	
	/**
	 * �ж�ָ���ַ����Ƿ�Ϊ�գ�null���մ������Ȳ�����0 ��Ϊ�գ���Ϊ���򷵻�true�����򷵻�false
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(null == str || str.length() < 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * ��listת��������
	 * @param list
	 * @return
	 */
	public static String[] listToStrArray(List<?> list){
		if(null == list || list.size()<1){
			return new String[0];
		}
		String[] array = new String[list.size()];
		for(int i = 0; i < list.size(); i++){
			array[i] = list.get(i).toString();
		}
		return array;
	}
	
	public static String[] getMapKeys(Map<String, ?> map){
		if(null == map){
			return null;
		}
		String[] array = new String[map.size()];
		Iterator<String> it = map.keySet().iterator();
		int index = 0;
		while (it.hasNext()){
			array[index] = it.next();
			index++;
		}
		return array;
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
