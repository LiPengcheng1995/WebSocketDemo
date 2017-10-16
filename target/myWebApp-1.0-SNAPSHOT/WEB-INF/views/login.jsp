<%--
  Created by IntelliJ IDEA.
  User: lm
  Date: 2017/10/13
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
请输入你的名字，点击确认后跳转，不许输PublishPage

<!--之前这里有大问题，这里的表单跳转都是以域名为基础的，如果项目部署不是在根目录下就挂了-->
<form action="${pageContext.request.contextPath}/ListernPage" method="get" id="loginForm">
<input type="text" name="userName">
    <input type="submit" value="确认">
</form>


</body>
</html>
