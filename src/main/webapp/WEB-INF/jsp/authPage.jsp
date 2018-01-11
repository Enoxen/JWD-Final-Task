<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<style>
    .form{
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top:200px;
    }
    body{
        width: 100%;
        margin: 0;
        padding: 0;
    }
</style>
<head>
    <title>Title</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.auth" var="loc"/>
    <fmt:message bundle="${loc}" key="auth.login" var="login"/>
    <fmt:message bundle="${loc}" key="auth.password" var="password"/>
    <fmt:message bundle="${loc}" key="auth.submit" var="submit"/>
    <fmt:message bundle="${loc}" key="auth.authorize" var="authorize"/>
</head>
<body>
<div>
    <%--<form action="FrontController" method="get">--%>
        <%--<input type="hidden" name="command" value="ru">--%>
        <%--<input type="hidden" name="page" value="WEB-INF/jsp/authPage.jsp">--%>
        <%--<input type="submit" value="RU">--%>
    <%--</form>--%>
    <%--<form action="FrontController" method="get">--%>
        <%--<input type="hidden" name="command" value="en">--%>
        <%--<input type="hidden" name="page" value="WEB-INF/jsp/authPage.jsp">--%>
        <%--<input type="submit" value="EN">--%>
    <%--</form>--%>
    <h2>${authorize}</h2>
    <form action="FrontController" method="get">
        <input type="hidden" name="command" value="authorization">
        <br>${login}:
        <br><input type="text" name="login" value="" required pattern="^([A-Za-z]+)|([А-Яа-я]+)$">
        <br>${password}:
        <br><input type="password" name="password" value="" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$">
        <input type="submit" value="${submit}">
    </form>
</div>
<footer>
    <a href="/FrontController?command=change_locale&locale=ru">Русский</a>
    <a href="/FrontController?command=change_locale&locale=en">English</a>
</footer>
</body>
</html>
