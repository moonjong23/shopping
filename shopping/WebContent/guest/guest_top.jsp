<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String memid = (String)session.getAttribute("idKey");
String log="";

if(memid == null){
	log = "<a href='login.jsp'>로그인</a>";
}else{
	log = "<a href='logout.jsp'>로그아웃</a>";
}

String mem="";

if(memid == null){
	mem = "<a href='../member/register.jsp'>회원가입</a>";
}else{
	mem = "<a href='../member/memberupdate.jsp'>회원수정</a>";
}

%>

<table style="width: 80%">
	<tr style="background-color: orange; text-align: center;">
		<td><%=log %></td>
		<td><%=mem %></td>
		<td><a href="productlist.jsp"><b>상품목록</b></a></td>
		<td><a href="cartlist.jsp"><b>장바구니</b></a></td>
		<td><a href="orderlist.jsp"><b>구매목록</b></a></td>
		<td><a href="../board/boardlist.jsp"><b>게시판</b></a></td>
	</tr>
</table>