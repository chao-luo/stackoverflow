<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<h1>Demo Trainee Form</h1>
<%--@elvariable id="regDemoInfo" type="apple"--%>
<form:form method="POST" action="demoTrainee" modelAttribute="regDemoInfo">
    <table>
        <tr>
            <td><form:label path="registerationId">RegId</form:label></td>
            <td><form:input path="registerationId" /></td>
        </tr>
        <tr>
            <td><form:label path="demoInfo.trainer">Trainer</form:label></td>
            <td><form:input path="demoInfo.trainer" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Generate Demo Slip" /></td>
        </tr>
    </table>
</form:form>