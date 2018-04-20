<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Meals</title>
    </head>

    <body>
        Meals

        <table>
            <jsp:useBean id="meals" scope="request" type="java.util.List"/>
            <c:forEach items="${meals}" var="meal" >
                <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealWithExceed"/>
                <tr>
                    <td>${meal.dateTime}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
