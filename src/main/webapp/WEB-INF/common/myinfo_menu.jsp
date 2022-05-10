<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
<link href="<%=request.getContextPath() %>/css/menu2.css" rel="stylesheet" type="text/css"> 
</head>
<body>
	<aside class="col-lg-3">
		<div class="col aside">
			<h3>내 정보</h3>
			<hr align="left" width="120px" style="border: 0.5px solid #c47100" />
			<div class="aside-content" onclick="location.href='<%=request.getContextPath()%>/studymember/mypage'">
				<li class="">마이페이지</li>
			</div>
			<div class="aside-content" onclick="location.href='<%=request.getContextPath()%>/studymember/myprofile'">
				<li class=""> 프로필 </li>
			</div>
		
					
			<div class="aside-content">
				<div class="dropdown">
  <div class="dropbtn"> 작성한 게시글</div>
  <div class="dropdown-content">
     <a href="<%=request.getContextPath()%>/community/comBoardmyList1"><div class="aside-content">커뮤니티 게시글</div></a>
   
   
    <a href="<%=request.getContextPath()%>/studymenu/mylist2"><div class="aside-content">스터디 게시글</div></a>
  </div>
</div>

			</div>

	</aside>
</body>