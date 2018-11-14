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
        <title>Viewing Location</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
        <link href="${pageContext.request.contextPath}/css/styles.css?v=8" type="text/css" rel="stylesheet">

        <!-- Google map stuff -->
        <script type="text/javascript">
            function loadMap() {
                var markerLat, markerLong;
                markerLat =<c:out value="${location.locationLat}"/>;
                markerLong =<c:out value="${location.locationLong}"/>;

                var latlng = new google.maps.LatLng(markerLat, markerLong);
                var myOptions = {
                    zoom: 4,
                    center: latlng,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                var map = new google.maps.Map(document.getElementById("map"), myOptions);

                var marker = new google.maps.Marker({
                    position: latlng,
                    map: map,
                    title: "Location"
                });
            }
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

            <p>
                <b>Location Name:</b> <c:out value="${location.locationName}"/>
            </p>
            <p>
                <b>Location Description:</b> <c:out value="${location.locationDescr}"/>
            </p>
            <p>
                <b>Location Address:</b> <c:out value="${location.locationAddress}"/>
            </p>
            <p>
                <b>Location Latitude:</b> <c:out value="${location.locationLat}"/>
            </p>
            <p>
                <b>Location Longitude:</b> <c:out value="${location.locationLong}"/>
            </p>
            <p>
                <b>Google Maps Location:</b>
            <div id="map"></div>
        </p>
        <p><small><i>Please note: If location not showing correctly on map, check if Lat and Long are correctly formatted</i></small></p>
        <input type="button" class="btn btn-default" name="cancel" onclick="history.back()" value="Back"/>
    </div>



    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
</body>
</html>
