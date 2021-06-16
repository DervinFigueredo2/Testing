package com.meli.obtenerdiploma.service;

import com.meli.obtenerdiploma.exception.StudentNotFoundException;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.StudentDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ObtenerDiplomaServiceTest {

    @Mock
    StudentDAO dao = new StudentDAO();

    @InjectMocks
    ObtenerDiplomaService service = new ObtenerDiplomaService();

    @Test
    public void testAnalyzeScoresCorrectValues(){
        ArrayList<SubjectDTO> subjects = new ArrayList<>();
        SubjectDTO sub1 = new SubjectDTO("Matemática", 9.0);
        SubjectDTO sub2 = new SubjectDTO("Física", 7.0);
        SubjectDTO sub3 = new SubjectDTO("Química", 6.0);
        subjects.add(sub1);
        subjects.add(sub2);
        subjects.add(sub3);

        StudentDTO student1 = new StudentDTO();
        student1.setId((long)1);
        student1.setSubjects(subjects);
        student1.setStudentName("Juanadasda");

        Mockito.when(dao.findById(1L)).thenReturn(student1);

        StudentDTO response = this.service.analyzeScores(1L);
        assertEquals(response.getAverageScore(), 7.333333333333333);
    }

    @Test
    public void testAnalyzeScoreCongratsMsg(){
        ArrayList<SubjectDTO> subjects = new ArrayList<>();
        SubjectDTO sub1 = new SubjectDTO("Matemática", 9.0);
        SubjectDTO sub2 = new SubjectDTO("Física", 9.0);
        SubjectDTO sub3 = new SubjectDTO("Quimica", 10.0);
        subjects.add(sub1);
        subjects.add(sub2);
        subjects.add(sub3);

        StudentDTO student1 = new StudentDTO();
        student1.setId(1L);
        student1.setSubjects(subjects);
        student1.setStudentName("Juan");

        Mockito.when(dao.findById(1L)).thenReturn(student1);

        StudentDTO response  = this.service.analyzeScores(1L);
        assertEquals("El alumno Juan ha obtenido un promedio de 9,33. Felicitaciones!", response.getMessage());
    }

    @Test
    public void testAnalyzeScoreNoCongrats(){
        ArrayList<SubjectDTO> subjects = new ArrayList<>();
        SubjectDTO sub1 = new SubjectDTO("Matemática", 3.0);
        SubjectDTO sub2 = new SubjectDTO("Física", 4.0);
        SubjectDTO sub3 = new SubjectDTO("Quimica", 5.0);
        subjects.add(sub1);
        subjects.add(sub2);
        subjects.add(sub3);

        StudentDTO student1 = new StudentDTO();
        student1.setId(1L);
        student1.setSubjects(subjects);
        student1.setStudentName("Juan");

        Mockito.when(dao.findById(1L)).thenReturn(student1);

        StudentDTO response  = this.service.analyzeScores(1L);
        assertEquals("El alumno Juan ha obtenido un promedio de 4. Puedes mejorar.", response.getMessage());
    }
}