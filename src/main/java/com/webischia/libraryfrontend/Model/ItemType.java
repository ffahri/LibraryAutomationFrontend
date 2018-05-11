package com.webischia.libraryfrontend.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
