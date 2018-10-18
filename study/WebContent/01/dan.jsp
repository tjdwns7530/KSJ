<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>구구단</title>
</head>
<body>

<%
for(int i = 0; i <= 9; i++){
	for(int j = 0; j <= 9 ; j++){
		int dan = (i * j);
		out.print(j + " * " + i + " = " + dan + "   |   " );
	}
	out.print("<br>");
}
%>


1 * 1 = 1<br>
1 * 2 = 2<br>
1 * 3 = 3<br>
1 * 4 = 4<br>
1 * 5 = 5<br>
1 * 6 = 6<br>
1 * 7 = 7<br>
1 * 8 = 8<br>
1 * 9 = 9


<%

%>


</body>
</html>