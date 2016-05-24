package main.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageService {
		public static HashMap<String, Message> messages = new HashMap<String, Message>();
		 
		private static int nextMessageId = 0;

		static {
			
			Message message1 = new Message("1", "hello", "john");
			Message message2 = new Message("2", "world", "ted");
			Message message3 = new Message("3", "rest", "sam");
			messages.put("1", message1);
			messages.put("2", message2);
			messages.put("3", message3);
		    
			nextMessageId = 4;
		}
		
		public static List<Message> findAll() {
		    return new ArrayList<Message>(messages.values());
		}
		
		public static Message find(String id) {
		    return messages.get(id);
		}
		
		public static void save(Message message) {
		    if (message.getId() == null) {
		        String id = String.valueOf(nextMessageId);
		        message.setId(id);
		        nextMessageId++;
		    }
		    messages.put(message.getId(), message);
		}
		
		public static void remove(String id) {
		    messages.remove(id);
		}
}
