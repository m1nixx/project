package com.joinus.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
			
			session.setAttribute("member_no", 1);
			
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
		public String createClubPost(ClubVo vo ,MultipartFile file,HttpServletRequest request) {
		
			//모임 사진등록 
			if(file != null) {
				// 파일이 업로드 될 경로 설정
				String saveDir = request.getSession().getServletContext().getRealPath("/resources/upload/clubImg");
				//위에서 설정한 경로의 폴더가 없을 경우 생성
		        java.io.File dir = new java.io.File(saveDir);
		        if(!dir.exists()) {
		            dir.mkdirs();
		        }
		        String originalfilename = file.getOriginalFilename();
				String ext = originalfilename.substring(originalfilename.lastIndexOf("."));
		        		
		        	
						 // 이름 값 변경을 위한 설정
		                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		                String uuid = UUID.randomUUID().toString();
		                
		                // 파일 이름 변경
		                String fileName = sdf.format(System.currentTimeMillis()) + "_" + uuid + ext;
		               vo.setClub_image(fileName);
		        }
			
	
			//클럽정보저장
			service.createClub(vo);
			//클럽장 권한 테이블 정보 생성
			//모임관심사 테이블 정보 생성
			return "redirect:/club/info";
		}
		
		
		// http://localhost:8088/club/info
		@RequestMapping(value = "/info", method = RequestMethod.GET)
		public void info(/* @RequestParam("club_no") int Cno */Model model) {
			
			ClubVo vo = service.getClubInfo(1);
			model.addAttribute("clubvo", vo);
			
			//모임가입 여부 확인
			//모임장 여부 확인
			//모임관심사 정보로 관심사 가져오기
		}
	
}
