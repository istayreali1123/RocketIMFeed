package com.thanos.session.core;

import java.io.*;

//can be serialized
public class SessionMetaData implements Serializable {

    private static final long serialVersionuuid = 124438185184833546L;

    private byte[] attributeHash;

    public SessionMetaData() {
        this.attributeHash = new byte[0];
    }

    public byte[] getAttributeHash() {
        return attributeHash;
    }

    public void setAttributeHash(byte[] attributeHash) {
        this.attributeHash = attributeHash;
    }

    //copy metadata
    public void copyFieldFrom(SessionMetaData sessionMetaData) {
        this.setAttributeHash(sessionMetaData.getAttributeHash());
    }


    //write attributehash to ouputstream
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.write(attributeHash.length);
        outputStream.write(attributeHash);
    }


    private void readObject(ObjectInputStream inputStream) throws IOException {
        int hashLength = inputStream.readInt();
        byte[] readBytes = new byte[hashLength];
        inputStream.read(readBytes, 0, hashLength);
        this.attributeHash = readBytes;
    }

}
