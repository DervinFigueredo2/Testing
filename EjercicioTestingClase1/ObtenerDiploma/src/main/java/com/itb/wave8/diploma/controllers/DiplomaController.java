package com.itb.wave8.diploma.controllers;

import com.itb.wave8.diploma.entities.Alumno;
import com.itb.wave8.diploma.entities.Diploma;
import com.itb.wave8.diploma.exceptions.UserNotActiveException;
import com.itb.wave8.diploma.exceptions.UserNotFoundException;
import com.itb.wave8.diploma.services.IDiplomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/diploma")
public class DiplomaController {

    @Autowired
    private IDiplomaService diplomaService;

    @PostMapping("/crearAlumno")
    public ResponseEntity crearAlumno(@Valid @RequestBody Alumno alumno){
        this.diplomaService.crearAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/verAlumnos")
    public ResponseEntity<List<Alumno>> verAlumnos(){
        return ResponseEntity.ok(this.diplomaService.verAlumnos());
    }

    @GetMapping("/{nombreAlumno}")
    public ResponseEntity<Diploma> generarDiploma(@PathVariable String nombreAlumno) throws UserNotActiveException, UserNotFoundException {
        Alumno alumnoEncontrado = this.diplomaService.buscarAlumno(nombreAlumno);
        if(alumnoEncontrado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.diplomaService.generarDiploma(alumnoEncontrado));
    }
}
