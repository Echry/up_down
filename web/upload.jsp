<%--
  Created by IntelliJ IDEA.
  User: Ederment
  Date: 2020-10-28
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form enctype="multipart/form-data" action="/upload" method="post">
        <input name="desc" type="text"/><br>
        <input name="img" type="file"/><br>
        <input type="submit" value="上传"/>
    </form>
</body>
</html>
