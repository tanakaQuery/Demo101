<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.SellerInfo" %>
<%
SellerInfo seller = (SellerInfo)session.getAttribute("seller");
Boolean pageMode = true;
if (seller.getSellingHouseID() != null) {
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
<h1><%=seller.getName() %>様のマイページ</h1><br>
<hr>
<% if (pageMode) {%>
 <p> <%= seller.getSellingHouseID() %> 売り出し中</p>

<% } else { %>
<p> 新規物件登録</p>
<% } %>



</body>
</html>