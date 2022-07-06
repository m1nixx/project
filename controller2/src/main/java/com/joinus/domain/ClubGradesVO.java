package com.joinus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClubGradesVO {

	private int club_grade_no;
	private int club_no;
	private int member_no;
	private int club_grade_rate;
	
}
