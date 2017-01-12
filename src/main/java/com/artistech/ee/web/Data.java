/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.web;

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
    
    public String getJointEreOut() {
        return map.get("joint_ere_out");
    }
    
    public void setJointEreOut(String value) {
        map.put("joint_ere_out", value);
    }
}
