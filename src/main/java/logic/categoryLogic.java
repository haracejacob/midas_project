package main.java.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import main.resources.data.SqlSessionManager;
import main.java.except.BaseException;

import org.apache.ibatis.session.SqlSession;

import main.java.vo.categoryVO;

public class categoryLogic {

	public static List<categoryVO> getCategoryList() throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		List<categoryVO> result;

		try {
			result = sqlSession.selectList("category.GetCategoryList");
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
	
	public static List<categoryVO> getCategoryList(int seq) throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		List<categoryVO> result;

		try {
			result = sqlSession.selectList("category.GetMyCategoryList",seq);
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
	
	public static void setCategory(categoryVO category) throws BaseException, SQLException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
		conn.setAutoCommit(false);

		try {
			sqlSession.update("category.UpdateCategory", category);
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

	public static void insertCategory(categoryVO categoryElement) throws BaseException {
		HashMap<String, Object> param = new HashMap<String, Object>();
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
		
		param.put("category_seq", categoryElement.getCategory_seq());
		param.put("user_seq", categoryElement.getUser_seq());	
		try {
			sqlSession.insert("category.InsertCategory", categoryElement);
			sqlSession.insert("category.InsertCategoryUser", param);
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

	public static void deleteCategory(int seq) throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();	
		
		try {
			sqlSession.delete("category.DeleteCategory", seq);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.close();
			throw new BaseException("DB에러입니다.");
		}
		sqlSession.close();
	}

	public static Integer checkCategorySeq(String code) throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		Integer result;

		try {
			result = sqlSession.selectOne("category.GetCategorySeq",code);
		} catch (Exception e) {
			sqlSession.close();
			throw new BaseException("DB에러입니다.");
		}
		sqlSession.close();

		return result;
	}

	public static void joinCategory(int category_seq, int user_seq) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
		
		param.put("category_seq", category_seq);
		param.put("user_seq", user_seq);
				
		try {
			sqlSession.insert("category.InsertCategoryUser",param);
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
}
