package org.spring.springboot.controller;

import java.io.IOException;
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
import org.apache.poi.ss.usermodel.IndexedColors;
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


@RestController
@RequestMapping(value = "/city")
public class ExcelExportController {
	
	private Log log = LogFactory.getLog(ExcelExportController.class);
	
	@Autowired
	private CityService cityService;

    @RequestMapping(value = "/export")
    public String cityAllExport(HttpServletResponse response) throws UnsupportedEncodingException {
    	//1. httpServletResponse 
    	
    	//2. 封装数据
    	ExcelData excelData = new ExcelData();
    	excelData.setSheetName("城市");//工作表名称
    	List<String> titles = new ArrayList<>();
    	titles.add("ID");
    	titles.add("省份ID");
    	titles.add("名称");
    	titles.add("描述");
    	excelData.setTitles(titles);//表头
    	List<City> cList= cityService.findAll();
    	List<List<Object>> rows = new ArrayList<List<Object>>();
    	for (City city : cList) {
			List<Object> list = new ArrayList<Object>();
			list.add(city.getId());
			list.add(city.getProvinceId());
			list.add(city.getCityName());
			list.add(city.getDescription());
			rows.add(list);
		}
    	excelData.setRows(rows);//列数据
    	
    	try {
			exportExcel(response, excelData);
		} catch (IOException e) {
			return "failed";
		}
    	return "success";
    }

	private void exportExcel(HttpServletResponse response, ExcelData excelData) throws IOException {
		//3. 设置响应头 (浏览器用什么软件可以打开,文件名)
    	response.setHeader("content-Type","application/vnd.ms-excel" );//告诉浏览器用什么软件可以打开此文件
    	SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    	String fileName=dataFormat.format(new Date())+".xlsx";
    	//String fileName=dataFormat.format(new Date())+"xlsx";
    	try {
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
    	
    	//4. 创建excel文档HssFWorkbook
    	XSSFWorkbook wb = new XSSFWorkbook();
    	
    	//5. 创建工作表sheet
    	XSSFSheet sheet = wb.createSheet();
    	
    	//6. 设置单元格样式
    	XSSFCellStyle cellStyle = wb.createCellStyle();
    	//字体
    	XSSFFont font = wb.createFont();
    	font.setFontName("simsun");//字体
    	font.setColor(IndexedColors.RED.index);//颜色
    	cellStyle.setFont(font);
    	cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
    	
    	//7.设置数据
    	List<List<Object>> rows = excelData.getRows();
		for (int i = 0; i < rows.size()+1; i++) {
			//创建行
    		XSSFRow titleRow = sheet.createRow(i);
    		//表头
    		if(i==0){
				List<String> titles = excelData.getTitles();
				for (int j = 0; j < titles.size(); j++) {
    				//创建单元格
    	    		XSSFCell cell = titleRow.createCell(j);
    	    		//设置单元格内容
    	    		cell.setCellValue(titles.get(j));
    	    		cell.setCellStyle(cellStyle);
				}
    		}
    		//行数据
    		else{
        		List<Object> cells = rows.get(i-1);
				for (int j = 0; j < cells.size(); j++) {
					//创建单元格
					XSSFCell cell = titleRow.createCell(j);
					if(cells.get(j)!=null){
						cell.setCellValue(cells.get(j).toString());
					}else{
						cell.setCellValue("");
					}
					cell.setCellStyle(cellStyle);
				}
    		}
		}
		//8. 设置单元格宽度自适应变化
		for (int i = 0; i < excelData.getTitles().size(); i++) {
			sheet.autoSizeColumn(i);
		}
		
		//9. wb.write(out);
		ServletOutputStream out = response.getOutputStream();
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		//10. 一定不能忘了关流	
			out.close();
		}
	}

}
