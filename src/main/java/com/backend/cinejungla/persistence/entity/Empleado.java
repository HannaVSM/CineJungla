package com.backend.cinejungla.persistence.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "empleado")
public class Empleado {

    @Id
    @Column(name = "cedula_empleado")
    private Integer cedulaEmpleado;

    @Column(name = "nombre_empleado")
    private  String nombreEmpleado;

    @Column (name = "codigo_multiplex")
    private Integer codigoMultiplex;

    @OneToMany(mappedBy = "multiplex")
    private List <CargoOcupado> cargos;

    @ManyToOne
    @JoinColumn(name = "codigo_multiplex", insertable = false, updatable = false)
    private Multiplex multiplex;

    public Integer getCedulaEmpleado() {
        return cedulaEmpleado;
    }

    public void setCedulaEmpleado(Integer cedulaEmpleado) {
        this.cedulaEmpleado = cedulaEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Integer getCodigoMultiplex() {
        return codigoMultiplex;
    }

    public void setCodigoMultiplex(Integer codigoMultiplex) {
        this.codigoMultiplex = codigoMultiplex;
    }
}
