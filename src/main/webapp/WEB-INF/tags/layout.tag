<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>
<%@ attribute name="pageName" required="true" %>
<%@ attribute name="back" required="true" %>
<%@ attribute name="customScript" required="false" fragment="true"%>
<%@ attribute name="customCss" required="false" fragment="true"%>

<!DOCTYPE html>
<html>
    <studapp:htmlHeader/>
    <jsp:invoke fragment="customCss" />
    <body>
    <studapp:bodyHeader menuName="${pageName}" back="${back}"/>
    <div class="container-fluid">
        <jsp:doBody/>
    </div>
    <studapp:footer/>
    <jsp:invoke fragment="customScript" />
</body>
</html>
