package com.itb.wave8.diploma.services;

import com.itb.wave8.diploma.entities.Alumno;
import com.itb.wave8.diploma.entities.Diploma;
import com.itb.wave8.diploma.exceptions.UserNotActiveException;
import com.itb.wave8.diploma.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiplomaService implements IDiplomaService {

    private List<Alumno> listaAlumnos;
    private static final double NOTA_RECONOCIMIENTO = 9;

    public DiplomaService() {
        this.listaAlumnos = new ArrayList<>();
    }

    @Override
    public void crearAlumno(Alumno alumno) {
        this.listaAlumnos.add(alumno);
    }

    @Override
    public List<Alumno> verAlumnos() {
        return this.listaAlumnos;
    }

    @Override
    public Alumno buscarAlumno(String nombreAlumno)  throws UserNotFoundException {
        for (Alumno alumno : listaAlumnos) {
            if (nombreAlumno.equals(alumno.getNombre())) {
                return alumno;
            }
        }
        throw new UserNotFoundException("El alumno no existe");
    }

    @Override
    public Diploma generarDiploma(Alumno alumno) throws UserNotActiveException{
        if(!alumno.isAlumnoActivo()){
            throw new UserNotActiveException("Error el alumno no se puede graduar ");
        }
        Diploma diploma = new Diploma();
        diploma.setAlumno(alumno);
        diploma.setPromedio(alumno.calcularPromedio());
        diploma.setMensaje(diploma.getPromedio()>= NOTA_RECONOCIMIENTO?"Felicitaciones por tu excelente trabajo":"Eres del monton");
        return diploma;
    }
}
