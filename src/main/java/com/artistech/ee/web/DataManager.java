/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.web;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Used in tomcat and jetty.
 * 
 * This class used to manage pipeline builders.
 *
 * @author matta
 */
public class DataManager {

    private String pipeline_id;

    private static final TreeMap<String, Data> DATAS = new TreeMap<>();

    /**
     * Get all registered Data objects.
     * @return 
     */    
    public static synchronized ArrayList<Data> getAllData() {
        ArrayList<Data> ret = new ArrayList<>(DATAS.values());
        return ret;
    }
    
    /**
     * Get the Data object with the specified id.
     * @param id
     * @return 
     */
    public static synchronized Data getData(String id) {
        if (id != null && DATAS.containsKey(id)) {
            Data data = DATAS.get(id);
            data.updateLastUse();
            return data;
        } else {
            return null;
        }
    }

    /**
     * Set the Data object with the specified id.
     * @param id
     * @param data 
     */
    public static synchronized void setData(String id, Data data) {
        if (id != null) {
            DATAS.put(id, data);
        }
    }

    /**
     * Check if the Data with the specified object exists.
     * @param id
     * @return 
     */
    public static synchronized boolean containsData(String id) {
        return id != null ? DATAS.containsKey(id) : false;

    }

    /**
     * Get the algolink id.
     * @return 
     */
    public String getPipeline_id() {
        return pipeline_id;
    }

    /**
     * Set the algolink id.
     * @param value 
     */
    public void setPipeline_id(String value) {
        pipeline_id = value;
    }

    /**
     * Get the Data associated with the current algolink id.
     * @return 
     */
    public Data getData() {
        if (pipeline_id != null && DataManager.containsData(pipeline_id)) {
            DataManager.getData(pipeline_id).updateLastUse();
            return DataManager.getData(pipeline_id);
        } else {
            return null;
        }
    }

    /**
     * Set the Data with the current algolink id.
     * @param value 
     */
    public void setData(Data value) {
        if (pipeline_id != null) {
            setData(pipeline_id, value);
        }
    }

    /**
     * Remove Data with the specified id.
     * @param id 
     */
    public static void removeData(String id) {
        DATAS.remove(id);
    }
}
