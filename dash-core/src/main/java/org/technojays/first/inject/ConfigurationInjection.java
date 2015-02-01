package org.technojays.first.inject;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author DaPortlyJester
 * @since 1/18/2015
 *
 * Configure application properties from local file specified in dash.config.file system property or
 * based on configured environment properties
 */
public class ConfigurationInjection implements Module {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void configure(Binder binder) {
        Properties appProperties;
        String configFile = System.getProperty("dash.config.file");

        if (configFile != null && !configFile.isEmpty()) {
            logger.debug("Found config file {}.", configFile);
            appProperties = new Properties();
            try {
                appProperties.load(new FileReader(configFile));
            } catch (IOException e) {
                logger.error("Failed to read specified properties file {}", configFile, e);
                throw new RuntimeException("Unable to read specified dash.config.file", e);
            }
        } else {
            logger.debug("Configuring using environment properties");
            appProperties = System.getProperties();
            /**
             * Uncomment to see loaded environment properties - All properties are loaded from environment on Heroku
             * See system.properties file for more information
            for(String propertyName : appProperties.stringPropertyNames()){
                logger.debug("Property: {} - {}", propertyName, appProperties.getProperty(propertyName));
            }**/
        }

        // binds properties for Guice
        Names.bindProperties(binder, appProperties);
    }
}
