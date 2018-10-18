<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
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
<pre>
	이름 : ${param.userName }
	취미 : <%
				String[] hobbys = request.getParameterValues("userHobby");
				for(String hobby : hobbys){
					out.println(hobby);
				}
			%>
	혈액형 : <%
				List<String> hello = new ArrayList();
				hello.add("안뇽화쒜요우");
				hello.add("방가워요우");
				hello.add("이름이 뭐에요?? 전화번호 뭐에요??");
				
				request.setAttribute("hello", hello);
				
				
				String blood = request.getParameter("userBlood");
				out.println(blood);
				StringBuilder path = new StringBuilder();
				
				switch(blood){
				case "A" : 
					out.println("A형입니다.");

					break;
				case "B" : 
					out.println("B형입니다.");
					break;
				case "O" : 
					out.println("O형입니다.");
					break;
				case "AB" : 
					out.println("AB형입니다.");
					break;
				default :
					out.println("모르는 혈액형이에요;;");
				}
				
				//path = request.getContextPath() + "/07/" + blood + ".jsp?blood=" + blood;
				path.setLength(0);
				path = new StringBuilder();
				
				path.append(request.getContextPath());
				path.append("/07/");
				path.append(request.getParameter("userBlood"));
				path.append(".jsp");
		
				out.println(path.toString());
// 				response.sendRedirect(path.toString());	// sendRedirect 는 경로를 바꿔주는 역할을 한다.
			%>
			<jsp:forward page="<%=path.toString() %>"></jsp:forward>	
<!-- 			forward URL은 그대로 놔두고 해당 경로의 데이터를 해석해서 얻어온다. -->
</pre>

</body>
</html>