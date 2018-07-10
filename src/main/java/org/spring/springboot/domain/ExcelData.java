package org.spring.springboot.domain;

import java.util.List;

public class ExcelData {
	
	/**
	 * 表头,数据列名
	 */
	private List<String> titles;
	
	/**
	 * 数据
	 */
	private List<List<Object>> rows;
	
	/**
	 * 工作表名称
	 */
	private String sheetName;

	@Override
	public String toString() {
		return "ExcelData [titles=" + titles + ", rows=" + rows + ", sheetName=" + sheetName + "]";
	}

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	public List<List<Object>> getRows() {
		return rows;
	}

	public void setRows(List<List<Object>> rows) {
		this.rows = rows;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
}
