package com.cmcc.android.ats.util;

/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 

import android.annotation.SuppressLint;
import android.util.Log;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

	public LogUtils() {
		tag = "ContactManager";
		if (writeFileFlag) {
			File file = new File(logFile);
			if (file.exists())
				file.delete();
			try {
				file.createNewFile();
				fileinput = new FileOutputStream(file);
			} catch (IOException ioexception) {
				ioexception.printStackTrace();
			}
		}
	}

	public LogUtils(String s) {
		tag = "ContactManager";
		tag = s;
		if (writeFileFlag) {
			File file = new File(logFile);
			if (file.exists())
				file.delete();
			try {
				file.createNewFile();
				fileinput = new FileOutputStream(file);
			} catch (IOException ioexception) {
				ioexception.printStackTrace();
			}
		}
	}

	private String getFunctionName() {
		StackTraceElement astacktraceelement[] = Thread.currentThread()
				.getStackTrace();
		if (astacktraceelement == null)
			return null;
		StackTraceElement astacktraceelement1[] = astacktraceelement;
		int j = astacktraceelement1.length;
		for (int k = 0; k < j; k++) {
			StackTraceElement stacktraceelement = astacktraceelement1[k];
			if (!stacktraceelement.isNativeMethod()
					&& !stacktraceelement.getClassName().equals(
							(new Thread()).getName())
					&& !stacktraceelement.getClassName().equals(
							getClass().getName()))
				return (new StringBuilder()).append("[ ")
						.append(Thread.currentThread().getId()).append(": ")
						.append(stacktraceelement.getFileName()).append(":")
						.append(stacktraceelement.getLineNumber()).append(" ]")
						.toString();
		}

		return null;
	}

	public void info(Object obj) {
		if (logLevel <= 4) {
			String s = getFunctionName();
			String s1 = s != null ? (new StringBuilder()).append(s)
					.append(" - ").append(obj).toString() : obj.toString();
			Log.i(tag, s1);
			if (writeFileFlag)
				writeToFile("Info", tag, (new StringBuilder()).append(s)
						.append(" - ").append(obj).toString());
		}
	}

	public void i(Object obj) {
		info(obj);
	}

	public void verbose(Object obj) {
		if (logLevel <= 2) {
			String s = getFunctionName();
			String s1 = s != null ? (new StringBuilder()).append(s)
					.append(" - ").append(obj).toString() : obj.toString();
			Log.v(tag, s1);
			if (writeFileFlag)
				writeToFile("Verbose", tag, s1);
		}
	}

	public void v(Object obj) {
		verbose(obj);
	}

	public void warn(Object obj) {
		if (logLevel <= 5) {
			String s = getFunctionName();
			String s1 = s != null ? (new StringBuilder()).append(s)
					.append(" - ").append(obj).toString() : obj.toString();
			Log.w(tag, s1);
			if (writeFileFlag)
				writeToFile("Warn", tag, s1);
		}
	}

	public void w(Object obj) {
		warn(obj);
	}

	public void error(Object obj) {
		if (logLevel <= 6) {
			String s = getFunctionName();
			String s1 = s != null ? (new StringBuilder()).append(s)
					.append(" - ").append(obj).toString() : obj.toString();
			Log.e(tag, s1);
			if (writeFileFlag)
				writeToFile("Error", tag, s1);
		}
	}

	public void error(Exception exception) {
		if (logLevel <= 6) {
			StringBuffer stringbuffer = new StringBuffer();
			String s = getFunctionName();
			StackTraceElement astacktraceelement[] = exception.getStackTrace();
			if (s != null)
				stringbuffer.append((new StringBuilder()).append(s)
						.append(" - ").append(exception).append("\r\n")
						.toString());
			else
				stringbuffer.append((new StringBuilder()).append(exception)
						.append("\r\n").toString());
			if (astacktraceelement != null && astacktraceelement.length > 0) {
				StackTraceElement astacktraceelement1[] = astacktraceelement;
				int j = astacktraceelement1.length;
				for (int k = 0; k < j; k++) {
					StackTraceElement stacktraceelement = astacktraceelement1[k];
					if (stacktraceelement != null)
						stringbuffer.append((new StringBuilder()).append("[ ")
								.append(stacktraceelement.getFileName())
								.append(":")
								.append(stacktraceelement.getLineNumber())
								.append(" ]\r\n").toString());
				}

			}
			Log.e(tag, stringbuffer.toString());
			if (writeFileFlag)
				writeToFile("Excep", tag, stringbuffer.toString());
		}
	}

	public void e(Object obj) {
		error(obj);
	}

	public void e(Exception exception) {
		error(exception);
	}

	public void debug(Object obj) {
		if (logLevel <= 3) {
			String s = getFunctionName();
			String s1 = s != null ? (new StringBuilder()).append(s)
					.append(" - ").append(obj).toString() : obj.toString();
			Log.d(tag, s1);
			if (writeFileFlag)
				writeToFile("Debug", tag, s1);
		}
	}

	public void d(Object obj) {
		debug(obj);
	}

	@SuppressLint("SimpleDateFormat") 
	private void writeToFile(String s, String s1, String s2) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(
				"yyyy-MM-dd   hh:mm:ss");
		String s3 = simpledateformat.format(new Date());
		String s4 = (new StringBuilder()).append(s3).append("  ").append(s)
				.append("--").append(s1).append(":").append(s2).toString();
		try {
			fileinput.write(s4.toString().getBytes());
			fileinput.write("\r\n".getBytes());
			fileinput.flush();
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
	}

	private String tag;
	public static int logLevel = 2;
	public static boolean writeFileFlag = false;
	public static String logFile = "chinamobile_cloud_contact.log";
	public static FileOutputStream fileinput = null;

}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from:
 * /Users/cmcc/workspace/svnRepo/Sync_SDK_Test/libs/sync_sdk_rcs_sync_20140702
 * .jar Total time: 15 ms Jad reported messages/errors: Exit status: 0 Caught
 * exceptions:
 */
