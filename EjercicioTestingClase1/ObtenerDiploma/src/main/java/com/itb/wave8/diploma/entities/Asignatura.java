package com.itb.wave8.diploma.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura {

    @NotBlank(message = "El nombre de la materia no puede estar vacío.")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre de la materia debe comenzar con mayúscula")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres")
    private String nombre;

    @NotNull(message = "La nota no puede estar vacía")
    @Min(value = 0, message = "La mínima nota es 0.0")
    @Max(value = 10, message = "La máxima nota es 10.0")
    private Double nota;
}
