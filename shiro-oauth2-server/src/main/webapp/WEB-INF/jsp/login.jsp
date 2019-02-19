<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action="" method="post">
    用户名：<input type="text" name="username" value="<shiro:principal/>"><br/>
    密 码：<input type="password" name="password"><br/>
    <input type="submit" value="登录">
</form>

</body>
</html>