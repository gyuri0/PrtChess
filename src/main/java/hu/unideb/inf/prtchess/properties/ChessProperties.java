/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prtchess.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author György
 */
public class ChessProperties {
    private static String xmlFilePath;
    
    static
    {
        try {
            InputStream inputStream  = ChessProperties.class.getResourceAsStream("/properties/config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            xmlFilePath = properties.getProperty("xmlfilepath");
        } catch (IOException ex) {
            Logger.getLogger(ChessProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getXMLFilePath()
    {
        return xmlFilePath;
    }
}
