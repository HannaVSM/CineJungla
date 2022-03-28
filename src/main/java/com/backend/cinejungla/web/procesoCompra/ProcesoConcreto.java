package com.backend.cinejungla.web.procesoCompra;


import com.backend.cinejungla.domain.service.*;
import com.backend.cinejungla.persistence.entity.*;
import com.backend.cinejungla.persistence.entity.Silla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.sql.Date;
import java.time.LocalTime;
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
    public Pedido leerArchivo(){
        Pedido pedido = new Pedido();

        try{

            FileInputStream fileIn = new FileInputStream(new File("pedido.obj"));
            ObjectInputStream entrada = new ObjectInputStream(fileIn);

            pedido = (Pedido)entrada.readObject();
            entrada.close();
        }
        catch(Exception e){
            System.out.println("Fallo");
            e.printStackTrace();
        }

        return pedido;
    }


    @Override
    public void guardarArchivo(Pedido pedido){

        try{
            FileOutputStream fileOut = new FileOutputStream(new File("pedido.obj"));
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            salida.writeObject(pedido);
            salida.close();
        }
        catch(Exception e){
            System.out.println("Fallo");
            e.printStackTrace();
        }
    }

    //Esto también será innecesario
    @Override
    public Pedido guardarInfoBasica(Pedido pedido, int codigoMultiplex, int codigoPelicula, Date fechaFuncion){

        pedido.setFechaFuncion(fechaFuncion);

        Optional<Multiplex> multiplex = procesoConcreto.multiplexService.getMultiplexbyCodigo(codigoMultiplex);
        pedido.setMultiplex(multiplex.get());


        Optional<Pelicula> pelicula = procesoConcreto.peliculaService.getPeliculaByCodigo(codigoPelicula);
        pedido.setPelicula(pelicula.get());

        return pedido;
    }

    //Se obtienen las funciones que cumplen con la pelicula y la fecha escogida
    @Override
    public Pedido funcionesPorPeliculaAndFecha(Pedido pedido){

        List<Funcion> funciones = procesoConcreto.funcionService.getFuncionesByPeliculaAndFecha(pedido.getPelicula().getCodigoPelicula(), pedido.getFechaFuncion());

        //Esto es innecesario (eliminar)
        pedido.setFunciones(funciones);

        return pedido;
    }

    //De las funciones del primer paso, se mira cuales se proyectan en el multiplex escogido
    @Override
    public Pedido funcionesEnMultiplex(Pedido pedido){

        List<Funcion> funcionesEliminar = new ArrayList<Funcion>();

        for(int i=0; i<pedido.getFunciones().size(); i++){

            int codigoSala = pedido.getFunciones().get(i).getCodigoSala();

            Optional<Sala> sala = procesoConcreto.salaService.getSalaByCodigo(codigoSala);

            if(sala.get().getCodigoMultiplex() != pedido.getMultiplex().getCodigoMultiplex()){

                funcionesEliminar.add(pedido.getFunciones().get(i));
            }
        }

        for(int i=0; i<funcionesEliminar.size(); i++){
            pedido.getFunciones().remove(funcionesEliminar.get(i));
        }

        return pedido;
    }

    //Escogida una funcion, se traen todas las sillas de la sala donde se proyecta la funcion, filtrandolas por el tipo de silla escogido
    @Override
    public Pedido sillasParaLaFuncion(int codigoFuncion, String tipoSilla){

        Pedido pedido = (Pedido)leerArchivo();

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
            sillaTM.setCodigoFuncion(pedido.getFuncionEscogida().getCodigoFuncion());

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

    //(Opcional) se filtran las funciones por 2D o 3D
    @Override
    public Pedido quintoPaso(Pedido pedido){

        return pedido;
    }

    //(Opcional) se filtran las funciones por dobladas o subtituladas
    @Override
    public Pedido sextoPaso(Pedido pedido){

        return pedido;
    }


    //Se guardan las sillas escogidas y los snacks comprados
    @Override
    public Pedido guardarSillas(List<SillaTM> sillasTM){

        Pedido pedido = (Pedido)leerArchivo();

        //Uso de un atributo que sobra (cambiar)
        pedido.setSillasEscogidas(sillasTM);

        return pedido;
    }

    //Se guardan los snacks comprados
    @Override
    public Pedido guardarSnacks(Optional<List<SnackTM>> listSnacksTM){

        Pedido pedido = (Pedido)leerArchivo();

        //Innecesario (eliminar)
        if(!listSnacksTM.isEmpty()){
            List<SnackTM> snacksTM = listSnacksTM.get();
            for(int i=0; i<snacksTM.size(); i++){
                SnackTM snack = snacksTM.get(i);
                snacksTM.get(i).setCantidadStock(snack.getCantidadStock() - snack.getCantidadComprada());
            }

            pedido.setSnacksComprados(snacksTM);
        }
        else{
            pedido.setSnacksComprados(null);
        }

        return pedido;
    }

    @Override
    public FacturaCompraTM generarFacturaCompra(){

        Pedido pedido = (Pedido)leerArchivo();

        FacturaCompraTM factura = new FacturaCompraTM();

        Calendar calendar = Calendar.getInstance();
        Date fechaActual = new Date(calendar.getTimeInMillis());

        /*factura.setCedulaCliente(pedido.getCliente().getCedulaCliente());

        factura.setNombreCliente(pedido.getCliente().getNombreCliente());*/

        factura.setFechaFactura(fechaActual);

        factura.setMultiplex(pedido.getMultiplex().getNombreMultiplex());

        factura.setPelicula(pedido.getPelicula().getNombrePelicula());

        Funcion funcionEscogida = pedido.getFuncionEscogida();

        factura.setFechaFuncion(pedido.getFechaFuncion());

        factura.setHoraFuncion(funcionEscogida.getHoraFuncion());

        factura.setTipoFuncion(funcionEscogida.getTipoFuncion());

        factura.setDoblada(funcionEscogida.getDoblaje());

        Optional<Sala> sala = procesoConcreto.salaService.getSalaByCodigo(funcionEscogida.getCodigoSala());

        factura.setNombreSala(sala.get().getNombreSala());

        List<String> sillas = new ArrayList<String>();
        for(int i=0; i<pedido.getSillasEscogidas().size(); i++){
            sillas.add(pedido.getSillasEscogidas().get(i).getUbicacionSilla());
        }
        factura.setSillas(sillas);

        int precioSillaUnitaria = pedido.getSillasEscogidas().get(0).getPrecioSilla();
        int precioSillas = (pedido.getSillasEscogidas().size())*precioSillaUnitaria;
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

        guardarArchivo(pedido);

        return factura;
    }

    @Override
    public int registrarFacturaCompra(boolean puntosRedimidos){
        Pedido pedido = (Pedido)leerArchivo();

        double totalPago = pedido.getFacturaCompraTM().getTotalPago();

        if(puntosRedimidos ^ pedido.getTipoSilla()=="general"){
            totalPago -= pedido.getSillasEscogidas().get(0).getPrecioSilla();
            pedido.getFacturaCompraTM().setPuntosRedimidos(true);
            pedido.getFacturaCompraTM().setTotalPago(totalPago);
            guardarArchivo(pedido);
            procesoConcreto.clienteService.actualizarPuntos(0, 76332522);
        }


        //int cedulaCliente = pedido.getCliente().getCedulaCliente();
        Date fechaFactura = pedido.getFacturaCompraTM().getFechaFactura();

        List<FacturaCompra> facturas = procesoConcreto.facturaCompraService.getAll();
        int codigoFactura = facturas.size() + 1;

        procesoConcreto.facturaCompraService.insertarRegistro(puntosRedimidos, totalPago, 76332522, fechaFactura);

        return codigoFactura;

    }

    @Override
    public void registrarDispoSillas(int codigoFacturaCompra){
        Pedido pedido = (Pedido)leerArchivo();


        //int cedulaCliente = pedido.getCliente().getCedulaCliente();
        //int codigoFacturaCompra = 0;
        boolean disponibilidadSilla = false;
        int codigoFuncion = pedido.getFuncionEscogida().getCodigoFuncion();

        for(int i=0; i<pedido.getSillasEscogidas().size(); i++){
            int codigoSilla = pedido.getSillasEscogidas().get(i).getCodigoSilla();

            /*Optional<FacturaCompra> facturaCompra = procesoConcreto.facturaCompraService.getFacturaByClienteAndFecha(76332522, pedido.getFacturaCompraTM().getFechaFactura());
            codigoFacturaCompra = facturaCompra.get().getCodigoFacturaCompra();*/

            procesoConcreto.detalleDispoSillaService.insertarRegistro(codigoSilla, codigoFuncion, codigoFacturaCompra, disponibilidadSilla);

        }

    }

    @Override
    public void registrarCompraSnack(int codigoFactura){
        Pedido pedido = (Pedido)leerArchivo();

        if(pedido.getSnacksComprados() != null){
            //int cedulaCliente = pedido.getCliente().getCedulaCliente();
            for(int i=0; i<pedido.getSnacksComprados().size(); i++){
                int codigoSnack = pedido.getSnacksComprados().get(i).getCodigoSnack();
                int cantidadSnackComprado = pedido.getSnacksComprados().get(i).getCantidadComprada();

                procesoConcreto.compraSnackService.insertarResgistro(codigoSnack, codigoFactura, cantidadSnackComprado);
            }
        }
    }

    @Override
    public void modificarVentaSnack(){
        Pedido pedido = (Pedido)leerArchivo();

        if(pedido.getSnacksComprados() != null){
            int codigoMultiplex = pedido.getMultiplex().getCodigoMultiplex();

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
        Pedido pedido = (Pedido)leerArchivo();

        int cantidadBoletasCompradas = pedido.getSillasEscogidas().size();
        int puntosPorBoletas = 10*cantidadBoletasCompradas;

        int puntosPorSnacks = 0;

        if(pedido.getSnacksComprados() != null){

            for(int i=0; i<pedido.getSnacksComprados().size(); i++){
                int cantidadSnackComprado = pedido.getSnacksComprados().get(i).getCantidadComprada();
                puntosPorSnacks += 5*cantidadSnackComprado;
            }
        }

        int puntosTotales = puntosPorBoletas+puntosPorSnacks;

        //int cedulaCliente = pedido.getCliente().getCedulaCliente();
        Optional<Cliente> cliente = procesoConcreto.clienteService.getByCedula(76332522);
        int puntosCliente = cliente.get().getPuntos();
        int puntosActuales = puntosTotales+puntosCliente;

        if(puntosCliente != 100){
            if(puntosActuales>=100){
                puntosActuales = 100;
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, 6);
                Date fechaCaducidadPuntos = new Date(calendar.getTimeInMillis());

                procesoConcreto.clienteService.actualizarPuntosAndFecha(puntosActuales, fechaCaducidadPuntos, 76332522);
            }
            else{
                procesoConcreto.clienteService.actualizarPuntos(puntosActuales, 76332522);
            }
        }
    }
}
