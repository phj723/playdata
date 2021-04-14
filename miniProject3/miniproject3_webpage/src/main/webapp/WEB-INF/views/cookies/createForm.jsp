<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>신규 쿠키 등록</title>
<meta charset="utf-8">
<style type="text/css">
@keyframes blink {
  from { color : white;}
  to { color : red;}
}
#warn {
	animation-name : blink;
	animation-duration : 1s;
	animation-iteration-count : infinite;
	color : red;
}
#need {
	color : red;
}
</style>

<script type="text/javascript">
	function inCheck(f) {
		if (f.name.value.length == 0) {
			alert("쿠키 이름을 입력하세요");
			f.name.focus();
			return false;
		}
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
		<h3 style="text-align:center;">🍪 새로운 쿠키 등록</h3><br>
		
		<label class="col-sm-offset-2 col-sm-10" style="margin-bottom:20px;">(<span id="need"> * </span>필수 입력 사항 )</label>
		
		<form class="form-horizontal" 
			  action="create" 
			  method="post" 
			  name='frm'
			  enctype="multipart/form-data" 
			  onsubmit="return inCheck(this)">
			  
			<div class="form-group">
				<label class="control-label col-sm-2" for="name"><span id="need">* </span>쿠키 이름</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="name"
						placeholder="쿠키 이름을 입력하세요" name="name">
				</div>
				<span id="warn">※ 쿠키 이름은 등록 후 수정이 불가능합니다.</span>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="fnameMF">쿠키 사진</label>
				<div class="col-sm-3">
					<input type="file" class="form-control" id="fnameMF" name="fnameMF"
						accept=".jpg,.gif,.png">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="grade"><span id="need">* </span>쿠키 등급</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="grade" 
						placeholder="쿠키의 등급을 입력하세요" name="grade">
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
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="position"><span id="need">* </span>전투 위치</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="position"
						placeholder="쿠키의 전투 위치를 입력하세요" name="position">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="cmt">쿠키 설명</label>
				<div class="col-sm-6">
					<textarea class="form-control" id="cmt"
						placeholder="쿠키에 대한 설명을 입력하세요" name="cmt" style="height:100px;"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="skillname"><span id="need">* </span>스킬 이름</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="skillname"
						placeholder="쿠키의 스킬 이름을 입력하세요" name="skillname">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="sfnameMF">스킬 사진</label>
				<div class="col-sm-3">
					<input type="file" class="form-control" id="sfnameMF" name="sfnameMF"
						accept=".jpg,.gif,.png">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="skillcmt">스킬 설명</label>
				<div class="col-sm-6">
					<textarea class="form-control" id="skillcmt"
						placeholder="쿠키의 스킬에 대한 설명을 입력하세요" name="skillcmt" style="height:80px;"></textarea>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-5">
					<button type="submit" class="btn btn-default">등록</button>
					<button type="reset" class="btn btn-default">초기화</button>
					<button type="button" class="btn btn-default" onclick="history.back()">등록 취소</button>
				</div>
			</div>
		</form>
	</div>
	<br>
	<br>
</body>
</html>