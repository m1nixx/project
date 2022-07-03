<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp"%>


	<style>
	
		.master>.p-lg-5 .ps-lg-0 {
		 width: 800px;
		}
		.container .contact .px-lg-0 {
			height: 1000px;
		}
		.sub {
		font-size: 1.5em; font-weight: bold; color: #1A2A36; margin-top: 50px;"	
		}
		.sub2 {
		font-size: 1.8em; font-weight: bold;  margin-top: 50px;"	
		}
		.sub3 {
		font-size: 1.8em; font-weight: bold;  margin-top: 100px;"	
		}
		h1>.mb-4 {
			font-size: 3em;
		}
		.detail {
			display: none; font-size: large;
		}
		form {
			width: 50%; 
			margin-bottom: 200px;
		}
		
		.GreenP {
			color:#32C36C;font-size: 0.9em;
		}
		.marginTOP {
			margin-top: 5em;
		}
	</style>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
		
	$('#inter1,#inter2,#inter3,#inter4,#inter5,#inter6,#inter7,#inter8').click(function(){
			var item = $(this).text();
			var itemNum;
			$('#interest').val(item);
			
			if(item=="요리/제조"){ itemNum = 1; }
			else if(item == "봉사활동"){ itemNum = 2;}
			else if(item == "운동/스포츠"){ itemNum = 3;}
			else if(item == "오락/게임"){ itemNum = 4;}
			else if(item == "음악/악기"){ itemNum = 5;}
			else if(item == "여행"){ itemNum = 6;}
			else if(item == "외국/언어"){ itemNum = 7;}
			else if(item == "문화/공연/축제"){ itemNum = 8;}
			
			
			
		  $.ajax({
			url:'/club/getdetail',
			data: {itemNum:itemNum},
			datatype: 'json',
			type:'GET',
			success:function(data){
				
				
				for(i=0;i<data.length;i++){
				$('.newForm').append("   <a href='#f-d'onclick='select(this);''>"+data[i].interest_detail_name+"</a>   ");
				}
				
				$('.detail').slideDown();
			}
			 
		 
		});
	}); 
	
});

  
function select(item){
	  var item = $(item).text();
	  $('#interest_detail').val(item);
} 

</script>


<body>
    
    <!-- Contact Start -->
                
        <div id="title" align="center">
            <h6 class="text-primary">JoinUs</h6>
            <h1 class="mb-4">소모임 등록하기</h1>
            <p class="mb-4">우리들만의 모임을 자유롭게 만들어보세요! </p>
        </div>
    <div class="master">
    <div class="container-fluid bg-light overflow-hidden px-lg-0" style="margin: 3rem 0;" align="center" >
        <div class="container contact px-lg-0" >
            <div class="col-lg-6 contact-text py-5 wow fadeIn" data-wow-delay="0.5s">
              <!--  <div class="p-lg-5 ps-lg-0"> -->
               	아이콘?이미지 들어갈 공간
                  <p class="sub"> ${membervo.member_name }님의 주요관심사는 ' ${interest.interest_name } ' 입니다! </p>   
                <br>  <hr>    
                  
	              <p class="sub2"> 어떤 관심사로 모임을 만들까요? </p>      
                  <div id="select" style="font-size: large;">
		            <a href="#detail" id="inter1" >요리/제조</a>
		  			<a href="#detail" id="inter2" >봉사활동</a>
		  			<a href="#detail" id="inter3" >운동/스포츠</a>
		  			<a href="#detail" id="inter4" >오락/게임</a> 
		  			<br>
		  			<a href="#detail" id="inter5" >음악/악기</a>
		  			<a href="#detail" id="inter6" >여행</a>
		  			<a href="#detail" id="inter7" >외국/언어</a>
		  			<a href="#detail" id="inter8" >문화/공연/축제</a>
                  </div>
                  
                  	 	
                  	 	
                  <div class="detail" >
                   	 	<p class="marginTOP"> ▼ </p>
	                 <p class="sub3"> 세부관심사를 선택해주세요 </p>      
	                 <div class="newForm"> </div>
	                
	                
	                           
                         <form method="post" enctype="multipart/form-data" >                          
                         <div class="row g-3" align="center">
	                 <p class="sub3"> 모임의 정보를 작성해주세요 </p>      
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="interest" placeholder="관심사" name="interest_no" readonly="readonly">
                                        <label for="name">관심사</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="interest_detail" name="interest_detail_no" readonly="readonly">
                                        <label for="name">세부관심사</label>
                                    </div>
                                </div>
                                  <div class="col-12">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="subject" name="club_name" >
                                        <label for="subject">모임이름</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <textarea class="form-control" name="club_content" id="message" style="height: 100px"></textarea>
                                        <label for="message">모임의 소개글을 입력하세요</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="club_capacity">
                                        <label for="name">정원</label>
                                    </div>
                                </div>
                              
                              
                              	 <div class="col-12">
                                    <div class="form-floating">
                                        <input type="file" class="btn py-2 position-absolute top-0 end-0 mt-2 me-2" name="club_image">
                                    </div>
                                </div><br><br>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <p class="GreenP">대표이미지를 올려주세요(선택사항)</p>
                                    </div>
                                    </div>
                                    <hr> 
                                 <div class="col-12">
                                    <button class="btn btn-primary rounded-pill py-3 px-5" type="submit">모임 개설하기</button>
                                </div>
                             </div>
                        </form>
                             </div>
                                  </div>
                              
                                </div>
                                </div>
                                </div>
     
    <!-- Contact End -->
    
    </body>
	

    
<%@ include file="../include/footer.jsp"%>