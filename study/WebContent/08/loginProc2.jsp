<%@page import="kr.or.nextit.login.impl.LoginServiceImpl"%>
<%@page import="javax.print.URIException"%>
<%@page import="kr.or.nextit.login.*" %>
<%@ page import="java.net.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	아이디 : ${param.userId }
	<br> 비밀번호 : ${param.userPw }
	<br>
<% LoginService loginService = new LoginServiceImpl();
	loginService.loginCheck(request);

	boolean result = (Boolean)request.getAttribute("result");		// 데이터를 변수에 담아서 사용하고자 할때 이렇게 씀.
	String message = (String)request.getAttribute("message");
	
	out.println(result);
	out.println(message);
	
%>
<!-- 데이터를 화면에 바로 출력할때 이렇게 씀 -->
${result }
${message }
</body>
</html>