package org.zmf.poi.excel.yangpeng;
/**
 * 网站排名查询：致杨鹏同学
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Main {

	public static void main(String[] args) throws Exception {
		File file = new File("D:\\zmf\\sites.xls");
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("网站排名查询结果");

		int rowIndex = 0;
		{
			Row row = sheet.createRow(rowIndex++);
			Cell cell0 = row.createCell(0, Cell.CELL_TYPE_STRING);
			cell0.setCellValue("网站名称");

			Cell cell1 = row.createCell(1, Cell.CELL_TYPE_STRING);
			cell1.setCellValue("网址（URL）");

			Cell cell2 = row.createCell(2, Cell.CELL_TYPE_NUMERIC);
			cell2.setCellValue("排名:当前");

			Cell cell3 = row.createCell(3, Cell.CELL_TYPE_NUMERIC);
			cell3.setCellValue("排名:一周平均");

			Cell cell4 = row.createCell(4, Cell.CELL_TYPE_NUMERIC);
			cell4.setCellValue("排名:三个月平均");

			Cell cell5 = row.createCell(5, Cell.CELL_TYPE_NUMERIC);
			cell5.setCellValue("独立访问者(人/百万人):当前");

			Cell cell6 = row.createCell(6, Cell.CELL_TYPE_NUMERIC);
			cell6.setCellValue("独立访问者(人/百万人):一周平均");

			Cell cell7 = row.createCell(7, Cell.CELL_TYPE_NUMERIC);
			cell7.setCellValue("独立访问者(人/百万人):三个月平均");
			
			Cell cell8 = row.createCell(8, Cell.CELL_TYPE_NUMERIC);
			cell8.setCellValue("人均页面浏览量(页/人):当前");
			
			Cell cell9 = row.createCell(9, Cell.CELL_TYPE_NUMERIC);
			cell9.setCellValue("人均页面浏览量(页/人):一周平均");
			
			Cell cell10 = row.createCell(10, Cell.CELL_TYPE_NUMERIC);
			cell10.setCellValue("人均页面浏览量(页/人):三个月平均");
			
			
			
		}

		
		File file1 = new File("D:\\zmf\\map.obj") ;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file1)) ;
		HashMap<String, RankingContent> map1 = (HashMap<String, RankingContent>)ois.readObject() ;
		Set<String> keys = map1.keySet() ;
		for(String key:keys){
			RankingContent rc = map1.get(key);
			
			Row row = sheet.createRow(rowIndex++);
			Cell cell0 = row.createCell(0, Cell.CELL_TYPE_STRING);
			cell0.setCellValue(rc.getSiteName());

			Cell cell1 = row.createCell(1, Cell.CELL_TYPE_STRING);
			cell1.setCellValue(rc.getSiteUrl());

			Cell cell2 = row.createCell(2, Cell.CELL_TYPE_STRING);
			cell2.setCellValue(rc.getRanking()[0]);

			Cell cell3 = row.createCell(3, Cell.CELL_TYPE_STRING);
			cell3.setCellValue(rc.getRanking()[1]);

			Cell cell4 = row.createCell(4, Cell.CELL_TYPE_STRING);
			cell4.setCellValue(rc.getRanking()[2]);

			Cell cell5 = row.createCell(5, Cell.CELL_TYPE_STRING);
			cell5.setCellValue(rc.getUniqueVisitors()[0]);

			Cell cell6 = row.createCell(6, Cell.CELL_TYPE_STRING);
			cell6.setCellValue(rc.getUniqueVisitors()[1]);

			Cell cell7 = row.createCell(7, Cell.CELL_TYPE_STRING);
			cell7.setCellValue(rc.getUniqueVisitors()[2]);
			
			Cell cell8 = row.createCell(8, Cell.CELL_TYPE_STRING);
			cell8.setCellValue(rc.getAveragePageViews()[0]);
			
			Cell cell9 = row.createCell(9, Cell.CELL_TYPE_STRING);
			cell9.setCellValue(rc.getAveragePageViews()[1]);
			
			Cell cell10 = row.createCell(10, Cell.CELL_TYPE_STRING);
			cell10.setCellValue(rc.getAveragePageViews()[2]);
			
		}
		ois.close() ;
		

		workbook.write(new FileOutputStream(file));

	}
}
