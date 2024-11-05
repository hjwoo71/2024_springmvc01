<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 II </title>
<style type="text/css">
	a { text-decoration: none;}
	table{width: 600px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 600px; margin:auto; text-align: center;}
</style>

</head>
<body>
	<div>
		<h2>방 명 록 II : 내용화면</h2>
		<hr>
		<p>[<a href="/gb2_list">목록으로 이동]</a></p>
		<form method="post" action="${pageContext.request.contextPath}/GuestBook2Controller">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td>${gb2vo.gb2_name }</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제  목</td>
					<td>${gb2vo.gb2_subject}</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td>${gb2vo.gb2_email}</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">첨부파일</td>
					<c:choose>
						<c:when test="${empty gb2vo.gb2_file_name}" >
							<td><b>첨부 파일 없음</b></td>
						</c:when>
						<c:otherwise>
							<td>
								<a href="/guestbook2_down?gb2_file_name=${gb2vo.gb2_file_name}">
									<img src="resources/upload/${gb2vo.gb2_file_name}" width="300px"> 
								</a>
								${gb2vo.gb2_file_name}
							</td>
						</c:otherwise>
					</c:choose>
					
				</tr>
				<tr align="left">
					<td colspan="2">
						<pre style="padding-left: 15px">${gb2vo.gb2_content }</pre> 
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="hidden" name="gb2_idx" value="${gb2vo.gb2_idx }">
							<input type="hidden" name="gb2_pw" value="${gb2vo.gb2_pw }">
							<input type="button" value="수정" onclick="update_go(this.form)">
							<input type="button" value="삭제" onclick="delete_go(this.form)">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		function update_go(f) {
			f.action="/gb2_update";
			f.submit();
		}
		function delete_go(f) {
			f.action="/gb2_delete";
			f.submit();
		}
	</script>
</body>
</html>















