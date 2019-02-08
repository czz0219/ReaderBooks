<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script type="text/javascript" src="../ajax-data/jquery-3.3.1.min.js"></script>

    <script type="text/javascript">

        $(document).ready(function(){
            $("button").click(function(){
                alert("开始发送Ajax！");
                $.ajax({
                    type : "POST",
                    url : "${pageContext.request.contextPath}/testJSON.action",
                    contentType : "application/json; charset=utf-8",
                    data : JSON.stringify(GetJsonData()),
                    dataType : "json",
                    success : function(message) {
                        alert("从前端到后台再回来获取到的数据："+message.id+" "+message.name+" "+message.age);
                    },
                    error : function(message) {
                        alert("提交数据失败！");
                    }
                });

            });
        });

        function GetJsonData() {
            var json = {
                "id" : "1",
                "name" : "zzc",
                "age" : "23"
            };
            return json;
        }
    </script>
</head>
<body>

<button type="button">Click me</button>
</body>
</html>