<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Boolean isSetHouseID = (Boolean)session.getAttribute("isSetHouseID");
Boolean pageMode = true;

if (isSetHouseID != null) {
	pageMode = true;
	} else {
	pageMode = false;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./view/css/mainStyles.css">
<link rel="stylesheet" href="./view/css/headerFooterStyles.css">
</head>
<body>
<jsp:include page="subViews/header.jsp"></jsp:include>
<hr>
<% if (pageMode) { %>
<% if (isSetHouseID) { %>
<h1>物件オーナーへ購入検討依頼をしました</h1>
<hr>
<% } else { %>
<h1>物件オーナーへの購入検討依頼が取り消しされました</h1>
<hr>
<% } %>
<% } else { %>
<br>
<% } %>
<a href="<%=request.getContextPath()%>/BuyerDataController?page=home">物件一覧へ戻る</a>

</body>
</html>