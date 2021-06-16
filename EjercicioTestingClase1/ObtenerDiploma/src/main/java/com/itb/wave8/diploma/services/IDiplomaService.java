package com.itb.wave8.diploma.services;

import com.itb.wave8.diploma.entities.Alumno;
import com.itb.wave8.diploma.entities.Diploma;
import com.itb.wave8.diploma.exceptions.UserNotActiveException;
import com.itb.wave8.diploma.exceptions.UserNotFoundException;

import java.util.List;

public interface IDiplomaService {

    public void crearAlumno(Alumno alumno);

    public List<Alumno> verAlumnos();

    public Alumno buscarAlumno(String nombreAlumno) throws UserNotFoundException;

    public Diploma generarDiploma(Alumno alumno) throws UserNotActiveException;

}
