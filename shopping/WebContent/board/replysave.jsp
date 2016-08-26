<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	 request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="bean" class="shop.board.BoardBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="boardMgr" class="shop.board.BoardMgr"/>
<%
String spage = request.getParameter("page");
int num = bean.getNum();
int gnum = bean.getGnum();
int onum = bean.getOnum() + 1;
int nested = bean.getNested() + 1;

System.out.println("replysave= gnum:" + gnum + ", onum:" + onum + ", Nested: " +nested);

//같은 그룹 내에서 새로운 댓글의 onum 보다 크거나 같은 값을 댓글 입력 전에 먼저 수정하기.
//작으면 수정하지 않음

boardMgr.updateReadcnt(gnum, onum); //onum 갱신처리
//댓글 저장
bean.setOnum(onum);
bean.setNested(nested);
bean.setBip(request.getRemoteAddr());
bean.setBdate();
bean.setNum(boardMgr.currentGetNum() + 1); // 댓글이지만 새 글로 취급

 int nnn = bean.getNested();
System.out.println("bean에서 가져온 Nested: " + nnn); 

boardMgr.saveReply(bean);
response.sendRedirect("boardlist.jsp?page="  + spage);

%>