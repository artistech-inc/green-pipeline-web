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
    <jsp:useBean scope="request" class="com.artistech.ee.web.PipelineBean" id="pipelineBean" type="com.artistech.ee.web.PipelineBean">
        <jsp:setProperty name="pipelineBean" property="*" />
    </jsp:useBean>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='style.css' type='text/css'>
        <title><c:out value="${pipelineBean.name}" /></title>
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    </head>
    <body>
        <h1><c:out value="${pipelineBean.name}" /></h1>
    </body>
</html>
