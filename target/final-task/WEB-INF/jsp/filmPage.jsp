<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
    <c:if test="${requestScope.film.name != null}">
        <table>
            <tr>
                <td>Name</td>
                <td>Genre</td>
            </tr>
            <tr>
                <td><c:out value="${requestScope.film.name}"/></td>
                <td><c:out value="${requestScope.film.genre}"/></td>
            </tr>
        </table>
    </c:if>
</body>
</html>
