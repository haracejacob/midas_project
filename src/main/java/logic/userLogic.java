package main.java.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import main.resources.data.SqlSessionManager;
import main.java.except.BaseException;

import org.apache.ibatis.session.SqlSession;

import main.java.vo.userVO;

public class userLogic {

	public static List<userVO> getUserList() throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		List<userVO> result;

		try {
			result = sqlSession.selectList("user.GetUserList");
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
	
	public static void setUser(userVO user) throws BaseException, SQLException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
		conn.setAutoCommit(false);

		try {
			sqlSession.update("user.UpdateUser", user);
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

	public static void insertUser(userVO userElement) throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
				
		try {
			sqlSession.insert("user.InsertUser", userElement);
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

	public static void deleteUser(int seq) throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();	
		
		try {
			sqlSession.delete("user.DeleteUser", seq);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.close();
			throw new BaseException("DB에러입니다.");
		}
		sqlSession.close();
	}

}
