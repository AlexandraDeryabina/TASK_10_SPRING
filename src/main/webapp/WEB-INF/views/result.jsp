<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Результаты</title>
</head>
<form>
    <p><strong>Данные о людях и квартирах</strong></p>
</form>
<table>
    <c:forEach items="${personList}" var="person" varStatus="statusPerson">
        <tr>
            <td>ФИО ${statusPerson.index+1}: ${person.fullName}</td>
            <td>
                <c:forEach items="${person.addressList}" var="address" varStatus="statusAddress">
                    адрес ${statusAddress.index+1}: ${address.fullAddress}<br>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>
</html>