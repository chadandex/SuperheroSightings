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
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Viewing Sighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="${pageContext.request.contextPath}/css/styles.css?v=6" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1>Superhero Sightings</h1>
            </div>
            <hr/>
            <nav>
                <a href="${pageContext.request.contextPath}/displayHomePage">Home</a>
                <a href="${pageContext.request.contextPath}/displayHeroesPage">Superhero</a>
                <a href="${pageContext.request.contextPath}/displayLocationPage">Locations</a>
                <a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a>
                <a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a>
                <div class="animation start-sighting"></div>
            </nav>
                
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a href="${pageContext.request.contextPath}/displayUserList">User Admin Tools</a>
                </sec:authorize>
            </c:if>
                
            <p>
                <b>Date of Sighting:</b>
                <fmt:parseDate value="${sightingDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                <fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd/MM/yyyy" />
                <c:out value="${newParsedDate}"/>
            </p>
            <p>
                <b>Location of Sighting:</b> <c:out value="${location.locationName}"/>
            </p>
            <p>
                <b>Superhero Sighted:</b> <c:out value="${hero.heroName}"/>
            </p>
            <input type="button" class="btn btn-default" name="cancel" onclick="history.back()" value="Back"/>
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
