package com.centrumhr.desktop.service;

import com.centrumhr.application.account.data.AccountData;
import com.centrumhr.application.sync.IDataBaseService;
import com.centrumhr.data.core.IORMLiteDataBase;
import com.centrumhr.desktop.data.ORMLiteDatabase;

import java.io.File;

/**
 * Created by Seweryn on 18.09.2016.
 */
public class DataBaseService implements IDataBaseService {

    private static String TAG = DataBaseService.class.getName();
    private static final int DB_VERSION = 4;

    private final String PATH_TO_DATABASE = "./data/";

    private ORMLiteDatabase ormLiteDatabase;

    @Override
    public String getDatabaseName( AccountData accountData ){
        return accountData.getUniqueId()+ "_" + DB_VERSION + ".db";
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
            ormLiteDatabase = new ORMLiteDatabase(dbFile);
            System.out.println("[DataBaseService]"+TAG+":connectDatabase:"+dbFile.getPath());
        }
        return dbExists;
    }

    @Override
    public IORMLiteDataBase createDataBase(AccountData accountData) {
        String name = getDatabaseName(accountData);
        File dbFile = getDatabaseFile(name);
        ormLiteDatabase = new ORMLiteDatabase(dbFile);
        ormLiteDatabase.onCreateDataBase();
        System.out.println("[DataBaseService]"+TAG+":createDataBase:"+dbFile.getPath());
        return ormLiteDatabase;
    }

    @Override
    public IORMLiteDataBase provideDataBase(){
        if(ormLiteDatabase==null)throw new RuntimeException("ormLiteDatabase==null");
        return ormLiteDatabase;
    }
}
