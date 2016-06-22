package main.java.vo;
import lombok.Data;

@Data
public class todoVO {
	private int todo_seq;
	private int user_seq;
	private int category_seq;
	private String category_name;
	private String start_date;
	private String end_date;
	private String contents;
	private int complete;
	private int bookmark;
}
