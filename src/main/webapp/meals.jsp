<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--    <link rel="stylesheet" href="css/style.css">--%>
    <jsp:useBean id="meals" type="java.util.List<ru.javawebinar.topjava.model.MealTo>" scope="request"/>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<section>
    <a href="meals?action=add">add Meal</a>
    <br>
    <br>
    <table border="1">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <jsp:useBean id="meal"
                         type="ru.javawebinar.topjava.model.MealTo"/>
            <tr style="color: ${meal.excess ? 'red' : 'green'}">
                <td>${meal.date} ${meal.time}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            <td><a href="meals?id=${meal.id}&action=edit">update</a></td>
            <td><a href="meals?id=${meal.id}&action=delete">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>