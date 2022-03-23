package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name="cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_cargo")
    private Integer codigoCargo;

    @Column(name = "nombre_cargo")
    private String nombreCargo;

    private Double salario;

    @OneToMany(mappedBy = "cargo")
    private List <CargoOcupado> empleados;

    public Integer getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(Integer codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
