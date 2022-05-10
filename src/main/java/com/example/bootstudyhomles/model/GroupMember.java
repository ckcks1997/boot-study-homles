package com.example.bootstudyhomles.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMember {

  private int group_member;
  private int boardnum;
  private String nickname;
  private Date regdate;

 
}
