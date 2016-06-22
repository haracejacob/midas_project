package main.java.vo;
import lombok.Data;

@Data
public class boardVO {
	private int board_seq;
	private String board_name;
	private String subject;
	private String board_contents;
	private String create_date;
	private String update_date;
}
