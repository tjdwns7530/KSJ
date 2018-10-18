<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<jsp:include page="/inc/lib.jsp"/>
</head>
<body>
	<jsp:include page="/inc/navbar.jsp"/>

<!-- 	<div class="container"> -->
<!-- 		<table class="table"> -->
<%-- 			<c:forEach var="j" begin="1" end="9"> --%>
<!-- 				<tr> -->
<%-- 					<c:forEach var="i" begin="2" end="9"> --%>
<%-- 							<td>${i } * ${j} = ${i * j }</td> --%>
<%-- 					</c:forEach> --%>
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>
<!-- 		</table> -->
<!-- 	</div> -->

	<div class="container">
		<table class="table">
			<tr>
			<c:forEach var="i" begin="1" end="10">
				<td>
					<table class="table">
						<c:forEach var="j" begin="1" end="10">
							<tr>
								<td>${i } * ${j} = ${i * j }</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</c:forEach>
			</tr>
		</table>
	</div>




</body>
</html>