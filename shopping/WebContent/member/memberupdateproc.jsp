<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="bean" class="shop.member.MemberBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="memberMgr" class="shop.member.MemberMgr"/>
<%
request.setCharacterEncoding("utf-8");
String id = (String)session.getAttribute("idKey");
boolean b = memberMgr.memberUpdate(bean, id);

if(b = true){
%>
	<script>
	alert("수정 성공");
	location.href = "../guest/guest_index.jsp";
	</script>
<%
}else{
%>
	<script>
	alert("수정 실패!\n 관리자에게 문의 바람");
	history.back();
	</script>
<%	
}
%>