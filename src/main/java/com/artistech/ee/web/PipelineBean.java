/*
 * Copyright 2017 ArtisTech, Inc.
 */
package com.artistech.ee.web;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matta
 */
public class PipelineBean {

    private Map map;
    private final Map<String, Part> parts;
    private final ArrayList<Part> path;
    private int index;

    public class Part {

        private final Map partMap;
        private final String name;
        private final Map<String, Parameter> params;

        public Part(String name, Map map) {
            partMap = map;
            this.name = name;
            params = new HashMap<>();
            ArrayList paramsObj = (ArrayList) map.get("parameters");
            ArrayList<Parameter> ret = new ArrayList<>();
            for (Object param : paramsObj) {
                Map m = (Map) ((Map) param).get("parameter");
                Parameter p = new Parameter(m.get("name").toString(), m);
                params.put(p.getName(), p);
            }
        }

        public String[] getRequires() {
            ArrayList list = (ArrayList) partMap.get("requires");
            return (String[]) list.toArray(new String[]{});
        }

        public String getPage() {
            return partMap.get("page").toString();
        }

        public String getName() {
            return this.name;
        }

        public Parameter[] getParameters() {
            return params.values().toArray(new Parameter[]{});
        }

        public Parameter getParameter(String name) {
            return params.get(name);
        }
    }

    public class Parameter {

        private final Map partMap;
        private final String name;
        private String value;
        private final ArrayList<String> values;

        public Parameter(String name, Map map) {
            partMap = map;
            this.name = name;
            values = new ArrayList<>();
            ArrayList vals = (ArrayList) map.get("values");
            for (Object obj : vals) {
                values.add(obj.toString());
            }
            value = partMap.get("value").toString();
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String[] getValues() {
            return values.toArray(new String[]{});
        }
    }

    public PipelineBean() {
        parts = new HashMap<>();
        path = new ArrayList<>();
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource("pipeline.yml");
            BufferedReader in = new BufferedReader(new InputStreamReader(resource.openStream()));
            YamlReader reader = new YamlReader(in);
            Object object = reader.read();
            map = (Map) object;
            Map get = (Map) map.get("parts");
            for (Object key : get.keySet()) {
                Part p = new Part(key.toString(), (Map) get.get(key));
                parts.put(key.toString(), p);
            }
        } catch (YamlException ex) {
            Logger.getLogger(PipelineBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PipelineBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        return map.get("name").toString();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int value) {
        this.index = value;
    }

    public Part[] getPath() {
        return path.toArray(new Part[]{});
    }

    public static void main(String[] args) {
        PipelineBean pb = new PipelineBean();
        System.out.println(pb.map.get("name"));
    }
}
