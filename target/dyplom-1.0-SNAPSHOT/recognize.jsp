<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Розпізнавання страв</title>
		<link rel="stylesheet" href="recognize.css">
        <script src="capture.js">
	</script>
	</head>

	<body>
        <div id="format" align="center">Розпізнавання страв</div>
        <div id="space"><%=request.getParameter("test")%>Покладіть страву у місце для розпізнавання та натисніть кнопку</div>
        <div id="container">
            <div id="menu">
              <button id="startbutton">Розпізнати страву</button>
            </div>
            <div id="content">
  <video id="video"></video>
  <canvas id="canvas">
  </canvas>
    <img id="photo" src=<%=request.getParameter("test")%> alt="The screen capture will appear in this box.">
            </div>
            <div id="addSection">
                <div id="block1">
                    Розпізнані страви
                </div>

                <div id="block2">
                    <table style="width:100%">
                        <tr style="background-color: #c2c2c2">
                            <th style="width:65%">Страва</th>
                            <th>Кількість</th>
                        </tr>
                        <c:forEach items="${dishes}" var="dishCounter">
                            <tr>
                                <td>${dishCounter.dish.name}</td>
                                <td>${dishCounter.count}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div id="block3">
                    <a href="submit">
                        <button>Завершити сканування</button>
                    </a>
                </div>

            </div>
        </div>
	</body>
</html>