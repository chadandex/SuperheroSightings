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
        <title>Superhero</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
        <link href="${pageContext.request.contextPath}/css/styles.css?v=6" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1>Superhero</h1>
            </div>
            <hr/>
            <nav>
                <a href="${pageContext.request.contextPath}/displayHomePage">Home</a>
                <a href="${pageContext.request.contextPath}/displayHeroesPage">Superhero</a>
                <a href="${pageContext.request.contextPath}/displayLocationPage">Locations</a>
                <a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a>
                <a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a>
                <div class="animation start-hero"></div>
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
                    <h2>Superheroes</h2>
                    <table id="heroTable" class="table table-bordered">
                        <tr>
                            <th id="tableTitle" width="40%">Superhero Name</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentHero" items="${heroList}">
                            <tr>
                                <td>
                                    <a href="displayHeroDetails?heroId=${currentHero.heroId}">
                                        <c:out value="${currentHero.heroName}"/>
                                    </a>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_MOD')">
                                        <a href="displayEditHeroForm?heroId=${currentHero.heroId}">
                                            Edit
                                        </a>
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteHero?heroId=${currentHero.heroId}">
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
                        <h2>Add New Superhero</h2>
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="createHero">
                            <div class="form-group">
                                <label for="add-name" class="col-md-4 control-label" style="color:white;">Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="heroName" placeholder="Name" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label" style="color:white;">Description:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="heroDescr" placeholder="Description" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-power" class="col-md-4 control-label" style="color:white;">Power:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="heroPower" placeholder="Power" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-org" class="col-md-4 control-label" style="color:white;">Organization:</label>
                                <div class="col-md-8">
                                    <div class="select">
                                        <!-- Heroes Organization drop down flooding -->
                                        <select name="heroesOrgSelect">
                                            <option value="" selected disabled hidden>Choose here</option>
                                            <c:forEach var="currentOrg" items="${orgList}">
                                                <option value="<c:out value="${currentOrg.orgId}"/>">
                                                    <c:out value="${currentOrg.orgName}"/>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Create Superhero"/>
                                </div>
                            </div>
                    </sec:authorize>
                </div> <!-- End col div -->

            </div> <!-- End row div -->     
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
