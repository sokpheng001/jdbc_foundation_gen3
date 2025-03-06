package utils;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConfig {
    private static Properties properties = new Properties();
    public static Properties getDataCredential(){
        try(FileReader fileReader = new FileReader("app.properties")){
            properties.load(fileReader);
        }catch (Exception exception){
            System.out.println("[!] Error during login properties :" + exception.getMessage());
        }
        return properties;
    }
    public static Connection getDataConnection(){
        try{
            Connection connection = DriverManager.getConnection(DatabaseConfig.getDataCredential().getProperty("db_url"),
                    DatabaseConfig.getDataCredential().getProperty("db_username"),
                    DatabaseConfig.getDataCredential().getProperty("db_password"));
            return connection;

        }catch (Exception exception){
            System.out.println("[+] Error during get database connection: " + exception.getMessage());
        }
        return null;
    }
}
