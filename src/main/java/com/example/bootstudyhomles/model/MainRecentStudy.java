package com.example.bootstudyhomles.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainRecentStudy {

	private String title;
	private String nickname;
	private int board_num;
	private int menuid;
	private int pernum; //최대인원수
	private int count; //현재인원
	private String region;
	private int point;
}
