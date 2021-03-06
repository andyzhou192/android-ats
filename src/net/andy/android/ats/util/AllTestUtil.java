package net.andy.android.ats.util;

import java.util.List;

import net.andy.android.ats.handler.ObtainClassHandler;
import net.andy.android.ats.model.ResultViewDO;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import android.app.Activity;
import android.util.Log;

public class AllTestUtil {
	private final static String TAG = String.valueOf(AllTestUtil.class);
	
	@SuppressWarnings("unchecked")
	public static Test suite(ResultViewDO rv){
        TestSuite s = new TestSuite();
        if(null == rv.getTestClass() || rv.getTestClass().length<1){
        	rv.setTestClass(getTestClass(rv.getMainActivity(), rv.getTestPackage()));
		}
        for(Class<?> cl:rv.getTestClass()){
        	//Log.d(TAG, cl.getName());
        	s.addTestSuite((Class<? extends TestCase>) cl);
        }
        
        return s;
    }
	
	private static Class<?>[] getTestClass(Activity activity, String testPackage) {
		Log.w(TAG, "����������Ϊ��");
		List<Class<?>> list = ObtainClassHandler.scan(activity, testPackage);
		Class<?>[] testClasses = new Class<?>[list.size()];
		for(int i=0; i<list.size(); i++){
			testClasses[i] = list.get(i);
		}
		return testClasses;
	}

}
