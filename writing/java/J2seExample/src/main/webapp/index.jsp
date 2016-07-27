<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Hello World</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    Hello World <br>
	
	<a href="util">工具部分</a>
	<a href="html">html例子</a>
	<a href="style">css例子</a>
	<a href="javascript">javascript例子</a>
	<a href="jsp">jsp例子</a>
	<a href="util/fileList.jsp">文件列表</a>
  </body>
</html>
