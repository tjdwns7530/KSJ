package kr.or.nextit.function.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.nextit.common.error.BizException;
import kr.or.nextit.function.service.ComCodeService;
import kr.or.nextit.function.service.CommCodeVo;
import kr.or.nextit.jdbc.ConnectionPool;
import kr.or.nextit.jdbc.MybatisSqlSessionFactory;

/**
 * 비즈니스 레이어 영역으로 데이터 베이스 연결 객체를 생성을 하고, 요청된 param 값을 가지고 제어 해주는 비즈니스 플로우....
 * 
 * @author pc11
 *
 */
public class ComCodeServiceImpl implements ComCodeService {

	private SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();

	/**
	 * 커넥션 풀 객체 인스턴스 생성
	 */
	private ConnectionPool pool = ConnectionPool.getInstance();

	/**
	 * 데이터 레이어 영역의 객체 인스턴스 생성
	 */
	private ComCodeServiceDao codeDao = ComCodeServiceDao.getInstance();

	@Override
	public List<CommCodeVo> getCodeList(String groupId) {

		Connection conn = null;

		try {
			conn = pool.getConnection();
			return codeDao.selectCodeList(conn, groupId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return null;
			return new ArrayList<>();
		} finally {
			if (conn != null) {
				try {
					pool.releaseConnection(conn);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}

			}
		}

	}

	@Override
	public CommCodeVo getCodeName(String codeKey) {

		Connection conn = null;

		CommCodeVo codeVo = null;
		try {
			conn = pool.getConnection();
			codeVo = codeDao.selectCodeName(conn, codeKey);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					pool.releaseConnection(conn);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}

		return codeVo;
	}

	@Override
	public void getErrorTest(String testValue) throws RuntimeException {

		System.out.println(String.format("전 = %s ", testValue));

		int num;
		try {

			num = Integer.parseInt(testValue);
			System.out.println(String.format("후 = %d ", num));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("문자열을 정수로 변경하다 에러 발생");
		}

	}

	@Override
	public void getErrorTest2(String testValue) throws Exception {

		System.out.println(String.format("전 = %s ", testValue));

		try {

			int num = Integer.parseInt(testValue);
			System.out.println(String.format("후 = %d ", num));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("문자열을 정수로 변경하다 에러 발생");
		}

	}

	@Override
	public void getBizErrorTest(String testValue) throws BizException {

		System.out.println(String.format("전 = %s ", testValue));

		try {

			int num = Integer.parseInt(testValue);
			System.out.println(String.format("후 = %d ", num));

		} catch (Exception e) {
			e.printStackTrace();
			// throw new BizException();
			// throw new BizException("담당자에게 연락을 하세요!!");
			// throw new BizException(
			// String.format("비즈니스 로직 error : %s", e.getMessage())
			// );
			// throw new BizException("error 발생", e);
			throw new BizException(e.getMessage(), e);

		}

	}

	public void getBizErrorTest2(String testValue) throws Exception {

		int num = Integer.parseInt(testValue);
		System.out.println(String.format("후 = %d ", num));

	}

	private ComMapper comMapper;

	@Override
	public List<CommCodeVo> getIpList() throws Exception {

		SqlSession session = null;

		try {
			// sqlSessionFactory 에서 session 객체 인스턴스 생성.
			// db 접근 pool 에서 session 가지고 와서 생성
			session = sqlSessionFactory.openSession();
			// comMapper 객체 타입에 맞춰서 comMapper.xml 구현체를
			// 인스턴스 생성
			comMapper = session.getMapper(ComMapper.class);
			// params type(CommCodeVo) param 값을 설정
			CommCodeVo params = new CommCodeVo();
			params.setKey("IP");

			// comMapper.xml getIpList 실행한 결과를 반환
			List<CommCodeVo> ipList = comMapper.getIpList(params);
			return ipList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public CommCodeVo getIpItem(String clientIp) throws Exception {

		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();

			comMapper = session.getMapper(ComMapper.class);

			CommCodeVo param = new CommCodeVo();
			param.setKey(clientIp);
			
			// 최초 등록된 아이피 조회
			CommCodeVo resultIpInfo = comMapper.getIpItem(param);
			
			if(resultIpInfo == null) {
				// 아이피가 등록 되어 있지 않으면 등록
				comMapper.insertIpItem(param);
				session.commit();
			}
				// 등록된 아이피를 다시 조회해서 결과 반환
			return comMapper.getIpItem(param);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			//return new CommCodeVo();
		} finally {
			if(session != null) {
				try {
					session.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

}
