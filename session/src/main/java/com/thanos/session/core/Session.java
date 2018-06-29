package com.thanos.session.core;

import org.apache.catalina.Manager;
import org.apache.catalina.session.StandardSession;

import java.util.HashMap;
import java.util.Map;

//继承tomcat基础session
public class Session extends StandardSession {

    private static final long serialVersionUUID = -6056744304016869278L;

    protected boolean dirty;

    protected Map<String, Object> changedAttributes;

    protected static Boolean manualDirtyTrackingSupportEnabled = false;

    protected static String manualDirtyTrackingAttributeKey = "__changed__";

    public Session(Manager manager) {
        super(manager);
        resetDirtyTracking();
    }

    //重置
    public void resetDirtyTracking() {
        this.changedAttributes = new HashMap<String, Object>();
        dirty = false;
    }

    public static long getSerialVersionUUID() {
        return serialVersionUUID;
    }

    public boolean isDirty() {
        return this.dirty || !this.changedAttributes.isEmpty();
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public Map<String, Object> getChangedAttributes() {
        return changedAttributes;
    }

    public void setChangedAttributes(Map<String, Object> changedAttributes) {
        this.changedAttributes = changedAttributes;
    }

    public static Boolean getManualDirtyTrackingSupportEnabled() {
        return manualDirtyTrackingSupportEnabled;
    }

    public static void setManualDirtyTrackingSupportEnabled(Boolean manualDirtyTrackingSupportEnabled) {
        Session.manualDirtyTrackingSupportEnabled = manualDirtyTrackingSupportEnabled;
    }

    public static String getManualDirtyTrackingAttributeKey() {
        return manualDirtyTrackingAttributeKey;
    }

    public static void setManualDirtyTrackingAttributeKey(String manualDirtyTrackingAttributeKey) {
        Session.manualDirtyTrackingAttributeKey = manualDirtyTrackingAttributeKey;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }


    @Override
    public void setAttribute(String name, Object value) {
        if (manualDirtyTrackingSupportEnabled && manualDirtyTrackingAttributeKey.equals(name)) {
            dirty = true;
            return;
        }
        Object oldValue = getAttribute(name);
        super.setAttribute(name, value);

        if ((value != null || oldValue != null) && (oldValue != null && value == null || oldValue == null && value != null
        || !value.getClass().isInstance(oldValue) || !value.equals(oldValue))) {
            //todo
           // if (this.manager instanceof SessionManager && (SessionManager)this.manager.getsave)
        }
    }


}
