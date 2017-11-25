<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.film" var="loc"/>
    <fmt:message bundle="${loc}" key="film.name" var="name"/>
    <fmt:message bundle="${loc}" key="film.genre" var="genre"/>
</head>
<body>
    <c:if test="${requestScope.film.name != null}">
        <table>
            <tr>
                <td>${name}</td>
                <td>${genre}</td>
            </tr>
            <tr>
                <td><c:out value="${requestScope.film.name}"/></td>
                <td><c:out value="${requestScope.film.genre}"/></td>
            </tr>
        </table>

    </c:if>

    <footer>
        <a href="/FrontController?command=change_locale&locale=ru">Русский</a>
        <a href="/FrontController?command=change_locale&locale=en">English</a>
    </footer>
</body>
</html>
