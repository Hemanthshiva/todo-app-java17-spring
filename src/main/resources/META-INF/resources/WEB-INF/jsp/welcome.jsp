<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
    <div class="container welcome-container">
        <div class="card welcome-card">
            <h1 class="welcome-title">Welcome ${name}</h1>
            <p class="welcome-message">You have successfully logged in. Start managing your tasks now!</p>
            <a href="list-todos" class="welcome-button">View your todos</a>
        </div>
    </div>
<%@ include file="common/footer.jspf" %>
