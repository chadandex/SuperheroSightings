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
        <title>Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/styles.css?v=6" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1>Sightings</h1>
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
            <!-- 
           Add a row to container - this will hold the summary table and the new
           hero form.
            -->
            <div class="row">
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <h2>Sightings</h2>
                    <table id="sightingTable" class="table table-bordered">
                        <tr>
                            <th id="tableTitle" width="40%">Sighting <small><i>[Ascending by recent date]</i></small></th>
                            <th id="tableTitle" width="30%">Date</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <tr>
                                <td>
                                    <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                        <c:out value="Sighting Report #${currentSighting.sightingId}"/>
                                    </a>
                                </td>
                                <td style="color:white;">
                                    <fmt:parseDate value="${currentSighting.sightingDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                    <fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="MM/dd/yyyy" />
                                    <c:out value="${newParsedDate}"/>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_MOD')">
                                        <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">
                                            Edit
                                        </a>
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
                                            Delete
                                        </a>
                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </table> 
                </div><!-- End col div -->
                <!-- 
                Add col to hold the new hero form - have it take up the other 
                half of the row
                -->
                <div class="col-md-6">
                    <sec:authorize access="hasRole('ROLE_MOD')">
                        <h2>Add New Sighting</h2>
                        <span>
                            <form class="form-horizontal" 
                                  role="form" method="POST" 
                                  action="createSighting">
                                <div class="form-group">
                                    <label for="add-location" class="col-md-4 control-label" style="color:white;">Location:</label>
                                    <div class="col-md-8">
                                        <div class="select">
                                            <!-- Location's drop down flooding -->
                                            <select name="locationSelect">
                                                <option value="" selected disabled hidden>Choose here</option>
                                                <c:forEach var="currentLocation" items="${locationList}">
                                                    <option value="<c:out value="${currentLocation.locationId}"/>">
                                                        <c:out value="${currentLocation.locationName}"/>
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="add-date" class="col-md-4 control-label" style="color:white;">Date:</label>
                                    <div class="col-md-8">
                                        <input type="date" class="form-control" name="sightingDate" placeholder="Date" required/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="add-hero" class="col-md-4 control-label" style="color:white;">Hero:</label>
                                    <div class="col-md-8">
                                        <div class="select">
                                            <!-- Heroes drop down flooding -->
                                            <select name="heroSelect" id="dropbox">
                                                <option value="" selected disabled hidden>Choose here</option>
                                                <c:forEach var="currentHero" items="${heroList}">
                                                    <option value="<c:out value="${currentHero.heroId}"/>">
                                                        <c:out value="${currentHero.heroName}"/>
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <input type="submit" class="btn btn-default" value="Create Sighting"/>
                                    </div>
                                </div>
                            </form>
                        </span>
                    </div> <!-- End col div -->
                </sec:authorize>
            </div> <!-- End row div -->  
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
