<%-- 
    Author     : Chad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Edit Organization</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
        <link href="${pageContext.request.contextPath}/css/styles.css?v=7" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container" style="color:white;">
            <div align="center">
                <h1>Edit Organization</h1>
            </div>
            <hr />
            <sec:authorize access="hasRole('ROLE_MOD') and !hasRole('ROLE_ADMIN')">
                <div align="center" >
                    <small><p style="color:red;">! <i>Currently viewing as 'Sidekick' and may only assign/delete Superheroes/Supervillains associated</i> !</p></small>
                </div>
                </sec:authorize>
                <div align="center">
                    <small><p>! <i>Please note: Deleting a Superhero/Supervillain will permanently delete without need of clicking update</i> !</p></small>
                </div>
            <sf:form class="form-horizontal" role="form" modelAttribute="org"
                     action="editOrganization" method="POST">
                <div class="form-group">
                    <label for="add-org-name" class="col-md-4 control-label">Organization Name:</label>
                    <div class="col-md-8">
                        <%-- Only admins can edit, mods only can change heroes related to org --%>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <sf:input type="text" class="form-control" id="add-org-name"
                                      path="orgName" placeholder="Organization Name"/>
                            <sf:errors path="orgName" cssclass="error"></sf:errors>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_MOD') and !hasRole('ROLE_ADMIN')">
                            <sf:input type="text" class="form-control" id="add-org-name"
                                      path="orgName" placeholder="Organization Name" readonly="true" />
                            <sf:errors path="orgName" cssclass="error"></sf:errors>
                        </sec:authorize>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org-description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                        <%-- Only admins can edit, mods only can change heroes related to org --%>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <sf:input type="text" class="form-control" id="add-org-description"
                                      path="orgDescr" placeholder="Description:"/>
                            <sf:errors path="orgDescr" cssclass="error"></sf:errors>   
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_MOD') and !hasRole('ROLE_ADMIN')">
                            <sf:input type="text" class="form-control" id="add-org-description"
                                      path="orgDescr" placeholder="Description:" readonly="true" />
                            <sf:errors path="orgDescr" cssclass="error"></sf:errors>   
                        </sec:authorize>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-org-address" class="col-md-4 control-label">Organization Address:</label>                          
                    <div class="col-md-8">
                        <%-- Only admins can edit, mods only can change heroes related to org --%>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <sf:input type="text" class="form-control" id="add-org-address"
                                      path="orgAddress" placeholder="Organization Address"/>
                            <sf:errors path="orgAddress" cssclass="error"></sf:errors>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_MOD') and !hasRole('ROLE_ADMIN')">
                            <sf:input type="text" class="form-control" id="add-org-address"
                                      path="orgAddress" placeholder="Organization Address" readonly="true" />
                            <sf:errors path="orgAddress" cssclass="error"></sf:errors>
                        </sec:authorize>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-phone" class="col-md-4 control-label">Organization Phone #:</label>                          
                    <div class="col-md-8">
                        <%-- Only admins can edit, mods only can change heroes related to org --%>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <sf:input type="text" class="form-control" id="add-phone"
                                      path="orgPhone" placeholder="Organization Phone"/>
                            <sf:errors path="orgPhone" cssclass="error"></sf:errors>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_MOD') and !hasRole('ROLE_ADMIN')">
                            <sf:input type="text" class="form-control" id="add-phone"
                                      path="orgPhone" placeholder="Organization Phone" readonly="true" />
                            <sf:errors path="orgPhone" cssclass="error"></sf:errors>
                        </sec:authorize>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-email" class="col-md-4 control-label">Organization Email:</label>                          
                    <div class="col-md-8">
                        <%-- Only admins can edit, mods only can change heroes related to org --%>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <sf:input type="email" class="form-control" id="add-email"
                                      path="orgEmail" placeholder="Organization Email"/>
                            <sf:errors path="orgEmail" cssclass="error"></sf:errors>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_MOD') and !hasRole('ROLE_ADMIN')">
                            <sf:input type="email" class="form-control" id="add-email"
                                      path="orgEmail" placeholder="Organization Email" readonly="true" />
                            <sf:errors path="orgEmail" cssclass="error"></sf:errors>
                        </sec:authorize>
                        <sf:hidden path="orgId"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-email" class="col-md-4 control-label">Organization Hero(es): </label>  
                    <div class="col-md-8">
                        <c:forEach var="currentHero" items="${hero}">
                            <li><c:out value="${currentHero.heroName}"/> <b>|</b>
                                <a href="deleteHeroFromOrg?orgId=${org.orgId}&heroId=${currentHero.heroId}">
                                    Delete
                                </a>
                            </li>
                        </c:forEach>
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
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Organization"/>
                        <a href="${pageContext.request.contextPath}/displayOrganizationPage">
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