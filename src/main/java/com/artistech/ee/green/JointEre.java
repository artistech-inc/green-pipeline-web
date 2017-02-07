/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.green;

import com.artistech.ee.beans.DataManager;
import com.artistech.ee.beans.Data;
import com.artistech.ee.beans.PipelineBean;
import com.artistech.utils.ExternalProcess;
import com.artistech.utils.StreamGobbler;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

/**
 * Handle running the Joint_ERE process.
 *
 * @author matta
 */
public class JointEre extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part pipeline_id_part = request.getPart("pipeline_id");
        String pipeline_id = IOUtils.toString(pipeline_id_part.getInputStream(), "UTF-8");
        Data data = (Data) DataManager.getData(pipeline_id);
        String input_sgm = data.getInput();
        String file_list = data.getTestList();

        File test_file = new File(file_list);
        if (!test_file.exists()) {
            try (java.io.BufferedWriter writer = new BufferedWriter(new FileWriter(test_file))) {
                for (String f : data.getInputFiles()) {
                    writer.write(f + System.lineSeparator());
                }
            }
        }
        ArrayList<PipelineBean.Part> currentParts = data.getPipelineParts();
        PipelineBean.Part get = currentParts.get(data.getPipelineIndex());
        PipelineBean.Parameter parameter = get.getParameter("model");
        String joint_ere_model = parameter.getValue();
        parameter = get.getParameter("tagger");
        String tagger = parameter.getValue();

        String joint_ere_path = get.getParameter("path") != null ? get.getParameter("path").getValue() : getInitParameter("path");
        String classpath = get.getParameter("classpath") != null ? get.getParameter("classpath").getValue() : getInitParameter("classpath");

        String joint_ere_out = data.getJointEreOut();
        File output_dir = new File(joint_ere_out);
        output_dir.mkdirs();

        ProcessBuilder pb = new ProcessBuilder("java", "-Xmx8G", "-cp", classpath, tagger, joint_ere_model, input_sgm, file_list, joint_ere_out);
        pb.directory(new File(joint_ere_path));
        pb.redirectErrorStream(true);
        Process proc = pb.start();

        //enable writing to console log
        OutputStream os = new FileOutputStream(new File(data.getConsoleFile()), true);
        StreamGobbler sg = new StreamGobbler(proc.getInputStream(), os);
        sg.write("Joint ERE");
        StringBuilder sb = new StringBuilder();
        for (String cmd : pb.command()) {
            sb.append(cmd).append(" ");
        }
        sg.write(sb.toString().trim());
        sg.start();
        ExternalProcess ex_proc = new ExternalProcess(sg, proc);
        data.setProc(ex_proc);

        // displays done.jsp page after upload finished
        getServletContext().getRequestDispatcher("/watchProcess.jsp").forward(
                request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Run  Joine ERE Step";
    }// </editor-fold>

}
