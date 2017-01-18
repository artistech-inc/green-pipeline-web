<!--
 * Copyright 2017 ArtisTech, Inc.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean scope="request" class="com.artistech.ee.web.DataManager" id="dataBean" type="com.artistech.ee.web.DataManager">
        <jsp:setProperty name="dataBean" property="*" />
    </jsp:useBean>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Hub</title>
        <link rel='stylesheet' href='style.css' type='text/css'>
        <script type='text/javascript'>
            var pipeline_id = "<c:out value="${dataBean.pipeline_id}" />";
        </script>
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    </head>
    <body>
        <h1>Hub:</h1>
        Your pipeline_id is: <c:out value="${dataBean.pipeline_id}" />
        <br />
        <%--<c:out value="${fn:length(dataBean.data.inputFiles)}" />--%>
        <c:if test="${fn:length(dataBean.data.inputFiles) gt 0}">
            <ul>Input Files:
                <c:forEach var="inputFile" items="${dataBean.data.inputFiles}">
                    <li><a target="_blank" href="ViewRaw?stage=input&pipeline_id=<c:out value="${dataBean.pipeline_id}"/>&file=<c:out value="${inputFile}"/>"><c:out value="${inputFile}"/></a></li>
                    </c:forEach>
            </ul>
        </c:if>
        <c:if test="${fn:length(dataBean.data.jointEreOutFiles) gt 0}">
            <ul>Joint ERE Output Files:
                <c:forEach var="inputFile" items="${dataBean.data.jointEreOutFiles}">
                    <li><a target="_blank" href="ViewRaw?stage=joint_ere_out&pipeline_id=<c:out value="${dataBean.pipeline_id}"/>&file=<c:out value="${inputFile}"/>"><c:out value="${inputFile}"/></a></li>
                    </c:forEach>
            </ul>
        </c:if>
        <c:if test="${fn:length(dataBean.data.enieOutFiles) gt 0}">
            <ul>ENIE Output Files:
                <c:forEach var="inputFile" items="${dataBean.data.enieOutFiles}">
                    <li><a target="_blank" href="ViewRaw?stage=enie_out&pipeline_id=<c:out value="${dataBean.pipeline_id}"/>&file=<c:out value="${inputFile}"/>"><c:out value="${inputFile}"/></a></li>
                    </c:forEach>
            </ul>
        </c:if>
        <c:if test="${fn:length(dataBean.data.mergedFiles) gt 0}">
            <ul>Merged Files:
                <c:forEach var="inputFile" items="${dataBean.data.mergedFiles}">
                    <li><a target="_blank" href="ViewRaw?stage=merge_out&pipeline_id=<c:out value="${dataBean.pipeline_id}"/>&file=<c:out value="${inputFile}"/>"><c:out value="${inputFile}"/></a></li>
                    </c:forEach>
            </ul>
        </c:if>
        <c:if test="${fn:length(dataBean.data.vizFiles) gt 0}">
            <ul>Generated Visualization:
                <c:forEach var="inputFile" items="${dataBean.data.vizFiles}">
                    <li><a target="_blank" href="View?pipeline_id=<c:out value="${dataBean.pipeline_id}"/>&file=<c:out value="${inputFile}"/>"><c:out value="${inputFile}"/></a></li>
                    </c:forEach>
            </ul>
        </c:if>
        <ul>
            <c:if test="${fn:length(dataBean.data.jointEreOutFiles) eq 0}">
                <li><a href="joint_ere.jsp?pipeline_id=<c:out value="${dataBean.pipeline_id}" />">joint_ere_release</a></li>
                </c:if>
                <c:if test="${fn:length(dataBean.data.enieOutFiles) eq 0}">
                <li><a href="enie.jsp?pipeline_id=<c:out value="${dataBean.pipeline_id}" />">enie</a></li>
                </c:if>
                <c:if test="${fn:length(dataBean.data.mergedFiles) eq 0 and
                              fn:length(dataBean.data.jointEreOutFiles) gt 0 and
                              fn:length(dataBean.data.enieOutFiles) gt 0}">
                <li><a href="merge.jsp?pipeline_id=<c:out value="${dataBean.pipeline_id}" />">merge</a></li>
                </c:if>
                <c:if test="${fn:length(dataBean.data.mergedFiles) gt 0 and
                              fn:length(dataBean.data.vizFiles) eq 0}">
                    <li><a href="visualize.jsp?pipeline_id=<c:out value="${dataBean.pipeline_id}" />">visualize</a></li>
                </c:if>
        </ul>
    </body>
</html>
