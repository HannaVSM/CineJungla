package com.backend.cinejungla.web.procesoCompra;


import com.backend.cinejungla.domain.service.*;
import com.backend.cinejungla.persistence.entity.*;
import com.backend.cinejungla.persistence.entity.Silla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Component
public class ProcesoConcreto extends ProcesoCompra {


    @Autowired
    private FuncionService funcionService;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private MultiplexService multiplexService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private SillaService sillaService;

    @Autowired
    private DetalleDispoSillaService detalleDispoSillaService;

    @Autowired
    private VentaSnackService ventaSnackService;

    @Autowired
    private SnackService snackService;

    @Autowired
    private FacturaCompraService facturaCompraService;

    @Autowired
    private CompraSnackService compraSnackService;

    @Autowired
    private ClienteService clienteService;

    private static ProcesoConcreto procesoConcreto;

    @PostConstruct
    public void init(){
        procesoConcreto = this;

        procesoConcreto.funcionService = this.funcionService;

        procesoConcreto.peliculaService = this.peliculaService;

        procesoConcreto.multiplexService = this.multiplexService;

        procesoConcreto.salaService = this.salaService;

        procesoConcreto.sillaService = this.sillaService;

        procesoConcreto.detalleDispoSillaService = this.detalleDispoSillaService;

        procesoConcreto.ventaSnackService = this.ventaSnackService;

        procesoConcreto.snackService = this.snackService;

        procesoConcreto.facturaCompraService = this.facturaCompraService;

        procesoConcreto.compraSnackService = this.compraSnackService;

        procesoConcreto.clienteService = this.clienteService;
    }

    @Override
    public Cliente leerArchivoCliente(){
        Cliente cliente = new Cliente();

        try{

            FileInputStream fileIn = new FileInputStream("cliente.obj");
            ObjectInputStream entrada = new ObjectInputStream(fileIn);

            cliente = (Cliente)entrada.readObject();
            entrada.close();
        }
        catch(Exception e){
            System.out.println("Fallo al leer el archivo cliente");
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public void guardarArchivoCliente(Cliente cliente){
        try{
            FileOutputStream fileOut = new FileOutputStream("cliente.obj");
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            salida.writeObject(cliente);
            salida.close();
        }
        catch(Exception e){
            System.out.println("Fallo al guardar del archivo cliente");
            e.printStackTrace();
        }
    }

    @Override
    public Pedido leerPedido(){
        Pedido pedido = new Pedido();

        try{

            FileInputStream fileIn = new FileInputStream("pedido.obj");
            ObjectInputStream entrada = new ObjectInputStream(fileIn);

            pedido = (Pedido)entrada.readObject();
            entrada.close();
        }
        catch(Exception e){
            System.out.println("Fallo al leer el archivo pedido");
            e.printStackTrace();
        }

        return pedido;
    }


    @Override
    public void guardarPedido(Pedido pedido){

        try{
            FileOutputStream fileOut = new FileOutputStream("pedido.obj");
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            salida.writeObject(pedido);
            salida.close();
        }
        catch(Exception e){
            System.out.println("Fallo al guardar el archivo pedido");
            e.printStackTrace();
        }
    }

    //Esto también será innecesario
    @Override
    public void guardarInfoBasica(Pedido pedido, int codigoMultiplex, int codigoPelicula, Date fechaFuncion){

        pedido.setFechaFuncion(fechaFuncion);

        pedido.setCodigoMultiplex(codigoMultiplex);

        pedido.setCodigoPelicula(codigoPelicula);

        guardarPedido(pedido);

    }

    //Se obtienen las funciones que cumplen con la pelicula y la fecha escogida
    @Override
    public List<Funcion> funcionesPorPeliculaAndFecha(){

        Pedido pedido = leerPedido();

        List<Funcion> funciones = procesoConcreto.funcionService.getFuncionesByPeliculaAndFecha(pedido.getCodigoPelicula(), pedido.getFechaFuncion());

        return funciones;
    }

    //De las funciones del primer paso, se mira cuales se proyectan en el multiplex escogido
    @Override
    public List<Funcion> funcionesEnMultiplex(List<Funcion> funciones){

        Pedido pedido = leerPedido();

        List<Funcion> funcionesEliminar = new ArrayList<Funcion>();

        for(int i=0; i<funciones.size(); i++){

            int codigoSala = funciones.get(i).getCodigoSala();

            Optional<Sala> sala = procesoConcreto.salaService.getSalaByCodigo(codigoSala);

            if(sala.get().getCodigoMultiplex() != pedido.getCodigoMultiplex()){

                funcionesEliminar.add(funciones.get(i));
            }
        }

        for(int i=0; i<funcionesEliminar.size(); i++){
            funciones.remove(funcionesEliminar.get(i));
        }

        return funciones;
    }

    //Escogida una funcion, se traen todas las sillas de la sala donde se proyecta la funcion, filtrandolas por el tipo de silla escogido
    @Override
    public Pedido sillasParaLaFuncion(int codigoFuncion, String tipoSilla){

        Pedido pedido = leerPedido();

        pedido.setTipoSilla(tipoSilla);

        Optional<Funcion> funcionEscogida = procesoConcreto.funcionService.getFuncionByCodigo(codigoFuncion);
        pedido.setFuncionEscogida(funcionEscogida.get());

        int codigoSala = funcionEscogida.get().getCodigoSala();

        List<Silla> sillas = procesoConcreto.sillaService.getSillasByCodigoSalaAndTipo(codigoSala, tipoSilla);

        pedido.setListadoSillas(sillas);

        return pedido;
    }

    //De las sillas del paso tres, se mira cuales estan ocupadas y cuales no
    @Override
    public Pedido disponibilidadSillas(Pedido pedido){

        List<SillaTM> sillasTM = new ArrayList<SillaTM>();

        List<Silla> sillas = pedido.getListadoSillas();

        for(int i=0; i<sillas.size(); i++){

            SillaTM sillaTM = new SillaTM();
            sillaTM.setCodigoSilla(sillas.get(i).getCodigoSilla());
            sillaTM.setUbicacionSilla(sillas.get(i).getUbicacionSilla());
            sillaTM.setTipoSilla(sillas.get(i).getTipoSilla());
            sillaTM.setPrecioSilla(sillas.get(i).getPrecioSilla());
            sillaTM.setCodigoSala(sillas.get(i).getCodigoSala());

            int codigoSilla = sillas.get(i).getCodigoSilla();
            int codigoFuncion = pedido.getFuncionEscogida().getCodigoFuncion();
            Optional<DetalleDispoSilla> detalleSilla = procesoConcreto.detalleDispoSillaService.getDetalleByCodigoSillaAndFuncion(codigoSilla, codigoFuncion);

            if(detalleSilla.isEmpty()){
                sillaTM.setDisponibilidadSilla(true);
            }
            else{
                sillaTM.setDisponibilidadSilla(false);
            }
            sillasTM.add(sillaTM);
        }

        pedido.setListadoSillasTM(sillasTM);

        return pedido;
    }

    //Se guardan las sillas escogidas y los snacks comprados
    @Override
    public Pedido guardarSillas(List<SillaTM> sillasTM){

        Pedido pedido = leerPedido();

        pedido.setListadoSillasTM(sillasTM);

        return pedido;
    }

    //Se guardan los snacks comprados
    @Override
    public Pedido guardarSnacks(Optional<List<SnackTM>> listSnacksTM){

        Pedido pedido = leerPedido();

        if(!listSnacksTM.isEmpty()){
            List<SnackTM> snacksTM = listSnacksTM.get();

            pedido.setSnacksComprados(snacksTM);
        }
        else{
            pedido.setSnacksComprados(null);
        }

        return pedido;
    }

    @Override
    public boolean posibilidadRedimirPuntos(){
        Cliente cliente = leerArchivoCliente();
        Pedido pedido = leerPedido();

        boolean poderRedimirPuntos = false;

        int puntosCliente = cliente.getPuntos();
        String tipoSilla = pedido.getTipoSilla();

        if(puntosCliente == 100 && tipoSilla.equals("general")){
            poderRedimirPuntos = true;
        }

        return poderRedimirPuntos;
    }

    @Override
    public FacturaCompraTM generarFacturaCompra(){

        Pedido pedido = leerPedido();

        FacturaCompraTM factura = new FacturaCompraTM();

        Cliente cliente = leerArchivoCliente();
        factura.setCedulaCliente(cliente.getCedulaCliente());
        factura.setNombreCliente(cliente.getNombreCliente());

        Calendar calendar = Calendar.getInstance();
        Date fechaActual = new Date(calendar.getTimeInMillis());

        factura.setFechaFactura(fechaActual);

        Optional<Multiplex> multiplex = procesoConcreto.multiplexService.getMultiplexbyCodigo(pedido.getCodigoMultiplex());
        factura.setMultiplex(multiplex.get().getNombreMultiplex());

        Optional<Pelicula> pelicula = procesoConcreto.peliculaService.getPeliculaByCodigo(pedido.getCodigoPelicula());
        factura.setPelicula(pelicula.get().getNombrePelicula());

        Funcion funcionEscogida = pedido.getFuncionEscogida();

        factura.setFechaFuncion(pedido.getFechaFuncion());

        factura.setHoraFuncion(funcionEscogida.getHoraFuncion());

        factura.setTipoFuncion(funcionEscogida.getTipoFuncion());

        factura.setDoblada(funcionEscogida.getDoblaje());

        Optional<Sala> sala = procesoConcreto.salaService.getSalaByCodigo(funcionEscogida.getCodigoSala());

        factura.setNombreSala(sala.get().getNombreSala());

        List<String> sillas = new ArrayList<String>();
        for(int i=0; i<pedido.getListadoSillasTM().size(); i++){
            sillas.add(pedido.getListadoSillasTM().get(i).getUbicacionSilla());
        }
        factura.setSillas(sillas);

        int precioSillaUnitaria = pedido.getListadoSillasTM().get(0).getPrecioSilla();
        int precioSillas = (pedido.getListadoSillasTM().size())*precioSillaUnitaria;
        factura.setPrecioSillas(precioSillas);

        if(pedido.getSnacksComprados() != null){

            List<SnackTM> snacks = new ArrayList<SnackTM>();
            for(int i=0; i<pedido.getSnacksComprados().size(); i++){
                snacks.add(pedido.getSnacksComprados().get(i));
            }
            factura.setSnacksComprados(snacks);

            double precioSnacks = 0;

            for(int i=0; i<pedido.getSnacksComprados().size(); i++){

                double precioSnackUnitario = pedido.getSnacksComprados().get(i).getPrecioUnitario();

                int cantidadSnack = pedido.getSnacksComprados().get(i).getCantidadComprada();

                precioSnacks += precioSnackUnitario*cantidadSnack;
            }

            factura.setPrecioSnacks(precioSnacks);

            factura.setPuntosRedimidos(false);

            factura.setTotalPago((double)precioSillas + precioSnacks);
        }
        else{
            factura.setPrecioSnacks(0);
            factura.setPuntosRedimidos(false);
            factura.setTotalPago((double)precioSillas);
        }

        pedido.setFacturaCompraTM(factura);

        guardarPedido(pedido);

        return factura;
    }

    @Override
    public int registrarFacturaCompra(boolean puntosRedimidos){
        Pedido pedido = leerPedido();
        Cliente cliente = leerArchivoCliente();

        double totalPago = pedido.getFacturaCompraTM().getTotalPago();

        if(puntosRedimidos ^ pedido.getTipoSilla()=="general"){
            totalPago -= pedido.getListadoSillasTM().get(0).getPrecioSilla();
            pedido.getFacturaCompraTM().setPuntosRedimidos(true);
            pedido.getFacturaCompraTM().setTotalPago(totalPago);
            guardarPedido(pedido);
            procesoConcreto.clienteService.resetearPuntosAndFecha(cliente.getCedulaCliente());
        }

        Date fechaFactura = pedido.getFacturaCompraTM().getFechaFactura();

        List<FacturaCompra> facturas = procesoConcreto.facturaCompraService.getAll();
        int codigoFactura = facturas.size() + 1;

        procesoConcreto.facturaCompraService.insertarRegistro(puntosRedimidos, totalPago, cliente.getCedulaCliente(), fechaFactura);

        return codigoFactura;

    }

    @Override
    public void registrarDispoSillas(int codigoFacturaCompra){
        Pedido pedido = leerPedido();

        boolean disponibilidadSilla = false;
        int codigoFuncion = pedido.getFuncionEscogida().getCodigoFuncion();

        for(int i=0; i<pedido.getListadoSillasTM().size(); i++){
            int codigoSilla = pedido.getListadoSillasTM().get(i).getCodigoSilla();

            procesoConcreto.detalleDispoSillaService.insertarRegistro(codigoSilla, codigoFuncion, codigoFacturaCompra, disponibilidadSilla);
        }
    }

    @Override
    public void registrarCompraSnack(int codigoFactura){
        Pedido pedido = leerPedido();

        if(pedido.getSnacksComprados() != null){

            for(int i=0; i<pedido.getSnacksComprados().size(); i++){
                int codigoSnack = pedido.getSnacksComprados().get(i).getCodigoSnack();
                int cantidadSnackComprado = pedido.getSnacksComprados().get(i).getCantidadComprada();

                procesoConcreto.compraSnackService.insertarResgistro(codigoSnack, codigoFactura, cantidadSnackComprado);
            }
        }
    }

    @Override
    public void modificarVentaSnack(){
        Pedido pedido = leerPedido();

        if(pedido.getSnacksComprados() != null){
            int codigoMultiplex = pedido.getCodigoMultiplex();

            for(int i=0; i<pedido.getSnacksComprados().size(); i++){
                int codigoSnack = pedido.getSnacksComprados().get(i).getCodigoSnack();
                Optional<VentaSnack> ventaSnack = procesoConcreto.ventaSnackService.getByCodigoMultiplexAndSnack(codigoMultiplex, codigoSnack);
                int stockActual = ventaSnack.get().getStockActual();

                int stockNuevo = stockActual - pedido.getSnacksComprados().get(i).getCantidadComprada();

                procesoConcreto.ventaSnackService.actualizarVentaSnack(stockNuevo, codigoMultiplex, codigoSnack);
            }
        }
    }

    @Override
    public void modificarPuntosCliente(){
        Pedido pedido = leerPedido();
        Cliente cliente = leerArchivoCliente();

        int cantidadBoletasCompradas = pedido.getListadoSillasTM().size();
        int puntosPorBoletas = 10*cantidadBoletasCompradas;

        int puntosPorSnacks = 0;

        if(pedido.getSnacksComprados() != null){

            for(int i=0; i<pedido.getSnacksComprados().size(); i++){
                int cantidadSnackComprado = pedido.getSnacksComprados().get(i).getCantidadComprada();
                puntosPorSnacks += 5*cantidadSnackComprado;
            }
        }

        int puntosCompra = puntosPorBoletas+puntosPorSnacks;

        int cedulaCLiente = cliente.getCedulaCliente();
        int puntosCliente = cliente.getPuntos();
        int puntosActuales = puntosCompra+puntosCliente;

        if(puntosCliente != 100){
            if(puntosActuales>=100){
                puntosActuales = 100;
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, 6);
                Date fechaCaducidadPuntos = new Date(calendar.getTimeInMillis());

                cliente.setPuntos(puntosActuales);
                cliente.setFechaCaducidadPuntos(fechaCaducidadPuntos);
                guardarArchivoCliente(cliente);

                procesoConcreto.clienteService.actualizarPuntosAndFecha(puntosActuales, fechaCaducidadPuntos, cedulaCLiente);
            }
            else{
                cliente.setPuntos(puntosActuales);
                guardarArchivoCliente(cliente);
                procesoConcreto.clienteService.actualizarPuntos(puntosActuales, cedulaCLiente);
            }
        }
    }
}
