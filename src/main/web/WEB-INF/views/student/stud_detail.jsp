<%@ page import="base.models.Course" %>
<%@ page import="base.daos.CourseDao" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<c:import url="/WEB-INF/views/header.jsp" >
    <c:param name="title" value="Student Detail" />
</c:import>
<%
    CourseDao courseDao = new CourseDao(); // Initialize your CourseDao
    List<Course> courses = courseDao.getAllCourses(); // Fetch courses using your DAO
    request.setAttribute("course", courses); // Set courses as a request attribute
%>

<div class="main_contents">
    <div id="sub_content">
        <form:form action="/studUpdate" method="post" modelAttribute="student" enctype="multipart/form-data">
            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Details</h2>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="studentID" class="col-md-2 col-form-label">Student ID</label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" id="studentID"  readonly="true" path="id" />
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="studname" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" id="studname"  path="name" />
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="dob" class="col-md-2 col-form-label">DOB</label>
                <div class="col-md-4">
                    <input type="date" class="form-control" id="dob" value="${student.dob}" name="dob">
                </div>
            </div>

            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                <div class="col-md-4">
                    <div class="form-check-inline">
                        <form:radiobutton class="form-check-input"   id="option1" value="Male"
                                          path="gender"/>
                        <label class="form-check-label" for="option1">
                            Male
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <form:radiobutton class="form-check-input"  id="option2" value="Female" path="gender" />
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
                    <form:input type="text" class="form-control" id="phone"  path="phone" />
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="education" class="col-md-2 col-form-label">Education</label>
                <div class="col-md-4">
                    <form:select class="form-select" aria-label="Education" id="education" name="education" path="education">
                        <form:option  value="">Bachelor of Information Technology</form:option>
                        <form:option value="Diploma in IT">Diploma in IT</form:option>
                        <form:option value="Beachelor of Computer Science">Bachelor of Computer Science</form:option>
                    </form:select>
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

                <div class="col-md-4">
                    <div class="col-md-6">

                    <button type="submit" class="btn btn-secondary">
                        <span>Update</span>
                    </button>
                    <button type="button" class="btn btn-danger " onclick="location.href = '/studentDelete?id=${student.id}'">
                        Delete
                    </button>
                        <button type="button" class="btn btn-secondary  " onclick="location.href = '/studView'">
                            Back
                        </button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp" />