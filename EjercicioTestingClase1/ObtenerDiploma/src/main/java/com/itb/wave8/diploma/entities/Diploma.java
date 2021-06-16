package com.itb.wave8.diploma.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diploma {
    private String mensaje;
    private Alumno alumno;
    private Double promedio;
}
