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
        <title>Edit Organization</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
        <link href="${pageContext.request.contextPath}/css/styles.css?v=1" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container" style="color:white;">
            <div align="center">
                <h1>Edit Organization</h1>
            </div>
            <hr />
            <sf:form class="form-horizontal" role="form" modelAttribute="org"
                     action="editOrganization" method="POST">
                <div class="form-group">
                    <label for="add-org-name" class="col-md-4 control-label">Organization Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-org-name"
                                  path="orgName" placeholder="Organization Name"/>
                        <sf:errors path="orgName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-org-description" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-org-description"
                                  path="orgDescr" placeholder="Description:"/>
                        <sf:errors path="orgDescr" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-org-address" class="col-md-4 control-label">Organization Address:</label>                          
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-org-address"
                                  path="orgAddress" placeholder="Organization Address"/>
                        <sf:errors path="orgAddress" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-phone" class="col-md-4 control-label">Organization Phone #:</label>                          
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-lat"
                                  path="orgPhone" placeholder="Organization Phone"/>
                        <sf:errors path="orgPhone" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-email" class="col-md-4 control-label">Organization Email:</label>                          
                        <div class="col-md-8">
                        <sf:input type="email" class="form-control" id="add-long"
                                  path="orgEmail" placeholder="Organization Email"/>
                        <sf:errors path="orgEmail" cssclass="error"></sf:errors>
                        <sf:hidden path="orgId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Organization"/>
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
