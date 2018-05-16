package com.webischia.libraryfrontend.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

//Le grandi cose non sono fatte d’impulso, ma attraverso una serie di piccole cose messe insieme. -van gogh
@Data
@NoArgsConstructor
public class Items {
    int itemID;
    String itemName;
    int typeID;
    String itemDesc;
    String ISBN; //= STOCKNO
    String stockNo;
    String pageNumber;
    String sizeValue;
    String editionNo;
    String printYear;
    Timestamp editDate;
    String itemLang;
    int publisherID;

    public Items(int itemID, String itemName, int typeID, String itemDesc, String ISBN, String stockNo, String pageNumber, String sizeValue, String editionNo, String printYear, Timestamp editDate, String itemLang,int publisherID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.typeID = typeID;

        this.itemDesc = itemDesc;
        this.publisherID = publisherID;
        this.ISBN = ISBN;
        this.stockNo = stockNo;
        this.pageNumber = pageNumber;
        this.sizeValue = sizeValue;
        this.editionNo = editionNo;
        this.printYear = printYear;
        this.editDate = editDate;
        this.itemLang = itemLang;
    }
}
