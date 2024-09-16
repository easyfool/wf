<%@ page contentType="text/html; charset=utf-8" language="java" %>
<html>
<head>
    <title>
        文件上传下载
    </title>
</head>
<body>
<form action="/file2/uploadServlet" enctype="multipart/form-data" method="post">
    UserName <input type="text" name="username"><br/>
    Avatar <input type="file" name="photo"> <br>
    <button type="submit">提交</button>
</form>
</body>
</html>