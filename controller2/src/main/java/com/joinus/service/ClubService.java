package com.joinus.service;

import java.util.List;

import com.joinus.domain.ClubMembers;
import com.joinus.domain.ClubVo;
import com.joinus.domain.InterestDetailsVo;
import com.joinus.domain.InterestVo;
import com.joinus.domain.MemberVo;

public interface ClubService {
	//회원정보 가져오기
	public MemberVo getMember(Integer num);
	//회원관심사 가져오기
	public InterestVo getMemberInterest(Integer num);
	//회원이 선택한 관심사의 세부관심사리스트 가져오기
	public List<InterestDetailsVo> getDetailName(Integer num);
	
	//회원이 입력한 클럽정보 저장하고 클럽넘버 가져오기
	public void newClub(ClubVo vo);
	//회원이 선택한 관심사 넘버값 가져오기
	public InterestDetailsVo getInterestNo(String name);
	//회원이 입력한 클럽관심사 저장하기
	public void newClubInterest(Integer club_no, Integer interest_no,Integer interest_detail_no);
		
	//모임가입하기
	public void join(ClubMembers members);
		
	
	
	
	//모임 정보 가져오기
	public ClubVo getClubInfo(Integer num);
	
}
