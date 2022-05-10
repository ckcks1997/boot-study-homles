<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
<link href="<%=request.getContextPath() %>/css/menu1.css" rel="stylesheet" type="text/css"> 
</head>
<body>
	<aside class="col-lg-3 mb-5">
		<div class="col aside ">
			<h4><strong>그룹게시판 목록</strong></h4>
			<div class="aside-content">
				<a href="<%=request.getContextPath()%>/group/groupBoard?boardid=1&boardnum=${boardnum}"><li class=" <c:if test="${boardid eq 1 }">selected</c:if> ">질문 답변</li></a>
				<a href="<%=request.getContextPath()%>/group/groupBoard?boardid=2&boardnum=${boardnum}"><li class="<c:if test="${boardid eq 2 }">selected</c:if>">자료공유</li></a>
			</div>
		</div>
	</aside> 
</body>