<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>쿠키 정보 삭제</title>
<meta charset="utf-8">
<style type="text/css">
@keyframes blink {
  from { opacity : 0;}
  to { opacity : 1;}
}
#warn {
	animation-name : blink;
	animation-duration : 0.5s;
	animation-iteration-count : infinite;
}
@keyframes blink2 {
  from { opacity : 1;}
  to { opacity : 0;}
}
#warn2 {
	animation-name : blink2;
	animation-duration : 0.5s;
	animation-iteration-count : infinite;
}
#red {
	color: red;
}
</style>

<script type="text/javascript">
  	$(function(){
  		$('#btnDel').on('click',function(){
  			var form = {
  						name : $('#name').val(),
  						fname : $('#oldfile').val(),
  						sfname : $('#oldsfile').val()
  						}//json객체
  			$.ajax({
  	            url: "./deleteAjax",
  	            type: "POST",
  	            data: JSON.stringify(form),
  	            contentType: "application/json; charset=utf-8;",
  	            dataType: "json",
  	            success: function(data){
  	                $('#red').text('');
  	                $('#red').text(data.str);
  	                $('#btnDel').hide();
  	                $('#btnCxl').hide();
  	              	$('#btnList').show();
  	            },
  	            error: function(request,status,error){
  	             alert("code = "+ request.status + " message = " + request.responseText + " error = " + error);
  	            }  
  	        });//ajax end
  		});//버튼 이벤트 설정		
  	});//페이지 로딩
  </script>
</head>

<body>
	<div class="container">
		<h3 style="text-align:center;">🍪 ${param.name} 쿠키 삭제</h3><br>
		
		<input type="hidden" name="name" id="name" value="${param.name}"> 
		<input type="hidden" name="oldfile" id="oldfile" value="${param.oldfile}"> 
		<input type="hidden" name="oldsfile" id="oldsfile" value="${param.oldsfile}">
		
		<p id="red" style="text-align:center; margin:30px;"><span id="warn">🚨 </span><span id="warn2">🚨 </span>삭제하면 복구할 수 없습니다. 정말로 삭제하시겠습니까?</p>
		
		<div class="form-group">
			<div style="text-align:center;">
				<button class="btn btn-default" id="btnDel">삭제</button>
				<button class="btn btn-default" id="btnCxl" onclick="history.back()">취소</button>
				<button class="btn btn-default" id="btnList" onclick="location.href='list'" style="display:none">쿠키 목록</button>
			</div>
		</div>
	</div>
</body>
</html>