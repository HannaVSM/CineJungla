package com.backend.cinejungla.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CargoOcupadoPK implements Serializable {

    @Column(name = "cedula_empleado")
    private Integer cedulaEmpleado;

    @Column(name = "codigo_cargo")
    private Integer codigoCargo;

    public Integer getCedulaEmpleado() {
        return cedulaEmpleado;
    }

    public void setCedulaEmpleado(Integer cedulaEmpleado) {
        this.cedulaEmpleado = cedulaEmpleado;
    }

    public Integer getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(Integer codigoCargo) {
        this.codigoCargo = codigoCargo;
    }
}
