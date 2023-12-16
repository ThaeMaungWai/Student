<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp">
    <c:param name="title" value="Menu" />
</c:import>
<div class="main-contents">
    <div id="contents">

        <h3>Welcome Back...! <br><br>
            Enjoy this project and try your best in your own!</h3>

        <!-- Simulated Alert -->
        <div id="simulatedAlert" class="simulated-alert">
            ${msg}
        </div>
    </div>
</div>

<script type="text/javascript">
    // Function to hide the simulated alert with a timer
    function hideSimulatedAlert() {
        var simulatedAlert = document.getElementById('simulatedAlert');
        simulatedAlert.style.display = 'none';
    }

    // Show the simulated alert when the page is loaded
    window.onload = function() {
        setTimeout(hideSimulatedAlert, 2000);
    };
</script>

<jsp:include page="footer.jsp"/>
