package com.thanos.session.core;


public class SessionContext {

    private String id;

    private Session session;

    private boolean persisted;

    private SessionMetaData metadata;

    /**
     * To get session id
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * To set session id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * To get session
     *
     * @return
     */
    public Session getSession() {
        return session;
    }

    /**
     * To set session
     *
     * @param session
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * To check session is persisted
     *
     * @return
     */
    public boolean isPersisted() {
        return persisted;
    }

    /**
     * To set session persisted
     *
     * @param persisted
     */
    public void setPersisted(boolean persisted) {
        this.persisted = persisted;
    }

    /**
     * To get session meta-data
     *
     * @return
     */
    public SessionMetaData getMetadata() {
        return metadata;
    }

    /**
     * To set session meta-data
     *
     * @param metadata
     */
    public void setMetadata(SessionMetaData metadata) {
        this.metadata = metadata;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "SessionContext [id=" + id + "]";
    }

}
