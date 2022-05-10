<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>알림 목록</title>
<style>

 .n_info{
    color:black;
 }
 .n_info:hover{
    background-color: #ddd; 
}
.container-css{
    background-color: #fff;
}

.box-css{
    background-color: #fff; 
    border-bottom: 1px solid #ddd; 
}
.cursor{
	cursor: pointer;
}
</style>
</head>
<body>

 <div class="container">
            <div class="row pt-5">
 
                <br>
                <br>
                <div class="col-9 mx-auto">

                    <h2><strong>알림 목록</strong></h2>
                    <hr align="left" width="200px" style="background-color: #c47100; height:1px;" />

                    <br> 
                     
                     <div class="container shadow-sm container-css p-5 ">
                         <div class="d-flex flex-column justify-content-center">
                         <div class="row">
                            <h4 class="col">제목</h4>
                            <h4 class="col text-right">날짜</h4>
                         </div>
                         <c:forEach  items="${noticeList}" var="i">
                            	
                                <div class="col box-css"> 
                                <div class="row "> 
                                    <div class="col-sm-11 n_info py-2">
                           			 <a href="<%=request.getContextPath()%>/studymember/noticeInfo?noticeNum=${i.notice_num}">
	                                    <div class="row n_info">
	                                        <div class="col n_info">
		                                          from: ${i.nickname_from} 
		                                         
	                                        </div>
	                                        <div class="col n_info text-right">
			                                     <fmt:formatDate value="${i.regdate }" pattern="YYYY/MM/dd hh시 mm분"/>
		                                     </div>
	                                    </div>
	                                    <div class="n_info">
	                                  
	                                    <c:if test="${fn:contains (i.info,':')}">
	                                        내용: ${fn:substringAfter(i.info, ':')} 
	                                    </c:if>
	                                    <c:if test="${not fn:contains (i.info,':')}">
	                                        내용: ${i.info} 
	                                    </c:if>
	                                   
	                                    </div>
                           			 </a>
                                    </div>
	                                <div class="col-sm-1 text-center py-2 my-auto cursor" onclick="noti_del(${i.notice_num})" style="color:black" >
	                                	삭제
	                                </div>
	                              </div>
                                </div>  
                            <hr class="my-0">
                         </c:forEach>
                         <c:if test="${empty noticeList}"> <br><h5>알림이 없습니다.</h5> </c:if>
                         </div>
                     </div>
                      
                </div>
            </div>
        </div>
 
 <br><br><br>
<script>
 function noti_del(num){
	console.log(num)
	var str = '{"noticeNum" :' +num+'}';
	 $.ajax({ 
	        type: "post",
	        url: "<%=request.getContextPath()%>/studymember/noticeDelete",
	        data: str,
	        dataType: 'text',
	        contentType:'application/json',
	        success : function(result){
	        	if(result == 1){
	            alert("삭제되었습니다"); 
	        	}else{
	        		alert("오류");
	        	}
	            location.reload();
	        },
	        error: function (result){
	            console.log(result)
	            alert("error");
	        }   
	    }); //end ajax
	 
 }
 </script>
</body>
</html>
