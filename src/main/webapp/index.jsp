<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Input page</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.submit" var="submit"/>
</head>
<body>
    <form action="FrontController" method="get">
        <input type="hidden" name="command" value="RU">
        <input type="submit" value="RU">
    </form>
    <form action="FrontController" method="get">
        <input type="hidden" name="command" value="EN">
        <input type="submit" value="EN">
    </form>
    <form action="FrontController" method="get">
        <input type="hidden" name="command" value="authorization">
        <br>${login}:
        <br><input type="text" name="login" value="">
        <br>${password}:
        <br><input type="password" name="password" value="">
        <input type="submit" value="${submit}">
    </form>
    <form action="FrontController" method="get">
        <input type="hidden" name="command" value="registration">
        <br>Enter login:
        <br><input type="text" name="login" value="">
        <br>Enter surname:
        <br><input type="password" name="password" value="">
        <input type="submit" value="${submit}">
    </form>
</body>
</html>
