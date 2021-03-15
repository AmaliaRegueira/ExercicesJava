/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioconexion;

import Modelo.Modelo;
import Vista.Vista;
import javax.swing.JFrame;

/**
 *
 * @author a.regueira.fernandez
 */
public class EjercicioConexion {

    Modelo modelo = new Modelo();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
        
    }
    
    public String crearPedido(int nPedido, String fecha, String nombre){
        return modelo.savePedido(nPedido, fecha, nombre);
    }
    
    public String crearLineaPedido(int nPedido, String descripcion, double cantidad, double precio){
        return modelo.saveLineaPedido(nPedido, descripcion, cantidad, precio);
    }
    
    public String[] get(int nPedido){
        return modelo.get(nPedido);
    }
    
    
}
