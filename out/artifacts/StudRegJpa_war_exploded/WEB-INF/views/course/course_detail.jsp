<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="/WEB-INF/views/header.jsp">
    <c:param name="title" value="Course Detail"/>
</c:import>

<table class="main_contents">
    <tr>
        <td>
            <div id="sub_content">
                <%--@elvariable id="course" type="base"--%>
                <form:form action="/courseDetail" method="post" modelAttribute="course">
                    <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Course Update</h2>

                    <div class="row mb-4">
                        <div class="col-md-2"></div>
                        <label for="id" class="col-md-2 col-form-label">Course ID</label>
                        <div class="col-md-4">
                            <form:input type="text" class="form-control" id="id" path="id"/>
                        </div>
                    </div>

                    <div class="row mb-4">
                        <div class="col-md-2"></div>
                        <label for="name" class="col-md-2 col-form-label">Course Name</label>
                        <div class="col-md-4">
                            <form:input type="text" class="form-control" id="name" path="name"/>
                        </div>
                    </div>

                    <div class="row mb-4">
                        <div class="col-md-4"></div>
                        <div class="col-md-6">
                            <button type="submit" class="btn btn-success col-md-2" data-bs-toggle="modal"
                                    data-bs-target="#exampleModal">Update
                            </button>
                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                                 aria-hidden="true">
                            </div>
                            <button type="button" class="btn btn-secondary col-md-2 "
                                    onclick='location.href = "/courseView"'>
                                Back
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </td>
    </tr>
</table>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
