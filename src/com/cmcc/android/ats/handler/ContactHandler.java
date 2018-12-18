package net.andy.android.ats.handler;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * ������ϵ�˴���
 * @author zhouyelin
 *
 */
public class ContactHandler {

	/**
	 * ��ѯ������ϵ��  
	 */
	public static List<String> queryAll(Context ctx) {  
		List<String> list = new ArrayList<String>();
        ContentResolver resolver = ctx.getContentResolver();  
        Uri uri = Uri.parse("content://com.android.contacts/contacts");  
        Cursor idCursor = resolver.query(uri, new String[] { "_id" }, null, null, null); 
        while (idCursor.moveToNext()) { 
            //��ȡ��raw_contacts���е�id  
            int id = idCursor.getInt(0);   
            //���ݻ�ȡ����ID��ѯdata���е�����  
            uri = Uri.parse("content://com.android.contacts/contacts/" + id + "/data");  
            Cursor dataCursor = resolver.query(uri, new String[] { "data1", "mimetype" }, null, null, null);  
            StringBuilder sb = new StringBuilder();  
            sb.append("id=" + id);  
            //��ѯ��ϵ�˱��е�
            while (dataCursor.moveToNext()) {  
                String data = dataCursor.getString(0);  
                String type = dataCursor.getString(1);  
                if ("vnd.android.cursor.item/name".equals(type))  
                    sb.append(", name=" + data);  
                else if ("vnd.android.cursor.item/phone_v2".equals(type))  
                    sb.append(", phone=" + data);  
                else if ("vnd.android.cursor.item/email_v2".equals(type))  
                    sb.append(", email=" + data);  
            } 
            list.add(sb.toString());
        }  
        return list;
    } 
    
    /**
     * ���ݵ绰�����ѯ��ϵ������  
     */
    public static String queryNameByNo(Context ctx, String phoneNum) {  
    	String name = null;
        ContentResolver resolver = ctx.getContentResolver();  
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + phoneNum);  
        Cursor c = resolver.query(uri, new String[] { "display_name" }, null, null, null);  
        if (c.moveToNext()) {  
            System.out.println(c.getString(0));  
            name = c.getString(0);
        }  
        return name;
    } 
    
    public static void deleteByName(Context ctx, String[] names){
    	ContentResolver resolver = ctx.getContentResolver(); 
    	//ContactsContract.Contacts.CONTENT_URI;
    	//Data.CONTENT_URI;
	    Uri uri = Uri.parse("content://com.android.contacts/data");  
	    resolver.delete(uri, ContactsContract.Contacts.DISPLAY_NAME + "=?", names); 
    }
    
    /**
     * �����ϵ��  
     */
    public static void insert(Context ctx, String name, String phoneNum, String email) {  
	    ContentResolver resolver = ctx.getContentResolver();  
	    Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");  
	    ContentValues values = new ContentValues();  
	    //��raw_contacts����һ������ID֮��, ����ȫ��ΪNULL�ļ�¼, ID���Զ����ɵ�  
	    long id = ContentUris.parseId(resolver.insert(uri, values));   
	    
	    //�����ϵ������  
	    uri = Uri.parse("content://com.android.contacts/data");  
	    values.put("raw_contact_id", id);  
	    values.put("data2", name);  
	    values.put("mimetype", "vnd.android.cursor.item/name");  
	    resolver.insert(uri, values);  
	    
	    //�����ϵ�˵绰  
	    values.clear(); // ����ϴε�����  
	    values.put("raw_contact_id", id);  
	    values.put("data1", phoneNum);  
	    values.put("data2", "2");  
	    values.put("mimetype", "vnd.android.cursor.item/phone_v2");  
	    resolver.insert(uri, values);  
	    
	    //�����ϵ������  
	    values.clear();  
	    values.put("raw_contact_id", id);  
	    values.put("data1", email);  
	    values.put("data2", "1");  
	    values.put("mimetype", "vnd.android.cursor.item/email_v2");  
	    resolver.insert(uri, values);  
    }
    
    /**
     * ʹ�����������ϵ��  
     * @throws Exception
     */
    public static void insertWithBatch(Context ctx, String name, String phoneNum, String email) throws Exception {  
        ContentResolver resolver = ctx.getContentResolver();  
      
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();  
        
        //����id
        ContentProviderOperation operation1 = ContentProviderOperation   
                .newInsert(Uri.parse("content://com.android.contacts/raw_contacts"))   
                .withValue("_id", null)  
                .build();  
        operations.add(operation1);  
        //��������
        ContentProviderOperation operation2 = ContentProviderOperation   
                .newInsert(Uri.parse("content://com.android.contacts/data"))   
                .withValueBackReference("raw_contact_id", 0)  
                .withValue("data2", name)  
                .withValue("mimetype", "vnd.android.cursor.item/name")  
                .build();  
        operations.add(operation2);  
        //������ϵ�˵绰
        ContentProviderOperation operation3 = ContentProviderOperation  
                .newInsert(Uri.parse("content://com.android.contacts/data"))   
                .withValueBackReference("raw_contact_id", 0)  
                .withValue("data1", phoneNum)  
                .withValue("data2", "2")  
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")   
                .build();  
        operations.add(operation3);  
        //��������
        ContentProviderOperation operation4 = ContentProviderOperation 
                .newInsert(Uri.parse("content://com.android.contacts/data"))   
                .withValueBackReference("raw_contact_id", 0)  
                .withValue("data1", email)   
                .withValue("data2", "2") 
                .withValue("mimetype", "vnd.android.cursor.item/email_v2")   
                .build();  
        operations.add(operation4);  
      
        //ִ����������
        resolver.applyBatch("com.android.contacts", operations);  
    }  
}
