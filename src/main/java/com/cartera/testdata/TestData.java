package com.cartera.testdata;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class TestData {

    private Properties properties = new Properties();
    private InputStream inputStream;

    public String getData(String propName) {

        try {
            String name = System.getenv("ENV_DATA_FILE");
            inputStream = this.getClass().getResourceAsStream(name);
            properties.load(inputStream);
            return properties.getProperty(propName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<String> getList(String propName) {
        List<String> dataList = new LinkedList<String>();
        String[] lines = getData(propName).split(";");

        for (String line : lines) {
            dataList.add(line);
        }
        return dataList;
    }

    public String isTestDisabled(String propName) {
        try {
            String name = System.getenv("ENV_SUITE_FILE");
            inputStream = this.getClass().getResourceAsStream(name);
            properties.load(inputStream);
            return properties.getProperty(propName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean isTestCaseDisabled(String propName, String testCaseName) {
        try {
            String name = System.getenv("ENV_SUITE_FILE");
            inputStream = this.getClass().getResourceAsStream(name);
            properties.load(inputStream);
            String prop = properties.getProperty(propName);
            return prop.contains(testCaseName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
