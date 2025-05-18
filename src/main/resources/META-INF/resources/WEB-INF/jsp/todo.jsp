<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
    <div class="container">
        <div class="todo-header text-center">
            <h1>Enter Todo Details</h1>
        </div>

        <div class="todo-form">
            <form:form method="post" modelAttribute="todo">
                <div class="form-group">
                    <form:label path="description" cssClass="form-label">Description</form:label>
                    <form:input type="text" path="description" required="required" cssClass="form-control" placeholder="Enter todo description"/>
                    <form:errors path="description" cssClass="text-danger"/>
                </div>

                <div class="form-group">
                    <form:label path="targetDate" cssClass="form-label">Target Date</form:label>
                    <form:input type="text" path="targetDate" required="required" cssClass="form-control"/>
                    <form:errors path="targetDate" cssClass="text-danger"/>
                </div>

                <form:input type="hidden" path="id"/>
                <form:input type="hidden" path="done"/>

                <div class="form-group text-center">
                    <input type="submit" class="btn btn-primary btn-action" value="Save"/>
                    <a href="/list-todos" class="btn btn-secondary btn-action">Cancel</a>
                </div>
            </form:form>
        </div>
    </div>
<%@ include file="common/footer.jspf" %>
<script type="text/javascript">
    $('#targetDate').datepicker({
        format: 'yyyy-mm-dd'
    });
</script>
