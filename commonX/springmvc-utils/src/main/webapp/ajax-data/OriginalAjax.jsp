<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2018/7/22
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <style type="text/css">
      #mydiv{
        position: absolute;
          left:50%;
          top:50%;
        margin-left: -200px;
        margin-top: -50px;
      }
    </style>
      <script type="text/javascript">
          var xmlhttp;
          function getMoreContents() {
              //获得用户输入
              var content= document.getElementById("keyword");
              if(content.value==""){
                  return ;
              }
              alert(content.value);
             //发送输入内容
              xmlhttp=createXMLHttp();
              var url="/serch?keyword="+escape(content.value);
              xmlhttp.open("GET",url,true);
              xmlhttp.onreadystatechange=callBack;
              xmlhttp.send();
          }
          function createXMLHttp()
          {
              var xmlhttp;
              if (window.XMLHttpRequest)
              {// code for IE7+, Firefox, Chrome, Opera, Safari
                  xmlhttp=new XMLHttpRequest();
              }
              else
              {// code for IE6, IE5
                  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                  if(!xmlhttp){
                      xmlhttp=new ActiveXObject("Msxml2.XMLHTTP");
                  }
              }
              return xmlhttp
          }
          function callBack() {
            if(xmlhttp.readyState==4 && xmlhttp.status==200){
                var result=xmlhttp.responseText;
                alert(result);
                //解析获得数据JSON
            //    var json=eval("("+result+")");

            }
          }
          //设置关联数据的展示
          function setContent(contents) {
              var size=contents.length;
              for(var i=0;i<size;i++){
                  var nextNode=contents[i];//代表JSON格式数据的第I个元素
                  var tr=document.createElement("tr");
                  var td=document.createTextNode("td");
                  td.setAttribute("border","0");
                  td.setAttribute("bgcolor","#FFFAFA");
                  td.onmouseover=function () {
                      this.className='mouseOver';
                  };
                  td.onmouseout=function () {
                      this.className='mouseOut';
                  };
                  td.onclick=function () {
                      //选择一个关联的数据时候，自动设置到输入框
                  };
                  var text=document.createTextNode(nextNode);
                  td.appendChild(text);
                  tr.appendChild(td);
                  document.getElementById("content_table_body").appendChild(tr);
              }

          }
      </script>

  </head>
  <body>
  <div id="mydiv">
    <input type="text" size="50" id="keyword" onkeyup="getMoreContents()"/>
    <input type="button" value="百度一下" width="50px">
      <div id="popDiv">
        <table id="content_table" bgcolor="#FFFAFA" border="0" cellspacing="0"
               cellpadding="0">
            <tbody id="content_table_body">
            <tr><td>ajax</td></tr>
            <tr><td>ajax1</td></tr>
            <tr><td>ajax2</td></tr>
            </tbody>
        </table>
      </div>
  </div>
  </body>
</html>
