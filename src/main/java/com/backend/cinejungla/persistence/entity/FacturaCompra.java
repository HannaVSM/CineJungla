package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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

    @Column(name = "fecha_factura")
    private Date fechaFactura;

    @OneToMany(mappedBy = "facturaCompra")
    private List<DetalleDispoSilla> detalleDispoSillas;

    @ManyToOne
    @JoinColumn(name = "cedula_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "facturaCompra")
    private List<CompraSnack> compraSnacks;

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

    public Date getFechaFactura() { return fechaFactura; }

    public void setFechaFactura(Date fechaFactura) { this.fechaFactura = fechaFactura; }
}
