<%-- 
    Author     : Chad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Edit Sighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
        <link href="${pageContext.request.contextPath}/css/styles.css?v=6" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container" style="color:white;">
            <div align="center">
                <h1>Edit Sighting</h1>
            </div>
            <hr />
            <sf:form class="form-horizontal" role="form" modelAttribute="sighting"
                     action="editSighting" method="POST">
                <div class="form-group">
                    <label for="add-location" class="col-md-4 control-label">Sighting Location:</label>
                    <div class="col-md-8">
                        <p>
                            <c:out value="${location.locationName}"/> 
                        </p>
                        <div class="col-md-16" >
                            <div class="select">
                                <!-- Location's drop down flooding -->
                                <select name="locationSelect" >
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
                </div>

                </br>

                <div class="form-group">
                    <label for="add-hero" class="col-md-4 control-label">Sighting Hero:</label>   
                    <div class="col-md-8">
                        <p>
                            <c:out value="${hero.heroName}"/> 
                        </p>
                        <div class="col-md-16">
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
                        </p>
                    </div>
                </div>

                </br>
                <div class="form-group">
                    <label for="add-date" class="col-md-4 control-label">Sighting Date:</label>
                    <div class="col-md-8">
                        <sf:input type="date" class="form-control" id="add-date"
                                  path="sightingDate" placeholder="Date"/>
                        <sf:errors path="sightingDate" cssclass="error"></sf:errors>
                        <sf:hidden path="sightingId"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Sighting"/>
                        <a href="${pageContext.request.contextPath}/displaySightingPage">
                            <input type="button" class="btn btn-default" value="Cancel"/>
                        </a>
                    </div>
                </div>
            </sf:form>
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>