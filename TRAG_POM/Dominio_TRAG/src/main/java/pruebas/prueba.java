/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import conexion.Conexion;
import javax.persistence.EntityManager;

/**
 *
 * @author PC Gamer
 */
public class prueba {

    public static void main(String[] args) {
        EntityManager em = Conexion.crearConexion();

        try {
            em.getTransaction().begin();

            entidades.prueba p = new entidades.prueba();
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
