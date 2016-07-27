 package com.xy2401.example.poi;

import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ColorTest {

	public static void main(String[] args)   {
		
		short colorNum = 2;//每行显示的颜色的个数
		short width1 = 2000;//颜色序號宽度
		short width = 6000;//颜色名栏位的宽度
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		CellStyle thStyle = workbook.createCellStyle();
	  
		XSSFSheet sheet = workbook.createSheet("IndexedColors遍历");
		
		Row row =null;
		short i,j,index=0;
		for(i=0;i<colorNum;i++){
			sheet.setColumnWidth((short) i*3 , width1 );
			sheet.setColumnWidth((short) i*3 + 1 , width );
		}

		
		for (IndexedColors c : IndexedColors.values()){
			
			 i=(short) (index/colorNum);
			 j=(short) (index%colorNum);
			 
			 thStyle = workbook.createCellStyle();
			 thStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  //设置前景填充样式  
			 thStyle.setFillForegroundColor(c.getIndex());
 
             row = j==0?sheet.createRow(i):row;//如果到了换行的时候row new一下 否则就当什么都没有发生
		     
//             text="";
//             text += (index + 1000 + "").substring(1,4) +"||";
//             text += (c.getIndex() + 1000 + "").substring(1,4) +"||";
//             text += c;
            
             row.createCell(3*j).setCellValue("  "+(c.getIndex() + 1000 + "").substring(1,4));
			 row.createCell(3*j+1).setCellValue(c+"");
			 
			 row.createCell(3*j+2).setCellStyle(thStyle); 
			 
			 index++;//计数
		}
			   
		 sheet = workbook.createSheet("30X"+colorNum+"颜色遍历");
		 
		 for( i=0;i<30;i++ ){
			 row = sheet.createRow(i); 
			 for(j=0;j<colorNum;j++){
				 
				 thStyle = workbook.createCellStyle();
				 thStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  //设置前景填充样式  
				 thStyle.setFillForegroundColor((short)(colorNum*i + j));
		
				 row.createCell(2*j).setCellValue((short)(colorNum*i + j));
				 row.createCell(2*j+1).setCellStyle(thStyle); 
			 }			 
		 }
         
		 FileOutputStream fileOut = null;
		  try {
				fileOut = new FileOutputStream("POI颜色大全.xlsx");
				workbook.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				System.out.println("保存xlsx的时候发生的异常");
				e.printStackTrace();
			}
 

	}

}
