package com.thanos.session.core;

import org.apache.catalina.session.ManagerBase;
import org.springframework.context.Lifecycle;

import java.io.IOException;
//ManagerBase为tomcat session管理基类
public class SessionManager extends ManagerBase implements org.apache.catalina.Lifecycle {

    public void load() throws ClassNotFoundException, IOException {

    }

    public void unload() throws IOException {

    }
}
