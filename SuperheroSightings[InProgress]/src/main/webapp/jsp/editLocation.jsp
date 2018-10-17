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
        <title>Edit Location</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
        <link href="${pageContext.request.contextPath}/css/styles.css?v=1" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container" style="color:white;">
            <div align="center">
                <h1>Edit Location</h1>
            </div>
            <hr />
            <sf:form class="form-horizontal" role="form" modelAttribute="location"
                     action="editLocation" method="POST">
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">Location Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-name"
                                  path="locationName" placeholder="Location Name"/>
                        <sf:errors path="locationName" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-description"
                                  path="locationDescr" placeholder="Description:"/>
                        <sf:errors path="locationDescr" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-address" class="col-md-4 control-label">Location Address:</label>                          
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-address"
                                  path="locationAddress" placeholder="Location Address"/>
                        <sf:errors path="locationAddress" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-lat" class="col-md-4 control-label">Location Latitude:</label>                          
                    <div class="col-md-8">
                        <sf:input type="lat" class="form-control" id="add-lat"
                                  path="locationLat" placeholder="Location Latitude"/>
                        <sf:errors path="locationLat" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-long" class="col-md-4 control-label">Location Longitude:</label>                          
                    <div class="col-md-8">
                        <sf:input type="long" class="form-control" id="add-long"
                                  path="locationLong" placeholder="Location Longitude"/>
                        <sf:errors path="locationLong" cssclass="error"></sf:errors>
                        <sf:hidden path="locationId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Location"/>
                        <input type="button" class="btn btn-default" name="cancel" onclick="history.back()" value="Cancel"/>
                    </div>
                </div> 
            </sf:form>
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
