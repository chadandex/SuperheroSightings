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
        <title>Organizations</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css?v=5" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1>Organizations</h1>
            </div>
            <hr/>
            <nav>
                <a href="${pageContext.request.contextPath}/displayHomePage">Home</a>
                <a href="${pageContext.request.contextPath}/displayHeroesPage">Superhero</a>
                <a href="${pageContext.request.contextPath}/displayLocationPage">Locations</a>
                <a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a>
                <a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a>
                <div class="animation start-org"></div>
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
                    <h2>Organizations</h2>
                    <table id="orgTable" class="table table-bordered">
                        <tr>
                            <th id="tableTitle" width="40%">Organization Name</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentOrg" items="${orgList}">
                            <tr>
                                <td>
                                    <a href="displayOrganizationDetails?orgId=${currentOrg.orgId}">
                                        <c:out value="${currentOrg.orgName}"/>
                                    </a>
                                </td>
                                <td>
                                    <a href="displayEditOrgForm?orgId=${currentOrg.orgId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteOrganization?orgId=${currentOrg.orgId}">
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
                    <h2>Add New Organization</h2>
                    <span>
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="createOrganization">
                            <div class="form-group">
                                <label for="add-name" class="col-md-4 control-label" style="color:white;">Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="orgName" placeholder="Name" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label" style="color:white;">Description:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="orgDescr" placeholder="Description" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-address" class="col-md-4 control-label" style="color:white;">Address:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="orgAddress" placeholder="Address" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-phone" class="col-md-4 control-label" style="color:white;">Phone #:</label>
                                <div class="col-md-8">
                                    <input type="tel" class="form-control" name="orgPhone" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" placeholder="Phone [123-555-1234]"
                                           required />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-email" class="col-md-4 control-label" style="color:white;">Email:</label>
                                <div class="col-md-8">
                                    <input type="email" class="form-control" name="orgEmail" placeholder="Email" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Create Organization"/>
                                </div>
                            </div>
                        </form>
                    </span>
                </div> <!-- End col div -->

            </div> <!-- End row div -->    
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
