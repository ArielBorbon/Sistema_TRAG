
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Archivo: Conexion.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class Conexion {
     /**
     * Clase EntityManagerFactory para crear objetos
     * EntityManager cuando se requiera, para el manejo de las
     * operaciones CRUD con la base de datos.
     */
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionTRAG"); // solo un factory en toda la aplicación
    
    /**
     * Crea una nueva conexión con la base de datos.
     * @return Objeto EntityManager.
     */
    public static EntityManager crearConexion() {
        return emf.createEntityManager(); // Se reutiliza el factory y se obtiene un nuevo EntityManager
    }
    
    /**
     * Cierra la conexión con la base de datos.
     */
    public static void cerrar() {
        if (emf.isOpen()) 
            emf.close();
    }
}