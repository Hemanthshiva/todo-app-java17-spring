<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
    <div class="container">
        <div class="todo-header text-center">
            <h1>Your Todos</h1>
        </div>

        <div class="table-responsive todo-table">
            <table class="table table-striped table-bordered">
                <thead class="table-light">
                <tr>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <jsp:useBean id="todos" scope="request" type="java.util.List"/>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td class="${todo.done ? 'completed' : ''}">${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${todo.done}">
                                    <span class="badge bg-success">Completed</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-warning">Pending</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="action-buttons">
                            <a href="update-todo?id=${todo.id}" class="btn btn-primary btn-sm">Update</a>
                            <a href="delete-todo?id=${todo.id}" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="text-center add-todo-btn">
            <a href="add-todo" class="btn btn-success">Add New Todo</a>
        </div>
    </div>
<%@ include file="common/footer.jspf" %>
