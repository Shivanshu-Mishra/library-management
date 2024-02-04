package io.system.design;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Hello world!
 *
 */
public class LibraryApp 
{
    private static Properties appProperties;
    private static Logger log=Logger.getLogger(LibraryApp.class);
    public static void main( String[] args )
    {
        init();
        log.info("Welcome to "+getAppProperties().get("name"));
    }

    public static Properties getAppProperties(){
        if(appProperties==null){
            appProperties=new Properties();
            try(InputStream inputStream=LibraryApp.class.getClassLoader().getResourceAsStream("application.properties")){
                if(inputStream!=null){
                    appProperties.load(inputStream);
                }else{
                    log.error("application.properties file not found");
                }
            }catch(IOException ioe){
                log.error("Issue in loading application.properties",ioe);
            }
        }
        return appProperties;
    }

    public static void init(){
        Properties properties=new Properties();
        try(InputStream inputStream=LibraryApp.class.getClassLoader().getResourceAsStream("log4j.properties")){
            if(inputStream!=null){
                properties.load(inputStream);
                PropertyConfigurator.configure(properties);
            }else{
                System.out.println("log4j.properties file not found");
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
