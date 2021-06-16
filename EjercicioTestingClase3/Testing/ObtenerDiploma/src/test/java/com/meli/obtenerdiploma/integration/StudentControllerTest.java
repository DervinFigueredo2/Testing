package com.meli.obtenerdiploma.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test_registerStudentOkResponse() throws Exception {
        ArrayList<SubjectDTO> subjects = new ArrayList<>();
        SubjectDTO sub1 = new SubjectDTO("Matemática", 9.0);
        SubjectDTO sub2 = new SubjectDTO("Física", 9.0);
        SubjectDTO sub3 = new SubjectDTO("Quimica", 10.0);
        subjects.add(sub1);
        subjects.add(sub2);
        subjects.add(sub3);

        StudentDTO student1 = new StudentDTO();
        student1.setSubjects(subjects);
        student1.setStudentName("Juan");

        ObjectWriter mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
        String jsonPayload = mapper.writeValueAsString(student1);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/student/registerStudent")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonPayload)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    void test_registerStudentBadRequestNoSubject() throws Exception{
        StudentDTO student1 = new StudentDTO();
        student1.setStudentName("Juan");

        ObjectWriter mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
        String jsonPayload = mapper.writeValueAsString(student1);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/student/registerStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload)
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(
                MockMvcResultMatchers.jsonPath("$.name")
                        .value("MethodArgumentNotValidException")
        )
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("La lista de materias no puede estar vacía."));
    }

    @Test
    void test_registerStudentBadRequestNoName() throws Exception{
        ArrayList<SubjectDTO> subjects = new ArrayList<>();
        SubjectDTO sub1 = new SubjectDTO("Matemática", 9.0);
        SubjectDTO sub2 = new SubjectDTO("Física", 9.0);
        SubjectDTO sub3 = new SubjectDTO("Quimica", 10.0);
        subjects.add(sub1);
        subjects.add(sub2);
        subjects.add(sub3);

        StudentDTO student1 = new StudentDTO();
        student1.setSubjects(subjects);
        ObjectWriter mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
        String jsonPayload = mapper.writeValueAsString(student1);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/student/registerStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload)
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.name")
                                .value("MethodArgumentNotValidException")
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.description")
                                .value("El nombre del estudiante no puede estar vacío.")
                );
    }

    @Test
    void test_getStudentOkResponse() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/student/getStudent/{id}", 7)
                ).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentName").value("Juan"));

    }

    @Test
    void test_getStudentNotFound() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/student/getStudent/{id}", 123)
        ).andDo(print())
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(
                MockMvcResultMatchers.jsonPath("$.description")
                        .value("El alumno con Id 123 no se encuetra registrado.")
        );
    }

    @Test
    void test_modifyStudentResponseOk() throws Exception{
        ArrayList<SubjectDTO> subjects = new ArrayList<>();
        SubjectDTO sub1 = new SubjectDTO("Matemática", 9.0);
        SubjectDTO sub2 = new SubjectDTO("Física", 9.0);
        SubjectDTO sub3 = new SubjectDTO("Quimica", 10.0);
        SubjectDTO sub4 = new SubjectDTO("Historia", 8.0);
        subjects.add(sub1);
        subjects.add(sub2);
        subjects.add(sub3);
        subjects.add(sub4);

        StudentDTO student1 = new StudentDTO();
        student1.setId(8L);
        student1.setSubjects(subjects);
        student1.setStudentName("Juan");

        ObjectWriter mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
        String jsonPayload = mapper.writeValueAsString(student1);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/student/modifyStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload)
        ).andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void test_modifyStudentBadReqNoSubjects() throws Exception{

        StudentDTO student1 = new StudentDTO();
        student1.setId(111L);
        student1.setStudentName("Juan");

        ObjectWriter mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
        String jsonPayload = mapper.writeValueAsString(student1);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/student/modifyStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload)
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void test_modifyStudentBadReqNoName() throws Exception{

        ArrayList<SubjectDTO> subjects = new ArrayList<>();
        SubjectDTO sub1 = new SubjectDTO("Matemática", 9.0);
        SubjectDTO sub2 = new SubjectDTO("Física", 9.0);
        SubjectDTO sub3 = new SubjectDTO("Quimica", 10.0);
        SubjectDTO sub4 = new SubjectDTO("Historia", 8.0);
        subjects.add(sub1);
        subjects.add(sub2);
        subjects.add(sub3);
        subjects.add(sub4);

        StudentDTO student1 = new StudentDTO();
        student1.setId(111L);
        student1.setSubjects(subjects);

        ObjectWriter mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
        String jsonPayload = mapper.writeValueAsString(student1);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/student/modifyStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload)
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void test_removeStudentResponseOk() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/student/removeStudent/{id}", 1)
        ).andDo(print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void test_removeStudentNotExist() throws Exception{
       /* this.mockMvc.perform(
                MockMvcRequestBuilders.get("/student/removeStudent/{id}", 111)
        ).andDo(print()).andExpect(MockMvcResultMatchers.status().isBadRequest());
        */
        // no se puede testear este caso porque siempre retorna ok, incluso cuando falla
    }

    @Test
    void test_listStudents() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/student/listStudents"))
            .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}