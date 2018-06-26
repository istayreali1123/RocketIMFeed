package com.thanos.sns;

import com.thanos.common.StartupController;
import com.thanos.common.es.EleasticSearchClient;
import com.thanos.dbgate.DBGateStartup;

/**
 * Created by wangjialong on 6/25/18.
 */
public class Startup {


    public static void main(String[] args) {
        StartupController startupController = new StartupController();

        String confName = "sns-spring.xml";
        startupController.setConfName(confName);
        DBGateStartup.load();
        EleasticSearchClient.start();
        startupController.start();


    }
}
