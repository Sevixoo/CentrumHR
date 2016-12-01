package com.centrumhr.data.model.settings;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

/**
 * Created by Seweryn on 23.10.2016.
 */
@DatabaseTable(tableName = "settingItem")
public class SettingItem {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(unique = true)
    private String name;

    @DatabaseField
    private String value;

    public SettingItem( ) {  }

    public SettingItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
