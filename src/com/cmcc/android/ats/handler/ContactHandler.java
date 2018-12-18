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
 * 本地联系人处理
 * @author zhouyelin
 *
 */
public class ContactHandler {

	/**
	 * 查询所有联系人  
	 */
	public static List<String> queryAll(Context ctx) {  
		List<String> list = new ArrayList<String>();
        ContentResolver resolver = ctx.getContentResolver();  
        Uri uri = Uri.parse("content://com.android.contacts/contacts");  
        Cursor idCursor = resolver.query(uri, new String[] { "_id" }, null, null, null); 
        while (idCursor.moveToNext()) { 
            //获取到raw_contacts表中的id  
            int id = idCursor.getInt(0);   
            //根据获取到的ID查询data表中的数据  
            uri = Uri.parse("content://com.android.contacts/contacts/" + id + "/data");  
            Cursor dataCursor = resolver.query(uri, new String[] { "data1", "mimetype" }, null, null, null);  
            StringBuilder sb = new StringBuilder();  
            sb.append("id=" + id);  
            //查询联系人表中的
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
     * 根据电话号码查询联系人名称  
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
     * 添加联系人  
     */
    public static void insert(Context ctx, String name, String phoneNum, String email) {  
	    ContentResolver resolver = ctx.getContentResolver();  
	    Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");  
	    ContentValues values = new ContentValues();  
	    //向raw_contacts插入一条除了ID之外, 其他全部为NULL的记录, ID是自动生成的  
	    long id = ContentUris.parseId(resolver.insert(uri, values));   
	    
	    //添加联系人姓名  
	    uri = Uri.parse("content://com.android.contacts/data");  
	    values.put("raw_contact_id", id);  
	    values.put("data2", name);  
	    values.put("mimetype", "vnd.android.cursor.item/name");  
	    resolver.insert(uri, values);  
	    
	    //添加联系人电话  
	    values.clear(); // 清空上次的数据  
	    values.put("raw_contact_id", id);  
	    values.put("data1", phoneNum);  
	    values.put("data2", "2");  
	    values.put("mimetype", "vnd.android.cursor.item/phone_v2");  
	    resolver.insert(uri, values);  
	    
	    //添加联系人邮箱  
	    values.clear();  
	    values.put("raw_contact_id", id);  
	    values.put("data1", email);  
	    values.put("data2", "1");  
	    values.put("mimetype", "vnd.android.cursor.item/email_v2");  
	    resolver.insert(uri, values);  
    }
    
    /**
     * 使用事务添加联系人  
     * @throws Exception
     */
    public static void insertWithBatch(Context ctx, String name, String phoneNum, String email) throws Exception {  
        ContentResolver resolver = ctx.getContentResolver();  
      
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();  
        
        //插入id
        ContentProviderOperation operation1 = ContentProviderOperation   
                .newInsert(Uri.parse("content://com.android.contacts/raw_contacts"))   
                .withValue("_id", null)  
                .build();  
        operations.add(operation1);  
        //插入姓名
        ContentProviderOperation operation2 = ContentProviderOperation   
                .newInsert(Uri.parse("content://com.android.contacts/data"))   
                .withValueBackReference("raw_contact_id", 0)  
                .withValue("data2", name)  
                .withValue("mimetype", "vnd.android.cursor.item/name")  
                .build();  
        operations.add(operation2);  
        //插入联系人电话
        ContentProviderOperation operation3 = ContentProviderOperation  
                .newInsert(Uri.parse("content://com.android.contacts/data"))   
                .withValueBackReference("raw_contact_id", 0)  
                .withValue("data1", phoneNum)  
                .withValue("data2", "2")  
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")   
                .build();  
        operations.add(operation3);  
        //插入邮箱
        ContentProviderOperation operation4 = ContentProviderOperation 
                .newInsert(Uri.parse("content://com.android.contacts/data"))   
                .withValueBackReference("raw_contact_id", 0)  
                .withValue("data1", email)   
                .withValue("data2", "2") 
                .withValue("mimetype", "vnd.android.cursor.item/email_v2")   
                .build();  
        operations.add(operation4);  
      
        //执行批量操作
        resolver.applyBatch("com.android.contacts", operations);  
    }  
}
