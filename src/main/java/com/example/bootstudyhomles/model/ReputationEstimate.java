package com.example.bootstudyhomles.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReputationEstimate {

  private int num;
  private int group_num;
  private String nickname_to;
  private String nickname_from;
  private int score;
  private String info;
  private Date regdate;

}
