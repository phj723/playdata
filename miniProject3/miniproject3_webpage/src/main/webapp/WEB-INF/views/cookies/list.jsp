<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="util" uri="/ELFunctions"%>

<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<title>쿠키 목록</title>
<meta charset="utf-8">
<script type="text/javascript">
	function read(name) {
		var url = "${root}/cookies/read";
		url += "?name=" + name;
		location.href = url;
	}
</script>
</head>

<body>
	<div class="container">

		<c:choose>
			<c:when test="${param.grade == null}">
				<c:set var="str">모든</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="str">${param.grade}</c:set>
			</c:otherwise>
		</c:choose>

		<h3 style="text-align: center;">🍪 ${str} 쿠키 목록 📃</h3>
		<br>

		<form class="form-inline"
			  method="post" 
			  action="list"
			  style="float: right; margin-bottom: 20px;">

			<div class="form-group">
				<select name="col" class="form-control">
					<option value="name" <c:if test="${col==name}">selected</c:if>>쿠키 이름</option>
					<option value="position"
						<c:if test="${col==position}">selected</c:if>>전투 위치</option>
					<option value="cmt" <c:if test="${col==cmt}">selected</c:if>>쿠키 설명</option>
					<option value="total" <c:if test="${col==total}">selected</c:if>>모든 쿠키</option>
				</select>
			</div>

			<div class="form-group">
				<input type="text" class="form-control" name="word"
					required="required" value="${word}">
			</div>

			<button class="btn btn-default">검색</button>
			<button type="button" class="btn btn-default"
				onclick="location.href='./create'">등록</button>
		</form>

		<c:forEach var="dto" items="${list}">
			<table class="table table-bordered">
				<tr>
					<td rowspan="2" class="col-sm-2" style="text-align: center;">
						<a href="javascript:read('${dto.name}')"> 
							<img src="${root}/storage/${dto.fname}" class="img-rounded"	width="150px" height="150px">
					</a>
					</td>
					<th class="col-sm-2"
						style="text-align: center; vertical-align: middle;">쿠키 이름</th>
					<td class="col-sm-3"
						style="text-align: center; vertical-align: middle;"><a
						href="javascript:read('${dto.name}')">${dto.name}</a></td>
					<th class="col-sm-2"
						style="text-align: center; vertical-align: middle;">쿠키 등급</th>
					<td class="col-sm-3"
						style="text-align: center; vertical-align: middle;">${dto.grade}</td>
				</tr>
				<tr>
					<th class="col-sm-2"
						style="text-align: center; vertical-align: middle;">쿠키 유형</th>
					<td class="col-sm-3"
						style="text-align: center; vertical-align: middle;">${util:typename(dto.type)}</td>
					<th class="col-sm-2"
						style="text-align: center; vertical-align: middle;">전투 위치</th>
					<td class="col-sm-3"
						style="text-align: center; vertical-align: middle;">${dto.position}</td>
				</tr>
			</table>
		</c:forEach>

		${paging}

	</div>
</body>
</html>