package managers;

import funciones.FuncionApp;
import org.example.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainConsultasJPQL {

    public static void main(String[] args) {
        //Metodos TP
        buscarClientes();
        facturasUltimoMes();
        clienteMaxCantFacturas();
        articulosMasVendidos();
        buscarFacturasUlt3MesesXCliente();
        montoFacturadoXCliente();
        articulosXFactura();
        articuloMasCaroXFactura();
        cantidadTotalFacturas();
        buscarFacturasXMontoMinimo();
        buscarFacturasXNombreArticulo();
        buscarArticulosXNombre();
        buscarArticulosXCodigo();
        articulosConPrecioMayorAlPromedio();
        articulosNoVendidos();
        //Metodos preexistentes
        //buscarFacturas();
        //buscarFacturasActivas();
        //buscarFacturasXNroComprobante();
        //buscarFacturasXRangoFechas();
        //buscarFacturaXPtoVentaXNroComprobante();
        //buscarFacturasXCliente();
        //buscarFacturasXCuitCliente();
        //buscarFacturasXArticulo();
        //mostrarMaximoNroFactura();
        //buscarClientesXIds();
        //buscarClientesXRazonSocialParcial();
    }


    public static void buscarFacturas(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturas();
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasActivas(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasActivas();
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXNroComprobante(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXNroComprobante(796910l);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXRangoFechas(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaInicio = FuncionApp.restarSeisMeses(fechaActual);
            List<Factura> facturas = mFactura.buscarFacturasXRangoFechas(fechaInicio, fechaActual);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturaXPtoVentaXNroComprobante(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Factura factura = mFactura.getFacturaXPtoVentaXNroComprobante(2024, 796910l);
            mostrarFactura(factura);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXCliente(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXCliente(7l);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXMontoMinimo(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasPorMontoMinimo(100D);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasUlt3MesesXCliente(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasUlt3MesesXCliente(1l);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXCuitCliente(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXCuitCliente("27236068981");
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXArticulo(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXArticulo(6l);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXNombreArticulo(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXNombreArticulo("pErA");
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void articulosMasVendidos(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Articulo> articulos = mFactura.getArticulosMasVendidos();
            mostrarArticulos(articulos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void articulosXFactura(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Articulo> articulos = mFactura.getArticulosXFactura(2L);
            mostrarArticulos(articulos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void articuloMasCaroXFactura(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Articulo> articulos = new ArrayList<>();
            articulos.add(mFactura.getArticuloMasCaroXFactura(1L));
            mostrarArticulos(articulos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void mostrarMaximoNroFactura(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Long nroCompMax = mFactura.getMaxNroComprobanteFactura();
            System.out.println("N° " + nroCompMax);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void cantidadTotalFacturas(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Long nroCompMax = mFactura.getCantFacturas();
            System.out.println("N° total de facturas:" + nroCompMax);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void facturasUltimoMes(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            LocalDate ultMes = LocalDate.now().minusMonths(1);
            List<Factura> facturas = mFactura.buscarFacturasFrom(ultMes);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarClientesXIds(){
        ClienteManager mCliente = new ClienteManager(true);
        try {
            List<Long> idsClientes = new ArrayList<>();
            idsClientes.add(1l);
            idsClientes.add(2l);
            List<Cliente> clientes = mCliente.getClientesXIds(idsClientes);
            for(Cliente cli : clientes){
                System.out.println("Id: " + cli.getId());
                System.out.println("CUIT: " + cli.getCuit());
                System.out.println("Razon Social: " + cli.getRazonSocial());
                System.out.println("-----------------");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mCliente.cerrarEntityManager();
        }
    }

    public static void buscarClientes(){
        ClienteManager mCliente = new ClienteManager(true);
        try {
            List<Cliente> clientes = mCliente.getClientes();
            for(Cliente cli : clientes){
                System.out.println("Id: " + cli.getId());
                System.out.println("CUIT: " + cli.getCuit());
                System.out.println("Razon Social: " + cli.getRazonSocial());
                System.out.println("-----------------");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mCliente.cerrarEntityManager();
        }
    }

    public static void clienteMaxCantFacturas(){
        ClienteManager mCliente = new ClienteManager(true);
        try {
            Cliente cli = mCliente.getClienteMaxCantFacturas();
                System.out.println("Id: " + cli.getId());
                System.out.println("CUIT: " + cli.getCuit());
                System.out.println("Razon Social: " + cli.getRazonSocial());
                System.out.println("-----------------");

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mCliente.cerrarEntityManager();
        }
    }

    public static void montoFacturadoXCliente(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Double total = mFactura.getMontoFacturadoXCliente(1L);
            System.out.println("Total Facturado Cliente 1: $" + total);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarClientesXRazonSocialParcial(){
        ClienteManager mCliente = new ClienteManager(true);
        try {
            List<Long> idsClientes = new ArrayList<>();
            idsClientes.add(1l);
            idsClientes.add(2l);
            List<Cliente> clientes = mCliente.getClientesXRazonSocialParcialmente("Lui");
            for(Cliente cli : clientes){
                System.out.println("Id: " + cli.getId());
                System.out.println("CUIT: " + cli.getCuit());
                System.out.println("Razon Social: " + cli.getRazonSocial());
                System.out.println("-----------------");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mCliente.cerrarEntityManager();
        }
    }

    public static void buscarArticulosXNombre(){
        ArticuloManager mArticulo = new ArticuloManager(true);
        try {
            List<Articulo> articulos = mArticulo.getArticulosXNombre("manza");
            mostrarArticulos(articulos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mArticulo.cerrarEntityManager();
        }
    }

    public static void buscarArticulosXCodigo(){
        ArticuloManager mArticulo = new ArticuloManager(true);
        try {
            List<Articulo> articulos = mArticulo.getArticulosXCodigo("345");
            mostrarArticulos(articulos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mArticulo.cerrarEntityManager();
        }
    }

    public static void articulosConPrecioMayorAlPromedio(){
        ArticuloManager mArticulo = new ArticuloManager(true);
        try {
            List<Articulo> articulos = mArticulo.getArticulosPrecioMayorAlPromedio();
            mostrarArticulos(articulos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mArticulo.cerrarEntityManager();
        }
    }

    public static void articulosNoVendidos(){
        ArticuloManager mArticulo = new ArticuloManager(true);
        try {
            List<Articulo> articulos = mArticulo.getArticulosNoVendidos();
            mostrarArticulos(articulos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mArticulo.cerrarEntityManager();
        }
    }

    public static void mostrarFactura(Factura factura){
        List<Factura> facturas = new ArrayList<>();
        facturas.add(factura);
        mostrarFacturas(facturas);
    }

    public static void mostrarFacturas(List<Factura> facturas){
        for(Factura fact : facturas){
            System.out.println("N° Comp: " + fact.getStrProVentaNroComprobante());
            System.out.println("Fecha: " + FuncionApp.formatLocalDateToString(fact.getFechaComprobante()));
            System.out.println("CUIT Cliente: " + FuncionApp.formatCuitConGuiones(fact.getCliente().getCuit()));
            System.out.println("Cliente: " + fact.getCliente().getRazonSocial() + " ("+fact.getCliente().getId() + ")");
            System.out.println("------Articulos------");
            for(FacturaDetalle detalle : fact.getDetallesFactura()){
                System.out.println(detalle.getArticulo().getDenominacion() + ", " + detalle.getCantidad() + " unidades, $" + FuncionApp.getFormatMilDecimal(detalle.getSubTotal(), 2));
            }
            System.out.println("Total: $" + FuncionApp.getFormatMilDecimal(fact.getTotal(),2));
            System.out.println("*************************");
        }
    }

    public static void mostrarArticulos(List<Articulo> articulos) {
        for (Articulo art : articulos) {
            System.out.println("ID: " + art.getId());
            System.out.println("Código: " + art.getCodigo());
            System.out.println("Denominación: " + art.getDenominacion());

            // Usamos el mismo formateador de moneda que en tu ejemplo
            System.out.println("Precio Venta: $" + FuncionApp.getFormatMilDecimal(art.getPrecioVenta(), 2));

            // Replicamos el formato para un objeto relacionado (como Cliente en Factura)
            if (art.getUnidadMedida() != null) {
                // Asumo que UnidadMedida tiene un método .getDenominacion() y .getId()
                // similar a como Cliente tiene .getRazonSocial() y .getId()
                System.out.println("Unidad de Medida: " + art.getUnidadMedida().getDenominacion() + " (" + art.getUnidadMedida().getId() + ")");
            } else {
                System.out.println("Unidad de Medida: (No especificada)");
            }

            // Separador final
            System.out.println("*************************");
        }
    }

}
