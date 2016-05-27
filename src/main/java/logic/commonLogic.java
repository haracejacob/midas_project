package main.java.logic;

import main.java.except.BaseException;
import main.resources.data.SqlSessionManager;

import org.apache.ibatis.session.SqlSession;

public class commonLogic {
	
	public static int getCurrentAI(String tablename) throws BaseException{
		SqlSession sqlSession = SqlSessionManager.getSession();
		int result;	
		
		try {
			result = sqlSession.selectOne("common.GetCurrentAI",tablename);
		} catch (Exception e) {
			sqlSession.close();
			throw new BaseException("DB에러입니다.");
		}
		sqlSession.close();
		
		return result;
	}
}
