package managers;

import org.example.Articulo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticuloManager {
    EntityManagerFactory emf = null;
    EntityManager em = null;

    public ArticuloManager(boolean anularShowSQL) {
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

    public List<Articulo> getArticulosXNombre(String nombreArticulo){
        String jpql = "FROM Articulo WHERE LOWER(denominacion) LIKE :nombreArticulo";
        Query query = em.createQuery(jpql);
        query.setParameter("nombreArticulo", "%"+nombreArticulo.toLowerCase()+"%");
        return query.getResultList();
    }

    public List<Articulo> getArticulosXCodigo(String codigoArticulo){
        String jpql = "FROM Articulo WHERE LOWER(codigo) LIKE :codigoArticulo";
        Query query = em.createQuery(jpql);
        query.setParameter("codigoArticulo", "%"+codigoArticulo.toLowerCase()+"%");
        return query.getResultList();
    }

    public List<Articulo> getArticulosPrecioMayorAlPromedio(){
        String jpql = "FROM Articulo art WHERE art.precioVenta > (SELECT AVG(art2.precioVenta) FROM Articulo art2)";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    //Obtener todos los Artículos que nunca han sido incluidos en un detalle de factura (es decir, artículos sin ventas).
    public List<Articulo> getArticulosNoVendidos(){
        String jpql = "SELECT a FROM Articulo a WHERE NOT EXISTS (SELECT fd FROM FacturaDetalle fd WHERE fd.articulo = a)";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
    public void cerrarEntityManager(){
        em.close();
        emf.close();
    }
}
