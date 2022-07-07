<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp"%>


	<style>
		.container .contact .px-lg-0 {
			height: 1000px;
		}
		.sub {
		font-size: 1.2em;  color: #32C36C;	
		}
		.sub2 {
		font-size: 1.7em; font-weight: bold; margin-top: 150px; margin-bottom: 30px; font-style: italic;
		}
		.sub3 {
		font-size: 1.7em; font-weight: bold;  margin-top: 300px;  font-style: italic;
		}
		h1>.mb-4 {
			font-size: 3em;
		}
		.detail, .detail2 {
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
		
		#NewTitle {
			margin-top: 80px;
			margin-bottom: 80px;
		}
		#NewContent {
			margin-right: 300px;
		}
		
		#centercontrol {
			width: 100%;
		}
		
		#inter1,#inter2,#inter3,#inter4,#inter5,#inter6,#inter7,#inter8 {
		    font-weight: bold;
			color: white;
			
		}
		
		#newDetail {
		    font-weight: bold;
			color: white;
		}
		
		.interTable>tr {
			padding: 50px;
		}
		.interTable>td {
			width: 250px;
			background-color: 32C36C;
			font-size: large;
			text-align:center;
			padding: 20px;
			vertical-align: middle;
		    cursor: pointer;
 		  	border-radius: 35px;
 		  	color:white;
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
			url:'${pageContext.request.contextPath}/club/getdetail',
			data: {itemNum:itemNum},
			datatype: 'json',
			type:'GET',
			success:function(data){
				
				for(i=0;i<data.length;i++){
				$('.newForm').append("<td><div onclick='select(this);'' id='newDetail'>"+data[i].interest_detail_name+"</td>");
				}
				
				$('.detail').slideDown();
			
				
			}
			 
		 
		});
	}); 
	
});

  
function select(item){
	  var item = $(item).text();
	  $('.detail2').slideDown();
	  $('#interest_detail').val(item);
} 

function readImage(input) {
    // 인풋 태그에 파일이 있는 경우
    if(input.files && input.files[0]) {
        // 이미지 파일인지 검사 (생략)
        // FileReader 인스턴스 생성
        const reader = new FileReader()
        // 이미지가 로드가 된 경우
        reader.onload = e => {
            const previewImage = document.getElementById("preview-image")
            previewImage.src = e.target.result
        }
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0])
    }
}
// input file에 change 이벤트 부여
const inputImage = document.getElementById("input-image")
inputImage.addEventListener("change", e => {
    readImage(e.target)
})



</script>


<body>
    
    <!-- Contact Start -->
                
        <div id="NewTitle" align="center">
            <h6 class="text-primary">JoinUs</h6>
            <h1 class="mb-4">소모임 등록하기</h1>
            <p class="mb-4">우리들만의 모임을 자유롭게 만들어보세요! </p>
        </div>
     
    <div class="container-fluid bg-light overflow-hidden px-lg-0" align="center" >
        <div class="container contact px-lg-0" >
            <div class="col-lg-6 contact-text py-5 wow fadeIn" data-wow-delay="0.5s" id="centercontrol">
             
	              <div id="NewContent" align="center">
                  <p class="sub"> ${membervo.member_name }님의 주요관심사는 ' ${interest.interest_name } ' 입니다! </p>   
                <hr>    
	              <p class="sub2"> 1. 어떤 관심사로 모임을 만들까요? </p>    
                  <div id="select" >
                  	<table >
                  		<tr class="interTable">
                  			<td><div id="inter1" >요리/제조</div></td>
                  			<td><div id="inter2" >봉사활동</div></td>
                  			<td><div id="inter3" >운동/스포츠</div></td>
                  			<td><div id="inter4" >오락/게임</div></td>
                  		</tr>
                  		<tr class="interTable">
                  			<td><div id="inter5" >음악/악기</div></td>
                  			<td><div id="inter6" >여행</div></td>
                  			<td><div id="inter7" >외국/언어</div></td>
                  			<td><div id="inter8" >문화/공연/축제</div></td>
                  		</tr>
                  	</table>
                  </div>
                  
                  	 	
                  	 	
                  <div class="detail" >
                   	 	<p class="marginTOP"> ▼ </p>
	                 <p class="sub3"> 2. 세부관심사를 선택해주세요 </p>    
	                 <table >
                  		<tr class="interTable newForm">				
                  		</tr>
                  	</table>
                  		
	                 <div class=""> </div>
	                
	              </div>
	                
		       
               
                  <div class="detail2" >
                         <form action="" method="post" enctype="multipart/form-data">    
                         <input type="hidden" value="${membervo.member_no}" name="member_no" >                      
                         <div class="row g-3" align="center">
	                 <p class="sub3"> 3. 모임의 정보를 작성해주세요 </p>     
	               			  <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="" readonly="readonly">
                                        <label for="name">지역</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                </div>
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="interest" name="interest_no" readonly="readonly">
                                        <label for="name">관심사</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="interest_detail" name="interest_detail_name" readonly="readonly">
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
                                        <input type="file" class="btn py-2 position-absolute top-0 end-0 mt-2 me-2"  id="input-image"name="file">
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
	<hr>
                                </div>
     
  <!-- Contact End -->
    
    </body>
	

    
<%@ include file="../include/footer.jsp"%>