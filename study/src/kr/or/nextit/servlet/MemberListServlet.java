package kr.or.nextit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.member.MemberService;
import kr.or.nextit.member.MemberVo;
import kr.or.nextit.member.impl.MemberServiceImpl;

@WebServlet(name="MemberListServlet", urlPatterns= {"/memberList.next"}, 
			initParams= {
					@WebInitParam(name="test1" , value="valueTest1" ),
					@WebInitParam(name="test2" , value="valueTest2" ),
					@WebInitParam(name="test3" , value="valueTest3" )
					})
public class MemberListServlet extends HttpServlet {
	
	private MemberService memberService;

	@Override
	public void init(ServletConfig config) throws ServletException {

		System.out.println("========== MemberServlet 시작 ===========");
		
		memberService = new MemberServiceImpl();
		
		System.out.println(config.getInitParameter("test"));
		
		Enumeration<String> params = config.getInitParameterNames();
		while(params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			System.out.printf("paramName : %s, paramValue : %s ",paramName, config.getInitParameter(paramName));
			System.out.println();
		}
		
		super.init(config);
		System.out.println("========== MemberServlet 종료 ===========");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		System.out.println("회원 목록 출력 ~~");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>회원 목록</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>회원 목록 입니다.</h1>");
		
		MemberVo memberVo = new MemberVo();
		
		
		try {
			List<MemberVo> memberResult = memberService.getMemberList(memberVo);
			
			out.print("<table class=\"table\" border=\"5\">");
			
			
			out.print("<tbody>");
			for(MemberVo memberItem : memberResult) {
				out.println("<tr>");
				
				
		out.printf("<td> <a href=\"/memberView.next?userId=%s\"> %s </a> </td> \n",
				memberItem.getUserId(),
				memberItem.getUserId());
				
				
				out.printf("<td> %s </td> \n", memberItem.getUserName());
				out.printf("<td> %s </td> \n", memberItem.getUserPw());
				out.printf("<td> %s </td> \n", memberItem.getUserHp());
				out.printf("<td> %s </td> \n", memberItem.getUserEmail());
				out.println("</tr>");
			}
			out.print("</tbody>");
			out.println("</table>");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.printf("error Message : ", e.getMessage());
		}
		
		
		out.println("</body>");
		out.println("</html>");
		
	}
	
}
