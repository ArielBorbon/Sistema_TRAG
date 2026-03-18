
package pruebas;

import conexion.Conexion;
import javax.persistence.EntityManager;

/**
 *
 * Archivo: Prueba.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class Prueba {

    public static void main(String[] args) {
        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();

            entidades.Prueba p = new entidades.Prueba();
            p.setNombre("Ariels");

            em.persist(p);

            em.getTransaction().commit();


        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            Conexion.cerrar();
        }
    }
}
