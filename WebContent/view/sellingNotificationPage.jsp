<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.SellerInfo" %>
<%@ page import="model.SoldHouseInfo" %>
<%
SellerInfo seller = (SellerInfo)session.getAttribute("seller");
Boolean pageMode = true;
if (seller.getSellingHouseID() != 0) {
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

<% if (pageMode) {%>
<h1>物件の登録が完了しました</h1>

<% } else { %>
<h1>売り出し物件を削除しました</h1>

<% } %>

<a href="<%=request.getContextPath()%>/SellerDataController?page=mypage">マイページへ戻る</a>

</body>
</html>