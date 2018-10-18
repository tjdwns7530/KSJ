package kr.or.nextit.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.common.web.IController;

@WebServlet(name="comBoardServlet", urlPatterns= {"*.do"})
public class ComBoardServlet extends HttpServlet {
	
	private Map<String, IController> controllerMap = new HashMap<>();

	@Override
	public void init() throws ServletException {
		
		System.out.println("ComBoardServlet.init()");
		// 클래스패스에서 프로퍼티 파일 검색
		ResourceBundle bundle = ResourceBundle.getBundle("ComBoardMapper");
		
		Enumeration<String> keys = bundle.getKeys();
		
		while(keys.hasMoreElements()) {
			
			// trim() : 빈 공간 띄어 쓰기가 되어 있으면 제거 하고, 해당하는 값을 가지고옴.
			String key = keys.nextElement().trim();
			// key 값에 해당하는 녀석의 value 값을 가지고 옴.
			String value = bundle.getString(key).trim();
			
			try {	// 객체 인스턴스 생성시 에러가 발생할 경우 에러처리를 함.
				
				// class.forName 으로 인터페이스에 해당하는 객체(class)를 찾아서
				// 인스턴스를 생성을 함 (controller 변수에 생성)
				IController controller = (IController) Class.forName(value).newInstance();
				
				// controllerMap 객체에 등록(map.put 키, 생성된 인스턴스 객체)
				controllerMap.put(key, controller);
				
				System.out.printf("%s 의 컨트롤 %s 등록", key, value);
				
			} catch ( ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
				ex.printStackTrace();
				throw new ServletException(ex);
			}
			
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 공통적으로 설정해야 할 부분(인코딩)
		req.setCharacterEncoding("UTF-8");
		
		
		try {

			// 2. 사용자의 요청을 분석(URI 분석)
			String uri = req.getRequestURI();	// "/member/memberList.do"
			System.out.printf("사용자 요청 URI : %s", uri);
			
			// was contextPast 를 제거 해줌
			uri = uri.substring(req.getContextPath().length());
			System.out.printf("변경된 URI : %s" , uri);
			
			Set<String> keys = controllerMap.keySet();

			if(controllerMap.containsKey(uri)) {
				// map 객체에 등록된 키 값으로 해당하는 생성된 인스턴스를 가지고옴.
				IController controller = controllerMap.get(uri);
				String viewPage = controller.process(req, resp);
				
				if(controller.isRedirect()) {
					resp.sendRedirect(viewPage);
				} else {
					RequestDispatcher dispatcher = req.getRequestDispatcher(String.format("/WEB-INF/view%s.jsp",viewPage));
					dispatcher.forward(req, resp);
				}
				
				
			} else {
				// 요청에 대한 컨트롤러가 없는 경우, 404 에러
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);	// 404
			}
			
//			for (String key : keys) {
//				System.out.printf(" key : %s \n", key);
//				if(uri.equals(key)) {
//					IController controller = controllerMap.get(key);
//					controller.process(req, resp);
//				} else {
//					// resp.setStatus(HttpServletResponse.SC_NOT_FOUND);	// 404
//				}
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		super.service(arg0, arg1);
	}
	
}
