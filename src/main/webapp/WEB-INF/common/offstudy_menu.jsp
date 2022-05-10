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
                        <a href="<%=request.getContextPath()%>/studymenu/offStudyMenuList?menuid=1">
                        <li class="<c:if test="${menuid eq 1}">selected</c:if>">
                                전체 스터디 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/offStudyMenuList?menuid=2">
                        <li class="<c:if test="${menuid eq 2 }">selected</c:if>">
                                개발/프로그래밍 </li></a>

                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/offStudyMenuList?menuid=3">
                        <li class="<c:if test="${menuid eq 3 }">selected</c:if>">
                              보안/네트워크 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/offStudyMenuList?menuid=4">
                        <li class="<c:if test="${menuid eq 4 }">selected</c:if>">
                               크리에이티브 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/offStudyMenuList?menuid=5">
                        <li class="<c:if test="${menuid eq 5 }">selected</c:if>">
                             직무/마케팅 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/offStudyMenuList?menuid=6">
                        <li class="<c:if test="${menuid eq 6 }">selected</c:if>">
                            학문/외국어 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/offStudyMenuList?menuid=7">
                        <li class="<c:if test="${menuid eq 7 }">selected</c:if>">
                             교양 </li></a>
                    </div>
            </aside>
</body>