package main.java.logic;

import java.sql.Connection;
import java.sql.SQLException;
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
			throw new BaseException("DB에러입니다.");
		}
		sqlSession.close();
		if (result == null) {
			throw new BaseException("존재하지 않습니다..");
		}

		return result;
	}
	public static void setTest(testVO test) throws BaseException, SQLException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
		conn.setAutoCommit(false);

		try {
			sqlSession.update("test.UpdateTest", test);
			conn.commit();
		} catch (Exception e) {
			try {
				if (conn != null) conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println(e);
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		conn.close();
	}
	
	public static void insertTest(testVO testElement) throws BaseException, SQLException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
				
		try {
			sqlSession.insert("test.InsertTest", testElement);
			conn.commit();	
		}
		catch(Exception e) {
			try {
				if(conn != null) conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println(e);
		}
		finally {
			try {	
				if(conn != null) conn.close();
			} 
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void deleteTest(int seq) throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();	
		
		try {
			sqlSession.delete("test.DeleteTest", seq);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.close();
			throw new BaseException("DB에러입니다.");
		}
		sqlSession.close();
	}
}
