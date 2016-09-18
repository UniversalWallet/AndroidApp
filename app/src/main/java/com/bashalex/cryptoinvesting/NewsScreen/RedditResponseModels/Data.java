
package com.bashalex.cryptoinvesting.NewsScreen.RedditResponseModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Data {

    private String modhash;
    private List<Child> children = new ArrayList<Child>();
    private String after;
    private Object before;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The modhash
     */
    public String getModhash() {
        return modhash;
    }

    /**
     * 
     * @param modhash
     *     The modhash
     */
    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    /**
     * 
     * @return
     *     The children
     */
    public List<Child> getChildren() {
        return children;
    }

    /**
     * 
     * @param children
     *     The children
     */
    public void setChildren(List<Child> children) {
        this.children = children;
    }

    /**
     * 
     * @return
     *     The after
     */
    public String getAfter() {
        return after;
    }

    /**
     * 
     * @param after
     *     The after
     */
    public void setAfter(String after) {
        this.after = after;
    }

    /**
     * 
     * @return
     *     The before
     */
    public Object getBefore() {
        return before;
    }

    /**
     * 
     * @param before
     *     The before
     */
    public void setBefore(Object before) {
        this.before = before;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
