package kr.or.nextit.function.service;

import java.util.List;

import kr.or.nextit.common.error.BizException;

public interface ComCodeService {

	/**
	 * groudId 를 가지고서 비즈니스 플로우를 처리하는 기능으로
	 * 공통 코드 리스트를 반환 (List<CommCodeVo>)
	 * @param groupId
	 * @return
	 */
	public List<CommCodeVo> getCodeList(String groupId); 
	
	/**
	 * key 값을 넘겨서 CommCodeVo 객체를 반환
	 * @param codeKey
	 * @return
	 */
	public CommCodeVo getCodeName(String codeKey);
	
	public void getErrorTest(String testValue) throws Exception;
	
	public void getErrorTest2(String testValue) throws Exception;
	
	public void getBizErrorTest(String testvalue) throws BizException;
	
	public void getBizErrorTest2(String testvalue) throws Exception;
	
	
	/**
	 * 공통 코드 테이블에서 IP 목록을 가지고 옴
	 * @return
	 * @throws Exception
	 */
	public List<CommCodeVo> getIpList() throws Exception;
	
	/**
	 * 클라이언트 접근 Ip 정보를 조건으로 접근 승인 정보 체크
	 * @return
	 * @throws Exception
	 */
	public CommCodeVo getIpItem(String clientIp) throws Exception;

	
}
