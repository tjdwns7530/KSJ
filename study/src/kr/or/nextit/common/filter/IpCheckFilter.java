package kr.or.nextit.common.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.nextit.function.service.ComCodeService;
import kr.or.nextit.function.service.CommCodeVo;
import kr.or.nextit.function.service.impl.ComCodeServiceImpl;
import kr.or.nextit.login.service.LoginVo;

@WebFilter(filterName = "IpCheckFilter", urlPatterns = { "/test123/*" })
public class IpCheckFilter implements Filter {

	private Map<String, String> ipMap;

	private ComCodeService codeService;

	private List<CommCodeVo> ipList = null;

	@Override
	public void init(FilterConfig arg0) throws ServletException {

		try {
			codeService = new ComCodeServiceImpl();
			ipList = codeService.getIpList();

			for (CommCodeVo commCodeVo : ipList) {
				System.out.println(String.format("Ip key = %s : 승인여부 = %s", commCodeVo.getKey(), commCodeVo.getValue()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 초기화
		ipMap = new HashMap<>();
		ipMap.put("127.0.0.1", "D");
		ipMap.put("192.168.10.2", "A");
		ipMap.put("192.168.10.22", "A");
		ipMap.put("192.168.10.20", "A");

		System.out.println("ipMap data init() 등록된 IP 확인 !!!");
		Set<String> ipkey = ipMap.keySet();
		for (String key : ipkey) {
			System.out.println(String.format("Ip Key = %s : 승인여부 = %s", key, ipMap.get(key)));
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		ipMap = null;

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

//		HttpServletRequest myreq = (HttpServletRequest) req;
//		HttpServletResponse myresp = (HttpServletResponse) resp;
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		LoginVo loginInfo = (LoginVo)session.getAttribute("loginInfo");
		
//		if(loginInfo != null) {
//			// 로그인 됨
//			chain.doFilter(req, resp);
//		} else {
//			// 로그인 안됨
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/13/loginForm.jsp");
//		}

		// 32진수 IPv4 : 127.0.0.1 ,
		String ip = req.getRemoteAddr();
		boolean flag = false;
		
		try {
			CommCodeVo resultCodeVo = codeService.getIpItem(ip);
			if (ip != null && resultCodeVo != null && ip.equals(resultCodeVo.getKey()) && "A".equals(resultCodeVo.getValue()) ) {
				chain.doFilter(req, resp);
					
			}else {
					// 접근 금지
				throw new Exception(String.format(" ' %s ' 사용자는 접근 금지령~~~", ip));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("존재하는 IP 거부 ===============");
			 resp.setCharacterEncoding("utf-8");
			 resp.setContentType("text/html");
			 RequestDispatcher dispatcher = req.getRequestDispatcher("/Deny.jsp");
			 dispatcher.forward(req, resp);
		}
		
		
		// if (ip != null && ipMap.containsKey(ip)) {
		// if ("A".equals(ipMap.get(ip))) {
		// System.out.println("존재하는 IP 승인 ===============");
		// chain.doFilter(req, resp);
		// } else {
		// System.out.println("존재하는 IP 거부 ===============");
		// resp.setCharacterEncoding("utf-8");
		// resp.setContentType("text/html");
		// RequestDispatcher dispatcher = myreq.getRequestDispatcher("/Deny.jsp");
		// dispatcher.forward(req, resp);
		// }
		// } else {
		// System.out.println("존재하지 않는 IP index.jsp ===============");
		// RequestDispatcher dispatcher = myreq.getRequestDispatcher("/Deny.jsp");
		// dispatcher.forward(req, resp);
		// }

	}

}
