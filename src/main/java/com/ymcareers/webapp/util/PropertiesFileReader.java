package com.ymcareers.webapp.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** This class created to read Properties file. */
public class PropertiesFileReader {

  private final String workingDir = System.getProperty("user.dir");

  /**
   * Method will load the properties file.
   *
   * @param properties Name of the Properties file.
   * @return Properties
   */
  public Properties initialiseProperties(String properties) {
    Properties prop = new Properties();
    try (FileInputStream file =
        new FileInputStream(
            ""
                + workingDir
                + "/src/main/java/com/ymcareers/webapp/config/"
                + properties
                + ".properties")) {
      prop.load(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return prop;
  }
}
