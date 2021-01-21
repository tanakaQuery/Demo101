<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h1>物件オーナーへ購入検討依頼をしました</h1>
<h2>複数物件への依頼はできませんので、過去の依頼は削除されます</h2>
<hr>
<a href="<%=request.getContextPath()%>/BuyerDataController?page=home">物件一覧へ戻る</a>

</body>
</html>