package com.centrumhr.desktop.service;

import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.application.sync.IORMLiteDataBaseService;
import com.centrumhr.application.sync.IXMLDataBaseService;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.data.core.xml.IXMLDataBase;
import com.centrumhr.desktop.data.ORMLiteDatabase;
import com.centrumhr.desktop.data.XMLDatabase;

import java.io.File;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class XMLDataBaseService implements IXMLDataBaseService {

    private static String TAG = XMLDataBaseService.class.getName();

    private static final int DB_VERSION = 4;

    private final String PATH_TO_DATABASE = "./data/";

    private XMLDatabase xmlDataBase;

    @Override
    public String getDatabaseName( AccountData accountData ){
        return "attendanceDataBase_" + DB_VERSION + ".xml";
    }

    private File getDatabaseFile( String name ){
        return new File(PATH_TO_DATABASE + name);
    }

    @Override
    public boolean dataBaseExists( AccountData accountData ) {
        String name = getDatabaseName(accountData);
        File dbFile = getDatabaseFile(name);
        boolean dbExists = dbFile.exists() && !dbFile.isDirectory();
        if(dbExists){
            xmlDataBase = new XMLDatabase(dbFile);
            System.out.println("[XMLDataBaseService]"+TAG+":connectDatabase:"+dbFile.getPath());
        }
        return dbExists;
    }

    @Override
    public IXMLDataBase createDataBase(AccountData accountData) {
        String name = getDatabaseName(accountData);
        File dbFile = getDatabaseFile(name);
        xmlDataBase = new XMLDatabase(dbFile);
        xmlDataBase.onCreateDataBase();
        System.out.println("[ORMLiteDataBaseService]"+TAG+":createDataBase:"+dbFile.getPath());
        return xmlDataBase;
    }

    @Override
    public IXMLDataBase provideDataBase(){
        if(xmlDataBase==null)throw new RuntimeException("ormLiteDatabase==null");
        return xmlDataBase;
    }
}
