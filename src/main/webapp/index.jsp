<!--
 * Copyright 2017 ArtisTech, Inc.
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>File Upload</title>
        <link rel='stylesheet' href='style.css' type='text/css'>
        <script type="text/javascript">
            function guid() {
                return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                    var r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
                    return v.toString(16);
                });
            }
            function onload() {
                console.log("pipeline_id: " + guid());
                document.getElementById("pipeline_id").value = guid();    
            }
        </script>
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    </head>
    <body onload="onload()">
        <h1>Green Pipeline Web</h1>
        Web app for processing through the green pipeline involving joint_ere_release and ENIE.
        <hr />
        <form method="post" action="UploadServlet" enctype="multipart/form-data">
            <input type="hidden" name="step" id="step" value="/hub.jsp" />
            Select SGM file to upload:
            <input type="hidden" name="pipeline_id" id="pipeline_id"/>
            <input type="file" name="dataFile" id="fileChooser"/><br/><br/>
            <input type="submit" value="Upload" />
        </form>
    </body>
</html>