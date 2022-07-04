package com.joinus.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClubVo {
	
	private int club_no;
	private String club_name;
	private String club_capacity;
	private String club_content;
	private String club_image;
	private Timestamp club_regdate;
	

}
