package com.joinus.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
		
		// http://localhost:8088/club/new  모임등록 페이지
		// http://localhost:8088/club/new?member_no=1
		@RequestMapping(value="/new", method = RequestMethod.GET)
		public String createClubGet(Model model, HttpSession session) {
			
			session.setAttribute("member_no",7);
			
			//회원X = 로그인화면 이동 
			Integer member_no = (Integer)session.getAttribute("member_no");
			if(member_no == null) {
				return "redirect:/club/info";
//				return "redirect:/members/signin";
			}
			
			//회원 이름 출력
			MemberVo membervo = service.getMember(member_no);
			model.addAttribute("membervo", membervo);
			//회원 관심사 출력
			InterestVo interestvo = service.getMemberInterest(member_no);
			model.addAttribute("interest", interestvo);
			 
			 return "/club/new";
		}
	
		
		// 상세관심사 가져오기
		// http://localhost:8088/club/getdetail
		@ResponseBody
		@RequestMapping(value="/getdetail", method = RequestMethod.GET)
		public List<InterestDetailsVo> test(@RequestParam("itemNum") int itemNum) {
			//ajax로 받아온 관심사를 상세관심사 리스트로 출력
			List<InterestDetailsVo> detailList = service.getDetailName(itemNum);
			return detailList;
		}
		
		
		
		// 모임등록
		@RequestMapping(value="/new", method = RequestMethod.POST)
		public String createClubPost(@RequestParam("interest_detail_name") String detail,@RequestParam("member_no") int member_no,ClubVo clvo,Model model,HttpSession session) {
		
			/* MultipartFile file, HttpServletRequest request
			 * //모임 사진등록 if(file != null) { // 파일이 업로드 될 경로 설정 String saveDir =
			 * request.getSession().getServletContext().getRealPath(
			 * "/resources/upload/clubImg"); //위에서 설정한 경로의 폴더가 없을 경우 생성 java.io.File dir =
			 * new java.io.File(saveDir); if(!dir.exists()) { dir.mkdirs(); } String
			 * originalfilename = file.getOriginalFilename(); String ext =
			 * originalfilename.substring(originalfilename.lastIndexOf("."));
			 * 
			 * 
			 * // 이름 값 변경을 위한 설정 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			 * String uuid = UUID.randomUUID().toString();
			 * 
			 * // 파일 이름 변경 String fileName = sdf.format(System.currentTimeMillis()) + "_" +
			 * uuid + ext; clvo.setClub_image(fileName); }
			 * 
			 */
			
			
			  //관심사번호 가져오기
				InterestDetailsVo interDetail = service.getInterestNo(detail); 
		      //클럽정보 저장 + 넘버가져오기 
				service.newClub(clvo); 
				int club_no = clvo.getClub_no();
				System.out.println(club_no);
			  //모임관심사 저장
				service.newClubInterest(club_no, interDetail.getInterest_no(),interDetail.getInterest_detail_no()); 
				model.addAttribute("club_no", club_no);	
			  //모임가입	
				ClubMembers members = new ClubMembers();
				members.setClub_no(club_no);
				members.setMember_no(member_no);
				members.setClub_role_no(2); //모임 첫생성은 관리자
				service.join(members);
			
			model.addAttribute("mNo", member_no);	
			return "redirect:/club/{club_no}";
		}
		
		
		// http://localhost:8088/club/21
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
			
			//회원번호
			session.setAttribute("member_no", 56);
			model.addAttribute("member_no", (int)session.getAttribute("member_no"));
			
			//클럽회원정보
			List<ClubMembers> clubmemberVO = service.getClubMembers(club_no);
			model.addAttribute("clubmemberVO", clubmemberVO);
			
			//모임관심사 정보로 관심사 가져오기
			
			return "/club/info";
		}
		
		@ResponseBody
		@RequestMapping(value = "/join/{club_no}",method=RequestMethod.GET)
		public void joinClub(@PathVariable("club_no") int club_no, @RequestParam("member_no") int member_no) {
			
			ClubMembers members = new ClubMembers();
			members.setClub_member_no(member_no);
			members.setClub_no(club_no);
			members.setClub_role_no(1);
			service.join(members);
			System.out.println("모임가입완료");
			
		}
		
		
}
