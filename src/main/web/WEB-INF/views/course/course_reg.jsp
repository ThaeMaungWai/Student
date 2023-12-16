<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="/WEB-INF/views/header.jsp" >
    <c:param name="title" value="Course Registration" />
</c:import>
<div class="main_contents">
    <div id="sub_content">
        <form:form action="/courseReg" method="post" modelAttribute="course" id="courseForm">
            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Course Registration</h2>

            <!-- Error message div -->
            <div id="error-message" class="alert alert-danger" style="display: none;"></div>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="id" class="col-md-2 col-form-label">ID</label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" id="id" path="id" readonly="true" />
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" id="name" placeholder="Course" path="name" />
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-md-4"></div>
                <div class="col-md-6">
                    <button type="button" class="btn btn-secondary col-md-2" onclick="validateForm()">Add</button>
                    <button type="button" class="btn btn-secondary col-md-2" onclick='location.href = "/courseView"'>See Course</button>
                </div>
            </div>
        </form:form>
    </div>
</div>

<script>
    function validateForm() {
        const name = document.getElementById('name').value;
        const errorMessageDiv = document.getElementById('error-message');

        // Reset error message
        errorMessageDiv.style.display = 'none';
        errorMessageDiv.innerHTML = '';

        // Validate Name
        if (name.trim() === '') {
            displayError('Please enter a valid name.');
            return;
        }

        // Additional validations for other fields can be added here

        // If all validations pass, submit the form
        document.getElementById('courseForm').submit();
    }

    function displayError(message) {
        const errorMessageDiv = document.getElementById('error-message');
        errorMessageDiv.innerHTML = message;
        errorMessageDiv.style.display = 'block';
    }
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />