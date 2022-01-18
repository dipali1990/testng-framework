package com.ymcareers.webapp.listener;

import java.util.Map;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class EnvReaderListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        Map<String, String> parameters =
                suite.getXmlSuite().getParameters();
        for (Map.Entry<String, String> parameter : parameters
                .entrySet()) {
            String env = System.getenv(parameter.getKey());
            if (env != null && !env.trim().isEmpty()) {
                parameter.setValue(env);
            }
        }
    }

    @Override
    public void onFinish(ISuite suite) {

    }
}


