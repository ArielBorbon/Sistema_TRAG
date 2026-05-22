/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controles;

import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.IControlAdministrarClientes;
import presentacion.interfaces.IControlClientes;
import presentacion.interfaces.vistas.IVistaPrincipalClientes;
import presentacion.vistas.MenuPrincipal;

/**
 *
 * @author sonic
 */
public class ControlClientes implements IControlClientes {

    private final IControlAdministrarClientes controlAdministrarClientes;
    private IVistaPrincipalClientes vistaPrincipal;

    public ControlClientes(IControlAdministrarClientes controlAdministrarClientes) {
        this.controlAdministrarClientes = controlAdministrarClientes;
        ((ControlAdministrarClientes) this.controlAdministrarClientes).setControlClientes(this);
    }

    @Override
    public void administrarClientes() {
        if (vistaPrincipal != null) {
            vistaPrincipal.ocultar();
        }
        controlAdministrarClientes.iniciar();
    }

    @Override
    public void crearCliente() {
        controlAdministrarClientes.irAgregarCliente();
    }

    @Override
    public void editarCliente() {
        administrarClientes();
    }

    @Override
    public void cancelarOperacion() {
        administrarClientes();
    }

    @Override
    public void volver() {
        if (this.vistaPrincipal != null) {
            this.vistaPrincipal.ocultar();
        }
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
    }

    @Override
    public void iniciarModulo() {
        this.vistaPrincipal = FabricaVistas.obtenerVistaPrincipalClientes(this);
        this.vistaPrincipal.mostrar();
    }
}
