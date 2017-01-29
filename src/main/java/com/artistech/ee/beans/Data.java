/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.beans;

import com.artistech.utils.ExternalProcess;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matta
 */
public class Data extends DataBase {

    public static final String ENIE_DIR = "enie_out";
    public static final String JOINT_ERE_DIR = "joint_ere_out";
    public static final String MERGE_DIR = "merge_out";
    public static final String VISUALIZATION_DIR = "viz_out";
    public static final String TEST_LIST = "test_list";

    private ExternalProcess proc;

    public Data(String key) {
        super(key);
    }

    public String getTestList() {
        return getPipelineDir() + File.separator + TEST_LIST;
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

    @Override
    public String[] getKeys() {
        ArrayList<String> keys = new ArrayList<>();
        keys.addAll(Arrays.asList(super.getKeys()));

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

    public static String[] getDataKeys() {
        ArrayList<String> keys = new ArrayList<>();
        Field[] fields = Data.class.getFields();
        for (Field f : fields) {
            int modifiers = f.getModifiers();
            if ((modifiers & (Modifier.STATIC | Modifier.FINAL))
                    == (Modifier.STATIC | Modifier.FINAL)) {
                try {
                    keys.add(f.get(null).toString());
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return keys.toArray(new String[]{});
    }
}
