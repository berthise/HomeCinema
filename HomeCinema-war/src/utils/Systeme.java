/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author seb
 */
public class Systeme {
    // Glassfish Instance Root folder system variable

  private static String glassfishInstanceRootPropertyName = "com.sun.aas.instanceRoot";

// "config" sub-folder name
  private static String glassfishDomainConfigurationFolderName = "config";

// Read a given file from Glassfish Domain Configuration folder
  private static File readFileFromGlassfishDomainConfigFolder(final String fileName) throws FileNotFoundException {
    // Instance Root folder
    final String instanceRoot = java.lang.System.getProperty(glassfishInstanceRootPropertyName);
    if (instanceRoot == null) {
      throw new FileNotFoundException("Cannot find Glassfish instanceRoot. Is the com.sun.aas.instanceRoot system property set?");
    }
    java.lang.System.out.println("path : " + instanceRoot + File.separator + glassfishDomainConfigurationFolderName);
    // Instance Root + /config folder
    File configurationFolder = new File(instanceRoot + File.separator + glassfishDomainConfigurationFolderName);
    File configFile = new File(configurationFolder, fileName);

    // return the given file
    return configFile;
  }
}
