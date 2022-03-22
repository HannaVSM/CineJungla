package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cargo_ocupado")
public class CargoOcupado {

    @EmbeddedId
    private CargoOcupadoPK id;

    @Column(name = "fecha_inicio_cargo")
    private Date fechaInicioCargo;

    @Column(name = "fecha_fin_cargo")
    private Date fechaFinCargo;

    @ManyToOne
    @JoinColumn(name = "codigo_cargo", insertable = false, updatable = false)
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "cedula_empleado", insertable = false, updatable = false)
    private Empleado empleado;

    public CargoOcupadoPK getId() {
        return id;
    }

    public void setId(CargoOcupadoPK id) {
        this.id = id;
    }

    public Date getFechaInicioCargo() {
        return fechaInicioCargo;
    }

    public void setFechaInicioCargo(Date fechaInicioCargo) {
        this.fechaInicioCargo = fechaInicioCargo;
    }

    public Date getFechaFinCargo() {
        return fechaFinCargo;
    }

    public void setFechaFinCargo(Date fechaFinCargo) {
        this.fechaFinCargo = fechaFinCargo;
    }
}
