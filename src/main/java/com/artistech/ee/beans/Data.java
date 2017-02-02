/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.beans;

import java.io.File;

/**
 * This is the bean that has accessors for data during the Green-Pipeline.
 *
 * @author matta
 */
public class Data extends DataBase {

    public static final String ENIE_DIR = "enie_out";
    public static final String JOINT_ERE_DIR = "joint_ere_out";
    public static final String MERGE_DIR = "merge_out";
    public static final String VISUALIZATION_DIR = "viz_out";
    private static final String TEST_LIST = "test_list";

    /**
     * Constructor.
     *
     * @param key
     */
    public Data(String key) {
        super(key);
    }

    /**
     * Get the file which is the list of files to process.
     *
     * @return
     */
    public String getTestList() {
        return getPipelineDir() + File.separator + TEST_LIST;
    }

    /**
     * Get the output directory from joint_ere.
     *
     * @return
     */
    public String getJointEreOut() {
        return getPipelineDir() + File.separator + JOINT_ERE_DIR;
    }

    /**
     * Get the output files from joint_ere.
     *
     * @return
     */
    public String[] getJointEreOutFiles() {
        File f = new File(getJointEreOut());
        if (f.exists()) {
            return f.list();
        }
        return new String[]{};
    }

    /**
     * Get the output directory from enie.
     *
     * @return
     */
    public String getEnieOut() {
        return getPipelineDir() + File.separator + ENIE_DIR;
    }

    /**
     * Get the output files from enie.
     *
     * @return
     */
    public String[] getEnieOutFiles() {
        File f = new File(getEnieOut());
        if (f.exists()) {
            return f.list();
        }
        return new String[]{};
    }

    /**
     * Get the output directory from merge.
     *
     * @return
     */
    public String getMergeOut() {
        return getPipelineDir() + File.separator + MERGE_DIR;
    }

    /**
     * Get the output files from merge.
     *
     * @return
     */
    public String[] getMergedFiles() {
        File f = new File(getMergeOut());
        if (f.exists()) {
            return f.list();
        }
        return new String[]{};
    }

    /**
     * Get the output directory from Visualization.
     *
     * @return
     */
    public String getVizOut() {
        return getPipelineDir() + File.separator + VISUALIZATION_DIR;
    }

    /**
     * Get the output files from visualization.
     *
     * @return
     */
    public String[] getVizFiles() {
        File f = new File(getVizOut());
        if (f.exists()) {
            return f.list();
        }
        return new String[]{};
    }
}
