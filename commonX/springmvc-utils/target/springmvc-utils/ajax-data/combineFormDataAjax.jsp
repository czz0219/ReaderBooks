<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ajax提交表单</title>
    <script type="text/javascript" src="jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="jquery.form.js"></script>
</head>

<body>
<!-- 共有的某行数据 -->
<input type="hidden" id="myid" name="myid" value="123456">
<form action="form_submit" method="post" id="myform" name="myform">
    <input type="text" id="mytext" name="mytext" value="text"/>
    <button type="button" id="mybutton" name="mybutton">提交</button>
</form>

<!-- 导入必须的文件 -->
<script type="text/javascript">
    <!--向后台发送post请求-->
    $(document).ready(function() {
        $("#mybutton").click(function () {
            //准备数据
            var myform=$('#myform').serialize();//表单序列化
            //myform += "&myid="+encodeURIComponent($('#myid').val());//方法1：按格式拼接
            myform+= "&"+$('#myid').serialize();//方法2：拼接上需要的字符串
            alert(myform);
            $.ajax({
                type:"POST",//提交请求的方式
                cache:true,//是否有缓存
                url:"${pageContext.request.contextPath}/form",//访问servlet的路径
                dataType:"json",//没有这个，将把后台放会的json解析成字符串
                data:myform,//把内容序列化
                async:true,//是否异步
                error:function(request) {//请求出错
                    alert("出错");
                },
                success:function(data) {//获得返回值
                    alert("服务端返回"+JSON.stringify(data));
                }
            })

        });
    });
</script>
</body>
</html>