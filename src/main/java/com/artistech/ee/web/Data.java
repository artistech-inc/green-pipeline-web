/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.web;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author matta
 */
public class Data {

    private Calendar last_use;
    private final String key;
    private final HashMap<String, String> map = new HashMap<>();

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
        return map.get("gest_list");
    }

    public void setTestList(String value) {
        map.put("gest_list", value);
    }

    public String getPipelineDir() {
        return map.get("pipeline_dir");
    }

    public void setPipelineDir(String value) {
        map.put("pipeline_dir", value);
    }

    public String getInput() {
        return map.get("input");
    }

    public void setInput(String value) {
        map.put("input", value);
    }
    
    public String[] getInputFiles() {
        File f = new File(map.get("input"));
        String[] list = f.list();
        return list;
    }

    public String getJointEreOut() {
        return map.get("joint_ere_out");
    }

    public void setJointEreOut(String value) {
        map.put("joint_ere_out", value);
    }
    
    public String[] getJointEreOutFiles() {
        File f = new File(map.get("joint_ere_out"));
        return f.list();
    }

    public String getEnieOut() {
        return map.get("enie_out");
    }

    public void setEnieOut(String value) {
        map.put("enie_out", value);
    }
    
    public String[] getEnieOutFiles() {
        File f = new File(map.get("enie_out"));
        return f.list();
    }

    public String getMergeOut() {
        return map.get("merge_out");
    }

    public void setMergeOut(String value) {
        map.put("merge_out", value);
    }
    
    public String[] getMergedFiles() {
        File f = new File(map.get("merge_out"));
        return f.list();
    }
}
