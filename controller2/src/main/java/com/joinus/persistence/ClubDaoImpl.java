package com.joinus.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.joinus.domain.ClubGradesVO;
import com.joinus.domain.ClubMembers;
import com.joinus.domain.ClubVo;
import com.joinus.domain.InterestDetailsVo;
import com.joinus.domain.InterestVo;
import com.joinus.domain.MemberVo;

@Repository
public class ClubDaoImpl implements ClubDao{

	@Inject
	private SqlSession session;
	@Inject
	private static final String NAMESPACE="com.joinus.mapper.clubMapper";
		
	
	//회원정보 가져오기
	@Override
	public InterestVo interest(Integer num) {
		return session.selectOne(NAMESPACE+".getMemberInterest",num);
	}
	//회원관심사 가져오기
	@Override
	public MemberVo getMember(Integer num) {
		return session.selectOne(NAMESPACE+".getMember", num);
	}
	
	//회원이 선택한 관심사의 세부관심사리스트 가져오기
	@Override
	public List<InterestDetailsVo> getDetailName(Integer num) {
		return session.selectList(NAMESPACE+".getInterestNameDetails", num);
	}

	
	//회원이 입력한 클럽정보 저장하기
	@Override
	public void newClub(ClubVo vo) {
		session.insert(NAMESPACE+".createClub", vo);
	}
	//회원이 선택한 관심사 넘버값 가져오기
	@Override
	public InterestDetailsVo getInterestNo(String name) {
		return session.selectOne(NAMESPACE+".getInterestNo", name);
	}
	//회원이 입력한 클럽관심사 저장하기
	@Override
	public void newClubInterest(Integer club_no, Integer interest_no, Integer interest_detail_no) {
		Map<String, Integer> num = new HashMap<String, Integer>();
		num.put("club_no", club_no);
		num.put("interest_no", interest_no);
		num.put("interest_detail_no", interest_detail_no);
		
		session.insert(NAMESPACE+".createClubInterest", num);
	}

	
	//모임가입하기
	@Override
	public void join(ClubMembers members) {
		session.insert(NAMESPACE+".joinMembers",members);
	}



	@Override
	public ClubVo getClubInfo(Integer num) {
		return session.selectOne(NAMESPACE+".getClubInfo", num);
	}
	@Override
	public List<ClubMembers> getClubMembers(Integer num) {
		return session.selectList(NAMESPACE+".getClubMember", num);
	}
	
	//별점주기
	@Override
	public void clubGrade(ClubGradesVO vo) {
		session.selectList(NAMESPACE+".clubGrade", vo);		
	}
	//별점정보 가져오기
	@Override
	public List<ClubGradesVO> getClubGrade(Integer num) {
		return session.selectList(NAMESPACE+".getClubGrade", num);
	}
	//별점 평균값,참여자수 가져오기
	@Override
	public List<Map<String, Integer>> getClubAvgCnt(Integer num) {
		List<Map<String, Integer>> list = session.selectList(NAMESPACE+".getGradeOption", num);
		return list;
	}
	
	
}
