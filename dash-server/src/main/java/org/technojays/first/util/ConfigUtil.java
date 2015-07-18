package org.technojays.first.util;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author DaPortlyJester
 * @since 5/3/2015
 */
public class ConfigUtil {

    private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

    public static Properties loadConfig(Properties properties, String configFile) {
        if(Strings.isNullOrEmpty(configFile)) {
            loadH4SystemProperties(properties);
        } else {
            loadH4ConfigFile(properties, configFile);
        }
        return properties;
    }

    public static Properties loadH4SystemProperties(Properties properties){
        logger.debug("Configuring using environment properties");
        properties.setProperty(FDC.H4_DRIVER, System.getProperty(FDC.H4_DRIVER));
        properties.setProperty(FDC.H4_URL, System.getProperty(FDC.H4_URL));
        properties.setProperty(FDC.H4_USER, System.getProperty(FDC.H4_USER));
        properties.setProperty(FDC.H4_PASS, System.getProperty(FDC.H4_PASS));
        properties.setProperty(FDC.H4_POOL_SIZE, System.getProperty(FDC.H4_POOL_SIZE));
        properties.setProperty(FDC.H4_DIALECT, System.getProperty(FDC.H4_DIALECT));
        properties.setProperty(FDC.H4_DDL_AUTO, System.getProperty(FDC.H4_DDL_AUTO));
        return properties;
    }

    public static Properties loadH4ConfigFile(Properties properties, String configFile) {
        logger.debug("Using H4 config file {}.", configFile);
        try {
            properties.load(new FileReader(configFile));
        } catch (IOException e) {
            logger.error("Failed to read specified H4 properties file {}", configFile, e);
            throw new RuntimeException("Unable to read specified H4 dash.config.file", e);
        }
        return properties;
    }

}
