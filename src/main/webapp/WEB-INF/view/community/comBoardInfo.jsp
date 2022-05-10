<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath() %>/css/boardinfo.css"
	rel="stylesheet" type="text/css">

<title>커뮤니티</title>

</head>

<body>


	<div class="container-fluid famous-saying-box">
		<div class="container p-3">
			<div class="mt-3">
				<h2 style="color: white">이야기를 나눠요</h2>
				<p style="color: white">어려운 내용은 함께 토론해봐요</p>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row pt-5">
			<!----------------왼쪽 사이드 메뉴 ------------------------------------------------------------------------>
			<%--aside부분 --%>
			<%@include file="/WEB-INF/common/community_menu.jsp"%>


			<!---------------------    메인      ------------------------------------------------------------------ -->
			<div class="main col-lg-9">

				<h2 style="font-weight: bold">
					<c:if test="${com.boardid =='1'}"> 질문 & 답변</c:if>
					<c:if test="${com.boardid =='2'}"> 자유</c:if>
					<c:if test="${com.boardid =='3'}"> 정보공유</c:if>
					<c:if test="${com.boardid =='4'}"> 공지</c:if>
					<c:if test="${com.boardid =='5'}"> 문의사항</c:if>
				</h2>

				<hr align="left" width="150px"
					style="background-color: #c47100; height: 1px;" />
				<input type="hidden" id="board_num" name="board_num"
					value="${com.board_num}"> <input type="hidden"
					name="nickname" value='${com.nickname}'>
				<div class="row">
					<div class="col-sm-10">
						<div class="title">
							<c:if test="${com.boardid =='1'}">
								<h5 style="font-weight: bold">Q: ${com.title}</h5>
							</c:if>
							<c:if test="${com.boardid != '1'}">
								<h5 style="font-weight: bold">${com.title}</h5>
							</c:if>
						</div>
						
						
						<div class="postInfo">

							<p>
								<c:if test="${com.picture eq null }">
									<img class="pic_mini"
										src="<%=request.getContextPath()%>/img/profile_empty.jpg">
								</c:if>
								<c:if test="${com.picture ne null }">
									<img class="pic_mini"
										src="<%=request.getContextPath()%>/imgupload/${com.picture}">
								</c:if>
								<a
									href="<%=request.getContextPath() %>/studymember/userinfo?usernick=${com.nickname}">
									${com.nickname} </a> · ${com.regdate}


								<c:if test="${memberNickname eq com.nickname}">
									<span class="txt_bar">|</span>
									<a
										href="<%=request.getContextPath()%>/community/comBoardUpdateForm?board_num=${com.board_num}">수정</a>
								</c:if>
							</p>
						</div>

					</div>


					<div class="col-sm-2">
						<c:if test="${com.boardid != 5 }">
						<div class="readcnt">
							<span> <svg xmlns="http://www.w3.org/2000/svg" width="16"
									height="16" fill="currentColor" class="bi bi-eye"
									viewBox="0 0 16 16">
  													<path
										d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z" />
  													<path
										d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z" />
													</svg>
							</span>${com.readcnt}</div>
							</c:if>
						<div class="comment">
							<span> <svg xmlns="http://www.w3.org/2000/svg" width="16"
									height="16" fill="currentColor" class="bi bi-chat-square-text"
									viewBox="0 0 16 16">
  													<path
										d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1h-2.5a2 2 0 0 0-1.6.8L8 14.333 6.1 11.8a2 2 0 0 0-1.6-.8H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h2.5a1 1 0 0 1 .8.4l1.9 2.533a1 1 0 0 0 1.6 0l1.9-2.533a1 1 0 0 1 .8-.4H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z" />
													<path
										d="M3 3.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3 6a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 6zm0 2.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z" />
													</svg>
							</span>${reply_count }
						</div>



					</div>
				</div>



				<br />
				<div class="col-md-10">
					<p>${com.content}</p>
				</div>
				<br /> <br /> <br />


				<!-- ------------------댓글 ------------------------------------------------------------------------ -->

				<div class="col-md-10">
					<c:if test="${com.boardid != 4 }">
						<%-- 댓글은 공지 게시판 외에서만 사용가능 || 공지게시판 댓글 사용불가 --%>
						<h5 style="font-weight: bold">댓글 ${reply_count}</h5>
						<hr align="left" style="background-color: #c47100; height: 0.7px;" />
						<div id="replyList">
							<c:forEach var="reply" items="${reply_list}">
								<div class="reply" id="r${reply.reply_num}">

									<div class="row">
										<div class="col-md-10" id="replyInfo">
											<input type="hidden" id="reply_num" name="reply_num"
												value="${reply.reply_num}">
											<p>
												<c:if test="${reply.picture eq null }">
													<img class="pic_mini"
														src="<%=request.getContextPath()%>/img/profile_empty.jpg">
												</c:if>
												<c:if test="${reply.picture ne null }">
													<img class="pic_mini"
														src="<%=request.getContextPath()%>/imgupload/${reply.picture}">
												</c:if>

												${reply.nickname} · ${reply.regdate2}
											</p>
										</div>

										<c:if test="${memberNickname eq reply.nickname}">
											<div class="col-md-2">
												<input type="button" class="btn btn-light"
													onclick="deleteReply('${reply.reply_num}')" value="삭제" />
											</div>
										</c:if>

									</div>
									<div class="col-md-12 replyTxt" id = "replyTxt">
										<p>${reply.content}</p>
									</div>
									<hr align="left"
										style="background-color: 333b3d; height: 0.7px;" />

								</div>

							</c:forEach>

						</div>

						<!-- 로그인이 되어있으면 댓글 이용 가능 -->
						<c:if test="${memberNickname != null}">

							<div class="row">
								<div class="col-md-10">
									<input type="hidden" id="board_num" name="board_num"
										value="${com.board_num}"> <input type="hidden"
										name="reply_nickname" id="reply_nickname"
										value="${memberNickname}">

									<textarea class="col-md-12" rows="5" cols="80"
										name="reply_content" placeholder="댓글을 달아주세요"
										id="reply_content"></textarea>
								</div>
								<div class="col-md-2">
									<input type="button" id="writeReply" class="btn btn-danger"
										value="등록" />
								</div>
							</div>

						</c:if>


						<!-- 로그인이 안되어있으면 댓글 이용불가 -->
						<c:if test="${memberNickname == null}">
							<div class="row">
								<div class="col-md-10">
									<p style="font-weight: bold;">로그인 후 댓글 이용이 가능합니다</p>
								</div>
							</div>
						</c:if>
					</c:if>
					
					
							<button type="button" class="btn btn-dark mt-3"			
								onclick="location.href ='comBoardList?boardid=${boardid }&pageNum=${pageNum }&sort=${sort}&part=${part}&searchData=${searchData}'">목록으로</button>
						
					<%--공지, 문의게시판 외에서만 신고 사용가능 || 공지, 문의게시판은 신고 불가--%>
					<c:if test="${com.boardid != 4 && com.boardid != 5}">

						<!-- 1)로그인 된 회원이고 2)글 작성자와 다른 회원만 신고버튼 활성화 -->
						<c:if
							test="${memberNickname != null && memberNickname != com.nickname}">
							<c:set var="nickList" value="${nicknameList}" />
							<c:choose>
								<%--리스트값에 이미 닉네임이 있다면 신고거절모달 --%>
								<c:when test="${fn:contains(nickList, memberNickname) }">
									<button type="button" class="btn btn-dark mt-3"
										data-toggle="modal" data-target="#rejectModal">신고</button>
								</c:when>
								<%--리스트값에 닉네임이 없다면 신고등록모달 --%>
								<c:otherwise>
									<button type="button" class="btn btn-dark mt-3"
										id="reportButton" data-toggle="modal"
										data-target="#reportModal">신고</button>
								</c:otherwise>
							</c:choose>
						</c:if>
						<%--공지, 문의게시판은 신고 불가로 막아놓음 --%>

					</c:if>


					<c:if test="${memberNickname eq com.nickname}">
						<button type="button" data-toggle="modal"
							data-target="#deleteModal" class="btn btn-danger mt-3">삭제</button>
					</c:if>

				</div>
			</div>
		</div>
	</div>

	<br>
	<br>

	<!-------------- 게시글 삭제 모달창 --------------------------------------------------------------------------------------------------------------------------------->

	<div class="modal fade" id="deleteModal" aria-hidden="true"
		tabindex="-1" aria-labelledby="deleteBoardLabel">
		<div class="modal-dialog">
			<div class="modal-content">

				<form
					action="<%=request.getContextPath()%>/community/comBoardDelete"
					method="post">
					<div class="form-group">
						<input type="hidden" id="board_num" name="board_num"
							value="${com.board_num}">
						<div class="modal-header">
							<h5 class="modal-title" id="deleteBoardLabel">게시글 삭제</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>

						<div class="modal-body">이 글을 삭제합니다. 계속하시겠습니까?</div>


						<div class="modal-footer">
							<input type="submit" class="btn btn-primary" value="확인">
							<button class="btn btn-outline-primary" data-dismiss="modal">취소</button>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>



	<!-- -------------------신고 거절 모달창 ------------------------------------------------------------------------>

	<div class="modal fade" id="rejectModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">신고</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>
						이미 신고한 게시물입니다 <br /> <br /> 신고는 게시물당 한번만 가능하며 <br /> 누적신고수가 3회
						이상이면 삭제됩니다
					</p>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">확인</button>

				</div>
			</div>
		</div>
	</div>







	<!-- --------------------신고 등록 모달창------------------------------------------------------------------------------------- -->


	<div class="modal" id="reportModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">신고사유를 선택해주세요</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">


					<div class="form-check">
						<input class="form-check-input" type="radio" name="reportReason"
							id="reportReason1" value="영리목적/스팸홍보성" checked> <label
							class="form-check-label" for="reportReason1"> 영리목적/스팸홍보성
						</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="reportReason"
							id="reportReason2" value="음란성/선정성"> <label
							class="form-check-label" for="reportReason2"> 음란성/선정성 </label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="reportReason"
							id="reportReason3" value="욕설/비방/혐오"> <label
							class="form-check-label" for="reportReason3"> 욕설/비방/혐오 </label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="reportReason"
							id="reportReason4" value="개인정보 노출"> <label
							class="form-check-label" for="reportReason4"> 개인정보 노출 </label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="reportReason"
							id="reportReason5" value="도배성(같은 내용의
							반복 게시)"> <label
							class="form-check-label" for="reportReason5"> 도배성(같은 내용의
							반복 게시) </label>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">취소</button>
					<button type="button" id="sendReport" class="btn btn-primary"
						data-dismiss="modal">신고하기</button>
				</div>
			</div>
		</div>
	</div>


	<!-- -----------------------------댓글 자바스크립트-------------------------------------------- -->
	<script>
<!--댓글 달기 -->
$("#writeReply").on("click", function(){
	let reply_content = document.querySelector("#reply_content")
	
  //alert(reply_content.value)
	let reply = {
			"board_num" : "${com.board_num}",
			"reply_content" : reply_content.value			
	}


<!--data: 변수를 json 문자열로 바꾸고, dataType으로 서버에서 리턴하는 데이터를 text로 인식하기로, contentType으로 body에 보내는 데이터를 json타입으로 전송할거라고 명시-->
	$.ajax({ 
		type: "post",
		url: "<%=request.getContextPath()%>/reply/writeReply",
		data: JSON.stringify(reply),
		dataType: 'text',
		contentType: 'application/json',
		success : function(result){
			result = result.trim()
			result.replace(" ","")
			
			//alert("["+result+"]");
			//alert(result);
		
			let newReply = document.querySelector('#replyList')
			let reply_num = JSON.parse(result).reply_num;
			let nickname = document.querySelector('#reply_nickname').value
			let content = document.querySelector('#reply_content').value
			let today = new Date();
			let year =today.getFullYear();
			let month = today.getMonth()+1; 
			month = (month < 10 ? '0'+month:month);
			let date = today.getDate();
			let regdate = year + '-' + month + '-' + date;
			
			let temp = 'id="r'+reply_num+'"'
			
			let pic = 	<c:if test="${memberPicture eq null }">
							'<img class="pic_mini" src="<%=request.getContextPath()%>/img/profile_empty.jpg">'
						</c:if>
						<c:if test="${memberPicture ne null }">
							'<img class="pic_mini" src="<%=request.getContextPath()%>/imgupload/${memberPicture}">'
						</c:if>;
			
			let line =  '<div class = "reply"       '+temp+' >'
						+ '<div class = "row">'
						+ '<div class = "col-md-10" id = "replyInfo">'
						+ '<input type = "hidden" id = "reply_num" name = "reply_num" value= '+reply_num+'>'
						+ '<p> '+ pic +' '+ nickname+' · '+ regdate +'</p>'
			          	+ '</div>'
			          	+ '<div class = "col-md-2">'
			          	+ '<input type = "button" class = "btn btn-light"       onclick="deleteReply(\''+reply_num +'\')" value = "삭제"/>'
			          	+ '</div>'
			      		+ '</div>'
			      		+ '<div class = "col-md-12 replyTxt">'
						+  '<p>'+content+'</p>'
						+ '</div>'
			            +  '<hr align="left" style="background-color: 333b3d; height:0.7px;" />'
			        	+ '</div>' ;
			            
			 newReply.innerHTML +=line
			 			 
			 reply_content.value = "";

		},
		error: function (result){
			console.log(result);
			alert("error");
		}	
	}); <!-- end ajax -->
	
})





<!--댓글삭제--------------------------------------------------------------------------------------------->
function deleteReply(num){

	//alert(num)
	var deleteReply = {
					"board_num" : "${com.board_num}",
					"reply_num" : num
	}

	
	$.ajax({
		type: 'post',
		url : "<%=request.getContextPath()%>/reply/deleteReply",
				data : JSON.stringify(deleteReply),
				dataType : 'text',
				contentType: 'application/json',
				success : function(result) {
					alert("댓글이 삭제됩니다");
					//alert(result)
					var deleteReply = document.querySelector('#r' + num)
					//alert(deleteReply.innerHTML) //삭제할 내용 확인
					deleteReply.innerHTML = "";

				},
				error : function(result) {
					console.log(result);
					alert("error");
				}

			})

		}
		
$('#deleteReply').on("click", function() {
		var reply_num = document.querySelector("#reply_num")

	})
		

<!--신고 전달 ---------------------------------------------------------------------------------------------------------------------->
$("#sendReport").on("click",function(){
	let report_reason = $('input[name=reportReason]:checked').val();
	
	
	let report = {
			"report_reason": report_reason,
			"board_num": "${com.board_num}"
	}
	
	$.ajax({
		type: "post",
		url: "<%=request.getContextPath()%>/report/sendReport",
				data : JSON.stringify(report),
				dataType : 'text',
				contentType: "application/json",
				success : function(result) {
					alert("신고되었습니다");
						//alert(report_reason); //option값 잘 들어오는지 확인
						let button = document.querySelector("#reportButton");
						button.disabled = true;
						
						
				},
				error : function(result) {
					console.log(result);
					alert("신고처리가 되지 않습니다.");
				}
			})
		})
	</script>



</body>

</html>