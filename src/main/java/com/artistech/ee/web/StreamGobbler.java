/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matta
 */
public class StreamGobbler extends Thread {

    InputStream is;
    String type;

    public StreamGobbler(InputStream is, String type) {
        this.is = is;
        this.type = type;
    }

    @Override
    public void run() {
        Logger logger = Logger.getLogger(StreamGobbler.class.getName());
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                logger.log(Level.WARNING, line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
