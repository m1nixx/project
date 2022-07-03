package com.joinus.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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
		
	@Override
	public InterestVo interest(Integer num) {
		return session.selectOne(NAMESPACE+".getMemberInterest",num);
	}
	
	@Override
	public MemberVo getMember(Integer num) {
		return session.selectOne(NAMESPACE+".getMember", num);
	}
	

	@Override
	public List<InterestDetailsVo> getDetailName(Integer num) {
		return session.selectList(NAMESPACE+".getInterestNameDetails", num);
	}

	@Override
	public void createClub(ClubVo vo) {
		session.insert(NAMESPACE+".createClub", vo);
	}

	@Override
	public ClubVo getClubInfo(Integer num) {
		return session.selectOne(NAMESPACE+".getClubInfo", num);
	}

	


}
