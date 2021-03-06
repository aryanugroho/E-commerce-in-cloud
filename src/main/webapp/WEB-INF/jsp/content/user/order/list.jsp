<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page trimDirectiveWhitespaces="true" %>

<h2><spring:message code="order.new"/></h2>
<table>
    <tr>
        <th>&nbsp;</th>
        <th><spring:message code="order.created"/></th>
        <th><spring:message code="delivery.name"/></th>
        <th><spring:message code="payment.name"/></th>
    </tr>
    <c:forEach items="${newOrders}" var="order">
        <c:url value="/order/detail/${order.id}" var="orderUrl"/>
        <tr>
            <td><a href="${orderUrl}">#${order.id}</a></td>
            <td><a href="${orderUrl}">${order.created}</a></td>
            <td>${order.deliveryType.name}</td>
            <td>${order.paymentType.name}</td>
        </tr>
    </c:forEach>
</table>

<h2><spring:message code="order.old"/></h2>
<table>
    <tr>
        <th>&nbsp;</th>
        <th><spring:message code="order.created"/></th>
        <th><spring:message code="delivery.name"/></th>
        <th><spring:message code="payment.name"/></th>
    </tr>
    <c:forEach items="${oldOrders}" var="oldOrder">
        <c:url value="/order/detail/${oldOrder.id}" var="orderUrl"/>
        <tr>
            <td><a href="${orderUrl}">#${oldOrder.id}</a></td>
            <td><a href="${orderUrl}">${oldOrder.created}</a></td>
            <td>${oldOrder.deliveryType.name}</td>
            <td>${oldOrder.paymentType.name}</td>
        </tr>
    </c:forEach>
</table>
