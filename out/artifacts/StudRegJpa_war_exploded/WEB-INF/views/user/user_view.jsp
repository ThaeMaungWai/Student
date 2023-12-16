<%@ page import="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="/WEB-INF/views/header.jsp" >
    <c:param name="title" value="User Details" />
</c:import>

<div class="main_contents">
    <div id="sub_content">
        <div style="color: green">${updSuccess}</div>
        <div style="color: green">${success}</div>
        <div style="color: red">${disable}</div>
        <div style="color: green">${delSuccess}</div>
        <form:form class="row g-3 mt-3 ms-2" action="/userSearch" method="post" modelAttribute="user">
            <div class="col-auto">
                <label for="staticEmail2" class="visually-hidden">User Id</label>
                <form:input type="text" class="form-control" id="staticEmail2" placeholder="User ID"   path="id"/>
            </div>
            <div class="col-auto">
                <label for="inputPassword2" class="visually-hidden">User Name</label>
                <form:input type="text" class="form-control" id="inputPassword2" placeholder="User Name" path="name"/>
            </div>

            <div class="col-auto">
                <button type="submit" class="btn btn-primary " >
                    Search
                </button>
            </div>

            <div class="col-auto">
                <button type="button" class="btn btn-secondary " onclick="location.href = '/userReg'">
                    Add
                </button>
            </div>

            <div class="col-auto">
                <button type="button" class="btn btn-danger mb-3" data-bs-toggle="modal" data-bs-target="#exampleModal5">Reset</button>

                <!--model-->

            </div>
        </form:form>

        <table class="table table-striped table-hover"  id="stduentTable">
            <thead>
            <tr>
                <th scope="col">No</th>
                <th scope="col">User Name</th>
                <th scope="col">Email</th>
<%--                <c:if test="${not empty sessionScope.admin}">--%>
                    <th >Details</th>
<%--                </c:if>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                        <td>
                            <button type="button" class="btn btn-danger mb-3" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="location.href = '/userDelete?id=${user.id}'">
                                Delete
                            </button>
                        </td>
                    <td>
                        <button type="button" class="btn btn-primary mb-3" onclick="location.href = '/userDetail?id=${user.id}'">
                            Update
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp" />