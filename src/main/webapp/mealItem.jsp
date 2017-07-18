<%--
  Created by IntelliJ IDEA.
  User: Родион
  Date: 16.07.2017
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h3><a href="mealController?action=listItem">Back</a></h3>

<form method="POST" action='mealController' name="frmEditItem">
    <table>
        <tr>
            <td>ID :</td>
            <td><input type="text" readonly="readonly" name="id" value="<c:out value="${item.id}"/>"/></td>
        </tr>
        <tr>
            <td>Дата :</td>
            <td>
                <c:out value="${item.dateTime}" />
                <%--<fmt:parseDate value="${localDateTimeFormat.parse(item.datetime)}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />--%>
            </td>
        </tr>
        <tr>
            <td>Описание :</td>
            <td><input type="text" name="description"
                       value=<c:out value="${item.description}"/>
                />
            </td>
        </tr>
        <tr>
            <td>Калории :</td>
            <td><input type="text" name="calories" value=<c:out value="${item.calories}"/> /></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Записать"/></td>
        </tr>
    </table>
</form>
</body>
</html>
