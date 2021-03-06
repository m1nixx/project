package com.joinus.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		
		// http://localhost:8088/club/new  ???????????? ?????????
		// http://localhost:8088/club/new?member_no=7
		@RequestMapping(value="/new", method = RequestMethod.GET)
		public String createClubGet(Model model, /* @ModelAttribute("member_no") int member_no, */ HttpSession session) {
			
			//???????????? ???????????? ?????? ???(???????????? ????????? ??????)
			session.setAttribute("member_no", 7); //????????? ???????????? 
			int member_no =	(int)session.getAttribute("member_no");
			
			//?????? ?????? ??????
			MemberVo membervo = service.getMember(member_no);
			model.addAttribute("membervo", membervo);
			//?????? ????????? ??????
			InterestVo interestvo = service.getMemberInterest(member_no);
			model.addAttribute("interest", interestvo);
			 
			 return "/club/new";
		}
	
		
		// ??????????????? ????????????(ajax)
		// http://localhost:8088/club/getdetail
		@ResponseBody
		@RequestMapping(value="/getdetail", method = RequestMethod.GET)
		public List<InterestDetailsVo> test(@RequestParam("itemNum") int itemNum) {
			//ajax??? ????????? ???????????? ??????????????? ???????????? ??????
			List<InterestDetailsVo> detailList = service.getDetailName(itemNum);
			return detailList;
		}
		
		
		// ????????????
		@RequestMapping(value = "/new", method = RequestMethod.POST)
		public String createClubPost(@RequestParam("interest_detail_name") String detail,
				@RequestParam("member_no") int member_no, MultipartFile file,
				ClubVo clvo,Model model,HttpSession session,HttpServletRequest request ) throws IOException {
		
				log.info("???????????? ??????");
			
			  //?????? ???????????? 
				if(!file.isEmpty()) { 
				
				//??????????????? ?????? ??????
				ServletContext ctx =request.getServletContext();
				String realpath = ctx.getRealPath("/resources/upload/clubs");
				log.info("??????????????????: " +realpath);
				
				//realpath ????????? ?????? ????????? ??????
				File savePath = new File(realpath);
				if(!savePath.exists()) {
					savePath.mkdirs();
				} //????????? ?????? ??????
				
				
				//String FileName = file.getOriginalFilename();
				String savedFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				log.info("?????????: "+savedFileName);

				
				String fullpath = realpath;
				fullpath += File.separator + savedFileName;
				File saveFile = new File(fullpath);
				
				
				file.transferTo(saveFile);
				clvo.setClub_image(savedFileName);
				
				
				log.info("???????????? ??????");
				}
			  
				
				  //??????????????? ???????????? 
				  InterestDetailsVo interDetail = service.getInterestNo(detail);
				  //???????????? ?????? + ?????????????????? 
				  service.newClub(clvo); 
				  int club_no = (int)clvo.getClub_no();
				  model.addAttribute("club_no", club_no); 
				  //??????????????? ??????
				  service.newClubInterest(club_no,interDetail.getInterest_no(),interDetail.getInterest_detail_no()); 
				  //????????????
				  ClubMembers members = new ClubMembers(); 
				  members.setClub_no(club_no);
				  members.setMember_no(member_no); 
				  members.setClub_member_role("admin"); //?????? ???????????? ?????????
				  service.join(members);
				  
				  model.addAttribute("member_no", member_no);
				 
				  return "redirect:/club/{club_no}";
		}
		/*
		@RequestMapping(value="/display")
		public ResponseEntity<Resource> display(HttpServletRequest request, HttpSession session, HttpServletResponse response , @PathVariable("Club_image") String Club_image){
			
			log.info("display??????");
			
			String realFile = "/resources/upload/clubs";
			String fileNm = Club_image;
			
			Path filepath = null;

			BufferedOutputStream out = null;
			InputStream in = null;

			try {
				response.setContentType("image/" + ext);
				response.setHeader("Content-Disposition", "inline;filename=" + fileNm);
				File file = new File(realFile);
				if(file.exists()){
					in = new FileInputStream(file);
					out = new BufferedOutputStream(response.getOutputStream());
					int len;
					byte[] buf = new byte[1024];
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
				}
			} catch (Exception e) {
				
			} finally {
				if(out != null){ out.flush(); }
				if(out != null){ out.close(); }
				if(in != null){ in.close(); }
			}
			
			
			
			// ---------------------------------------------
			String path = "/resources/upload/clubs";
			String folder ="";
			
			Resource resource = new FileSystemResource(path+Club_image);
			
			if(!resource.exists()) {
				return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
			}
			
			HttpHeaders header = new HttpHeaders();
			Path filepath = null;
			
			try {
				filepath = Paths.get(path+Club_image);
				header.add("Content-Type", Files.probeContentType(filepath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			log.info("display ??????!");
			return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
		}
		
		*/
		
		// http://localhost:8088/club/
		@RequestMapping(value = "/{club_no}", method = RequestMethod.GET)
		public String info(Model model,HttpSession session,@PathVariable("club_no") int club_no) {
			
//			//??????X = ??????????????? ?????? 
//			if(member_no == null) {
//				redirect:/club/info;
//			return "redirect:/members/signin";
//			}
			
			//????????????
			ClubVo clubvo = service.getClubInfo(club_no);
			model.addAttribute("clubvo", clubvo);
			//System.out.println("???????????? ????????????!"+clubvo);
			
			//????????????
			session.setAttribute("member_no", 44);
			model.addAttribute("member_no", (int)session.getAttribute("member_no"));
			
			//??????????????????
			List<ClubMembers> clubmemberVO = service.getClubMembers(club_no);
			model.addAttribute("clubmemberVO", clubmemberVO);
			//System.out.println("?????? ???????????? ????????????!"+clubmemberVO);
			
			//??????????????????
			List<ClubGradesVO> gradevo = service.getClubGrade(club_no);
			model.addAttribute("clubGrade", gradevo);
			//System.out.println("?????? ???????????? ????????????!"+gradevo);
			
			//???????????? ??????,????????????
			model.addAttribute("gradeAvgCnt", service.getClubAvgCnt(club_no));   
			
			
			//??????????????? ????????? ????????? ????????????
			
			return "/club/info";
		}
		
		@ResponseBody
		@RequestMapping(value = "/join/{club_no}",method=RequestMethod.POST)
		public void joinClub(@PathVariable("club_no") int club_no, @RequestParam("member_no") int member_no) {
			
			ClubMembers members = new ClubMembers();
			members.setMember_no(member_no);
			members.setClub_no(club_no);
			members.setClub_member_role("common");
			service.join(members);
			System.out.println("??????????????????");
			
		}
		
		@ResponseBody
		@RequestMapping(value = "/grade", method = RequestMethod.POST)
		public void clubGrade(@ModelAttribute ClubGradesVO vo) {
				service.clubGrade(vo);
				System.out.println("???????????? ??????");
		}
}
