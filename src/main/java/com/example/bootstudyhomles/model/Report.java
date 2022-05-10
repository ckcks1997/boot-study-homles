package com.example.bootstudyhomles.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class Report {

	private int report_num;
	private String nickname;
	private String report_reason;
	private int board_num;
	private String writer_nickname;
	private String board_num_title;
	private Date board_num_regdate;
	


	public Report() {
		super();
	}
	public Report(int report_num, String nickname, String report_reason, int board_num, String writer_nickname,
			String board_num_title, Date board_num_regdate) {
		super();
		this.report_num = report_num;
		this.nickname = nickname;
		this.report_reason = report_reason;
		this.board_num = board_num;
		this.writer_nickname = writer_nickname;
		this.board_num_title = board_num_title;
		this.board_num_regdate = board_num_regdate;
	}


}
