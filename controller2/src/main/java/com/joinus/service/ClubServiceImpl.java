package com.joinus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.joinus.domain.ClubVo;
import com.joinus.domain.InterestDetailsVo;
import com.joinus.domain.InterestVo;
import com.joinus.domain.MemberVo;
import com.joinus.persistence.ClubDao;

@Service
public class ClubServiceImpl implements ClubService {

	@Inject
	private ClubDao dao;
	
	
	
	@Override
	public MemberVo getMember(Integer num) {
		return dao.getMember(num);
	}
	
	@Override
	public InterestVo getMemberInterest(Integer num) {
		return dao.interest(num);
	}

	@Override
	public List<InterestDetailsVo> getDetailName(Integer num) {
		return dao.getDetailName(num);
	}

	@Override
	public void createClub(ClubVo vo) {
		dao.createClub(vo);
	}

	@Override
	public ClubVo getClubInfo(Integer num) {
		return dao.getClubInfo(num);
	}



	

}
