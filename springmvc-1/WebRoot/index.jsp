<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
    <a href="helloworld">Hello World</a>
    <br/>
    
    <a href="springmvc/testRequestMapping">RequestMapping</a>
    <br/>
    
    <a href="springmvc/testMethod">Test Method</a>
    <br/>
    
    <form action="springmvc/testMethod" method="post">
    	<input type="submit" value="提交"/>
    </form>
    <br/>
    
    <a href="springmvc/testParamsAndHeaders?username=fjnu&age=10">Test ParamsAndHeaders</a>
    <br/>
    
    <a href="springmvc//testAntPath/fjnu/abc">Test AntPath</a>
    <br/>
    
    <a href="springmvc//testPathVariable/1">Test PathVariable</a>
    <br/>
    
    <a href="springmvc/testRest/1">Test Rest Get</a>
    <br/>
    
    <form action="springmvc/testRest" method="post">
    	<input type="submit" value="TestRest POST"/>
    </form>
    <br/>
    
    <form action="springmvc/testRest/1" method="post">
    	<input type="hidden" name="_method" value="DELETE"/>
    	<input type="submit" value="TestRest DELETE"/>
    </form>
    <br/>
    
    <form action="springmvc/testRest/1" method="post">
    	<input type="hidden" name="_method" value="PUT"/>
    	<input type="submit" value="TestRest PUT"/>
    </form>
    <br/>
  </body>
</html>
