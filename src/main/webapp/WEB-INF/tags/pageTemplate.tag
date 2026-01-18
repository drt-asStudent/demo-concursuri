<%@tag description="base page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="pageTitle"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/pages/menu.jsp" />
<main class="container-fluid mt-5">

<jsp:doBody/>
    <script src="${pageContext.request.contextPath}/scripts/form-validation.js"></script>
</main>
</body>
<footer>
    <jsp:include page="/WEB-INF/pages/footer.jsp" />
</footer>
</html>