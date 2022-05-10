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
                        <a href="<%=request.getContextPath()%>/studymenu/onoffStudyMenuList?menuid=15">
                        <li class="<c:if test="${menuid eq 15}">selected</c:if>">
                                전체 스터디 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onoffStudyMenuList?menuid=16">
                        <li class="<c:if test="${menuid eq 16 }">selected</c:if>">
                                개발/프로그래밍 </li></a>

                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onoffStudyMenuList?menuid=17">
                        <li class="<c:if test="${menuid eq 17 }">selected</c:if>">
                              보안/네트워크 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onoffStudyMenuList?menuid=18">
                        <li class="<c:if test="${menuid eq 18 }">selected</c:if>">
                               크리에이티브 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onoffStudyMenuList?menuid=19">
                        <li class="<c:if test="${menuid eq 19 }">selected</c:if>">
                             직무/마케팅 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onoffStudyMenuList?menuid=20">
                        <li class="<c:if test="${menuid eq 20 }">selected</c:if>">
                            학문/외국어 </li></a>
                    </div>
                    <div class="aside-content">
                        <a href="<%=request.getContextPath()%>/studymenu/onoffStudyMenuList?menuid=21">
                        <li class="<c:if test="${menuid eq 21 }">selected</c:if>">
                             교양 </li></a>
                    </div>
            </aside>
</body>