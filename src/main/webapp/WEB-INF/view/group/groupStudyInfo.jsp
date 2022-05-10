<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<link href="<%=request.getContextPath() %>/css/groupinfo.css" rel="stylesheet" type="text/css">  
<title>스터디 진행현황</title>

</head>
<body>

	<div class="container-fluid famous-saying-box">
		<div class="container p-3 ">
			<div class=" mt-3">
				<h3 class="color-white ">오늘의 명언</h3>
				<div class="color-white">생각 없이 배우기만 하면 얻는 것이 없고, 생각만 하고 배우지 않으면 오류나 독단에 빠질 위험이 있다. -공자</div>

			</div>
		</div>
	</div>
	<div class="container">
		<div class="row pt-5">
		 
            
			<br> <br>
			<div class="col-lg-9 mx-auto">
				<h2>스터디 진행현황</h2>
				<hr align="left" width="250px" style="background-color: #c47100; height:1px;" />
				<br>
				<div class="container container-css  p-5">
				<h3>  ${studyMenuInfo.title}</h3>
			        <div class="row">
	                    <div class="col">
	                      <h5>스터디 멤버:  ${total} / ${studyMenuInfo.pernum} 명</h5>
	                    </div>
	                </div>
	                    <hr>
				    <c:forEach items="${groupMemberList}" var="i">
                      <a>
                      <div class="col-sm-6 mx-auto">
					       <div class="row">
						       <div class="col-sm-6">
						           ${i.nickname}
		                       </div>
					       </div>
    				      <hr>
    				  </div>
				      </a>
				    </c:forEach>
					
					<a class="btn btn-c" href="<%=request.getContextPath()%>/socket/chat?boardnum=${groupMemberList[0].boardnum}">채팅 입장</a>
                    <a class="btn btn-c" href="<%=request.getContextPath()%>/group/groupBoard?boardnum=${groupMemberList[0].boardnum}&boardid=1">그룹 게시판</a>
                    <a class="btn btn-c" href="<%=request.getContextPath()%>/group/groupexit?boardnum=${groupMemberList[0].boardnum}">스터디 종료</a>

				</div>
				<br>
             </div>
         </div>
     </div>
	
 

</body>