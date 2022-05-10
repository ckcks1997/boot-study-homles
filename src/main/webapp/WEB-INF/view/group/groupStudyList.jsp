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
				<h2> 스터디 목록 </h2>
				<br>
			        <div class="row">
	                    <div class="col">
	                    <c:if test="${empty list}"> <br><h5>진행중인 스터디가 없습니다.</h5> </c:if>
	                      <c:if test="${!empty list}"><h5>진행중인 스터디</h5>  </c:if>
	                    </div>
	                </div>
	                    
				    <c:forEach items="${list}" var="i">
                      <a class="a-color" href="<%=request.getContextPath()%>/group/studyinfo?boardnum=${i.boardnum}">
				        <div class="row items m-3">
					        <div class="col">
					           <span class="i-title"> ${i.title }</span>
					           <c:if test="${i.represent eq 1}"> <span class="leader"> 리더 </span> </c:if>
                               <c:if test="${i.represent eq 0}"> <span class="jowon"> 조원 </span> </c:if>
	                        </div>
	                        <div class="col">
	                           
	                        </div> 
				        </div>
				      </a>
				      <hr>
				    </c:forEach>
					

				</div>
				<br>
             </div>
         </div>
     </div>
				 
 

</body>