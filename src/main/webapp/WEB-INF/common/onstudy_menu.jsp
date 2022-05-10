<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
<link href="<%=request.getContextPath() %>/css/menu3.css" rel="stylesheet" type="text/css"> 
</head>
<body>
	<aside class="col-sm-3">
                <div class="col aside">
	                <div class="mb-3">
	                    <h4><strong>스터디</strong></h4> 
	                </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onStudyMenuList?menuid=8">
                        <li class="<c:if test="${menuid eq 8}">selected</c:if>">
                                전체 스터디 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onStudyMenuList?menuid=9">
                        <li class="<c:if test="${menuid eq 9 }">selected</c:if>">
                                개발/프로그래밍 </li></a>

                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onStudyMenuList?menuid=10">
                        <li class="<c:if test="${menuid eq 10 }">selected</c:if>">
                              보안/네트워크 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onStudyMenuList?menuid=11">
                        <li class="<c:if test="${menuid eq 11 }">selected</c:if>">
                               크리에이티브 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onStudyMenuList?menuid=12">
                        <li class="<c:if test="${menuid eq 12 }">selected</c:if>">
                             직무/마케팅 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onStudyMenuList?menuid=13">
                        <li class="<c:if test="${menuid eq 13 }">selected</c:if>">
                            학문/외국어 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onStudyMenuList?menuid=14">
                        <li class="<c:if test="${menuid eq 14 }">selected</c:if>">
                             교양 </li></a>
                    </div>
            </aside>
</body>