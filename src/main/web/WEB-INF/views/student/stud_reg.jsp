<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="base.daos.CourseDao" %>
<%@ page import="base.models.Course" %>
<c:import url="/WEB-INF/views/header.jsp" >
    <c:param name="title" value="Student Registration" />
</c:import>
<%
    CourseDao courseDao = new CourseDao(); // Initialize your CourseDao
    List<Course> courses = courseDao.getAllCourses(); // Fetch courses using your DAO
    request.setAttribute("course", courses); // Set courses as a request attribute
%>
<div class="main_contents">
    <div id="sub_content">

        <form:form  modelAttribute="students" action="/stud_reg" method="post" enctype="multipart/form-data">
            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Registration</h2>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="id" class="col-md-2 col-form-label">ID</label>
                <div class="col-md-4">
                    <form:input path="id" type="text" class="form-control" id="id" readonly="true" />
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Name" />
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="dob" class="col-md-2 col-form-label">DOB</label>
                <div class="col-md-4">
                    <input type="date" class="form-control" id="dob" name="dob"/>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                <div class="col-md-4">
                    <div class="form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="option1" value="Male"
                               checked>
                        <label class="form-check-label" for="option1">
                            Male
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="option2" value="Female">
                        <label class="form-check-label" for="option2">
                            Female
                        </label>
                    </div>

                </div>
            </fieldset>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="phone" class="col-md-2 col-form-label">Phone</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="+95 "/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="education" class="col-md-2 col-form-label">Education</label>
                <div class="col-md-4">
                    <select class="form-select" aria-label="Education" id="education" name="education">
                        <option selected>Bachelor of Information Technology</option>
                        <option value="Diploma in IT">Diploma in IT</option>
                        <option value="Beachelor of Computer Science">Bachelor of Computer Science</option>

                    </select>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Attend</legend>
                <div class="col-md-4">
                    <c:forEach items="${course}" var="course">
                        <div class="form-check-inline col-md-2">
                            <input class="form-check-input" type="checkbox" name="courseAttend" id="attend_${course.id}" value="${course.name}">
                            <label class="form-check-label" for="attend_${course.id}">
                                    ${course.name}
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </fieldset>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="image" class="col-md-2 col-form-label">Photo</label>
                <div class="col-md-4">
                    <input type="file" class="form-control" id="image" name="image">
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-md-4"></div>
                <div class="col-md-6">
                    <button type="submit" class="btn btn-secondary col-md-2" data-bs-toggle="modal" data-bs-target="#exampleModal">Add</button>

                </div>
            </div>
        </form:form>

    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />