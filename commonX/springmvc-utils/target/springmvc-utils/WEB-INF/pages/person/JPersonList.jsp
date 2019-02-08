<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2018/7/1
  Time: 11:02
  To change this template use File | Settings | File Templates.


  引入 jstl标签库
  Ctrl+Alt+s ->在Setting下找到languages&Frameworks—->Schemas and DTDs,
  External Schemas and DTDs框下点击+
  URI中填入http://java.sun.com/jsp/jstl/core (URL可以随便定义，用来映射你的c.tld位置),选择 Files中选择你需要用到的标签
  再在JSP文件中添加 taglib
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function formSubmit() {
            //通过js 进行post提交
            var tableIsNull = document.getElementById("personInfo").rows[0];
            if(tableIsNull == null){
                alert("all in null !")
            }else {
                document.forms[0].action ="${pageContext.request.contextPath}/person/delete.action";
                document.forms[0].submit();
            }

        }
    </script>
</head>
<div>
    <a href="${pageContext.request.contextPath}/person/toCreate.action">新增</a>
    <a href="#" onclick="formSubmit();">批量删除</a>
</div>

<body>
<div>
    <input type="file" id="file">
</div><br>

<form method="post">
    <h3>人员信息列表</h3><br>
    <table id="personInfo" border="1">
        <tr>
            <td>序号</td>
            <td>编号</td>
            <td>选择</td>
            <td>名字</td>
            <td>年龄</td>
            <td>参加工作日期</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${person_list}" var="p" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${p.id}</td>
                <td><input type="checkbox" name="id" value="${p.id}"/></td> <!--只有   <input/>标签才会被视为forms[]数组中的元素 -->
                <td>${p.name}</td>
                <td>${p.age}</td>
                <td>${p.joinDate}</td>
                <td><a href="${pageContext.request.contextPath}/person/toupdate.action?id=${p.id}">修改</a></td>
                <td><a href="${pageContext.request.contextPath}/person/deleteById.action?id=${p.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
