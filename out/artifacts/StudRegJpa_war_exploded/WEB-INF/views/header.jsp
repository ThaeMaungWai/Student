<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 7/3/2023
  Time: 8:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    Date currentDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
    String date = dateFormat.format(currentDate);
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="assets/css/style.css" type="text/css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <title>${param.title}</title>
</head>

<body>
<div id="testheader">
    <div class="container">
        <div class=row>
            <div class="col-md-5 ">
                <a href="/menu"><h3>Student Registration</h3></a>
            </div>
            <div class="col-md-6">
                <c:if test="${not empty sessionScope.user}">

                    <p>User: <a href="/userDetail?id=${sessionScope.user.id}" style="text-decoration: none"> ${sessionScope.user.name}  </a> </p>
                </c:if>

                <c:if test="${not empty sessionScope.admin}">

                    <p>Admin: <a href="/userDetail?id=${sessionScope.admin.id}" style="text-decoration: none"> ${sessionScope.admin.name}  </a> </p>
                </c:if>
                <p>Current Date : <%= date %> </p>
                <p>User Name    :${sessionScope.name}</p>
            </div>

            <c:if test="${not empty sessionScope.name}">
                <div class="col-md-1" >
                    <input type="button" class="btn-basic" id="lgnout-button" value="Log Out" onclick='location.href="/logout"'>
                </div>
            </c:if>
        </div>
    </div>

</div>
<!-- <div id="testsidebar">Hello World </div> -->
<div class="sidenav">

    <button class="dropdown-btn" > Class Management </button>

    <div class="dropdown-container">
        <a href="${pageContext.request.contextPath}/courseReg">Course Registration </a>
        <a href="${pageContext.request.contextPath}/studReg">Student Registration </a>
        <a href=${pageContext.request.contextPath}/studView>Student Search </a>
    </div>
    <a href="${pageContext.request.contextPath}/userView">Users Management</a>
</div>

