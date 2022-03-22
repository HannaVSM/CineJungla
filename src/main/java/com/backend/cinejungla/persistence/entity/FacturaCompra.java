package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "factura_compra")
public class FacturaCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_factura_compra")
    private Integer codigoFacturaCompra;

    @Column (name = "puntos_redimidos")
    private Boolean puntosRedimidos;

    @Column (name = "total_pago")
    private Double totalPago;

    @Column(name = "cedula_cliente")
    private Integer cedulaCliente;

    public Integer getCodigoFacturaCompra() {
        return codigoFacturaCompra;
    }

    public void setCodigoFacturaCompra(Integer codigoFacturaCompra) {
        this.codigoFacturaCompra = codigoFacturaCompra;
    }

    public Boolean getPuntosRedimidos() {
        return puntosRedimidos;
    }

    public void setPuntosRedimidos(Boolean puntosRedimidos) {
        this.puntosRedimidos = puntosRedimidos;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public Integer getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(Integer cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }
}
