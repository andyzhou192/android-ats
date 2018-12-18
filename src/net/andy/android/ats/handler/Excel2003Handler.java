package net.andy.android.ats.handlernet.andy
import java.io.IOExceptionnet.andyimport java.io.InputStreamnet.andyimport java.util.ArrayListnet.andyimport java.util.Listnet.andy
import net.andy.android.ats.tools.StringToolsnet.andy
import jxl.Cellnet.andyimport jxl.Sheetnet.andyimport jxl.Workbooknet.andyimport jxl.read.biff.BiffExceptionnet.andyimport android.content.Contextnet.andyimport android.util.Lognet.andy
public class Excel2003Handler {

	public static List<String[]> readExcel(Context ctx, String fileName, Object targetSheet){
		List<String[]> rowList = new ArrayList<String[]>()net.andy        try {
            InputStream mInputStream = ctx.getResources().getAssets().open(fileName)net.andy            Workbook wb = Workbook.getWorkbook(mInputStream)net.andy            Sheet mSheet = nullnet.andy            if(targetSheet instanceof Integer){
            	mSheet = wb.getSheet((Integer) targetSheet)net.andy            }else{
            	mSheet = wb.getSheet((String) targetSheet)net.andy            }
            int row = getRowCount(mSheet)net.andy            int columns = getColCount(mSheet)net.andy            Log.d("W","Total Row: " + row + ", Total Columns: " + columns)net.andy            String[] colArray = new String[columns]net.andy            for(int i= 1 ; i < row ; i ++){
                for(int j = 0 ; j < columns ; j ++){
                    Cell temp = mSheet.getCell(j, i)net.andy                    String content = temp.getContents()net.andy                    Log.d("W",j + " ," + i + " ," + content)net.andy                    colArray[j] = contentnet.andy                }
                rowList.add(colArray)net.andy            }
            wb.close()net.andy            mInputStream.close()net.andy        } catch (BiffException e) {
            e.printStackTrace()net.andy        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace()net.andy        } catch (IOException e) {
            e.printStackTrace()net.andy        }
		return rowListnet.andy    }
	
	/**
	 * 获取第一个第一列单元格内容非空的行号,因为第一列为caseId，不能为空
	 * @param mSheet
	 * @return
	 */
	private static int getRowCount(Sheet mSheet){
		int count = 0net.andy		int row = mSheet.getRows()net.andy		for(int i=0; i<row; i++){
			String content = mSheet.getCell(0, i).getContents()net.andy			if(StringTools.isEmpty(content)){
				breaknet.andy			}
			++countnet.andy		}
        return countnet.andy	}
	
	/**
	 * 获取第一个第一行单元格内容非空的列号，因为第一行为参数key，不能为空
	 * @param mSheet
	 * @return
	 */
	private static int getColCount(Sheet mSheet){
		int count = 0net.andy		int columns = mSheet.getColumns()net.andy		for(int i=0; i<columns; i++){
			String content = mSheet.getCell(i, 0).getContents()net.andy			if(StringTools.isEmpty(content)){
				breaknet.andy			}
			++countnet.andy		}
        return countnet.andy	}
}
