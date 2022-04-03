package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.ClienteCrudRepository;
import com.backend.cinejungla.persistence.entity.*;
import com.backend.cinejungla.web.procesoCompra.FacturaCompraTM;
import com.backend.cinejungla.web.procesoCompra.SnackTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private FacturaCompraService facturaCompraService;

    @Autowired
    private DetalleDispoSillaService detalleDispoSillaService;

    @Autowired
    private SillaService sillaService;

    @Autowired
    private FuncionService funcionService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private MultiplexService multiplexService;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private CompraSnackService compraSnackService;

    @Autowired
    private SnackService snackService;

    public void guardarArchivoCliente(Cliente cliente){
        try{
            FileOutputStream fileOut = new FileOutputStream("cliente.obj");
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            salida.writeObject(cliente);
            salida.close();
        }
        catch(Exception e){
            System.out.println("Fallo guardando archivo cliente");
            e.printStackTrace();
        }
    }

    public Optional<Cliente> getByCedula(int cedulaCliente){
        return clienteCrudRepository.getByCedula(cedulaCliente);
    }

    public Optional<Cliente> inicioSesion(String usuarioCliente, String passwordCliente){

        Optional<Cliente> cliente = (Optional<Cliente>) clienteCrudRepository.inicioSesion(usuarioCliente, passwordCliente);
        if(!cliente.isEmpty()){
            guardarArchivoCliente(cliente.get());
        }
        return cliente;
    }

    //No pasa por el controller
    public void actualizarPuntos(int puntos, int cedulaCliente){
        clienteCrudRepository.actualizarPuntos(puntos, cedulaCliente);
    }

    //No pasa por el controller
    public void actualizarPuntosAndFecha(int puntos, Date fechaCaducidadPuntos, int cedulaCliente){
        clienteCrudRepository.actualizarPuntosAndFecha(puntos, fechaCaducidadPuntos, cedulaCliente);
    }

    public void resetearPuntosAndFecha(int cedulaCliente){
        clienteCrudRepository.resetearPuntosAndFecha(cedulaCliente);
    }

    public void registrarCliente(Cliente cliente){
        clienteCrudRepository.save(cliente);
        guardarArchivoCliente(cliente);
    }

    public Cliente consularPerfil(){

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

    public List<FacturaCompraTM> consultarFacturas(){

        Cliente cliente = consularPerfil();

        List<FacturaCompraTM> facturasTM = new ArrayList<>();

        Optional<List<FacturaCompra>> facturas = facturaCompraService.getFacturasCliente(cliente.getCedulaCliente());

        for(int i=0; i<facturas.get().size(); i++){

            FacturaCompraTM facturaCompraTM = new FacturaCompraTM();
            facturaCompraTM.setNombreCliente(cliente.getNombreCliente());
            facturaCompraTM.setCedulaCliente(cliente.getCedulaCliente());
            facturaCompraTM.setFechaFactura(facturas.get().get(i).getFechaFactura());
            facturaCompraTM.setPuntosRedimidos(facturas.get().get(i).getPuntosRedimidos());
            facturaCompraTM.setTotalPago(facturas.get().get(i).getTotalPago());


            int codigoFactura = facturas.get().get(i).getCodigoFacturaCompra();
            List<DetalleDispoSilla> detalleDispoSillas = detalleDispoSillaService.getDetallesDispoSillaFactura(codigoFactura);

            List<String> sillas = new ArrayList<>();
            int precioTotalSillas = 0;


            for(int j=0; j<detalleDispoSillas.size(); j++){

                System.out.println("Silla numero " + j);

                int codigoSilla = detalleDispoSillas.get(j).getCodigoSilla();
                Optional<Silla> silla = sillaService.getSillaByCodigo(codigoSilla);
                sillas.add(silla.get().getUbicacionSilla());

                if(silla.get().getTipoSilla().equals("general")){
                    System.out.println("A");
                    precioTotalSillas += 11000;
                }
                else if(silla.get().getTipoSilla().equals("preferencial")){
                    System.out.println("B");
                    precioTotalSillas += 15000;
                }
            }
            System.out.println(precioTotalSillas);
            facturaCompraTM.setSillas(sillas);
            facturaCompraTM.setPrecioSillas(precioTotalSillas);

            Optional<Funcion> funcion = funcionService.getFuncionByCodigo(detalleDispoSillas.get(0).getCodigoFuncion());
            facturaCompraTM.setFechaFuncion(funcion.get().getFechaFuncion());
            facturaCompraTM.setHoraFuncion(funcion.get().getHoraFuncion());
            facturaCompraTM.setTipoFuncion(facturaCompraTM.getTipoFuncion());
            facturaCompraTM.setDoblada(funcion.get().getDoblaje());

            Optional<Sala> sala = salaService.getSalaByCodigo(funcion.get().getCodigoSala());
            facturaCompraTM.setNombreSala(sala.get().getNombreSala());

            Optional<Multiplex> multiplex = multiplexService.getMultiplexbyCodigo(sala.get().getCodigoMultiplex());
            facturaCompraTM.setMultiplex(multiplex.get().getNombreMultiplex());

            Optional<Pelicula> pelicula = peliculaService.getPeliculaByCodigo(funcion.get().getCodigoPelicula());
            facturaCompraTM.setPelicula(pelicula.get().getNombrePelicula());


            List<CompraSnack> compraSnacks = compraSnackService.getComprasSnackFactura(codigoFactura);

            List<SnackTM> snacksTM = new ArrayList<>();
            double precioTotalSnacks = 0;
            for(int j=0; j<compraSnacks.size(); j++){

                System.out.println("Snack numero " + j);

                int codigoSnack = compraSnacks.get(j).getId().getCodigoSnack();
                SnackTM snackTM = new SnackTM();
                snackTM.setCodigoSnack(codigoSnack);
                int cantidadSncakComprado = compraSnacks.get(j).getCantidadSnackComprado();
                snackTM.setCantidadComprada(cantidadSncakComprado);
                snackTM.setCantidadStock(0);

                Optional<Snack> snack = snackService.getSnackByCodigo(codigoSnack);
                snackTM.setNombreSnack(snack.get().getNombreSnack());
                Double precioUnitarioSnack = snack.get().getPrecioUnitario();
                snackTM.setPrecioUnitario(precioUnitarioSnack);

                precioTotalSnacks += cantidadSncakComprado*precioUnitarioSnack;

                snacksTM.add(snackTM);
            }
            facturaCompraTM.setSnacksComprados(snacksTM);
            facturaCompraTM.setPrecioSnacks(precioTotalSnacks);

            facturasTM.add(facturaCompraTM);
        }
        return facturasTM;
    }
}
