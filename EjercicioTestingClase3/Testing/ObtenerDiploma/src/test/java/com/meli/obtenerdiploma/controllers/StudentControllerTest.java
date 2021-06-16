package com.meli.obtenerdiploma.controllers;


import com.meli.obtenerdiploma.controller.StudentController;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.rmi.server.ExportException;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    StudentService service;

    @InjectMocks
    StudentController controller;

    @Test
    void test_registerStudentOkResponse() throws Exception{
        Mockito.doNothing().when(this.service).create(any());
        ResponseEntity<?> response = this.controller.registerStudent(new StudentDTO());
        Mockito.verify(this.service, Mockito.atLeastOnce()).create(any());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
