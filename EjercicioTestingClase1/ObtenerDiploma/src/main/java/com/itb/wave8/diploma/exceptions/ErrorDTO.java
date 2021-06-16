package com.itb.wave8.diploma.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {

    private Timestamp timestamp;
    private String data;
    private int responseCode;
    private String status;

}
