<%--
  Created by IntelliJ IDEA.
  User: Родион
  Date: 16.07.2017
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal</title>
    <style type="text/css">
        TABLE {
            width: 300px; /* Ширина таблицы */
            background: #fffff0; /* Цвет фона*/
            border: 1px solid #a52a2a; /* Рамка вокруг таблицы */
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }
        TD, TH {
            padding: 3px; /* Поля вокруг содержимого ячейки */
        }

        TD {
            text-align: center; /* Выравнивание по центру */
            border-bottom: 1px solid #a52a2a; /* Линия внизу ячейки */
            border-right: 1px solid #a52a2a; /* Линия внизу ячейки */
        }

        TH {
            background: #a52a2a; /* Цвет фона */
            color: white; /* Цвет текста */
        }

        .la {
            text-align: left; /* Выравнивание по левому краю */
        }

        TR.exceed {
            color: #a51f06; /* Цвет текста при привышении */
        }

        TR.notExceed {
            color: #41a537; /* Цвет текста при норме */
        }

    </style>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <table>
        <tr>
            <th>Дата</th>
            <th>Описание</th>
            <th>Калории</th>
        </tr>
        <c:forEach var="meal" items="${mealsWithExceeded}">
        <c:if test="${meal.exceed}">
            <tr class="exceed">
                <td><c:out value="${dateFormater.format(meal.dateTime)}"/></td>
                <td class="la"><c:out value="${meal.description}"/></td>
                <td class="exceed"><c:out value="${meal.calories}"/></td>
            </tr>
        </c:if>
        <c:if test="${!meal.exceed}">
            <tr class="notExceed">
                <td><c:out value="${dateFormater.format(meal.dateTime)}"/></td>
                <td class="la"><c:out value="${meal.description}"/></td>
                <td><c:out value="${meal.calories}"/></td>
            </tr>
        </c:if>
        </c:forEach>
    </table>
</body>
</html>
