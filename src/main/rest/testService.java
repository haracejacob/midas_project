package main.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.except.BaseException;
import main.java.logic.commonLogic;
import main.java.logic.testLogic;
import main.java.vo.testVO;

public class testService {
	public static HashMap<String, testVO> messages = new HashMap<String, testVO>();
	private static int nextMessageId = 0;
	private static List<testVO> testList = null;
	
	static {
		testList = new ArrayList<testVO>();
		
	    try {
			testList = testLogic.getTestList();
			nextMessageId = commonLogic.getCurrentAI("usercore");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    for(int i=0; i<testList.size(); i++)
	    	messages.put(Integer.toString(testList.get(i).getSeq()), testList.get(i));
	}
	
	public static List<testVO> findAll() {
		return new ArrayList<testVO>(messages.values());
	}
	
	public static testVO find(String id) {
		return messages.get(id);
	}
	
	public static void update(testVO message) throws SQLException, BaseException {
		//testLogic.updateTest(message);
		System.out.println(message);
		messages.put(message.getTest_id(), message);
	}
	
	public static void save(testVO message) throws BaseException, SQLException {
	    System.out.println(message);
		if (message.getSeq() == 0) {
	        String id = String.valueOf(nextMessageId);
	        message.setSeq(Integer.parseInt(id));
	        nextMessageId++;
	        
		    testLogic.insertTest(message);
	    }
	    else {
	    	
	    	//testLogic.setTest(message);
	    }
	    messages.put(message.getTest_id(), message);
	}
	
	public static void remove(String id) throws BaseException {
		int seq = Integer.parseInt(id);
		testLogic.deleteTest(seq);
		messages.remove(id);
	}
}
