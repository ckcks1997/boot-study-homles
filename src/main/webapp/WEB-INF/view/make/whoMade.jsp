<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>감사합니다</title>
</head>
<style>
body {
	height: 100vh;
}

/* 명언 */
.famous-saying-box {
	height: 150px;
	background-color: #333b3d;
}

h5{
    font-family: "kita";
}

ul, li {
	list-style: none;
}

li {
	font-weight: 700;
	color: #777;
}

li:hover {
	font-weight: 700;
	color: #f55555;
}

a:hover {
	color: black;
	text-decoration: none;
}

.aside-content {
	display: block;
	width: 200px;
	/*height와 line-height를 같은 값으로 주면 세로로 중앙 정렬이 된다.*/
	height: 40px;
	line-height: 40px;
	text-align: left;
	padding-left: 10px;
}

.container-css {
	background: #f8f8f8;
	border-radius: 20px;
}

.whomade {
	justify-content:space-between;
	align-items: center;
}

.who-img {
	
	float: left;
	top: 10%;
	right: 5%;
	text-align: right;
	width: 150px;
	height: auto;
	border-radius: 10px;
}

.txt {
	font-weight: bold;
	
	
}

.name {
	
	font-size: 14px;
	
}


</style>
<body>

	<div class="container-fluid famous-saying-box">
		<div class="container p-3">
			<div class="mt-3">
				<h2 style="color: white">반가워요!</h2>
				<p style="color: white">스터디홈즈입니다</p>
			</div>
		</div>
	</div>



	<div class="container">
		<div class="row pt-5">
			<!-- ---------------------왼쪽 메뉴------------- ---------------------------------------------------->
			<aside class="col-sm-3">
				<div class="col aside">
					<h4>
						<strong>스터디홈즈</strong>
					</h4>
					<div class="aside-content">
						<a href="<%=request.getContextPath()%>/board/make"><li>
								소개</li> </a> <a
							href="<%=request.getContextPath()%>/board/whoMade"><li>만든
								이</li> </a>

					</div>
				</div>
			</aside>

			<!-- ----------------------------메인---------------------------------------------------- -->

			<div class="main col-sm-9">
				<h5>
					<strong>만든 이</strong>
				</h5>
				<hr align="left" width="170px" style="background-color: #c47100; height:1px;" />
				<div class="main col-sm-9">

					
						<div class="container">
						<div class = "row whomade">
							<div class="profile">
								<img class="who-img"
									src="<%=request.getContextPath()%>/img/whoProfile.jpg"
									alt="who">
							</div>
							<div class="txt">
								<br>
								<br>
								<p>프로젝트 경험이 없는 학생이 모여 
								<br>
							 	기획, 설계, 프로토타입 제작, 기능구현 단계를 거쳐 만든 첫 프로젝트입니다.</p>
								
								<p>관심갖고 봐주셔서 감사합니다 &nbsp; :&nbsp;)</p>
							
							
								<div class = "name">
								<br>	
								<p>류찬혁 · 이재성 · 김은지 · 최준현
								<br>	
								 MVC2: 22/03/10 &nbsp;-&nbsp; 22/03/25 <br>			 

								 Spring: 22/04/21 &nbsp;-&nbsp; 22/05/04 </p>	
								</div>
							
							</div>
							
							
							</div>							
						</div>
					
						
					<button class="btn btn-sm btn-secondary" id="donate">커피 사주기(1,000원)</button>
					
					
					<br>
					<br>


				</div>
			</div>




<script type="text/javascript">
var val = "";
$('#donate').click(function iamport(){
	//가맹점 식별코드
	IMP.init('imp70991296');
	IMP.request_pay({
	    pg : 'kcp',
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : '기부' , //결제창에서 보여질 이름
	    amount : 1000, //실제 결제되는 가격
	    buyer_email : 'example@siot.do',
	    buyer_name : '홍길동',
	    buyer_tel : '010-1234-5678',
	    buyer_addr : '서울 강남구 도곡동',
	    buyer_postcode : '123-456'
	}, function(rsp) {
		console.log(rsp);
		$.ajax({
        	type : "POST",
        	headers: { "Content-Type": "application/json" },
        	url : "<%=request.getContextPath()%>/iamport/verifyIamport/" + rsp.imp_uid 
        }).done(function(data) {
			console.log(data)
			val= data;
        	if(rsp.paid_amount == data.response.amount){
	        	alert("결제 완료");
        	} else {
        		
        		alert("관리자에게 문의하세요");
        	}
        })
	    
	});
})

</script>




		</div>
	</div>


</body>
</html>