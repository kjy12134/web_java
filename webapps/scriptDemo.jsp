<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="result"></div>

	<br />
	<button type="button" class="btn">scriptBtn</button>
	<br />
	<table>
		<tbody id="tbody">

		</tbody>
	</table>

	<select name="maker" id="maker">
		<option>benz</option>
		<option>audi</option>
		<option>BMW</option>
	</select>

	<select name="model" id="model">
		<option>선택하세요</option>
	</select>
	<p />
	<p />
	<p />
	<li><input type="checkbox" id="allchk"> <label>전체선택/해제</label></li>
	<hr />
	<div id="area">
		<li><input type="checkbox" name="box" value="a"><label>돈까스</label></li>
		<li><input type="checkbox" name="box" value="b"><label>육개장</label></li>
		<li><input type="checkbox" name="box" value="c"><label>사천면</label></li>
		<li><input type="checkbox" name="box" value="d"><label>도삭면</label></li>
		<li><input type="checkbox" name="box" value="e"><label>갈비탕</label></li>
	</div>

	<!-- script 선언  (하단부) -->
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
		var objAry = [ {
			seq : 1,
			title : '공지',
			content : '공지내용',
			writer : 'jslim',
			regdate : '2019',
			cnt : 0
		}, {
			seq : 2,
			title : '휴강안내',
			content : '휴강안내 내용',
			writer : 'jslim',
			regdate : '2019',
			cnt : 0
		} ];

		var objModel = {
			benz : [ 'c200', 'e220', 's500' ],
			audi : [ 'a4', 'a6', 'a8' ],
			bmw : [ '320d', '520d', '740d' ]
		};

		function displayAry(ary) {
			var txt = "";
			$.each(ary, function(idx, data) {
				txt += "<option>" + data + "</option>";
			})

			$("#model").html(txt);
		}

		/*jquery 사용 */
		$(document).ready(function() { // document실행 후 바로 javascript 실행하는 것. 만약 js코드를 body보다 위에 적은경우 ready를 써서 body가 실행 된 후에 js를 실행하도록 하는 것.
			///////////////////////////////////////
			$("#maker").change(function() {
				var maker = $(this).val(); // select box(maker)에서 선택된 값 가져오기(value)

				if (maker == 'benz') {
					displayAry(objModel.benz);
				}

				else if (maker == 'audi') {
					displayAry(objModel.audi);
				}

				else {
					displayAry(objModel.bmw);
				}
			})

			/////////////////////////////////////////
			$("#result").text("jquery 사용");

			$(".btn").click(function() {
				//window.alert("^&^");
				var txt = "";

				$.each(objAry, function(idx, obj) {
					txt += "<tr><td>" + obj.seq + "</td>";
					txt += "<td>" + obj.title + "</td>";
					txt += "<td>" + obj.content + "</td>";
					txt += "<td>" + obj.writer + "</td>";
					txt += "<td>" + obj.regdate + "</td>";
					txt += "<td>" + obj.cnt + "</td></tr>";
				})

				$("#tbody").html(txt);
				/*
					$("#tbody").empty();
					$("#tbody").append(txt);
				 */
			})

			////////////////////////////////////////
			$("#allchk").click(function(e) {
				if (this.checked) {
					$("input[name='box']:checkbox").each(function() {
						$(this).prop("checked", true);
					})
					
				} else {
					$("input[name='box']:checkbox").each(function() {
						$(this).prop("checked", false);
					})
				}
				//alert(this.checked); // $(this).checked (불가능), jquery 함수 호출
			});
			
			//////////////////////////////////////
			$("input[name='box']:checkbox").click(function() {
				var flag = true;
				
				$("input[name='box']:checkbox").each(function() {
					if ($(this).prop("checked") == false) {
						flag = false;
					}
				});

				if(flag) {
					$("#allchk").prop("checked", true);	
				} else {
					$("#allchk").prop("checked", false);	
				}
			});
			
			
		});
	</script>
</body>
</html>