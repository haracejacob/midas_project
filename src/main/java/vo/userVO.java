package main.java.vo;
import lombok.Data;

@Data
public class userVO {
	private int user_seq;
	private String user_name;
	private String user_passwd;
	private String user_email;
	private String user_phone;
}
