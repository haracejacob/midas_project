package main.resources.data;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionManager {
	private static final String resource = "main/resources/data/Configuration.xml";
	
	public static SqlSessionFactory sqlSession;
	public static String environment = "development";
	
	public static SqlSessionFactory getSqlSession() {
		if(sqlSession == null) initSession();
		return sqlSession;
	}
	
	private static synchronized void initSession() {
		Reader reader;
		try {
			reader = Resources.getResourceAsReader( resource );
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			sqlSession = builder.build( reader, environment );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSession() {
		if(sqlSession == null) initSession();
		return sqlSession.openSession();
	}
}