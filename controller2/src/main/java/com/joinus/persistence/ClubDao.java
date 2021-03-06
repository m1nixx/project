package com.joinus.persistence;

import java.util.List;
import java.util.Map;

import com.joinus.domain.ClubGradesVO;
import com.joinus.domain.ClubMembers;
import com.joinus.domain.ClubVo;
import com.joinus.domain.InterestDetailsVo;
import com.joinus.domain.InterestVo;
import com.joinus.domain.MemberVo;

public interface ClubDao {

	
	//회원정보 가져오기 - memberDao꺼 사용가능 기능확인 위해서 임의로 만듦
	public MemberVo getMember(Integer num);
	//회원관심사 가져오기 - memberDao꺼 사용가능 기능확인 위해서 임의로 만듦
	public InterestVo interest(Integer num);
	
	//회원이 선택한 관심사의 세부관심사리스트 가져오기
	public List<InterestDetailsVo> getDetailName(Integer num);
	
	//모임생성
	public void newClub(ClubVo vo);
	//회원이 선택한 관심사 넘버값 가져오기
	public InterestDetailsVo getInterestNo(String name);
	//모임관심사 저장
	public void newClubInterest(Integer club_no, Integer interest_no,Integer interest_detail_no);
	
	//모임가입하기
	public void join(ClubMembers members);
	//모임별점주기
	public void clubGrade(ClubGradesVO vo);
	
	//모임 별점 정보 가져오기
	public List<ClubGradesVO> getClubGrade(Integer num);
	//모임 별점 평균값, 참여자 수 가져오기
	public List<Map<String, Integer>> getClubAvgCnt(Integer num);
	
	//모임 정보 가져오기
	public ClubVo getClubInfo(Integer num);
	//모임 회원 정보 가져오기
	public List<ClubMembers> getClubMembers(Integer num);
	
}
