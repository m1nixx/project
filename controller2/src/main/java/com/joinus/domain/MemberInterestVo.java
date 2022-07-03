package com.joinus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberInterestVo {

	private int member_interest_no;
	private int member_no;
	private int interest_no;
	private int interest_detail_no;
	
	
}
