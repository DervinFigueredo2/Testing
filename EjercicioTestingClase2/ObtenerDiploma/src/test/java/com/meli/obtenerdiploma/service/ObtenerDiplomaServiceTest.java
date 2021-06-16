package com.meli.obtenerdiploma.service;

import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.IStudentDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObtenerDiplomaServiceTest {

    @Mock
    private IStudentDAO studentDAO;

    @InjectMocks
    private ObtenerDiplomaService obtenerDiplomaService;

    @Test
    void analyzeScores() {

        StudentDTO studentDTO = new StudentDTO(
                1L,
                "Agustin",
                null,
                null,
                List.of(new SubjectDTO("biologia", 8.0)));

        String expectedMessage = "El alumno Agustin ha obtenido un promedio de 8. Puedes mejorar.";
        Double expectedAverageScore = 8.0;

        when(studentDAO.findById(1L)).thenReturn(studentDTO);
        StudentDTO actual = obtenerDiplomaService.analyzeScores(1L);
        assertAll(
                () -> assertEquals(expectedMessage, actual.getMessage()),
                () -> assertEquals(expectedAverageScore, actual.getAverageScore())
        );

    }
}