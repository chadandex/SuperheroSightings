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
        <title>Locations</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css?v=8" type="text/css" rel="stylesheet">

        <!-- Google map stuff -->
        <script type="text/javascript">
            function loadMap() {
                var markerLat, markerLong;
                markerLat = [
            <c:forEach var="location" items="${locationList}">
                <c:out value="${location.locationLat}"/>,
            </c:forEach>
                ];
                markerLong = [
            <c:forEach var="location" items="${locationList}">
                <c:out value="${location.locationLong}"/>,
            </c:forEach>
                ];
                var map;
                var mapOptions = {
                    zoom: 2,
                    center: new google.maps.LatLng(36, 5),
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                map = new google.maps.Map(document.getElementById('map'), mapOptions);
                var infowindow = new google.maps.InfoWindow();
                var marker, i;

                for (i = 0; i < markerLat.length; i++) {
                    marker = new google.maps.Marker({
                        position: new google.maps.LatLng(markerLat[i], markerLong[i]),
                        map: map
                    });

                    google.maps.event.addListener(marker, 'click', (function (marker, i) {
                        return function () {
                            infowindow.setContent(markers[i]);
                            infowindow.open(map, marker);
                        };
                    })(marker, i));
                }
            }

            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
    </head>
    <body onload="loadMap()">
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
                                    <sec:authorize access="hasRole('ROLE_MOD')">
                                        <a href="displayEditLocationForm?locationId=${currentLocation.locationId}">
                                            Edit
                                        </a>
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteLocation?locationId=${currentLocation.locationId}">
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
                    </sec:authorize>
                </div> <!-- End col div -->
            </div> <!-- End row div -->   
            <hr/>
            <h2>All Locations Recorded</h2>
            <div id="map"></div>
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?"></script>
    </body>
</html>
