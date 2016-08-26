<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="memberMgr" class="shop.member.MemberMgr"/>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
boolean b = memberMgr.checkId(id);
//System.out.println("idcheck: " + b);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=id %>
<%
if(b == true){
%>
	는 이미 사용중입니다.<p/>
	<a href="#" onclick="opener.document.regForm.id.focus(); window.close()">닫기</a>	
<%
}else{ 
%>
는(은) 사용가능 합니다.<p/>
	<a href="#" onclick="opener.document.regForm.passwd.focus(); window.close()">닫기</a>	
<%	
}
%>

</body>
</html>