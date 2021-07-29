package edu.labIV.cfg;


import org.ini4j.Ini;

import java.io.*;

public class Config {

    private static Config instance;

    private Ini ini;

    private Config() {
        try {
            ini = new Ini(new File("config/config.devs"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTomcatURL(){
        return getString(ConfigSection.TOMCAT, ConfigKey.TOMCAT_URL);
    }

    public int getLoginAttempts(){
        return getInt(ConfigSection.ACCOUNT, ConfigKey.ACC_ATTEMPTS);
    }

    public String getDataBaseUsername(){
        return getString(ConfigSection.DATABASE, ConfigKey.DB_USER);
    }

    public String getDataBasePassword(){
        return getString(ConfigSection.DATABASE, ConfigKey.DB_PASS);
    }

    public String getDataBaseURL(){
        return getString(ConfigSection.DATABASE, ConfigKey.DB_URL);
    }

    public String getMailAccount(){
        return getString(ConfigSection.MAIL, ConfigKey.MAIL_USER);
    }

    public String getMailPassword(){
        return getString(ConfigSection.MAIL, ConfigKey.MAIL_PASS);
    }

    public String getMailProtocol(){
        return getString(ConfigSection.MAIL, ConfigKey.MAIL_PROTOCOL);
    }

    public String getMailHost(){
        return getString(ConfigSection.MAIL, ConfigKey.MAIL_SERVER);
    }

    public String getMailPort(){
        return getString(ConfigSection.MAIL, ConfigKey.MAIL_PORT);
    }

    public int getPostBufferSize(){
        return getInt(ConfigSection.POST, ConfigKey.POST_BUFFER);
    }

    public static Config getInstance(){
        if(instance == null)
            instance = new Config();

        return instance;
    }

    public String getString(ConfigSection section, ConfigKey key){
        return ini.get(section.name(), key.getKey());
    }

    public int getInt(ConfigSection section, ConfigKey key){
        return Integer.parseInt(getString(section, key) );
    }

}
