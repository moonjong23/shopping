<%@page import="shop.member.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="shop.member.MemberMgr"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
** 관리자 전체회원관리 **<br>
<%@ include file="admin_top.jsp" %>

<table style="width:80%">
	<tr>
		<th>아이디</th><th>회원명</th><th>비번</th><th>이메일</th><th>전화</th><th>수정</th>
	</tr>
<%
	ArrayList<MemberBean> list = memberMgr.getMemberAll();
	for(MemberBean m : list){
%>
	<tr style="text-align: center;">
	<td><%=m.getId() %></td>
	<td><%=m.getName() %></td>
	<td><%=m.getPasswd() %></td>
	<td><%=m.getEmail() %></td>
	<td><%=m.getPhone() %></td>
	<td><a href="javascript:memUpdate('<%=m.getId()%>')"> 수정하기</a></td> <!--m.getId()값은 for문에 있어서 가져올 수 없기때문에 jsp>script.js>jsp 경로로 보냈다.-->
	</tr>
<%
	}
%>
</table>
<%@include file="admin_bottom.jsp"%>
<form action="memberUpdate2.jsp" name="updateFrm" method="post">
<input type ="hidden" name ="id" >
</form>
</body>
</html>























