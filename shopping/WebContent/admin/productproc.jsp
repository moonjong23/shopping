<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="shop.product.ProductMgr"></jsp:useBean>
<%
String flag = request.getParameter("flag");
boolean result = false;

if(flag.equals("insert")){
	result = productMgr.insertProduct(request);

}else if(flag.equals("update")){
	%>
	<script>
	alert("update");
	</script>
	<%
	//result = productMgr.updateProduct(request);
}else if(flag.equals("delete")){
	response.sendRedirect("productmanager.jsp");
}

if(result){
%>
	<script>
		alert("정상적으로 처리되었습니다.");
		location.href="productmanager.jsp";
	</script>
<%
}else{
%>
	<script>
		alert("오류 발생!\n프로그램 관리자에게 문의바람");
		location.href="productmanager.jsp";
	</script>
<%
}
%>