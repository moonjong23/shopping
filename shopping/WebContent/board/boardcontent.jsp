<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="boardMgr" class="shop.board.BoardMgr"/>
    <jsp:useBean id="dto" class="shop.board.BoardDto"/>
<%
	String num = request.getParameter("num");
	String spage = request.getParameter("page");
	
	System.out.println("boardcontent :" + num +" " + spage);
	
	boardMgr.updateReadcnt(num); //조회수 증가
	dto = boardMgr.getData(num); //dto에 담긴 자료를 필요한 값만 가져오기.
	
	String name = dto.getName();
	String pass = dto.getPass();
	String title = dto.getTitle();
	String mail = dto.getMail();
	String cont = dto.getCont();
	String bip = dto.getBip();
	String bdate = dto.getBdate();
	int readcnt = dto.getReadcnt();
	
	String apass = "*****"; //일반인은 *이 보임.
	String adminOk = (String)session.getAttribute("adminOk");
	
	if(adminOk != null){
		if(adminOk.equalsIgnoreCase("admin")){
			apass = pass; //관리자는 비밀번호가 보인다.
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게 시 판</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
</head>
<body>
<table>
	<tr>
		<td>
			<b>비밀번호:<%=apass%></b>
		</td>
		<td>
			<a href="reply.jsp?num=<%=num %>&page=<%=spage%>">
				<img src="../image/reply.gif" />
				</a>
				<a href="edit.jsp?num=<%=num %>&page=<%=spage%>">
				<img src="../image/edit.gif" />
				</a>
				<a href="delete.jsp?num=<%=num %>&page=<%=spage%>">
				<img src="../image/del.gif" />
				</a>
				<a href="boardlist.jsp?page=<%=page%>">
				<img src="../image/list.gif" />
				</a>
		</td>
	</tr>
</table>
<table>
	<tr height="30">
		<td>
			작성자 : <a href="mailto:<%=mail %>"><%=mail %></a>(ip : <%=bip %>)
		</td>
		<td>
			작성일 : <%=bdate %>
		</td>
		<td>
			조회수 : <%=readcnt %>
		</td>
	</tr>
	<tr>
		<td colspan="3" style="background: cyan">제목 : <%=title %></td>
	</tr>
	<tr>
		<td colspan="3">
			<textarea rows="10" style="width:99% " readonly="readonly"><%=cont %></textarea>
		</td>
	</tr>
</table>
</body>
</html>











































