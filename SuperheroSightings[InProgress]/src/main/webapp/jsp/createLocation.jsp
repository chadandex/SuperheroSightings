<%-- 
    Author     : Chad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Locations</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css?v=5" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1>Locations</h1>
            </div>
            <hr/>
            <nav>
                <a href="${pageContext.request.contextPath}/displayHomePage">Home</a>
                <a href="${pageContext.request.contextPath}/displayHeroesPage">Superhero</a>
                <a href="${pageContext.request.contextPath}/displayLocationPage">Locations</a>
                <a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a>
                <a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a>
                <div class="animation start-location"></div>
            </nav>

            <!-- 
               Add a row to container - this will hold the summary table and the new
               hero form.
            -->
            <div class="row">
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <h2>Locations</h2>
                    <table id="locationTable" class="table table-bordered">
                        <tr>
                            <th id="tableTitle" width="40%">Location Name</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentLocation" items="${locationList}">
                            <tr>
                                <td>
                                    <a href="displayLocationDetails?locationId=${currentLocation.locationId}">
                                        <c:out value="${currentLocation.locationName}"/>
                                    </a>
                                </td>
                                <td>
                                    <a href="displayEditLocationForm?locationId=${currentLocation.locationId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteLocation?locationId=${currentLocation.locationId}">
                                        Delete
                                    </a>
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
                    <h2>Add New Location</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createLocation">
                        <div class="form-group">
                            <label for="add-name" class="col-md-4 control-label" style="color:white;">Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="locationName" placeholder="Name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label" style="color:white;">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="locationDescr" placeholder="Description" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-address" class="col-md-4 control-label" style="color:white;">Address:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="locationAddress" placeholder="Address" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-lat" class="col-md-4 control-label" style="color:white;">Latitude:</label>
                            <div class="col-md-8">
                                <input type="lat" class="form-control" name="locationLat" placeholder="Latitude" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-long" class="col-md-4 control-label" style="color:white;">Longitude:</label>
                            <div class="col-md-8">
                                <input type="long" class="form-control" name="locationLong" placeholder="Longitude" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Location"/>
                            </div>
                        </div>
                    </form>

                </div> <!-- End col div -->

            </div> <!-- End row div -->     

        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        
    </body>
</html>
