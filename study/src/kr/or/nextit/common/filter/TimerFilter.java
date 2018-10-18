package kr.or.nextit.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="TimerFilter", urlPatterns= {"/","/*"}, description="프로세스 실행 시간 체크") 
public class TimerFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// 메모리에 올라올 때
		// 초기화해야할 자원이 있으면...
		System.out.println("TimerFilter INIT 메서드 호출");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 주의 : req 타입이 ServletRequest이므로 HttpServletRequest로
		// 형 변환해서 작업을 해야 할 때도 있습니다.
		HttpServletRequest myreq = (HttpServletRequest) req;
		
		// 전처리 부분
		long startTime = System.currentTimeMillis();
		System.out.println("TimerFilter" + myreq.getRequestURI() + ", 시작 : " + startTime);
		
		req.setAttribute("title", "넥스트 IT (Filter Test)");
		req.setAttribute("loginTitle", "로그인 Filter");
		req.setAttribute("title2", "chain.doFilter 이후 테스트");
		// 요기가 포인트 ****
		chain.doFilter(req, resp);
		
		
		long endTime = (System.currentTimeMillis() - startTime);
		// 후처리 부분
		System.out.println("TimerFilter" + myreq.getRequestURI() + ", 걸린시간 : " + endTime);
		
	}
	
	@Override
	public void destroy() {
		// 메모리에서 내려갈때
		// 대체적으로 init 설정한 자원을 정리해야 하는 경우
		System.out.println("TimerFilter destroy 메서드 호출, 나 죽어요!!");
	}

}
