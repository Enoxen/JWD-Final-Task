<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.find" var="loc"/>
<fmt:message bundle="${loc}" key="find.text" var="text"/>
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <title>User output</title>
<body>
<form action="FrontController" method="get">
    <input type="hidden" name="command" value="find_film">
    <br>${text}:
    <br><input type="text" name="film_name" value="" required>
    <input type="submit" value="Send">
</form>

<footer>
    <a href="/FrontController?command=change_locale&locale=ru">Русский</a>
    <a href="/FrontController?command=change_locale&locale=en">English</a>
</footer>
</body>
</html>
