<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
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
    <h4>Success Page</h4>
    
    time : ${requestScope.time}
    <br/><br/>
    
    names : ${requestScope.names}
    <br/><br/>
    
    request user : ${requestScope.user}
    <br/><br/>
    
    session user : ${sessionScope.user}
    <br/><br/>
    
    <fmt:message key="i18n.username"></fmt:message>
    <br/><br/>
    
    <fmt:message key="i18n.password"></fmt:message>
    <br/><br/>
  </body>
</html>
