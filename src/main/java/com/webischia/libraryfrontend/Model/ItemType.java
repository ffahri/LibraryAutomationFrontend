package com.webischia.libraryfrontend.Model;

import lombok.Data;

@Data
public class ItemType {
    int typeID;
    String typeName;

    public ItemType(String typeName) {
        this.typeName = typeName;
    }

    public ItemType(int typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
    }
}
