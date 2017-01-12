<!--
 * Copyright 2017 ArtisTech, Inc.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            var algolink_id = "<c:out value="${dataBean.pipeline_id}" />";
        </script>
    </head>
    <body>
        <h1>Hub:</h1>
        Your pipeline_id is: <c:out value="${dataBean.pipeline_id}" />
        <ul>
            <li><a href="joint_ere.jsp?pipeline_id=<c:out value="${dataBean.pipeline_id}" />">joint_ere_release</a></li>
            <li><a href="enie.jsp?pipeline_id=<c:out value="${dataBean.pipeline_id}" />">enie</a></li>
            <li><a href="merge.jsp?pipeline_id=<c:out value="${dataBean.pipeline_id}" />">merge</a></li>

    </body>
</html>
