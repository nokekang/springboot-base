package org.spring.springboot.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.spring.springboot.domain.City;
import org.spring.springboot.domain.ExcelData;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/city")
public class ExcelImportController {
	
	private Log log = LogFactory.getLog(ExcelImportController.class);
	
	@Autowired
	private CityService cityService;

    @RequestMapping(value = "/import")
    public String cityimprot(MultipartFile cityFile ) throws IOException{
    	if(null == cityFile ){
    		return "failed";
    	}
    	//判断与解析不能共用流
    	InputStream inputStream = cityFile.getInputStream();
    	InputStream inputStream2 = cityFile.getInputStream();
    	
    	//1.判断版本类型
    	String version = readVersion(inputStream);
    	List<List<String>> data =null;
    	if("2003".equals(version)){
    		data = readXls(inputStream2);
    	}
    	if("2007".equals(version)){
    		data = readXlsx(inputStream2);
    	}
    	
    	return "success";
    }
    
    //解析2007版本excel
    private List<List<String>> readXlsx(InputStream inputStream2) throws IOException {
    	
    	//获取工作簿
    	XSSFWorkbook wb = new XSSFWorkbook(inputStream2);
    	List<List<String>> result = new ArrayList<List<String>>();
    	for (XSSFSheet sheet : wb) {
			if(null==sheet){
				continue;
			}
    		for (Row row : sheet) {
    			if(null==row){
					continue;
				}
    			List<String> rowList = new ArrayList<String>();
    			int firstCellNum = row.getFirstCellNum();
    			System.out.println(firstCellNum);
    			int lastCellNum = row.getLastCellNum();
    			System.out.println(lastCellNum);
    			for (int i = firstCellNum; i < lastCellNum; i++) {
					Cell cell = row.getCell(i);
					if(null==cell){
						rowList.add("");
						continue;
					}
					rowList.add(getCellString(cell));
				}
				System.out.println("2007"+rowList.toString());
				System.out.println(rowList.size());
				System.out.println("-------------------------------------");
			}
		}
		return result;
	}
    
	//解析2003版本excel
    private List<List<String>> readXls(InputStream inputStream2) throws IOException {
    	
    	List<List<String>> result = new ArrayList<List<String>>();
    	//获取工作簿
		HSSFWorkbook wb = new HSSFWorkbook(inputStream2);
		//获取sheet数量
		int numberOfSheets = wb.getNumberOfSheets();
		//遍历工作簿
		for (int i = 0; i < numberOfSheets; i++) {
			//获取sheet
			HSSFSheet sheet = wb.getSheetAt(i);
			if(null == sheet){
				//工作簿为空则跳过
				continue;
			}
			//遍历sheet
			for (Row row : sheet) {
				if(null==row){
					continue;
				}
				List<String> rowList = new ArrayList<>();
				int firstCellNum = row.getFirstCellNum();
				int lastCellNum = row.getLastCellNum();
				for (int j = firstCellNum; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					if(null == cell){
						rowList.add("");
						continue;
					}
					rowList.add(getCellString(cell));
				}
				result.add(rowList);
				System.out.println(rowList.toString());
				System.out.println(rowList.size());
			}
		}
		
		return result;
	}
    
    //将cell中的值解析为String
   	private String getCellString(Cell cell) {
   		switch (cell.getCellType()) {
           case Cell.CELL_TYPE_BOOLEAN:
               return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
           case Cell.CELL_TYPE_FORMULA:
               return cell.getCellFormula();
           case Cell.CELL_TYPE_NUMERIC:
               cell.setCellType(Cell.CELL_TYPE_STRING);
               return cell.getStringCellValue();
           case Cell.CELL_TYPE_STRING:
               return cell.getStringCellValue();
           default: 
               return null;
   		}
   	}

	//判断版本
	private String readVersion(InputStream inputStream) {
		if(! inputStream.markSupported()) {
    		inputStream = new PushbackInputStream(inputStream, 8);
		}
		try {
			if(POIFSFileSystem.hasPOIFSHeader(inputStream)) {
				return "2003";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(POIXMLDocument.hasOOXMLHeader(inputStream)) {
				return "2007";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    
}
