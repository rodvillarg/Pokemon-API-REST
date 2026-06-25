package com.example.PokemonAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio y no puede ser asi.")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El tipo es obligatorio y no puede ser vacio.")
    private String tipo;

    @NotNull(message = "el nivel es obligatorio")
    @Min(value = 1, message = "El nivel minimo es 1")
    @Max(value = 100, message = "El nivel maximo es 100")
    private Integer nivel;
    
    @Min(value = 0, message = "El ataque no puede ser negativo")
    private Integer ataque;

    @Min(value = 0, message = "La defensa no puede ser negativo")
    private Integer defensa;
    
    @Size(max = 255, message = "La descripcion no puede exceder de 255 caracteres")
    private String descripcion;

    @URL(message = "La imagen debe ser una URL valida")
    private String imagenUrl;

    @Email(message = "El formato de correo no es valido")
    private String correoEntrenador;

    @Past(message = "La fecha de captura no puede ser futura")
    private LocalDate fechaCaptura;
}

