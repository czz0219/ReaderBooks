<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>爱测试爱生活</title>
      <script type="text/javascript" src="jquery-3.3.1.min.js"></script>
    
    <script type="text/javascript">
    $(document).ready(function(){

    });

    
    function test02() {
		var data={"test_feild":$("#test_feild").val()
	};
        alert(JSON.stringify(data));
	$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/serch",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",
			dataType:"json",
			timeout:30000,
			beforeSend: function(request) {
				request.setRequestHeader("device_id", "1234");
			},
			async:true,
			error:function(msg){
				alert(JSON.stringify(msg));
			},
			success:function(msg){
				alert(JSON.stringify(msg));
			}
		});

}
  </script>
    </head>
  <body>
	<p>
	<input type="text" id="test_feild">
	<button onclick="test02()">发起交易</button>

  </body>
</html>
