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

    <style>
        /* Adjust these two values to match your real menu/footer heights */
        :root {
            --app-header-height: 72px;
            --app-footer-height: 56px;
        }

        body {
            padding-top: var(--app-header-height);
            padding-bottom: var(--app-footer-height);
        }

        .app-header,
        .app-footer {
            left: 0;
            right: 0;
            z-index: 1030; /* similar to Bootstrap navbar */
        }

        .app-footer {
            z-index: 1020;
        }
    </style>
</head>

<body style="background: linear-gradient(rgba(255,255,255,0.6), rgba(255,255,255,0.6)), url('${pageContext.request.contextPath}/images/2b.jpg') repeat-y center center; background-size: 100% auto;">

<div class="app-header fixed-top">
    <jsp:include page="/WEB-INF/pages/menu.jsp" />
</div>

<main class="container-fluid">
    <div class="container">
        <jsp:doBody/>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/form-validation.js"></script>
</main>

<div class="app-footer fixed-bottom">
    <jsp:include page="/WEB-INF/pages/footer.jsp" />
</div>

</body>
</html>