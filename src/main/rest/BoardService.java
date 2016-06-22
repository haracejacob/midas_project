package main.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.except.BaseException;
import main.java.logic.commonLogic;
import main.java.logic.boardLogic;
import main.java.vo.boardVO;

public class BoardService {
	public static HashMap<String, boardVO> messages = new HashMap<String, boardVO>();
	private static int nextMessageId = 0;
	private static List<boardVO> boardList = null;
	
	static {
		boardList = new ArrayList<boardVO>();
		
	    try {
			boardList = boardLogic.getBoardList();
			nextMessageId = commonLogic.getCurrentAI("board");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    for(int i=0; i<boardList.size(); i++)
	    	messages.put(Integer.toString(boardList.get(i).getBoard_seq()), boardList.get(i));
	}
	
	public static List<boardVO> findAll() {
		return new ArrayList<boardVO>(messages.values());
	}
	
	public static boardVO find(String id) {
		return messages.get(id);
	}
	
	public static void update(boardVO message) throws SQLException, BaseException {
		//boardLogic.updateboard(message);
		System.out.println(message);
		messages.put(Integer.toString(message.getBoard_seq()), message);
	}
	
	public static void save(boardVO message) throws BaseException, SQLException {
	    System.out.println(message);
		if (message.getBoard_seq() == 0) {
	        String id = String.valueOf(nextMessageId);
	        message.setBoard_seq(Integer.parseInt(id));
	        nextMessageId++;
	        
		    boardLogic.insertBoard(message);
	    }
	    else {
	    	
	    	//boardLogic.setboard(message);
	    }
	    messages.put(Integer.toString(message.getBoard_seq()), message);
	}
	
	public static void remove(String id) throws BaseException {
		int seq = Integer.parseInt(id);
		boardLogic.deleteBoard(seq);
		messages.remove(id);
	}
}
