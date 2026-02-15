<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="isAdmin" value="${pageContext.request.isUserInRole('admin')}" />
<c:set var="isOrganizator" value="${pageContext.request.isUserInRole('organizator')}" />

<header data-bs-theme="dark">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container-fluid">

            <%--<c:if test="${pageContext.request.remoteUser == null}">--%>
                <a class="navbar-brand" href="${pageContext.request.contextPath}">COMPETIȚII</a>
            <%--</c:if>--%>



            <div class="collapse navbar-collapse" id="navbarCollapse">

                <ul class="navbar-nav me-auto mb-2 mb-md-0">

                    <c:if test="${isAdmin}">

                        <!-- CONFIRMARE ORGANIZATORI -->
                        <li class="nav-item">
                            <a class="nav-link
                               ${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf("/"))
                               eq '/about.jsp' ? ' active' : ''}"
                               aria-current="page"
                               href="${pageContext.request.contextPath}/AddAcceptOrganizers">
                                CONFIRMARE ORGANIZATORI
                            </a>
                        </li>

                        <!-- CONFIRMARE USERS -->
                        <li class="nav-item">
                            <a class="nav-link ${activePage eq 'AddAcceptUsers' ? 'active' : ''}"
                               aria-current="page"
                               href="${pageContext.request.contextPath}/AddAcceptUsers">
                                CONFIRMARE USERS
                            </a>
                        </li>
                    </c:if>


                    <c:if test="${pageContext.request.isUserInRole('student') or pageContext.request.isUserInRole('external')}">

                        <!-- CONCURSURI -->
                        <li class="nav-item">
                            <a class="nav-link ${activePage eq 'Concursuri' ? 'active' : ''}"
                               aria-current="page"
                               href="${pageContext.request.contextPath}/Concursuri">
                                HELL'S BELLS
                            </a>
                        </li>

                        <!-- INSCRIERILE MELE -->
                        <li class="nav-item">
                            <a class="nav-link ${activePage eq 'InscrierileMele' ? 'active' : ''}"
                               aria-current="page"
                               href="${pageContext.request.contextPath}/InscrierileMele">
                                INSCRIERILE MELE
                            </a>
                        </li>

                    </c:if>



                    <!-- USERS (adăugat corect ca și Cars)
                    <li class="nav-item">
                        <a class="nav-link ${activePage eq 'Users' ? 'active' : ''}"
                           aria-current="page"
                           href="${pageContext.request.contextPath}/Users">
                            USERS
                        </a>
                    </li>
                    -->


                    <c:if test="${pageContext.request.isUserInRole('organizator')}">

                        <!-- ADAUGARE CONCURS -->
                        <li>
                            <a href="${pageContext.request.contextPath}/AddConcurs"
                               class="nav-link ${activePage eq 'AddConcurs' ? 'active' : ''}">
                                ADĂUGARE CONCURS
                            </a>
                        </li>

                        <!-- ACCEPTARE LUCRARI -->
                        <li>
                            <a href="${pageContext.request.contextPath}/AcceptareLucrari"
                               class="nav-link ${activePage eq 'AcceptareLucrari' ? 'active' : ''}">
                                ACCEPTARE LUCRARI
                            </a>
                        </li>

                        <!-- NOTARE -->
                        <li>
                            <a href="${pageContext.request.contextPath}/Notare"
                               class="nav-link ${activePage eq 'Notare' ? 'active' : ''}">
                                NOTARE
                            </a>
                        </li>

                    </c:if>

                </ul>

                <!-- Login (adăugat corect ca și Cars) -->

                <ul class="navbar-nav">
                    <li class="nav-item">

                        <c:choose>
                            <c:when test="${pageContext.request.getRemoteUser() == null}">
                                <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                            </c:otherwise>
                        </c:choose>
                    </li>


                </ul>
            </div>
        </div>
    </nav>
</header>
