<!--
 * Copyright 2017 ArtisTech, Inc.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:useBean scope="request" class="com.artistech.ee.web.DataManager" id="dataBean" type="com.artistech.ee.web.DataManager">
        <jsp:setProperty name="dataBean" property="*" />
    </jsp:useBean>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Upload Done</title>
        <link rel='stylesheet' href='style.css' type='text/css'>
        <script type='text/javascript'>
            var algolink_id = "<c:out value="${dataBean.pipeline_id}" />";
        </script>
    </head>
    <body>
        <h3>Your file has been uploaded!</h3>
        Your pipeline_id is: <c:out value="${dataBean.pipeline_id}" />
    </body>
</html>