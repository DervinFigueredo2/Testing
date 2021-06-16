package com.itb.wave8.diploma.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    @NotBlank(message = "El nombre del alumno no puede estar vacío")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre del alumno comenzar con mayúscula")
    @Size(max = 50, message = "La longitud del nombre no puede superar los 50 caracteres")
    private String nombre;

    @NotEmpty(message = "La lista no puede ser vacía.")
    @Valid
    private List<Asignatura> listaAsignaturas;

    private boolean alumnoActivo;

    public Double calcularPromedio() {
        Double promedioAlumno = 0d;
        for (Asignatura asignatura : listaAsignaturas) {
            promedioAlumno += asignatura.getNota();
        }
        return promedioAlumno / listaAsignaturas.size();
    }

}
