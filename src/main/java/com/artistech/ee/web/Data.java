/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.web;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matta
 */
public class Data {

    public static final String INPUT_DIR = "input";
    public static final String ENIE_DIR = "enie_out";
    public static final String JOINT_ERE_DIR = "joint_ere_out";
    public static final String MERGE_DIR = "merge_out";
    public static final String VISUALIZATION_DIR = "viz_out";
    public static final String TEST_LIST = "test_list";
    public String dataDir = "";

    private Calendar last_use;
    private final String key;
//    private final HashMap<String, String> map = new HashMap<>();
    private ExternalProcess proc;

    public Data(String key) {
        this.key = key;
        last_use = Calendar.getInstance();
    }

    /**
     * Set when the Data was last accessed.
     *
     * @return
     */
    public Calendar updateLastUse() {
        last_use = Calendar.getInstance();
        return getLastUse();
    }

    /**
     * Get when the data was last accessed.
     *
     * @return
     */
    public Calendar getLastUse() {
        return (Calendar) last_use.clone();
    }

    public String getKey() {
        return key;
    }

    public String getTestList() {
        return getPipelineDir() + File.separator + TEST_LIST;
    }

//    public void setTestList(String value) {
//        map.put("test_list", value);
//    }
    public String getPipelineDir() {
        return dataDir;
    }

    public void setPipelineDir(String value) {
        dataDir = value + File.separator + key;
        File f = new File(dataDir);
        f.mkdirs();
    }

    public String getInput() {
        return getPipelineDir() + File.separator + INPUT_DIR;
    }

//    public void setInput(String value) {
////        map.put("input", value);
//    }

    public String[] getInputFiles() {
        File f = new File(getInput());
        if (f.exists()) {
            return f.list();
        }
        return new String[]{};
    }

    public String getJointEreOut() {
        return getPipelineDir() + File.separator + JOINT_ERE_DIR;
    }

//    public void setJointEreOut(String value) {
////        map.put("joint_ere_out", value);
//    }

    public String[] getJointEreOutFiles() {
        File f = new File(getJointEreOut());
        if (f.exists()) {
            return f.list();
        }
        return new String[]{};
    }

    public String getEnieOut() {
        return getPipelineDir() + File.separator + ENIE_DIR;
    }

//    public void setEnieOut(String value) {
////        map.put("enie_out", value);
//    }

    public String[] getEnieOutFiles() {
        File f = new File(getEnieOut());
        if (f.exists()) {
            return f.list();
        }
        return new String[]{};
    }

    public String getMergeOut() {
        return getPipelineDir() + File.separator + MERGE_DIR;
    }
//
//    public void setMergeOut(String value) {
////        map.put("merge_out", value);
//    }

    public String[] getMergedFiles() {
        File f = new File(getMergeOut());
        if (f.exists()) {
            return f.list();
        }
        return new String[]{};
    }

    public String getVizOut() {
        return getPipelineDir() + File.separator + VISUALIZATION_DIR;
    }

//    public void setVizOut(String value) {
////        map.put("viz_out", value);
//    }

    public String[] getVizFiles() {
        File f = new File(getVizOut());
        if (f.exists()) {
            return f.list();
        }
        return new String[]{};
    }

    public String[] getFiles(String key) {
        File f = new File(getData(key));
        if (f.exists() && f.isDirectory()) {
            return f.list();
        }
        return new String[]{};
    }

    public String getData(String key) {
        return getPipelineDir() + File.separator + key;
    }

    public String[] getKeys() {
        ArrayList<String> keys = new ArrayList<>();
        Field[] fields = Data.class.getFields();
        for (Field f : fields) {
            int modifiers = f.getModifiers();
            if ((modifiers & (Modifier.STATIC | Modifier.FINAL))
                    == (Modifier.STATIC | Modifier.FINAL)) {
                try {
                    keys.add(f.get(null).toString());
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return keys.toArray(new String[]{});
    }

    public ExternalProcess getProc() {
        return proc;
    }

    public void setProc(ExternalProcess value) {
        proc = value;
    }
}
