package com.webischia.libraryfrontend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDTO {
    String mail;
    String lokasyon1;
    String lokasyon2;
}
