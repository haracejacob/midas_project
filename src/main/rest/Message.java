package main.rest;

import lombok.Data;

@Data
public class Message {
	private String msg_id;
    private String text;
    private String author;
    
    public Message() {
        super();
    }

    public Message(String id, String text, String author) {
        super();
        this.msg_id = id;
        this.text = text;
        this.author = author;
    }
    
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((msg_id == null) ? 0 : msg_id.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        if (msg_id == null) {
            if (other.msg_id != null)
                return false;
        } else if (!msg_id.equals(other.msg_id))
            return false;
        return true;
    }
}
