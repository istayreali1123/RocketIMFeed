package com.thanos.session.core;

import java.io.Serializable;

//can be serialized
public class SessionMetaData implements Serializable {

    private static final long serialVersionuuid = 124438185184833546L;

    private final byte[] attributeHash;

    public SessionMetaData() {
        this.attributeHash = new byte[0];
    }



    




}
