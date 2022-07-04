package com.joinus.persistence;

import java.util.List;

import com.joinus.domain.ClubInterestVO;
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
	//회원이 입력한 클럽정보 저장하기
	public Integer createClubInfo(ClubVo vo);
	//회원이 입력한 클럽관심사 저장하기
	public void createClubInter(Integer num, String name);
	//모임 정보 가져오기
	public ClubVo getClubInfo(Integer num);
	//
	
}
