package com.example.bootstudyhomles.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyMember {
  private String email; 
  private String password; 
  private String nickname; 
  private String name; 
  private String tel; 
  private String picture;
  private Date regdate;
  private int point;
  private String profile_intro;//자기소개

}
