<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.Timestamp"%>

<html>
<head>
    <title>Оформлення чеку</title>
    <link rel="stylesheet" href="submit.css">
</head>
  <body>
    <div id="format" align="center">Оформлення чеку</div>
    </br>
    <div style="height:550px">
      <table style="width:100%">
        <tr style="background-color: #c2c2c2; height:50px; font-size:24px">
          <th style="width:20%">Назва страви</th>
          <th style="width:30%">Опис</th>
          <th style="width:10%">Кількість</th>
          <th style="width:15%">Вартість за одиницю</th>
          <th style="width:25%">Загальна вартість</th>
        </tr>
        <c:forEach items="${checks}" var="check">
            <tr>
                <td>${check.dishCounter.dish.name}</td>
                <td>${check.dishCounter.dish.description}</td>
                <td>${check.dishCounter.count}</td>
                <td>${check.price.value}</td>
                <td>${check.dishCounter.count*check.price.value}</td>
            </tr>
        </c:forEach>
      </table>
    </div>
    <div style="height:70px;text-align:center">
      <div style="float:left; width:40%;height:100%">
        <table style="height:100%;width:100%; border: none">
          <tr style="height:100%; border: none">
            <td style="width:50%;  border: none; font-weight: bold;font-size:42px;text-align:center">Сума до сплати:</td>
            <td style="width:50%; font-size:28px;text-align:center">
                <c:set var="res" value="${0}"/>
                <c:forEach items="${checks}" var="check">
                    <c:set var="res" value="${res + check.dishCounter.count * check.price.value}" />
                </c:forEach>
                ${res}
            </td>
          </tr>
        </table>
      </div>
      <div style="float:left;width:60%;height:100%">
        <form action="submit" method="post">
          <input type="submit" name="action" value="Відмінити покупку" />
          <input type="submit" name="action" value="Підтвердити покупку" />
        </form>
      </div>
    </div>
	</body>
</html>