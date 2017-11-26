<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
    <title>Input page</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.launch" var="loc"/>
    <fmt:message bundle="${loc}" key="launch.greeting" var="greeting"/>
    <fmt:message bundle="${loc}" key="launch.auth" var="auth"/>
    <fmt:message bundle="${loc}" key="launch.reg" var="reg"/>

</head>
    <body>
        <div class="form">
            <h1>${greeting}</h1>
            <br>
            <form action="FrontController">
                <input type="hidden" name="command" value="sign">
                <input type="hidden" name="page" value="WEB-INF/jsp/authPage.jsp">
                <input type="submit" name="auth" value="${auth}">
            </form>
            <br>
            <form action="FrontController">
                <input type="hidden" name="command" value="sign">
                <input type="hidden" name="page" value="WEB-INF/jsp/regPage.jsp">
                <input type="submit" name="reg" value="${reg}">
            </form>
        </div>
        <%--<footer>--%>
            <%--<a href="/FrontController?command=change_locale&locale=ru">Русский</a>--%>
            <%--<a href="/FrontController?command=change_locale&locale=en">English</a>--%>
        <%--</footer>--%>
</body>
</html>
