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
public class ClubMembers {

	private int club_member_no;
	private int club_no;
	private int member_no;
	private Timestamp club_member_regdate;
	private int club_role_no;

	
}
