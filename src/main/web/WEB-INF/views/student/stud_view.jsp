<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/views/header.jsp" >
    <c:param name="title" value="Student Search" />
</c:import>
<style> .circular-img {
    width: 80px; /* Adjust the width and height as needed */
    height: 80px;
    border-radius: 70%; /* Makes the image circular */
    object-fit: cover; /* Ensures the image covers the circular container */
}</style>
<div class="main_contents">
    <div id="sub_content">
        <%--@elvariable id="student" type="base"--%>
        <form:form class="row g-3 mt-3 ms-2" action="/studSearch" method="post" modelAttribute="student">
            <div class="col-auto">
                <label for="studId" class="visually-hidden">studentID</label>
                <input type="text"  class="form-control" id="studId" placeholder="Student ID"/>
            </div>
            <div class="col-auto">
                <label for="studName" class="visually-hidden">studentName</label>
                <input type="text" class="form-control" id="studName" placeholder="Student Name"  />
            </div>
            <div class="col-auto">
                <label for="AttendCoures" class="visually-hidden">Course</label>
                <input type="text" class="form-control" id="AttendCoures" placeholder="Course" name="course" />
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-3 " >
                    Search
                </button>
            </div>

            <div class="col-auto">
                <button type="button" class="btn btn-secondary " onclick="location.href = '/studReg'">
                    Add
                </button>
            </div>
            <div class="col-auto">
                <button type="button" class="btn btn-danger mb-3" onclick="location.href = '/studView'">Rest</button>
            </div>
        </form:form>
        <div>
            <table class="table table-striped" id="stduentTable">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Photo</th>
                    <th scope="col">Courses</th>
                    <th scope="col">Details</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${students}" var="stud" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.index+1}</th>
                        <td>${stud.name}</td>
                        <td>
                            <img src="${pageContext.request.contextPath}/assets/image/${stud.imageFilePath}" alt="StudentImage" class="circular-img">
                        </td>
                        <td>
                            <c:forEach items="${stud.courses}" var="course" varStatus="courseLoop">
                                ${course.name}<c:if test="${!courseLoop.last}">, </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/studentDetail?id=${stud.id}"><button type="submit" class="btn btn-secondary mb-2">See More</button></a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp" />
