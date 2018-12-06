<%-- 
    Author     : Chad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HERO: Add User</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">      
        <link href="${pageContext.request.contextPath}/css/styles.css?v=6" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1>Hero Education and Relationship Organization</h1>
            </div>
            <hr/>
            <nav>
                <a href="${pageContext.request.contextPath}/displayHomePage">Home</a>
                <a href="${pageContext.request.contextPath}/displayHeroesPage">Superhero</a>
                <a href="${pageContext.request.contextPath}/displayLocationPage">Locations</a>
                <a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a>
                <a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a>
                <div class="animation"></div>
            </nav>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
            <h1>Add User Form</h1>
            <div class="col-md-6">
                <form method="POST" action="addUser">
                    <label for="add-username" class="col-md-4 control-label">Username:</label>
                    <input type="text" 
                           name="username"/><br/>
                    <label for="add-password" class="col-md-4 control-label">Password:</label> 
                    <input type="password" 
                           name="password"/><br/>
                    <label for="add-mod" class="col-md-4 control-label">Sidekick User?</label>
                    <input type="checkbox" 
                           name="isMod" value="yes"/> <br/>
                    <label for="add-admin" class="col-md-4 control-label">Admin User? </label>
                    <input type="checkbox" 
                           name="isAdmin" value="yes"/> <br/>
                    <input type="submit" class="btn btn-default" value="Add User"/>
                    <input type="button" class="btn btn-default" name="cancel" onclick="history.back()" value="Cancel"/>
                </form>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>