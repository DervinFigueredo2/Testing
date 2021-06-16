package com.meli.obtenerdiploma.service;

import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.IStudentDAO;
import com.meli.obtenerdiploma.repository.IStudentRepository;
import com.meli.obtenerdiploma.repository.StudentDAO;
import com.meli.obtenerdiploma.repository.StudentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    IStudentDAO dao;

    @Mock
    IStudentRepository repo;

    @InjectMocks
    StudentService service;


    @Test
    void create() {
        StudentDTO st = new StudentDTO();

        Mockito.doNothing().when(this.dao).save(any());
        this.service.create(st);

        Mockito.verify(this.dao, Mockito.atLeastOnce()).save(st);
    }

    @Test
    void read() {
        StudentDTO st = new StudentDTO();
        st.setId(1L);
        Mockito.when(this.dao.findById(1L)).thenReturn(st);

        StudentDTO response = this.service.read(1L);
        assertEquals(response.getId(), st.getId());
        Mockito.verify(this.dao, Mockito.atLeastOnce()).findById(1L);
    }

    @Test
    void update() {
        StudentDTO st = new StudentDTO();

        Mockito.doNothing().when(this.dao).save(any());
        this.service.update(st);

        Mockito.verify(this.dao, Mockito.atLeastOnce()).save(st);
    }

    @Test
    void delete() {
        Mockito.when(this.dao.delete(1L)).thenReturn(true);
        this.service.delete(1L);
        Mockito.verify(this.dao, Mockito.atLeastOnce()).delete(1L);
    }

    @Test
    void getAll() {
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
        StudentDTO student2 = new StudentDTO();
        student2.setId(2L);
        student2.setStudentName("Pedro");

        Set<StudentDTO> set = new HashSet<>();
        set.add(student1);
        set.add(student2);

        Mockito.when(this.repo.findAll()).thenReturn(set);
        Set<StudentDTO> response = this.service.getAll();
        assertEquals(response.size(), set.size());
        Mockito.verify(this.repo, Mockito.atLeastOnce()).findAll();
    }
}