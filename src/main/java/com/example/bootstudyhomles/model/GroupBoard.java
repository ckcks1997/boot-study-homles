package com.example.bootstudyhomles.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupBoard {

	  private int board_num;
	  private int s_board_num;
	  private String title;
	  private String content;
	  private int replycnt;
	  private String boardid;
	  private Date regdate;
	  private String file1;
	  private int readcnt;
	  private String nickname;
	  private String picture;
	  
}
