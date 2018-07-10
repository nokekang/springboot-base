<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
</head>
<body>
	主页
	<form action="/city/export.do" id="exportForm">
		<button onclick="cityExport()" >导出城市</button>
	</form>
	<br>
	<a href="/template/城市导入模板.xlsx">导入模板下载</a>
	<form action="/city/import.do" id="importForm" enctype="multipart/form-data" method="post" >
		<label>导入</label><input type="file" name="cityFile">
		<input type="submit" />
	</form>
	
</body>
<script type="text/javascript">
	function cityExport(){
		$("#exportForm").submit();
	}
</script>


</html>