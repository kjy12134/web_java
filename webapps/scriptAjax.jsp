<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" name="id" id="id">
	<input type="text" name="pwd" id="pwd">
	<button type="button" id="ajaxBtn">AJAX</button>
	<p />
	<div id="result"></div>
	<button type="button" id="sendBtn">SEND</button>

	<script type="text/javascript"
		src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
	
		$(document).ready(function() {
			$("#ajaxBtn").bind("click", function(e) {
				console.log(e);
				$.ajax ({
					url 	 : "ajax.do",
					type	 : "post",
					data	 : {id : $("#id").val(), pwd : $("#pwd").val()},
					dataType : "json",
					success	 : function(obj) { <%-- dataType은 json // obj : data로 들어오는 객체, key로 접근  --%>
						$("#result").html(obj.id);
						//alert(obj); dataType : html
					},
					error	 : function() {
						alert("error");
					}
				});
			});
			
			
			//////////////////////////////////////////
			$("#sendBtn").click(function() {
				location.href = "json.sinc"; //get방식 : json.sinc?id="+$("#id").val()
				
			})
		});
		
	</script>
</body>
</html>