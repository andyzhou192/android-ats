package net.andy.android.ats.testscript;

import net.andy.android.ats.annotation.Parameters;
import net.andy.android.ats.annotation.Test;

import android.test.AndroidTestCase;

@Test
public class TestClass2 extends AndroidTestCase {

	@Parameters(value="123")
	public void test2_01(String str){
		assertEquals("123", str);
		assertTrue(false);
	}
	
//	@Parameters(value="abc")
//	public void test2_02(String str){
//		assertEquals("abc", str);
//	}
	
//	@Parameters
//	public void test2_03(){
//		assertTrue(true);
//	}
	
}
