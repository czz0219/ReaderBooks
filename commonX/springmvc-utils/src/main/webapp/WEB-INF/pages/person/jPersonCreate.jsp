<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2018/7/1
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增页面</title>
</head>
<body>
<!--
\<\%=\%\>称作jsp表达式，用于将已经声明的变量或者表达式输出到网页上面。
-->
<form action="${pageContext.request.contextPath}/person/insert.action" method="post">
    <h3>新增人员信息</h3><br>
    <table>
        <tr>
            <td>编号</td>
            <td><input type="text" name="id"/></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input type="text" name="age"/></td>
        </tr>
        <tr>
            <td>入职时间</td>
            <td><input type="text" name="joinDate"/></td>
        </tr>
        <tr>
            <td><input type="submit" name="btnSubmit" value="保存"/></td>
            <td></td>
        </tr>
    </table>
</form>
</body>
</html>
