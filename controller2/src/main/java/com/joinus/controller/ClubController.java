package com.joinus.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.joinus.domain.ClubGradesVO;
import com.joinus.domain.ClubMembers;
import com.joinus.domain.ClubVo;
import com.joinus.domain.InterestDetailsVo;
import com.joinus.domain.InterestVo;
import com.joinus.domain.MemberVo;
import com.joinus.service.ClubService;

@Controller
@RequestMapping("/club/*")
public class ClubController {

		@Inject
		private ClubService service;
		
		private static final Logger log = LoggerFactory.getLogger(ClubController.class);
		
		
		// http://localhost:8088/club/new  모임등록 페이지
		// http://localhost:8088/club/new?member_no=7
		@RequestMapping(value="/new", method = RequestMethod.GET)
		public String createClubGet(Model model, /* @ModelAttribute("member_no") int member_no, */ HttpSession session) {
			
			//회원넘버 세션으로 받을 시(넘겨주면 그걸로 받기)
			session.setAttribute("member_no", 7); //세션값 임의생성 
			int member_no =	(int)session.getAttribute("member_no");
			
			//회원 이름 출력
			MemberVo membervo = service.getMember(member_no);
			model.addAttribute("membervo", membervo);
			//회원 관심사 출력
			InterestVo interestvo = service.getMemberInterest(member_no);
			model.addAttribute("interest", interestvo);
			 
			 return "/club/new";
		}
	
		
		// 상세관심사 가져오기(ajax)
		// http://localhost:8088/club/getdetail
		@ResponseBody
		@RequestMapping(value="/getdetail", method = RequestMethod.GET)
		public List<InterestDetailsVo> test(@RequestParam("itemNum") int itemNum) {
			//ajax로 받아온 관심사를 상세관심사 리스트로 출력
			List<InterestDetailsVo> detailList = service.getDetailName(itemNum);
			return detailList;
		}
		
		
		// 모임등록
		@RequestMapping(value = "/new", method = RequestMethod.POST)
		public String createClubPost(@RequestParam("interest_detail_name") String detail,
				@RequestParam("member_no") int member_no, MultipartFile file,
				ClubVo clvo,Model model,HttpSession session,HttpServletRequest request ) throws IOException {
		
				log.info("모임등록 호출");
			
			  //모임 사진등록 
				if(!file.isEmpty()) { 
				
				//가상업로드 폴더 설정
				ServletContext ctx =request.getServletContext();
				String realpath = ctx.getRealPath("/resources/upload/clubs");
				log.info("파일저장경로: " +realpath);
				
				//realpath 경로에 폴더 있는지 확인
				File savePath = new File(realpath);
				if(!savePath.exists()) {
					savePath.mkdirs();
				} //없다면 폴더 만듦
				
				
				//String FileName = file.getOriginalFilename();
				String savedFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				log.info("파일명: "+savedFileName);

				
				String fullpath = realpath;
				fullpath += File.separator + savedFileName;
				File saveFile = new File(fullpath);
				
				
				file.transferTo(saveFile);
				clvo.setClub_image(savedFileName);
				
				
				log.info("사진저장 완료");
				}
			  
				
				  //관심사번호 가져오기 
				  InterestDetailsVo interDetail = service.getInterestNo(detail);
				  //클럽정보 저장 + 넘버가져오기 
				  service.newClub(clvo); 
				  int club_no = clvo.getClub_no();
				  model.addAttribute("club_no", club_no); 
				  //모임관심사 저장
				  service.newClubInterest(club_no,interDetail.getInterest_no(),interDetail.getInterest_detail_no()); 
				  //모임가입
				  ClubMembers members = new ClubMembers(); 
				  members.setClub_no(club_no);
				  members.setMember_no(member_no); 
				  members.setClub_role_no(2); //모임 첫생성은 관리자
				  service.join(members);
				  
				  model.addAttribute("member_no", member_no);
				 
			return "redirect:/club/{club_no}";
		}
		
		
		// http://localhost:8088/club/
		@RequestMapping(value = "/{club_no}", method = RequestMethod.GET)
		public String info(Model model,HttpSession session,@PathVariable("club_no") int club_no) {
			
//			//회원X = 로그인화면 이동 
//			if(member_no == null) {
//				redirect:/club/info;
//			return "redirect:/members/signin";
//			}
			
			//클럽정보
			ClubVo clubvo = service.getClubInfo(club_no);
			model.addAttribute("clubvo", clubvo);
			//System.out.println("클럽정보 가져오기!"+clubvo);
			
			//회원번호
			session.setAttribute("member_no", 44);
			model.addAttribute("member_no", (int)session.getAttribute("member_no"));
			
			//클럽회원정보
			List<ClubMembers> clubmemberVO = service.getClubMembers(club_no);
			model.addAttribute("clubmemberVO", clubmemberVO);
			//System.out.println("클럽 회원정보 가져오기!"+clubmemberVO);
			
			//클럽별점정보
			List<ClubGradesVO> gradevo = service.getClubGrade(club_no);
			model.addAttribute("clubGrade", gradevo);
			//System.out.println("클럽 별점정보 가져오기!"+gradevo);
			
			//클럽별점 평균,참여자수
			model.addAttribute("gradeAvgCnt", service.getClubAvgCnt(club_no));   
			
			
			//모임관심사 정보로 관심사 가져오기
			
			return "/club/info";
		}
		
		@ResponseBody
		@RequestMapping(value = "/join/{club_no}",method=RequestMethod.POST)
		public void joinClub(@PathVariable("club_no") int club_no, @RequestParam("member_no") int member_no) {
			
			ClubMembers members = new ClubMembers();
			members.setMember_no(member_no);
			members.setClub_no(club_no);
			members.setClub_role_no(1);
			service.join(members);
			System.out.println("모임가입완료");
			
		}
		
		@ResponseBody
		@RequestMapping(value = "/grade", method = RequestMethod.POST)
		public void clubGrade(@ModelAttribute ClubGradesVO vo) {
				service.clubGrade(vo);
				System.out.println("별점주기 완료");
		}
}
