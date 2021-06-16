package com.meli.obtenerdiploma.repository;

import com.meli.obtenerdiploma.exception.StudentNotFoundException;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class StudentDAOTest {

    private StudentDAO studentDAO = new StudentDAO();

    private StudentDTO studentDTO = new StudentDTO(
            null,
            "Agustin",
            "mensaje",
            8.0,
            List.of(new SubjectDTO("nombre", 8.0)));

    @Test
    public void saveNull() {
        assertThrows(Exception.class, () -> studentDAO.save(null), "no se puede guardar datos nulos");
    }

    @Test
    void doesNotExists() {
        assertEquals(false, studentDAO.exists(studentDTO));
    }

    @Test
    void findByIdNull() {
        assertThrows(StudentNotFoundException.class,() -> studentDAO.findById(null));
    }

}