package main.java.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import main.resources.data.SqlSessionManager;
import main.java.except.BaseException;

import org.apache.ibatis.session.SqlSession;

import main.java.vo.todoVO;
import main.java.vo.userVO;

public class todoLogic {

	public static List<todoVO> getTodoList() throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		List<todoVO> result;

		try {
			result = sqlSession.selectList("todo.GetTodoList");
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
	
	public static List<todoVO> getTodoList(int seq) throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		List<todoVO> result;

		try {
			result = sqlSession.selectList("todo.GetMyTodoList", seq);
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
	
	public static void setTodo(todoVO todo) throws BaseException, SQLException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
		conn.setAutoCommit(false);

		try {
			sqlSession.update("todo.UpdateTodo", todo);
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

	public static void insertTodo(todoVO todoElement) throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
		
		Date date = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-M-dd");
		todoElement.setStart_date(sdformat.format(date));
		try {
			sqlSession.insert("todo.InsertTodo", todoElement);
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

	public static void deleteTodo(int seq) throws BaseException {
		SqlSession sqlSession = SqlSessionManager.getSession();	
		
		try {
			sqlSession.delete("todo.DeleteTodo", seq);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.close();
			throw new BaseException("DB에러입니다.");
		}
		sqlSession.close();
	}
	
	public static void setComplete(int todo_seq, int complete) throws BaseException, SQLException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
		conn.setAutoCommit(false);

		HashMap<String, Object> param = new HashMap<String, Object>();
		
		param.put("seq", todo_seq);
		param.put("complete", complete);
		
		try {
			sqlSession.update("todo.UpdateProperty", param);
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

	public static void setBookmark(int todo_seq, int bookmark) throws BaseException, SQLException {
		SqlSession sqlSession = SqlSessionManager.getSession();
		Connection conn = sqlSession.getConnection();
		conn.setAutoCommit(false);	
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		param.put("seq", todo_seq);
		param.put("bookmark", bookmark);
		try {
			sqlSession.update("todo.UpdateProperty", param);
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
}
