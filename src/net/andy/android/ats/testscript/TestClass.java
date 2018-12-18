//package net.andy.android.ats.testscript;
//
//import net.andy.android.ats.annotation.Parameters;
//import net.andy.android.ats.annotation.TargetSheet;
//import net.andy.android.ats.annotation.Test;
//
//import android.test.AndroidTestCase;
//
//@Test//(paramFile="test.xls")
//public class TestClass extends AndroidTestCase {
//
//	@TargetSheet(sheetIndex=0)
//	public void test_01(String arg1){
//		assertTrue(false);
//	}
//	
//	@TargetSheet(sheetIndex=0)
//	public void test_02(){
//		assertEquals("", null);
//	}
//	
//	@TargetSheet(sheetIndex=0)
//	public void test_03(){
//		assertTrue(true);
//	}
//	
//	public void test_04(){
//		String str = getString();
//		boolean isContain = str.contains("111");
//		assertTrue(isContain);
//	}
//	
//	@Parameters(value="abc")
//	public void test_05(String str){
//		assertEquals("abc", str);
//	}
//
//	private String getString() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
