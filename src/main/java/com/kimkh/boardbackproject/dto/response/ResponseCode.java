package com.kimkh.boardbackproject.dto.response;

public interface ResponseCode {
    String SUCCESS = "SU";

    String VALIDATION_FAILED = "VF";
    String DUPLICATED_EMAIL = "DE";
    String DUPLICATED_NICKNAME = "DN";  
    String DUPLICATED_TELNUMBER= "DT";
    String NOT_EXIST_USER = "NU";
    String NOT_EXIST_BOARD = "NB";      

    String SIGN_IN_FAILED = "SF";

    String DATABASE_ERROR = "DBE"; 
    
    String NO_PERMISSION = "NP";

    

}
