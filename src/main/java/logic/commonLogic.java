package main.java.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import main.resources.data.SqlSessionManager;
import main.java.except.BaseException;
import main.java.vo.categoryVO;
import main.java.vo.userVO;


public class commonLogic {
	
	public static int getCurrentAI(String tablename) throws BaseException{
		SqlSession sqlSession = SqlSessionManager.getSession();
		int result;	
		
		try {
			result = sqlSession.selectOne("common.GetCurrentAI",tablename);
		} catch (Exception e) {
			sqlSession.close();
			throw new BaseException("DB접속에러입니다.");
		}
		sqlSession.close();
		
		return result;
	}

	public static userVO loginCheck(String id, String pw) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		SqlSession sqlSession = SqlSessionManager.getSession();
		userVO result = new userVO();
		
		param.put("id", id);
		param.put("pw", pw);
		try {
			result = (userVO) sqlSession.selectOne("common.LoginCheck", param);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		finally {
			sqlSession.close();
		}
		
		return result;
	}
	
	public static String IdCheck(String id) {
		SqlSession sqlSession = SqlSessionManager.getSession();
		String result=null;
		
		try {
			result = sqlSession.selectOne("common.IdCheck", id);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		finally {
			sqlSession.close();
		}
		
		return result;
	}
	
	public static void signUser(String id, String pw) throws BaseException {
		HashMap<String, Object> param = new HashMap<String, Object>();
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
		categoryVO category = new categoryVO();

		param.put("id", id);
		param.put("pw", pw);
		try {
			sqlSession.insert("common.SignUser", param);
			category.setUser_seq(Integer.valueOf((String.valueOf(param.get("user_seq")))));
			category.setCategory_name("ToDo");
			sqlSession.insert("category.InsertCategory",category);
			param.put("category_seq", category.getCategory_seq());
			sqlSession.insert("category.InsertCategoryUser",param);
			category.setCategory_name("Bookmark");
			sqlSession.insert("category.InsertCategory",category);
			param.put("category_seq", category.getCategory_seq());
			sqlSession.insert("category.InsertCategoryUser",param);
			category.setCategory_name("Done");
			sqlSession.insert("category.InsertCategory",category);
			param.put("category_seq", category.getCategory_seq());
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
	
	public static int signUser(userVO user) throws BaseException {
		HashMap<String, Object> param = new HashMap<String, Object>();
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
		categoryVO category = new categoryVO();

		try {
			sqlSession.insert("common.SignUpUser", user);
			param.put("user_seq", user.getUser_seq());
			category.setUser_seq(user.getUser_seq());
			category.setCategory_name("ToDo");
			sqlSession.insert("category.InsertCategory",category);
			param.put("category_seq", category.getCategory_seq());
			sqlSession.insert("category.InsertCategoryUser",param);
			category.setCategory_name("Bookmark");
			sqlSession.insert("category.InsertCategory",category);
			param.put("category_seq", category.getCategory_seq());
			sqlSession.insert("category.InsertCategoryUser",param);
			category.setCategory_name("Done");
			sqlSession.insert("category.InsertCategory",category);
			param.put("category_seq", category.getCategory_seq());
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
		
		return user.getUser_seq();
	}

	public static Integer getCategoryUserConnection(int user_seq, Integer category_seq) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		SqlSession sqlSession = SqlSessionManager.getSession();
		Integer result = 0;
		
		param.put("user_seq", user_seq);
		param.put("category_seq", category_seq);
		try {
			result = (Integer)sqlSession.selectOne("common.IsMemberCheck", param);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		finally {
			sqlSession.close();
		}
		
		return result;
	}
}
