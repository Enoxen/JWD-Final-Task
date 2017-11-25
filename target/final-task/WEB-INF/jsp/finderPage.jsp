<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
    <title>User output</title>
<body>
<form action="FrontController" method="get">
    <input type="hidden" name="command" value="find_film">
    <br>Enter film name:
    <br><input type="text" name="film_name" value="">
    <input type="submit" value="Send">
</form>
</body>
</html>
