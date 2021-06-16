package com.meli.obtenerdiploma.repository;

import com.meli.obtenerdiploma.model.StudentDTO;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {

    StudentDAO dao = new StudentDAO();
    StudentRepository repo = new StudentRepository();

    @Test
    void saveNewElement() {
        Set<StudentDTO> students = repo.findAll();
        long nextId = students.size() + 1;

        StudentDTO s = new StudentDTO();
        s.setStudentName("Test");
        this.dao.save(s);

        StudentDTO st = this.dao.findById(nextId);
        assertEquals(st.getId(), nextId);
    }

    @Test
    void delete() {
    }

    @Test
    void exists() {
        StudentDTO st = new StudentDTO();
        st.setId(8L);
        boolean response = this.dao.exists(st);
        assertEquals(response, true);
    }

    @Test
    void findById() {
    }
}