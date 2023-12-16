<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="/WEB-INF/views/header.jsp" >
    <c:param name="title" value="User Registration" />
</c:import>
<div class="main_contents">
    <div id="sub_content">

        <form:form action="/userReg" method="post" modelAttribute="user">

            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">User Registration</h2>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="id" class="col-md-2 col-form-label">ID</label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" id="id" readonly="true"   path="id" />
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">User Name</label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" id="name"  path="name" />
                    <div id="name-error-message" class="text-danger"></div>
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="email" class="col-md-2 col-form-label">Email</label>
                <div class="col-md-4">
                    <form:input type="email" class="form-control" id="email" path="email" />
                    <div id="email-error-message" class="text-danger"></div>
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="password" class="col-md-2 col-form-label">Password</label>
                <div class="col-md-4">
                    <form:input type="password" class="form-control" id="password"  path="password" />
                    <div id="password-error-message" class="text-danger"></div>

                </div>
            </div>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="confirmPassword" class="col-md-2 col-form-label">Confirm Password</label>
                <div class="col-md-4">
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" oninput="validatePassword()" />
                    <div id="error-message" class="text-danger"></div>

                </div>
            </div>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="role" class="col-md-2 col-form-label">User Role</label>
                <div class="col-md-4">
                    <form:select class="form-select" aria-label="Education" id="role"  path="role">
                        <form:option value="Admin">Admin</form:option>
                        <form:option value="User">User</form:option>
                    </form:select>
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

<script>
    function validateForm() {
        const nameField = document.getElementById('name');
        const emailField = document.getElementById('email');
        const passwordField = document.getElementById('password');
        const confirmPasswordField = document.getElementById('confirmPassword');
        const nameErrorMessage = document.getElementById('name-error-message');
        const emailErrorMessage = document.getElementById('email-error-message');
        const passwordErrorMessage = document.getElementById('password-error-message');
        const confirmErrorMessage = document.getElementById('error-message');

        // Reset previous error messages
        nameErrorMessage.innerHTML = '';
        emailErrorMessage.innerHTML = '';
        passwordErrorMessage.innerHTML = '';
        confirmErrorMessage.innerHTML = '';

        // Validate Name
        if (nameField.value.trim() === '') {
            nameErrorMessage.innerHTML = 'Name is required!';
        }

        // Validate email
        if (emailField.value.trim() === '') {
            emailErrorMessage.innerHTML = 'Email is required!';
        }

        // Validate Password
        if (passwordField.value.length < 6) {
            passwordErrorMessage.innerHTML = 'Password must be at least 6 characters!';
        }

        // Validate Confirm Password
        if (confirmPasswordField.value !== passwordField.value) {
            confirmErrorMessage.innerHTML = 'Passwords do not match!';
        }

        // You can add more validations for other fields here if needed

        // Return true only if there are no error messages
        return nameErrorMessage.innerHTML === ''&& emailErrorMessage.innerHTML === ''  && passwordErrorMessage.innerHTML === '' && confirmErrorMessage.innerHTML === '';
    }

    document.addEventListener('DOMContentLoaded', function () {
        const form = document.querySelector('form');

        form.addEventListener('submit', function (event) {
            if (!validateForm()) {
                // Prevent the form from submitting if validation fails
                event.preventDefault();
            }
        });
    });
</script>





<jsp:include page="/WEB-INF/views/footer.jsp" />