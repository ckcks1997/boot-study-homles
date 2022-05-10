<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link href="<%=request.getContextPath()%>/css/boardlist.css"
	rel="stylesheet" type="text/css">
<style>
.pic_mini {
	width: 25px;
	height: 25px;
	border-radius: 70%;
}
</style>


<title>스터디 홈즈</title>
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
			<!------------------------- 왼쪽 사이드메뉴 -----------------					---------------------------------------------------->
			<%--aside부분 --%>
			<%@include file="/WEB-INF/common/community_menu.jsp"%>


			<!-- ---------------------------메인검색-------------------------------------------------------- -->
			<div class="main col-lg-9">
				
					<div class="col-md-4">
					<c:if test="${searchData != null && searchData != '' }">
						<p style="font-size: 15px; font-weight: lighter;">
							<i class="fa-solid fa-magnifying-glass" style=""></i>
							&nbsp;
							" ${searchData } " 검색결과 ${boardcount}개
						</p>
					</c:if>
						<h2 style="font-weight: bold">${boardName}</h2>

						<hr align="left" width="170px"
							style="background-color: #c47100; height: 1px;" />
					</div>
					
					
					
				





				<form
					action="<%=request.getContextPath()%>/community/comBoardList?boardid=${boardid}&part=${part}&searchData=${searchData}">
					<input type="hidden" name="boardid" value="${boardid}" />
					<div class="row">
						<div class="col-xs-12 col-sm-11 col-md-10">
							<div class="d-flex flex-row align-items-center">
								<div class="col-md-2">
									<select class="custom-select" name="part">
										<option value="title">제목</option>
										<option value="content">내용</option>
									</select>


								</div>

								<input type="text" class="form-control rounded"
									name="searchData" placeholder="내용을 검색해보세요!" required="required" />
							</div>
						</div>
						<div class="col-xs-12 col-sm-1 col-md-2 btn-search">
							<input class="btn btn-dark" type="submit" value="검색" />
						</div>
					</div>

				</form>
				<br />
				<!-- ------------------------------------------------------------------------------------ -->
				
				<div class="row col-sm-9 divide" style="float: left">
					<%--community컨트롤러 comBoardList()에 의해 boardid 가져오기 가능 --%>
				
					<c:if test="${boardid != 4 && boardid != 5}">
						<%--공지와 문의게시판이 아닌 곳에만 적용 --%>
						<a
							href="<%=request.getContextPath()%>/community/comBoardList?boardid=${boardid}&part=${part}&searchData=${searchData}">최신순</a>
					&nbsp;&nbsp;<strong> · </strong>&nbsp;&nbsp;
							<a
							href="<%=request.getContextPath()%>/community/comBoardList?boardid=${boardid }&sort=replycnt&part=${part }&searchData=${searchData}">댓글순</a>
					&nbsp;&nbsp;<strong> · </strong>&nbsp;&nbsp; <a
							href="<%=request.getContextPath()%>/community/comBoardList?boardid=${boardid }&sort=readcnt&part=${part}&searchData=${searchData}">조회수순</a>
					</c:if>
				</div>


				<c:choose>
					<c:when test="${memberNickname != '관리자' }">
						<%--만약 1)일반유저이고 --%>
						<c:if test="${boardid != 4}">
							<%--공지게시판이 아니면 --%>
							<%--글쓰기 버튼을 띄운다 --%>
							<div class="mb-2" style="float: right">
								<button type="button" class="btn btn-dark"
									onclick="location.href='<%=request.getContextPath()%>/community/comWriteForm'">글쓰기
								</button>
							</div>
						</c:if>
					</c:when>

					<c:otherwise>
						<%--관리자이고 --%>
						<c:if test="${boardid == 4 }">
							<%--공지게시판이면 --%>
							<%--글쓰기 버튼을 띄운다 --%>
							<div class="mb-2" style="float: right">
								<button type="button" class="btn btn-dark"
									onclick="location.href='<%=request.getContextPath()%>/community/comWriteForm'">글쓰기
								</button>
							</div>
						</c:if>
					</c:otherwise>
				</c:choose>
				<br />


				<!-------------메인 커뮤니티 게시판 --------------------------------------------------------------------------------------------------------- -->
				<div class="container">
					<table class="table">
						<c:if test="${empty list }">
							<!-- list.size() 가 0이면 -->
							<hr align="left" />
							<p>작성된 글이 없습니다.</p>

						</c:if>

						<c:forEach var="com" items="${list}">
							<tr>
								<td>
									<div class="row">

										<div class="col-sm-9">
											<input type="hidden" name="board_num"
												value="${com.board_num}"> <a
												href="<%=request.getContextPath() %>/community/comBoardInfo?board_num=${com.board_num}&sort=${sort }&part=${part}&searchData=${searchData}"
												style="color: black">
												<p style="font-size: 17px; font-weight: bold;">
													${com.title}</p> <br />
												<h6 style="color: gray;">
													<c:if test="${com.picture eq null }">
														<img class="pic_mini"
															src="<%=request.getContextPath()%>/img/profile_empty.jpg">
													</c:if>
													<c:if test="${com.picture ne null }">
														<img class="pic_mini"
															src="<%=request.getContextPath()%>/imgupload/${com.picture}">
													</c:if>
													<small> ${com.nickname} · ${com.regdate} </small>
												</h6>
											</a>


										</div>
										<div class="col-sm-3 reaction">
											<div class="circle">
												<div class="reactions">

													<c:if test="${boardid != 5}">
														<%--문의 외의 게시판에 조회수 출력 | 문의 게시판 조회수 X --%>
														<div class="readcnt">
															<span> <svg xmlns="http://www.w3.org/2000/svg"
																	width="16" height="16" fill="currentColor"
																	class="bi bi-eye" viewBox="0 0 16 16">
  													<path
																		d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z" />
  													<path
																		d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z" />
													</svg>
															</span>${com.readcnt }읽음
														</div>
													</c:if>

													<div class="comment">
														<span> <svg xmlns="http://www.w3.org/2000/svg"
																width="16" height="16" fill="currentColor"
																class="bi bi-chat-square-text" viewBox="0 0 16 16">
  													<path
																	d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1h-2.5a2 2 0 0 0-1.6.8L8 14.333 6.1 11.8a2 2 0 0 0-1.6-.8H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h2.5a1 1 0 0 1 .8.4l1.9 2.533a1 1 0 0 0 1.6 0l1.9-2.533a1 1 0 0 1 .8-.4H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z" />
													<path
																	d="M3 3.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3 6a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 6zm0 2.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z" />
													</svg>
														</span>${com.replycnt}개의 댓글
													</div>
												</div>
											</div>

										</div>

									</div>
								</td>
							</tr>
						</c:forEach>

					</table>
				</div>
				<!------------- 아래 페이징네이션 ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
				<ul class="pagination justify-content-center">
					<li
						class='page-item <c:if test ="${startPage <= bottomLine }"> disabled </c:if> '><a
						class="page-link"
						href="<%=request.getContextPath()%>/community/comBoardList?boardid=${boardid }&pageNum=${startPage-bottomLine}&sort=${sort }&part=${part}&searchData=${searchData}">이전</a></li>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<li
							class='page-item <c:if test = "${i==pageInt}" > active active2</c:if> '>
							<a class="page-link"
							href="<%=request.getContextPath()%>/community/comBoardList?boardid=${boardid }&pageNum=${i}&sort=${sort }&part=${part}&searchData=${searchData}">${i}</a>
						</li>
					</c:forEach>
					
					<li
						class='page-item <c:if test ="${endPage >= maxPage}"> disabled </c:if>  '>
						<a class="page-link"
						href="<%=request.getContextPath()%>/community/comBoardList?boardid=${boardid }&pageNum=${startPage+bottomLine}&sort=${sort }&part=${part}&searchData=${searchData}">다음</a>
					</li>
				</ul>
			</div>
		</div>
	</div>


	<br>
	<br>
</body>
</html>