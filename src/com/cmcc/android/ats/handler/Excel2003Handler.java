package com.cmcc.android.ats.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.cmcc.android.ats.tools.StringTools;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import android.content.Context;
import android.util.Log;

public class Excel2003Handler {

	public static List<String[]> readExcel(Context ctx, String fileName, Object targetSheet){
		List<String[]> rowList = new ArrayList<String[]>();
        try {
            InputStream mInputStream = ctx.getResources().getAssets().open(fileName);
            Workbook wb = Workbook.getWorkbook(mInputStream);
            Sheet mSheet = null;
            if(targetSheet instanceof Integer){
            	mSheet = wb.getSheet((Integer) targetSheet);
            }else{
            	mSheet = wb.getSheet((String) targetSheet);
            }
            int row = getRowCount(mSheet);
            int columns = getColCount(mSheet);
            Log.d("W","Total Row: " + row + ", Total Columns: " + columns);
            String[] colArray = new String[columns];
            for(int i= 1 ; i < row ; i ++){
                for(int j = 0 ; j < columns ; j ++){
                    Cell temp = mSheet.getCell(j, i);
                    String content = temp.getContents();
                    Log.d("W",j + " ," + i + " ," + content);
                    colArray[j] = content;
                }
                rowList.add(colArray);
            }
            wb.close();
            mInputStream.close();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return rowList;
    }
	
	/**
	 * 获取第一个第一列单元格内容非空的行号,因为第一列为caseId，不能为空
	 * @param mSheet
	 * @return
	 */
	private static int getRowCount(Sheet mSheet){
		int count = 0;
		int row = mSheet.getRows();
		for(int i=0; i<row; i++){
			String content = mSheet.getCell(0, i).getContents();
			if(StringTools.isEmpty(content)){
				break;
			}
			++count;
		}
        return count;
	}
	
	/**
	 * 获取第一个第一行单元格内容非空的列号，因为第一行为参数key，不能为空
	 * @param mSheet
	 * @return
	 */
	private static int getColCount(Sheet mSheet){
		int count = 0;
		int columns = mSheet.getColumns();
		for(int i=0; i<columns; i++){
			String content = mSheet.getCell(i, 0).getContents();
			if(StringTools.isEmpty(content)){
				break;
			}
			++count;
		}
        return count;
	}
}
