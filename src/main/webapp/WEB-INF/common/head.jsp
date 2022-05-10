<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="icon" href="<%=request.getContextPath()%>/img/star.svg" type="image/svg+xml" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link href="<%=request.getContextPath() %>/css/footer.css" rel="stylesheet" type="text/css"> 
<link href="<%=request.getContextPath() %>/css/header.css" rel="stylesheet" type="text/css"> 
 <style>
 	@font-face {
		font-family: "kita";
		src: url("<%=request.getContextPath()%>/fonts/KITA.ttf")
			format("truetype");
		font-weight: normal;
		font-style: normal;
	}
 </style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light shadow-sm">
		<div class="container px-4">
			<a class="navbar-brand" href="<%=request.getContextPath()%>/board/main"><span class="navbar-brand">스터디 홈즈</span> <img class="star" src="<%=request.getContextPath()%>/img/star.svg" /> </a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> 스터디 </a>
						<div class="dropdown-menu"> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/studymenu/onStudyMenuList?menuid=8">온라인</a> <a class="dropdown-item" href="<%=request.getContextPath()%>/studymenu/offStudyMenuList?menuid=1">오프라인</a> <a class="dropdown-item" href="<%=request.getContextPath()%>/studymenu/onoffStudyMenuList?menuid=15">온오프라인</a>
						</div></li>
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> 커뮤니티 </a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="<%=request.getContextPath()%>/community/comBoardList?boardid=1">질문 & 답변</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/community/comBoardList?boardid=2">자유게시판</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/community/comBoardList?boardid=3">정보를 나눠요</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/community/comBoardList?boardid=4">공지</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/community/comBoardList?boardid=5">문의사항</a> 
					
						</div></li>
					
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/board/make">홈즈</a></li>

				</ul>

				<div>
					<ul class="navbar-nav  mt-2 mt-lg-0 ml-3">
						<div class="d-flex align-items-center">
							<%-- 로그인 세션이 없는경우 --%>
							<c:choose>
								<c:when test="${sessionScope.memberID eq null}">
									<button class="btn btn-sm btn-dark">
										<a class="a-no-deco" href="<%=request.getContextPath()%>/studymember/loginForm">로그인</a>
									</button>
							             &nbsp;
							             <button class="btn btn-sm btn-danger">
										<a class="a-no-deco" href="<%=request.getContextPath()%>/studymember/join">회원가입</a>
									</button>
								</c:when>
								<%--/*로그인 된 경우 */--%>
								<c:otherwise>
								
								    <li class="nav-item ">
									    <a class="nav-link" href="<%=request.getContextPath()%>/studymember/notice">
									       <i class="fa-solid fa-bell"></i><span class="badge badge-danger" id="badgeNum"> ${noticeCount } </span>
									    </a>
								    </li>
								
	                                <li class="nav-item mr-2"><a class="nav-link" href="<%=request.getContextPath()%>/group/studylist"> <i class="fa-solid fa-comments"></i></a></li>
									<div class="dropdown">
										<button class="btn  dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${memberNickname} 님</button>
										<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
											<img class="profile" src="<%=request.getContextPath()%>/imgupload/${memberPicture}" width="200" height="200" id="pic" onerror="this.onerror=null; this.src='<%=request.getContextPath()%>/img/profile_empty.jpg'" />
											<li class="nav-item"><a class="dropdown-item" href="<%=request.getContextPath()%>/studymember/logout">로그아웃</a></li> 
											<a class="dropdown-item"
												href="<%=request.getContextPath()%>/studymember/mypage">마이페이지</a>
                                            <a class="dropdown-item"
                                                href="<%=request.getContextPath()%>/attend/check">출석 이벤트</a>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	
	<script>
	console.log("/upload/ 관련 404에러는 프로필 사진 미지정시 발생하는 error이며, 이때 대체 이미지(책 사진)가 나오므로 오류가 아닙니다!")
	$(function () {
		  $('[data-toggle="popover"]').popover()
		})
	$('.popover-dismiss').popover({
		  trigger: 'focus'
		})
		
	function check(){
	  $.ajax({
		  type:"POST",
		  url:"<%=request.getContextPath()%>/board/notice" ,
		  contentType: "text",
		  data:"memberNickname="+'${memberNickname}' ,
		  success:function(result){
			  let res = result.trim();
		        document.querySelector('#badgeNum').innerHTML = res;
		       
		  },
		  error: function(request, status, error){
		      console.log(status)
		  }
	 });
	
	}

check();
 
	</script>
</body>
</html>
