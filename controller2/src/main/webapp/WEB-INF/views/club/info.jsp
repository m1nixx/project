<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="../include/header.jsp"%>


	<style>
		
		.datail_vertical {
			margin-top: 100px;
		}
		#detail_nav {
			align-content: center;
		}
		#meetingTitle {
			cursor: pointer;
		}
	</style>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">
	//데이터 가져와서 출력시키는 거 여기서.. 어디서 들어와도 출력 가능하도록!

	
		/* $(document).ready(function(){
	
			//new Promise( (succ, fail)=>{	
			
				var club_no = ${session.club_no}
				
			alert('연결');
			alert(club_no);
			$.ajax({
				
			url:'${pageContext.request.contextPath}/club/ajax',
			type:'GET',
			data: {club_no},
			success: function(data){
				$('#club_name').append(data.club_name);					
				$('#club_capa').append("가입정원 :"+data.club_capa+" 명");					
				$('#club_content').append(data.club_content);					
			}
		//	fail: 
				
			});
		//	}).then((arg))=>{
				
		//	});
		}); */

</script>




<body>
    
    

    
   	 <div class="col-12 text-center">
			<ul class="list-inline mb-5" id="portfolio-flters">
			<li class="mx-2 active" >모임정보</li>
			<li class="mx-2"  onclick="listClubBoard()">모임게시판</li>
			<li class="mx-2"  onclick="listClubPhoto()">사진첩</li>
			<li class="mx-2"  onclick="listClubMember()">모임회원</li>
			</ul>
	</div>

    <!-- About Start  모임 설명 -->
    <div class="container-fluid bg-light overflow-hidden my-5 px-lg-0">
        <div class="container about px-lg-0">
            <div class="row g-0 mx-lg-0">
                <div class="col-lg-6 ps-lg-0 wow fadeIn" data-wow-delay="0.1s" style="min-height: 400px;">
                    <div class="position-relative h-100" align="center">
                    <div class="datail_vertical">
                        <h6 class="text-primary" >${interest }</h6>
                        <h1 class="mb-4" id="club_name">${clubvo.club_name }</h1>
                        <p id="club_capa"><i class="fa fa-check-circle text-primary me-3" ></i>정원:${clubvo.club_capacity } 명</p>
                        <c:if test="${clubmemberVO.member_no == member_no}">
                        <!-- 모임회원이면 모임평가(별 다섯개) 중복불가능하게 만들어야함-->
                        		별점 만들 예정.....
                        </c:if>
                        	<a href="" class="btn btn-primary rounded-pill py-3 px-5 mt-3">가입하기</a>
 							                     
                	    </div>
                    </div>
                </div>
                <div class="col-lg-6 about-text py-5 wow fadeIn" data-wow-delay="0.5s">
                    <div class="p-lg-5 pe-lg-0">
                <h6 class="text-primary">소개글</h6>
                        <p id="club_content"></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- About End -->

 <!-- Feature Start 정모 -->
    <div class="container-xxl py-5">
        <div class="container">
                  <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px; ">
                <h6 class="text-primary">${clubvo.club_name } </h6>
                <h1 class="mb-4">정모</h1>
                <!-- 정모만들기 모임장만 보일 수 있도록  -->
                <c:if test="${clubmemberVO.club_role_no == 2}">
                <a class="small fw-medium" href="">모임장 정모만들기<i class="fa fa-arrow-right ms-2"></i></a>
                </c:if>
           			 </div>
                <hr><br><br>
            <div class="row g-5">
                <div class="col-md-6 col-lg-3 wow fadeIn" data-wow-delay="0.1s">
                    <div class="d-flex align-items-center mb-4">
                        <div class="btn-lg-square bg-primary rounded-circle me-3">
                            <i class="fa fa-users text-white"></i>
                        </div>
                    </div>
                    <h3 class="mb-3" onclick="meetingTitle()" id="meetingTitle">정모주제</h3>
                    <span>날짜</span><br>
                    <span>장소</span><br>
                    <span>인원</span><br>
                </div>
                 <div class="col-md-6 col-lg-3 wow fadeIn" data-wow-delay="0.1s">
                    <div class="d-flex align-items-center mb-4">
                        <div class="btn-lg-square bg-primary rounded-circle me-3">
                            <i class="fa fa-users text-white"></i>
                        </div>
                    </div>
                    <h3 class="mb-3">정모주제</h3>
                    <span>날짜</span><br>
                    <span>장소</span><br>
                    <span>인원</span><br>
                </div>
              <div class="col-md-6 col-lg-3 wow fadeIn" data-wow-delay="0.1s">
                    <div class="d-flex align-items-center mb-4">
                        <div class="btn-lg-square bg-primary rounded-circle me-3">
                            <i class="fa fa-users text-white"></i>
                        </div>
                    </div>
                    <h3 class="mb-3">정모주제</h3>
                    <span>날짜</span><br>
                    <span>장소</span><br>
                    <span>인원</span><br>
                </div>
                 <div class="col-md-6 col-lg-3 wow fadeIn" data-wow-delay="0.1s">
                    <div class="d-flex align-items-center mb-4">
                        <div class="btn-lg-square bg-primary rounded-circle me-3">
                            <i class="fa fa-users text-white"></i>
                        </div>
                    </div>
                    <h3 class="mb-3">정모주제</h3>
                    <span>날짜</span><br>
                    <span>장소</span><br>
                    <span>인원</span><br>
                </div>
            </div>
        </div>
    </div>
    <!-- Feature Start -->



    <!-- Team Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                <h6 class="text-primary">모임이름</h6>
                <h1 class="mb-4">활동사진</h1>
            </div>
            <hr><br><br>
            <div class="row g-4">
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="team-item rounded overflow-hidden">
                        <div class="d-flex">
                            <img class="img-fluid w-75" src="./resources/img/team-1.jpg" alt="">
                            <div class="team-social w-25">
                                <a class="btn btn-lg-square btn-outline-primary rounded-circle mt-3" href=""><i class="fab fa-facebook-f"></i></a>
                                <a class="btn btn-lg-square btn-outline-primary rounded-circle mt-3" href=""><i class="fab fa-twitter"></i></a>
                                <a class="btn btn-lg-square btn-outline-primary rounded-circle mt-3" href=""><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                        <div class="p-4">
                            <h5>Full Name</h5>
                            <span>Designation</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                    <div class="team-item rounded overflow-hidden">
                        <div class="d-flex">
                            <img class="img-fluid w-75" src="./resources/img/team-2.jpg" alt="">
                            <div class="team-social w-25">
                                <a class="btn btn-lg-square btn-outline-primary rounded-circle mt-3" href=""><i class="fab fa-facebook-f"></i></a>
                                <a class="btn btn-lg-square btn-outline-primary rounded-circle mt-3" href=""><i class="fab fa-twitter"></i></a>
                                <a class="btn btn-lg-square btn-outline-primary rounded-circle mt-3" href=""><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                        <div class="p-4">
                            <h5>Full Name</h5>
                            <span>Designation</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                    <div class="team-item rounded overflow-hidden">
                        <div class="d-flex">
                            <img class="img-fluid w-75" src="./resources/img/team-3.jpg" alt="">
                            <div class="team-social w-25">
                                <a class="btn btn-lg-square btn-outline-primary rounded-circle mt-3" href=""><i class="fab fa-facebook-f"></i></a>
                                <a class="btn btn-lg-square btn-outline-primary rounded-circle mt-3" href=""><i class="fab fa-twitter"></i></a>
                                <a class="btn btn-lg-square btn-outline-primary rounded-circle mt-3" href=""><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                        <div class="p-4">
                            <h5>Full Name</h5>
                            <span>Designation</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Team End -->
    
    <script type="text/javascript">
  
  //모임 게시판으로 이동
  function listClubMember(){
	  location.href='${PageContext.request.contextPath }/test';
  }
  // 모임 사진첩으로 이동
  function listClubBoard(){
	  location.href='${PageContext.request.contextPath }/test';
  }
  // 정모페이지로 이동  	
  function meetingTitle(){
	  location.href='${PageContext.request.contextPath }/test';
  }
	
    </script>
    </body>
	

    
<%@ include file="../include/footer.jsp"%>