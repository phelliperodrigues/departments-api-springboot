package com.phellipe.departments.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BoardDirector {
     BUSINESS(1,"Business"),
     SECURITY(2, "Security");



     private int id;
     private String description;


}
