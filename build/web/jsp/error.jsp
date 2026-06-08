<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error Page</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <%@ page isErrorPage="true" %>
    <c:if test="${not empty result}">
        <h3>${result}</h3>
    </c:if>

    <c:remove var="cart" scope="session" />
    <c:remove var="books" scope="session" />
</body>
</html>