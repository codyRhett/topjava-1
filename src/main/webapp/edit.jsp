<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<section>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="uuid" size=30 value="${meal.uuid}">
            <dl>
                <dt style="float: left">DataTime:</dt>
                <dd><input type="datetime-local" name="Datetime" size=30 value="${meal.dateTime}"></dd>
            </dl>
            <dl>
                <dt>Description:</dt>
                <dd><input type="text" name="Description" size=30 value="${meal.description}"></dd>
            </dl>
            <dl>
                <dt>Calories:</dt>
                <dd><input type="number" name="Calories" size=30 value="${meal.calories}"></dd>
            </dl>
        <button type="submit">save</button>
        <button type="reset" onclick="window.history.back()">cancel</button>
    </form>
</section>
</body>
</html>
