<%--
  Created by IntelliJ IDEA.
  User: Intelligent
  Date: 2/16/2016
  Time: 8:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>

<body>
<h2>Enter your URL that you want to check!</h2>

<form:form method="POST" commandName="url">
    <table>
        <tr>
            <td>Enter your URL:</td>
            <td><form:input path="url" /></td>
            <td><form:errors path="url" cssStyle="color: #ff0000;"/></td>
        </tr>
        <tr>
            <td><input type="submit" name="submit" value="Submit"></td>
        </tr>
        <tr>
    </table>
</form:form>

</body>
</html>