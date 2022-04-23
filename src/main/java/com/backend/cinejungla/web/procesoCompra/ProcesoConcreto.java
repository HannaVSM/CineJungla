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
            fileIn.close();
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
            File fichero = new File("cliente.obj");

            if(fichero.exists()){
                System.out.println("El fichero de cliente ya esta creado");
                fichero.delete();
                System.out.println("Se ha eliminado el fichero de cliente para guardar nuevamente el cliente");
            }
            else{
                if(fichero.createNewFile()){
                    System.out.println("Se ha creado el fichero de cliente correctamente");
                }
                else{
                    System.out.println("Error al crear el fichero de cliente");
                }
            }
            FileOutputStream fileOut = new FileOutputStream("cliente.obj");
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            salida.writeObject(cliente);
            salida.flush();
            salida.close();
            fileOut.close();
        }
        catch(Exception e){
            System.out.println("Fallo al guardar del archivo cliente");
            e.printStackTrace();
        }
    }

    @Override
    public void guardarInfoBasica(int codigoMultiplex, int codigoPelicula){

        insertarPedido(codigoMultiplex, codigoPelicula);
    }

    //Se obtienen las funciones que cumplen con la pelicula y la fecha escogida
    @Override
    public List<Funcion> funcionesPorPeliculaAndFecha(Date fechaFuncion){

        Pedido pedido = consultarPedido();

        List<Funcion> funciones = procesoConcreto.funcionService.getFuncionesByPeliculaAndFecha(pedido.getCodigoPelicula(), fechaFuncion);

        return funciones;
    }

    //De las funciones del primer paso, se mira cuales se proyectan en el multiplex escogido
    @Override
    public List<Funcion> funcionesEnMultiplex(List<Funcion> funciones){

        Pedido pedido = consultarPedido();

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
    public List<Silla> sillasParaLaFuncion(int codigoFuncion, String tipoSilla){

        Pedido pedido = consultarPedido();
        pedido.setTipoSilla(tipoSilla);

        Optional<Funcion> funcionEscogida = procesoConcreto.funcionService.getFuncionByCodigo(codigoFuncion);
        actualizarPedido(codigoFuncion, tipoSilla);

        int codigoSala = funcionEscogida.get().getCodigoSala();

        List<Silla> sillas = procesoConcreto.sillaService.getSillasByCodigoSalaAndTipo(codigoSala, tipoSilla);

        return sillas;

    }

    //De las sillas del paso tres, se mira cuales estan ocupadas y cuales no
    @Override
    public List<SillaTM> disponibilidadSillas(List<Silla> sillas){

        Pedido pedido = consultarPedido();

        List<SillaTM> sillasTM = new ArrayList<SillaTM>();

        for(int i=0; i<sillas.size(); i++){

            SillaTM sillaTM = new SillaTM();
            sillaTM.setCodigoSilla(sillas.get(i).getCodigoSilla());
            sillaTM.setUbicacionSilla(sillas.get(i).getUbicacionSilla());
            sillaTM.setTipoSilla(sillas.get(i).getTipoSilla());
            sillaTM.setPrecioSilla(sillas.get(i).getPrecioSilla());
            sillaTM.setCodigoSala(sillas.get(i).getCodigoSala());

            int codigoSilla = sillas.get(i).getCodigoSilla();
            int codigoFuncion = pedido.getCodigoFuncion();
            Optional<DetalleDispoSilla> detalleSilla = procesoConcreto.detalleDispoSillaService.getDetalleByCodigoSillaAndFuncion(codigoSilla, codigoFuncion);

            if(detalleSilla.isEmpty()){
                sillaTM.setDisponibilidadSilla(true);
            }
            else{
                sillaTM.setDisponibilidadSilla(false);
            }
            sillasTM.add(sillaTM);
        }

        return sillasTM;
    }

    //Se guardan las sillas escogidas
    @Override
    public void guardarSillas(List<SillaTM> sillasTM){

        Pedido pedido = consultarPedido();

        eliminarDetDispoSillaTemp(pedido.getCodigoPedido());

        for(int i=0; i<sillasTM.size(); i++){
            int codigoSilla = sillasTM.get(i).getCodigoSilla();
            int codigoFuncion = pedido.getCodigoFuncion();
            int codigoPedido = pedido.getCodigoPedido();
            boolean dispoSilla = false;

            insertarDetDispoSillaTemp(codigoSilla, codigoFuncion, codigoPedido, dispoSilla);
        }
    }



    //Se guardan los snacks comprados
    @Override
    public void guardarSnacks(Optional<List<SnackTM>> listSnacksTM){

        Pedido pedido = consultarPedido();

        eliminarCompraSnackTemp(pedido.getCodigoPedido());

        if(!listSnacksTM.isEmpty()){
            List<SnackTM> snacksTM = listSnacksTM.get();

            for(int i=0; i<snacksTM.size(); i++){
                int codigoSnack = snacksTM.get(i).getCodigoSnack();
                int codigoPedido = pedido.getCodigoPedido();
                int cantidadComprada = snacksTM.get(i).getCantidadComprada();

                insertarCompraSnackTemp(codigoSnack, codigoPedido, cantidadComprada);
            }
        }
    }



    @Override
    public FacturaCompraTM generarFacturaCompra(){

        Pedido pedido = consultarPedido();

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

        Optional<Funcion> funcion = procesoConcreto.funcionService.getFuncionByCodigo(pedido.getCodigoFuncion());
        Funcion funcionEscogida = funcion.get();

        factura.setFechaFuncion(funcionEscogida.getFechaFuncion());

        factura.setHoraFuncion(funcionEscogida.getHoraFuncion());

        factura.setTipoFuncion(funcionEscogida.getTipoFuncion());

        factura.setDoblada(funcionEscogida.getDoblaje());

        Optional<Sala> sala = procesoConcreto.salaService.getSalaByCodigo(funcionEscogida.getCodigoSala());

        factura.setNombreSala(sala.get().getNombreSala());

        List<DetalleDispoSillaTemp> sillasTemp = getDetDispoSillaTemp();
        List<String> sillasEscogidas = new ArrayList<String>();
        for(int i=0; i<sillasTemp.size(); i++){
            Optional<Silla> silla = procesoConcreto.sillaService.getSillaByCodigo(sillasTemp.get(i).getCodigoSilla());
            sillasEscogidas.add(silla.get().getUbicacionSilla());
        }
        factura.setSillas(sillasEscogidas);

        Optional<Silla> silla = procesoConcreto.sillaService.getSillaByCodigo(sillasTemp.get(0).getCodigoSilla());
        int precioSillaUnitaria = silla.get().getPrecioSilla();
        int precioSillas = (sillasTemp.size())*precioSillaUnitaria;
        factura.setPrecioSillas(precioSillas);

        List<CompraSnackTemp> snacksTemp = getCompraSnackTemp();
        if(snacksTemp.size() != 0){

            List<SnackTM> snacksTM = new ArrayList<SnackTM>();
            for(int i=0; i<snacksTemp.size(); i++){
                SnackTM snackTM = new SnackTM();
                snackTM.setCodigoSnack(snacksTemp.get(i).getCodigoSnack());
                Optional<Snack> snack = procesoConcreto.snackService.getSnackByCodigo(snacksTemp.get(i).getCodigoSnack());
                snackTM.setNombreSnack(snack.get().getNombreSnack());
                snackTM.setPrecioUnitario(snack.get().getPrecioUnitario());
                snackTM.setCantidadComprada(snacksTemp.get(i).getCantidadSnackComprado());
                snacksTM.add(snackTM);
            }
            factura.setSnacksComprados(snacksTM);

            double precioSnacks = 0;

            for(int i=0; i<snacksTemp.size(); i++){

                Optional<Snack> snack = procesoConcreto.snackService.getSnackByCodigo(snacksTemp.get(i).getCodigoSnack());
                double precioSnackUnitario = snack.get().getPrecioUnitario();

                int cantidadSnack = snacksTemp.get(i).getCantidadSnackComprado();

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

        Pedido.setFacturaCompraTM(factura);

        return factura;
    }

    @Override
    public boolean posibilidadRedimirPuntos(){
        Cliente cliente = leerArchivoCliente();
        Pedido pedido = consultarPedido();

        boolean poderRedimirPuntos = false;

        int puntosCliente = cliente.getPuntos();
        String tipoSilla = pedido.getTipoSilla();

        if(puntosCliente == 100 && tipoSilla.equals("general")){
            poderRedimirPuntos = true;
        }

        return poderRedimirPuntos;
    }

    @Override
    public int registrarFacturaCompra(boolean puntosRedimidos){
        Pedido pedido = consultarPedido();
        Cliente cliente = leerArchivoCliente();

        double totalPago = Pedido.getFacturaCompraTM().getTotalPago();

        if(puntosRedimidos ^ pedido.getTipoSilla()=="general"){
            Optional<Silla> silla = procesoConcreto.sillaService.getSillaByCodigo(getDetDispoSillaTemp().get(0).getCodigoSilla());
            totalPago -= silla.get().getPrecioSilla();
            Pedido.getFacturaCompraTM().setPuntosRedimidos(true);
            Pedido.getFacturaCompraTM().setTotalPago(totalPago);
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
        Pedido pedido = consultarPedido();

        boolean disponibilidadSilla = false;
        int codigoFuncion = pedido.getCodigoFuncion();

        for(int i=0; i<getDetDispoSillaTemp().size(); i++){
            int codigoSilla = getDetDispoSillaTemp().get(i).getCodigoSilla();

            procesoConcreto.detalleDispoSillaService.insertarRegistro(codigoSilla, codigoFuncion, codigoFacturaCompra, disponibilidadSilla);
        }
    }

    @Override
    public void registrarCompraSnack(int codigoFactura){
        Pedido pedido = consultarPedido();

        List<CompraSnackTemp> snacksTemp = getCompraSnackTemp();

        if(snacksTemp.size() != 0){

            for(int i=0; i<snacksTemp.size(); i++){
                int codigoSnack = snacksTemp.get(i).getCodigoSnack();
                int cantidadSnackComprado = snacksTemp.get(i).getCantidadSnackComprado();

                procesoConcreto.compraSnackService.insertarResgistro(codigoSnack, codigoFactura, cantidadSnackComprado);
            }
        }
    }

    @Override
    public void modificarVentaSnack(){
        Pedido pedido = consultarPedido();

        List<CompraSnackTemp> snacksTemp = getCompraSnackTemp();

        if(snacksTemp.size() != 0){
            int codigoMultiplex = pedido.getCodigoMultiplex();

            for(int i=0; i<snacksTemp.size(); i++){
                int codigoSnack = snacksTemp.get(i).getCodigoSnack();
                Optional<VentaSnack> ventaSnack = procesoConcreto.ventaSnackService.getByCodigoMultiplexAndSnack(codigoMultiplex, codigoSnack);
                int stockActual = ventaSnack.get().getStockActual();

                int stockNuevo = stockActual - snacksTemp.get(i).getCantidadSnackComprado();

                procesoConcreto.ventaSnackService.actualizarVentaSnack(stockNuevo, codigoMultiplex, codigoSnack);
            }
        }
    }

    @Override
    public void modificarPuntosCliente(){
        Pedido pedido = consultarPedido();
        Cliente cliente = leerArchivoCliente();

        int cantidadBoletasCompradas = getDetDispoSillaTemp().size();
        int puntosPorBoletas = 10*cantidadBoletasCompradas;

        int puntosPorSnacks = 0;

        List<CompraSnackTemp> snacksTemp = getCompraSnackTemp();

        if(snacksTemp.size() != 0){

            for(int i=0; i<snacksTemp.size(); i++){
                int cantidadSnackComprado = snacksTemp.get(i).getCantidadSnackComprado();
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