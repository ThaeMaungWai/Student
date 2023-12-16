<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="/WEB-INF/views/header.jsp" >
    <c:param name="title" value="Couurse View" />
</c:import>
<div class="main_contents">
    <div id="sub_content">
        <%--@elvariable id="courses" type="base"--%>
        <form:form class="row g-3 mt-3 ms-2" action="/courseSearch" method="post" modelAttribute="courses">

            <div class="col-auto">
                <label for="id" class="visually-hidden">Course Id</label>
                <input type="text" class="form-control" id="id" placeholder="Course ID"  />
            </div>
            <div class="col-auto">
                <label for="name" class="visually-hidden">Course Name</label>
                <input type="text" class="form-control" id="name" placeholder="Course Name" />
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-3 " >
                    Search
                </button>
            </div>

            <div class="col-auto">
                <button type="button" class="btn btn-secondary " onclick="location.href = '/courseReg'">
                    Add
                </button>
            </div>

        </form:form>

        <table class="table table-striped table-hover"  id="studentTable">
            <thead>
            <tr>
                <th scope="col">No</th>
                <th scope="col">Course ID</th>
                <th scope="col">Course Name</th>
                <th scope="col">Details</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${courses}" var="course" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${course.id}</td>
                    <td>${course.name}</td>
                    <td>
                        <button type="button" class="btn btn-success " >
                            <a href="${pageContext.request.contextPath}/courseDetail?id=${course.id}" style="color: #d6f5e3; text-decoration: none;">Update </a>
                        </button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-secondary mb-3" data-bs-toggle="modal" data-bs-target="#exampleModal">
                            <a href="${pageContext.request.contextPath}/courseDelete?id=${course.id}" style="color: #d6f5e3; text-decoration: none;">Delete</a>
                        </button>
                    </td>



                </tr>
            </c:forEach>

            </tbody>
        </table>

        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal">Ok</button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />