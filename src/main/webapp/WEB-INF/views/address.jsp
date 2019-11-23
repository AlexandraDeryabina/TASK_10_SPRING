<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Данные о квартире</title>
</head>
<form>
    <p><strong>Данные о квартире</strong></p>
</form>
<form method="post" action="address">
    <select name="person">
        <c:forEach items="${personList}" var="person">
            <option value="${person.id}">${person.fullName}</option>
        </c:forEach>
    </select>
    <label for="street">Улица</label>
    <input name="street" id="street" required="required"/>

    <label for="house">Дом</label>
    <input name="house" id="house" required="required"/>

    <label for="flat">Квартира</label>
    <input name="flat" id="flat"/>

    <button>Перейти к данным</button>
</form>
</html>