<%--
  Created by IntelliJ IDEA.
  User: lm
  Date: 2017/10/13
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
<textarea id="message" style = "width:200px;height:200px;">在此输入内容</textarea>
        <div>
            在线人员列表
            <div id="checkBoxGroup">
            <c:forEach var="re" items="${userNames}">

                <input type="checkbox" userName="${re}">${re}
                <br />
            </c:forEach>
            </div>
        </div>
        <button id="sendAndClear">发送</button>
        <script>
            window.onload = function () {

              document.getElementById("sendAndClear").onclick = function () {
                 var  targets = document.getElementsByTagName("input");
                 var userNameList = new Array();
                 for(var i=0;i<targets.length;i++){
                     if(targets[i].checked){
                         //选中
                         userNameList.push(targets[i].getAttribute("userName"));
                         targets[i].checked = false;
                     }
                 }
                 for(var i=0;i<userNameList.length;i++){
                     console.log(userNameList[i]);
                 }
                  var locationArray = window.location.href.split("/");
                  var locationString = "http:";
                  locationString += "//";
                  for(var i=2;i<locationArray.length-1;i++){
                      locationString += locationArray[i];
                      locationString += "/";
                  }
                  locationString += "PublishInforMation";
                  var message = document.getElementById("message").value;

                  jQuery.ajax({
                      type: 'POST',
                      url: locationString ,
                      data: {"userNames":userNameList.toString(),"message":message},
                      dataType: "JSON",
                      traditional: true,
                      success: function (res) {
                          alert("发布成功！");
                      },
                      error: function (res) {

                      }
                  });

              }
            }
        </script>
</body>
</html>
