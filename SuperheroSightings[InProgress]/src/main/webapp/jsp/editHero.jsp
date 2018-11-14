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
        <title>Edit Superhero</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
        <link href="${pageContext.request.contextPath}/css/styles.css?v=7" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container" style="color:white;">
            <div align="center">
                <h1>Edit Superhero</h1>
            </div>
            <hr />
            <div align="center">
                <small><p>! <i>Please note: Deleting an Organization will permanently delete without need of clicking update</i> !</p></small>
            </div>
            <sf:form class="form-horizontal" role="form" modelAttribute="hero"
                     action="editHero" method="POST">
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">Superhero Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-name"
                                  path="heroName" placeholder="Superhero Name"/>
                        <sf:errors path="heroName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-description" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-description"
                                  path="heroDescr" placeholder="Description:"/>
                        <sf:errors path="heroDescr" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-power" class="col-md-4 control-label">Superhero Power:</label>                          
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-power"
                                  path="heroPower" placeholder="Superhero Power"/>
                        <sf:errors path="heroPower" cssclass="error"></sf:errors>
                        <sf:hidden path="heroId"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org" class="col-md-4 control-label">Superhero Organization(s):</label>                          
                    <div class="col-md-8">
                        <ul>
                            <c:forEach var="currentOrg" items="${org}">
                                <li><c:out value="${currentOrg.orgName}"/> <b>|</b>
                                    <a href="deleteHeroOrgAssoc?orgId=${currentOrg.orgId}&heroId=${hero.heroId}">
                                        Delete
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="col-md-8">
                            <div class="select">
                                <!-- Heroes Organization drop down flooding -->
                                <select name="heroesOrgSelectEdit">
                                    <option value="null" selected disabled hidden>Choose here</option>
                                    <c:forEach var="currentOrg" items="${orgList}">
                                        <option value="<c:out value="${currentOrg.orgId}"/>">
                                            <c:out value="${currentOrg.orgName}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Superhero"/>
                        <a href="${pageContext.request.contextPath}/displayHeroesPage">
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
