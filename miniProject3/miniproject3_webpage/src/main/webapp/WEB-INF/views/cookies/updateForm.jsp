<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>쿠키 정보 수정</title>
<meta charset="utf-8">
<style type="text/css">
#need {
	color: red;
}
</style>

<script type="text/javascript">
	function inCheck(f) {
		if (f.grade.value.length == 0) {
			alert("쿠키 등급을 입력하세요");
			f.grade.focus();
			return false;
		}
		if (f.type.selectedIndex == 0) {
			alert("쿠키 유형을 선택하세요");
			f.type.focus();
			return false;
		}
		if (f.position.value.length == 0) {
			alert("전투 위치를 입력하세요");
			f.position.focus();
			return false;
		}		
		if (f.skillname.value.length == 0) {
			alert("스킬 이름을 입력하세요");
			f.skillname.focus();
			return false;
		}
	}
</script>
</head>

<body>
	<div class="container">
		<h3 style="text-align:center;">🍪 ${dto.name} 쿠키의 정보 수정</h3><br>
		
		<label class="col-sm-offset-2 col-sm-10" style="margin-bottom:20px;">(<span id="need"> * </span>필수 입력 사항 )</label>

		<form class="form-horizontal" 
			  action="update" 
			  method="post" 
			  name='frm'
			  onsubmit="return inCheck(this)"
			  enctype="multipart/form-data">
			
			<input type="hidden" name="name" value="${dto.name}">
			<div class="form-group">
				<label class="control-label col-sm-2" for="name">쿠키 이름</label>
				<div class="col-sm-3">${dto.name}</div>
			</div>
			
			<input type="hidden" name="oldfile" value="${param.oldfile}">    
            <div class="form-group">
		      <label class="control-label col-sm-2" for="oldfile">기존 쿠키 사진</label>
		      <div class="col-sm-3">
		        <img src="${pageContext.request.contextPath}/storage/${param.oldfile }" 
		        class="img-rounded" class="img-rounded"	width="auto" height="200px">
		      </div>
		    </div>
		    
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="fnameMF">변경할 쿠키 사진</label>
		      <div class="col-sm-3">          
		        <input type="file" class="form-control" id="fnameMF" 
		        name="fnameMF" accept=".jpg,.png,.gif" required="required">
		      </div>
		      ${dto.fname}
		    </div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="grade"><span id="need">* </span>쿠키 등급</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="grade" value="${dto.grade}"
						name="grade">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="type"><span id="need">* </span>쿠키 유형</label>
				<div class="col-sm-3">
					<select name="type" class="form-control">
						<option value="0">쿠키 유형을 선택하세요</option>
						<option value="A01">돌격형</option>
						<option value="A02">마법형</option>
						<option value="A03">방어형</option>
						<option value="A04">사격형</option>
						<option value="A05">지원형</option>
						<option value="A06">침투형</option>
						<option value="A07">폭발형</option>
						<option value="A08">회복형</option>
					</select>
					
					<script type="text/javascript">
						document.frm.type.value = '${dto.type}';
					</script>
					
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="position"><span id="need">* </span>전투 위치</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="position" value="${dto.position}"
						name="position">
				</div>
			</div>			
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="cmt">쿠키 설명</label>
				<div class="col-sm-6">
					<textarea class="form-control" id="cmt"	name="cmt" 
					style="height:100px;">${dto.cmt}</textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="skillname"><span id="need">* </span>스킬 이름</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="skillname" value="${dto.skillname}"
						name="skillname">
				</div>
			</div>
			
			<input type="hidden" name="oldsfile" value="${param.oldsfile}">    
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="oldsfile">기존 스킬 사진</label>
		      <div class="col-sm-3">
		        <img src="${pageContext.request.contextPath}/storage/${param.oldsfile }" 
		        class="img-rounded" class="img-rounded"	width="auto" height="80px">
		      </div>
		    </div>
		    
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="sfnameMF">변경할 스킬 사진</label>
		      <div class="col-sm-3">          
		        <input type="file" class="form-control" id="sfnameMF" 
		        name="sfnameMF" accept=".jpg,.png,.gif" required="required">
		      </div>
		      ${dto.sfname}
		    </div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="skillcmt">스킬 설명</label>
				<div class="col-sm-6">
					<textarea class="form-control" id="skillcmt" name="skillcmt" 
					style="height:80px;">${dto.skillcmt}</textarea>
				</div>
			</div>
		
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-5">
					<button type="submit" class="btn btn-default">수정</button>
					<button type="reset" class="btn btn-default">초기화</button>
					<button type="button" class="btn btn-default" onclick="history.back()">수정 취소</button>
				</div>
			</div>
		</form>
	</div>
	<br>
	<br>
</body>
</html>