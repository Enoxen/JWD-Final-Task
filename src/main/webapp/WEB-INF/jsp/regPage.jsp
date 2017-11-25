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
    <fmt:message bundle="${loc}" key="auth.registr" var="reg"/>
</head>
<body>
<div>
    <h2>${reg}</h2>
    <form action="FrontController" method="get">
        <input type="hidden" name="command" value="authorization">
        <br>${login}:
        <br><input type="text" name="login" value="" required pattern="^([A-Za-z]+)|([А-Яа-я]+)$">
        <br>${password}:
        <br><input type="password" name="password" value="" required pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$">
        <input type="submit" value="${submit}">
    </form>
</div>
</body>
</html>
