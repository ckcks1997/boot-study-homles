package com.example.bootstudyhomles.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

  private int notice_num;
  private String nickname_to;
  private String nickname_from;
  private String info;
  private String info2;
  private int isread;
  private Date regdate;
  
}
