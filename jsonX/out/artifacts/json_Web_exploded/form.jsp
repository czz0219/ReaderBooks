<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ajax提交表单</title>
</head>
<body>
<form action="form_submit" method="post" id="myform" name="myform">
    <input type="text" id="mytext" name="mytext" value="mytext"/>
    <input type="text" id="name" name="name" value="name"/>
    <input type="text" id="age" name="age" value="age"/>
    <input type="text" id="email" name="email" value="email"/>
    <button type="button" id="mybutton" name="mybutton">提交</button>
</form>

<!-- 导入必须的文件 -->
<script type="text/javascript" src="jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="jquery.form.js"></script>
<script type="text/javascript">
    <!--向后台发送post请求-->
    $(document).ready(function() {
        $("#mybutton").click(function () {
            alert($('#myform').serialize());
            $.ajax({
                type:"POST",//提交请求的方式
                cache:true,//是否有缓存
                url:"${pageContext.request.contextPath}/form",//访问servlet的路径
                dataType:"json",//没有这个，将把后台放会的json解析成字符串
                data:$('#myform').serialize(),//把内容序列化
                async:true,//是否异步
                error:function(request) {//请求出错
                    alert("出错");
                },
                success:function(data) {//获得返回值
                    alert('接收成功:'+data);
                }
            })

        });
    });
</script>
</body>
</html>