package kr.or.nextit.jdbc;

import java.io.IOException;
import java.io.InputStream;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * mybatis session Factory mybatis-config.xml
 * 
 * @author pc11
 *
 */
public class MybatisSqlSessionFactory {

	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		try {
			
			String resource = "/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		}catch (IOException ex) {
			ex.printStackTrace();
		}
	
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}