package com.example.bootstudyhomles.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Community {
	private int board_num;
	private String title;
	private String content;
	private String boardid;
	private Date regdate;
	private String ip;
	private int readcnt;
	private String nickname;
	private int replycnt;
	private String picture; //left join 할때 사용
		
	public Community() {
		
	}

	public Community(int board_num, String title, String content, String boardid, Date regdate, String ip, int readcnt,
			String nickname, int replycnt, String picture) {
		super();
		this.board_num = board_num;
		this.title = title;
		this.content = content;
		this.boardid = boardid;
		this.regdate = regdate;
		this.ip = ip;
		this.readcnt = readcnt;
		this.nickname = nickname;
		this.replycnt = replycnt;
		this.picture = picture;
	}


}
