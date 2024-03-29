<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="path" value="${pageContext.request.contextPath}" />

<%-- <jsp:include page= "/WEB-INF/views/common/header.jsp"/> --%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="collapsed" name="style" />
	<jsp:param value="data-hover='active'" name="hover" />
</jsp:include>
<%-- <%@ include file="/WEB-INF/views/common/sideBar.jsp"%> --%>
<jsp:include page="/WEB-INF/views/common/sideBar.jsp">
   <jsp:param value="${edocCountWait }" name="edocCountWait"/>
</jsp:include>
<div class="hk-pg-wrapper">
	<div class="container-xxl">
		<!-- Page Header -->
		<div class="hk-pg-header pg-header-wth-tab pt-7 pb-2">
			<div class="d-flex">
				<div class="d-flex flex-wrap justify-content-between flex-1">
					<div class="mb-lg-0 mb-2 me-8">
						<h1 class="pg-title">세부근무현황  - ${loginEmp.emp_name }님</h1>
					</div>
				</div>
			</div>
		</div>
		<!-- /Page Header -->
		<div class="d-flex justify-content-end mb-1 mt-3">
			<button class="btn btn-dark" onclick="exceldownload();">근무내역다운로드</button>
		</div>
		<section class="workList">
			<!-- <table id="datable_1" class="table nowrap w-100 mb-5"> id="datable_1" 에 체크박스생성하는 js걸려있음-->
			<table class="table nowrap w-100 mb-5 "> 
				<thead>
					<tr>
						<th>
							<span class="form-check mb-0"> 
								<input class="form-check-input" type="checkbox"  id="checkAll">
								<label class="form-check-label" for="checkAll"></label>
							</span>
						</th>
						<th>순번</th>
						<th>출근일자</th>
						<th>출근시간</th>
						<th>퇴근시간</th>
						<th>지각여부</th>
						<th>조퇴여부</th>
						<th>결근여부</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty  workView}">
						<c:forEach var="w" items="${workView }" varStatus="status">
							<tr>
								<td>
									<div class="form-check">
								      <input class="form-check-input" type="checkbox" id="checkbox1">
								      <label class="form-check-label" for="checkbox1"></label>
								    </div>
								</td>
								<td>
									<div class="media align-items-center">
										<div class="media-body">
											<span class="badge badge-soft-light my-1  me-2"><c:out value="${status.count }"/></span>
										</div>
									</div>
								</td>
								<td><span class="badge badge-soft-success my-1  me-2"><c:out value="${w.workDay }"/></span></td>
								<td><span class="badge badge-soft-violet my-1  me-2"><fmt:formatDate value="${w.workStartTime }" pattern="HH:mm:ss"/></span></td>
								<td><span class="badge badge-soft-warning my-1  me-2"><fmt:formatDate value="${w.workEndTime }" pattern="HH:mm:ss"/></span></td>
								<td><span class="badge badge-soft-danger my-1  me-2"><c:out value="${w.workRealtime.workLate }"/></span></td>
								<td><span class="badge badge-soft-danger my-1  me-2"><c:out value="${w.workRealtime.workFastEnd }"/></span></td>
								<td><span class="badge badge-soft-danger my-1  me-2"><c:out value="${w.workRealtime.workAbsence }"/></span></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</section>
		
	</div>
</div>
<style>
	.workList{
	overflow: auto;
	height: 700px;
	}
	.workList::-webkit-scrollbar {
	  display: block;
	}
	::-webkit-scrollbar-thumb {
	    background-color: lightblue;
	}

</style>
<script>
	function exceldownload() {
		location.assign("${path}/work/exceldownload");
	}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>