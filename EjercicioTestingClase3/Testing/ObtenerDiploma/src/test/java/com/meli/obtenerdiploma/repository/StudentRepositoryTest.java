package com.meli.obtenerdiploma.repository;

import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class StudentRepositoryTest {

    @Mock
    StudentRepository repo;

    @Test
    void findAll() {
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
        student1.setStudentName("Juan");

        SubjectDTO sub4 = new SubjectDTO("Matemática", 9.0);
        SubjectDTO sub5 = new SubjectDTO("Física", 7.0);
        SubjectDTO sub6 = new SubjectDTO("Química", 6.0);
        ArrayList<SubjectDTO> subject2 = new ArrayList<>();
        subject2.add(sub4);
        subject2.add(sub5);
        subject2.add(sub6);

        StudentDTO student2 = new StudentDTO();
        student2.setId((long)2);
        student2.setSubjects(subject2);
        student2.setStudentName("Pedro");

        HashSet<StudentDTO> students = new HashSet<>();
        students.add(student2);
        students.add(student1);


        Mockito.when(this.repo.findAll()).thenReturn(students);
        Set<StudentDTO> response = this.repo.findAll();

        for(int i = 0; i < response.size(); i++){
            StudentDTO a = (StudentDTO)response.toArray()[i];
            StudentDTO b = (StudentDTO) students.toArray()[i];
            assertEquals(a.getId(), b.getId());
        }
    }
}