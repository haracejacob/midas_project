package main.rest;

import lombok.Data;

@Data
public class Message {
	private String id;
    private String text;
    private String author;
    
    public Message() {
        super();
    }

    public Message(String id, String text, String author) {
        super();
        this.id = id;
        this.text = text;
        this.author = author;
    }
    
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
