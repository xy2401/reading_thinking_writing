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
		
		short colorNum = 2;//ÿ����ʾ����ɫ�ĸ���
		short width1 = 2000;//��ɫ��̖���
		short width = 6000;//��ɫ����λ�Ŀ��
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		CellStyle thStyle = workbook.createCellStyle();
	  
		XSSFSheet sheet = workbook.createSheet("IndexedColors����");
		
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
			 thStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  //����ǰ�������ʽ  
			 thStyle.setFillForegroundColor(c.getIndex());
 
             row = j==0?sheet.createRow(i):row;//������˻��е�ʱ��row newһ�� ����͵�ʲô��û�з���
		     
//             text="";
//             text += (index + 1000 + "").substring(1,4) +"||";
//             text += (c.getIndex() + 1000 + "").substring(1,4) +"||";
//             text += c;
            
             row.createCell(3*j).setCellValue("  "+(c.getIndex() + 1000 + "").substring(1,4));
			 row.createCell(3*j+1).setCellValue(c+"");
			 
			 row.createCell(3*j+2).setCellStyle(thStyle); 
			 
			 index++;//����
		}
			   
		 sheet = workbook.createSheet("30X"+colorNum+"��ɫ����");
		 
		 for( i=0;i<30;i++ ){
			 row = sheet.createRow(i); 
			 for(j=0;j<colorNum;j++){
				 
				 thStyle = workbook.createCellStyle();
				 thStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  //����ǰ�������ʽ  
				 thStyle.setFillForegroundColor((short)(colorNum*i + j));
		
				 row.createCell(2*j).setCellValue((short)(colorNum*i + j));
				 row.createCell(2*j+1).setCellStyle(thStyle); 
			 }			 
		 }
         
		 FileOutputStream fileOut = null;
		  try {
				fileOut = new FileOutputStream("POI��ɫ��ȫ.xlsx");
				workbook.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				System.out.println("����xlsx��ʱ�������쳣");
				e.printStackTrace();
			}
 

	}

}
