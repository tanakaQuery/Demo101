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
	<button class="button mainHtml" onclick="location.href='<%=request.getContextPath()%>/BuyerDataController?page=guest'">ゲスト</button>
	<button class="button mainHtml" onclick="location.href='<%=request.getContextPath()%>/BuyerDataController?page=login'">ログイン</button>
	<button class="button mainHtml" onclick="location.href='<%=request.getContextPath()%>/BuyerDataController?page=new'">新規登録</button>

</body>
</html>