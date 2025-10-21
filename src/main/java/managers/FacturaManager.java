package managers;

import funciones.FuncionApp;
import org.example.Articulo;
import org.example.Cliente;
import org.example.Factura;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacturaManager {
    EntityManagerFactory emf = null;
    EntityManager em = null;

    public FacturaManager(boolean anularShowSQL) {
        Map<String, Object> properties = new HashMap<>();
        if(anularShowSQL){
            // Desactivar el show_sql (si está activado en el persistence.xml o configuración por defecto)
            properties.put("hibernate.show_sql", "false");
        }else{
            properties.put("hibernate.show_sql", "true");
        }
        emf = Persistence.createEntityManagerFactory("example-unit", properties);
        em = emf.createEntityManager();

    }

    public List<Factura> getFacturas(){
        String jpql = "FROM Factura";
        Query query = em.createQuery(jpql);

        List<Factura> facturas = query.getResultList();
        return facturas;
    }

    public List<Factura> getFacturasActivas(){
        //si quiero buscar distintos de NULL uso -> IS NOT NULL
        String jpql = "FROM Factura WHERE fechaBaja IS NULL ORDER BY fechaComprobante DESC";
        Query query = em.createQuery(jpql);

        List<Factura> facturas = query.getResultList();
        return facturas;
    }

    public List<Factura> getFacturasXNroComprobante(Long nroComprobante){
        String jpql = "FROM Factura WHERE nroComprobante = :nroComprobante";
        Query query = em.createQuery(jpql);
        query.setParameter("nroComprobante", nroComprobante);

        List<Factura> facturas = query.getResultList();
        return facturas;
    }

    public List<Factura> getFacturasUlt3MesesXCliente(Long clienteId){
        String jpql = "FROM Factura f WHERE f.cliente.id = :cId AND f.fechaComprobante >= :fechaInicial";
        Query query = em.createQuery(jpql);
        query.setParameter("cId", clienteId);
        query.setParameter("fechaInicial", LocalDate.now().minusMonths(3));

        List<Factura> facturas = query.getResultList();
        return facturas;
    }

    public List<Factura> buscarFacturasXRangoFechas(LocalDate fechaInicio, LocalDate fechaFin){
        String jpql = "FROM Factura WHERE fechaComprobante >= :fechaInicio AND fechaComprobante <= :fechaFin";
        Query query = em.createQuery(jpql);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);

        List<Factura> facturas = query.getResultList();
        return facturas;
    }

    public List<Factura> buscarFacturasFrom(LocalDate fechaInicio){
        String jpql = "FROM Factura WHERE fechaComprobante >=:fechaInicio";
        Query query = em.createQuery(jpql);
        query.setParameter("fechaInicio", fechaInicio);

        List<Factura> facturas = query.getResultList();
        return facturas;
    }

    public Factura getFacturaXPtoVentaXNroComprobante(Integer puntoVenta, Long nroComprobante){
        String jpql = "FROM Factura WHERE puntoVenta = :puntoVenta AND nroComprobante = :nroComprobante";
        Query query = em.createQuery(jpql);
        query.setMaxResults(1);
        query.setParameter("puntoVenta", puntoVenta);
        query.setParameter("nroComprobante", nroComprobante);

        Factura factura = (Factura) query.getSingleResult();
        return factura;
    }

    public List<Factura> getFacturasXCliente(Long idCliente){
        String jpql = "FROM Factura WHERE cliente.id = :idCliente";
        Query query = em.createQuery(jpql);
        query.setParameter("idCliente", idCliente);

        List<Factura> facturas = query.getResultList();
        return facturas;
    }

    public List<Factura> getFacturasXCuitCliente(String cuitCliente){
        String jpql = "FROM Factura WHERE cliente.cuit = :cuitCliente";
        Query query = em.createQuery(jpql);
        query.setParameter("cuitCliente", cuitCliente);

        List<Factura> facturas = query.getResultList();
        return facturas;
    }

    public List<Factura> getFacturasXArticulo(Long idArticulo){ //INNER JOIN, LEFT JOIN, LEFT OUTER JOIN, etc
        StringBuilder jpql = new StringBuilder("SELECT fact FROM Factura AS fact LEFT OUTER JOIN fact.detallesFactura AS detalle");
        jpql.append(" WHERE detalle.id = :idArticulo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("idArticulo", idArticulo);

        List<Factura> facturas = query.getResultList();
        return facturas;
    }

    public Double getMontoFacturadoXCliente(Long idCliente){
        StringBuilder jpql = new StringBuilder("SELECT SUM(f.total) FROM Factura f ");
        jpql.append("WHERE f.cliente.id = :idCliente");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("idCliente", idCliente);
        return (Double) query.getSingleResult();
    }

    public List<Articulo> getArticulosMasVendidos(){
        String jpql = "SELECT fd.articulo FROM FacturaDetalle fd GROUP BY (fd.articulo) ORDER BY SUM(fd.cantidad) DESC ";
        Query query = em.createQuery(jpql);

        List<Articulo> articulos = query.getResultList();
        return articulos;
    }

    public List<Articulo> getArticulosXFactura(Long idFactura){
        StringBuilder jpql = new StringBuilder("SELECT fd.articulo FROM FacturaDetalle fd");
        jpql.append(" WHERE fd.factura.id = :idFactura");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("idFactura", idFactura);
        return query.getResultList();
    }

    public Long getMaxNroComprobanteFactura(){ //MAX, MIN, COUNT, AVG, SUM
        StringBuilder jpql = new StringBuilder("SELECT MAX(nroComprobante) FROM Factura WHERE fechaBaja IS NULL");
        Query query = em.createQuery(jpql.toString());

        Long maxNroFactura = (Long) query.getSingleResult();
        return maxNroFactura;
    }

    public void cerrarEntityManager(){
        em.close();
        emf.close();
    }
}
