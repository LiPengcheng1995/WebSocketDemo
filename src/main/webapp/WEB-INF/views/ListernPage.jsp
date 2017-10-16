<%--
  Created by IntelliJ IDEA.
  User: lm
  Date: 2017/10/13
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
    <h1>欢迎：${userName}</h1>
    <hr/>
    <div>
        <ul id="messsages">

        </ul>
    </div>
    <script>
        window.onload = function () {
            var locationArray = window.location.href.split("/");
            var socketLocation = "ws:";
            socketLocation += "//";
            for(var i=2;i<locationArray.length-1;i++){
                socketLocation += locationArray[i];
                socketLocation += "/";
            }
            socketLocation += "aWebSocket";
            var socket = new WebSocket(socketLocation);//项目名称 + 处理器拦截路径名就会打开的目的websocket链接口

            /**
             * 建立成功的回调函数
             */
            socket.onopen = function() {
                console.log('open');
            };

            /**
             * 服务器有消息返回的回调函数
             */
            socket.onmessage = function(e) {
                console.log('message', e.data);
                $("#messsages").append("<li>"+e.data+"</li>");
            };

            /**
             * websocket链接关闭的回调函数
             */
            socket.onclose = function() {
                console.log('close');
            };
        }
    </script>
</body>
</html>
