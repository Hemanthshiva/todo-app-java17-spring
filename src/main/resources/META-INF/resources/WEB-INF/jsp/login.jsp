<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        .error { color: red; }
        .login-container { 
            width: 300px; 
            margin: 100px auto; 
            padding: 20px; 
            border: 1px solid #ccc; 
            border-radius: 5px;
        }
        .form-group { margin-bottom: 15px; }
        input[type="text"], input[type="password"] { 
            width: 100%; 
            padding: 8px; 
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form method="post" action="/login">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required/>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required/>
            </div>
            <div class="form-group">
                <input type="submit" value="Login"/>
            </div>
            <c:if test="${param.error != null}">
                <div class="error">Invalid username or password.</div>
            </c:if>
        </form>
    </div>
</body>
</html>
