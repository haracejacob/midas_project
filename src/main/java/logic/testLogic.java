package main.java.logic;

import java.util.List;

import main.resources.data.SqlSessionManager;
import main.java.except.BaseException;

import org.apache.ibatis.session.SqlSession;

import main.java.vo.testVO;



public class testLogic {
	public static List<testVO> getTestList() throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		List<testVO> result;

		try {
			result = sqlSession.selectList("test.GetTest");
		} catch (Exception e) {
			sqlSession.close();
			throw new BaseException("DB�����Դϴ�.");
		}
		sqlSession.close();
		if (result == null) {
			throw new BaseException("�������� �ʽ��ϴ�..");
		}

		return result;
	}
}
