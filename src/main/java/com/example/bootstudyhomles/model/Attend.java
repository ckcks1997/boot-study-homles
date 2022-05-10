package com.example.bootstudyhomles.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Attend {

  private String id;
  
  private String gift;
  
  
  public Attend() {}




public Attend(String id, String gift) {
	super();
	this.id = id;
	this.gift = gift;
}
  
  
  
  
}
