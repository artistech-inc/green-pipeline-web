/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author matta
 */
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

//    private static final String DATA_DIRECTORY = "data";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            return;
        }

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Sets the size threshold beyond which files are written directly to
        // disk.
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // constructs the folder where uploaded file will be stored
        String uploadFolder = getServletContext().getInitParameter("data_path");

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        Collection<Part> parts = request.getParts();
        Part part = request.getPart("step");
        String target = IOUtils.toString(part.getInputStream(), "UTF-8");

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);

        //TODO: Upload the data such that it is structured.
        try {
            Part part1 = request.getPart("dataFile");
            File f = new File(uploadFolder + File.separator + part1.getSubmittedFileName());
            if (f.exists()) {
                f.delete();
            }
            try (FileOutputStream fos = new FileOutputStream(f)) {
                IOUtils.copy(part1.getInputStream(), fos, 1024);
            }

            // displays done.jsp page after upload finished
            getServletContext().getRequestDispatcher(target).forward(
                    request, response);
        } catch (IOException | ServletException ex) {
            throw new ServletException(ex);
        }

    }

}
