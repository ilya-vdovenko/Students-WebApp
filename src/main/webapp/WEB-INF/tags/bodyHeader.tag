<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<%@ attribute name="menuName" required="true" rtexprvalue="true"
              description="Name of the active menu: home, students, employees, faculties or error" %>
<%@ attribute name="back" required="true" rtexprvalue="true"
              description="Allow back button on page" %>

<studapp:menu name="${menuName}" back="${back}"/>
