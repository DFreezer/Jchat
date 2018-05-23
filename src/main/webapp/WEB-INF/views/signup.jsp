<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 4567
  Date: 23.04.2018
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <title>Sign Up</title>
    <style>
        html, body {
            height: 100%;
        }
    </style>
</head>
<body>
<div class="container-fluid h-100">
    <div class="row h-100">
        <div class="col-12 h-100 d-flex justify-content-center align-items-center">
            <div class="signupForm w-25">
                <form:form method="post" action="/auth/regUser" modelAttribute="user">
                    <div class="formField form-group">
                        <form:label path="username">Username</form:label><br>
                        <form:input path="username" cssClass="form-control" placeholder="Enter username"/>
                        <form:errors path="username"/>
                    </div>
                    <div class="formField form-group">
                        <form:label path="email">Email</form:label><br>
                        <form:input path="email" cssClass="form-control" placeholder="Email"/>
                        <form:errors path="email"/>
                    </div>
                    <div class="formField form-group">
                        <form:label path="password">Password</form:label><br>
                        <form:password path="password" cssClass="form-control" placeholder="Password"/>
                        <form:errors path="password"/>
                    </div>
                    <div class="formField form-group">
                        <form:label path="confirmPassword">Confirm password</form:label><br>
                        <form:password path="confirmPassword" cssClass="form-control" placeholder="Confirm password"/>
                        <form:errors path="confirmPassword"/>
                        <c:if test="${error != null}">${error}</c:if>
                    </div>
                    <button type="submit" class="btn btn-dark">Sign Up</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>
</html>
