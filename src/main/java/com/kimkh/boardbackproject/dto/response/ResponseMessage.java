package com.kimkh.boardbackproject.dto.response;


public interface ResponseMessage {
    String SUCCESS = "Succss.";
    
    String VALIDATION_FAILED = "Validation Failed.";
    String DUPLICATED_EMAIL = "Duplicated email.";
    String DUPLICATED_NICKNAME = "Duplicated nickname.";
    String DUPLICATED_TELNUMBER = "Duplicated telNumber number";
    String NOT_EXIST_USER = "This user does exits";
    String NOT_EXIST_BOARD = "This board does exists";  

    String SIGN_IN_FAILED = "Login information mismatch";

    String DATABASE_ERROR = "DataBase error.";
    
}
