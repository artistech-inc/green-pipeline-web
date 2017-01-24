/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.web;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author matta
 */
public class Visualize extends HttpServlet {

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
        String viz_path = getInitParameter("path");
//        String classpath = getInitParameter("classpath");

        Part pipeline_id_part = request.getPart("pipeline_id");
        String pipeline_id = IOUtils.toString(pipeline_id_part.getInputStream(), "UTF-8");
        Data data = DataManager.getData(pipeline_id);
        String file_list = data.getTestList();
        String merge_out = data.getMergeOut();

        String viz_out = data.getVizOut();

        File source = new File(data.getInput());
        File dest = new File(data.getMergeOut());

        /**
         * MERGE VIZ!
         */
        if (dest.exists()) {
            try {
                FileUtils.copyDirectory(source, dest);
            } catch (IOException e) {
                Logger.getLogger(Visualize.class.getName()).log(Level.SEVERE, null, e);
            }

//        data.setVizOut(viz_out);
            File viz_dir = new File(viz_out);
            viz_dir.mkdirs();
            //java -Xmx8G -cp ere-11-08-2016_small.jar:lib/\* edu.rpi.jie.ere.joint.Tagger /work/Documents/FOUO/EntityExtraction/joint_ere/models/joint/joint_model /work/Dev/green-pipeline-web/data/f3eb38c8-aba3-4e1b-9a69-6a9e5b7b7d43/input/ /work/Dev/green-pipeline-web/data/f3eb38c8-aba3-4e1b-9a69-6a9e5b7b7d43/test.list /work/Dev/green-pipeline-web/data/f3eb38c8-aba3-4e1b-9a69-6a9e5b7b7d43/joint_ere_out/

            //TODO: need to know "$INPUT_SGM", "$FILE_LIST", "$JERE_OUTP"
            //java -Xmx8G -cp $MERG_CPTH arl.workflow.combine.MergeEnieEre $FILE_LIST $INPUT_SGM "" $ENIE_OUTP $JERE_OUTP .xml .apf.xml $MERG_OUTP
            ProcessBuilder pb = new ProcessBuilder("python3", "ere_visualizer.py", file_list, merge_out, viz_out);
            for (String cmd : pb.command()) {
                Logger.getLogger(Visualize.class.getName()).log(Level.WARNING, cmd);
            }
//        Map<String, String> environment = pb.environment();
            pb.directory(new File(viz_path));
            pb.redirectErrorStream(true);
            Process proc = pb.start();
            StreamGobbler sg = new StreamGobbler(proc.getInputStream());
            sg.start();
            ExternalProcess ex_proc = new ExternalProcess(sg, proc);
            data.setProc(ex_proc);
            try {
                proc.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(JointEre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * ENIE VIZ!
         */
        dest = new File(data.getEnieOut());
        if (dest.exists()) {
            try {
                FileUtils.copyDirectory(source, dest);
            } catch (IOException e) {
                Logger.getLogger(Visualize.class.getName()).log(Level.SEVERE, null, e);
            }

            ProcessBuilder pb = new ProcessBuilder("python3", "ere_visualizer.py", file_list, data.getEnieOut(), data.getEnieOut());
            for (String cmd : pb.command()) {
                Logger.getLogger(Visualize.class.getName()).log(Level.WARNING, cmd);
            }
//        Map<String, String> environment = pb.environment();
            pb.directory(new File(viz_path));
            pb.redirectErrorStream(true);
            Process proc = pb.start();
            StreamGobbler sg = new StreamGobbler(proc.getInputStream());
            sg.start();
            ExternalProcess ex_proc = new ExternalProcess(sg, proc);
            data.setProc(ex_proc);
            try {
                proc.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(JointEre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * JOINT ERE VIZ!
         */
        dest = new File(data.getJointEreOut());
        if (dest.exists()) {
            try {
                FileUtils.copyDirectory(source, dest);
            } catch (IOException e) {
                Logger.getLogger(Visualize.class.getName()).log(Level.SEVERE, null, e);
            }
            ProcessBuilder pb = new ProcessBuilder("python3", "ere_visualizer.py", file_list, data.getJointEreOut(), data.getJointEreOut());
            for (String cmd : pb.command()) {
                Logger.getLogger(Visualize.class.getName()).log(Level.WARNING, cmd);
            }
//        Map<String, String> environment = pb.environment();
            pb.directory(new File(viz_path));
            pb.redirectErrorStream(true);
            Process proc = pb.start();
            StreamGobbler sg = new StreamGobbler(proc.getInputStream());
            sg.start();
            ExternalProcess ex_proc = new ExternalProcess(sg, proc);
            data.setProc(ex_proc);
            try {
                proc.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(JointEre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        Part part = request.getPart("step");
//        String target = IOUtils.toString(part.getInputStream(), "UTF-8");
        // displays done.jsp page after upload finished
        getServletContext().getRequestDispatcher("/hub.jsp").forward(
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
        return "Short description";
    }// </editor-fold>

}
