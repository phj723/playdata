<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="util" uri="/ELFunctions"%>

<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>쿠키 정보</title>
<meta charset="utf-8">
<script type="text/javascript">
	function updateC() {
		var url = "update";
		url += "?name=${dto.name}";
		url += "&oldfile=${dto.fname}";
		url += "&oldsfile=${dto.sfname}";
		location.href = url;
	}

	function deleteAjax() {
		var url = "deleteAjax";
		url += "?name=${dto.name}";
		url += "&oldfile=${dto.fname}";
		url += "&oldsfile=${dto.sfname}";
		location.href = url;
	}
</script>
</head>

<body>
	<div class="container">
		<h3 style="text-align: center;">🍪 ${dto.name} 쿠키의 정보</h3><br>
		
		<table class="table table-bordered">
			<tr>
				<td rowspan="2" style="text-align: center; vertical-align: center; width: 250px;">
					<img src="${root}/storage/${dto.fname}" class="img-rounded"	width="auto" height="200px">
				</td>
				<td style="vertical-align: middle; font-size: 14pt;">${dto.name} 쿠키</td>
			</tr>
			<tr>
			<c:choose>
				<c:when test="${dto.cmt != null}">
					<td>${dto.cmt}</td>
				</c:when>	
				<c:otherwise>
					<td>😨 아직 쿠키에 대한 코멘트가 없습니다.</td>
				</c:otherwise>
			</c:choose>
			</tr>
			<tr>
				<td style="text-align: center; vertical-align: center;">유형</td>
				<td>${util:typename(dto.type)}</td>
			</tr>
			<tr>
				<td style="text-align: center; vertical-align: center;">위치</td>
				<td>${dto.position}</td>
			</tr>
			<tr>
				<td style="text-align: center; vertical-align: center;">등급</td>
				<td>${dto.grade}</td>
			</tr>
			<tr>
				<td rowspan="2" style="text-align: center; vertical-align: center;">
					<img src="${root}/storage/${dto.sfname}" class="img-rounded" width="auto" height="80px">
				</td>
				<td>${dto.skillname}</td>
			</tr>
			<tr>
			<c:choose>
				<c:when test="${dto.skillcmt != null}">
					<td>${dto.skillcmt}</td>
				</c:when>	
				<c:otherwise>
					<td>🙊 아직 쿠키 스킬에 대한 코멘트가 없습니다.</td>
				</c:otherwise>
			</c:choose>
			</tr>
		</table>

		<div style="text-align: center">
			<button class="btn btn-default" onclick="location.href='list'">쿠키 목록</button>
			<button class="btn btn-default" onclick="updateC()">정보 수정</button>
			<button class="btn btn-default" onclick="deleteAjax()">쿠키 삭제</button>
		</div>
	</div>
	<br>
	<br>
</body>
</html>