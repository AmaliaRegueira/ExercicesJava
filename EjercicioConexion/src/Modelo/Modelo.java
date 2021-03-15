/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.SingletonJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author amalia.r
 */
public class Modelo {

    public String[] get(int num_pedido) {
        String[] rt = null;
        try{
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT fecha_pedido,cliente FROM pedidos WHERE num_pedido=?");
            
            ps.setInt(1, num_pedido);
            ps.executeQuery();
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next() == true) {
                    rt = new String[3];
                    rt[0] = "Busqueda realizada satisfactoriamente.";
                    rt[1]= rs.getString("fecha_pedido");
                    rt[2] = rs.getString("cliente");
                } else {
                    rt = new String[3];
                    rt[0] = "No existe un artículo con dicho código";
                    rt[1] = "-";
                    rt[2] = "-";
                }

            } catch (SQLException ex) {
                rt = new String[3];
                rt[0] = "Error a la hora de consguir los datos. ";
                rt[1] = "-";
                rt[2] = "-";
                return rt;
            }

        } catch (SQLException ex) {
            rt = new String[3];
            rt[0] = "Error a la hora de consguir los datos. ";
            rt[1] = "-";
            rt[2] = "-";
            return rt;
        }
        return rt;
    }

    public String savePedido(int nPedido, String fecha, String cliente) {
        String rt="";
        try{
            
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT num_pedido FROM pedidos WHERE num_pedido = (?)");
            ps.setInt(1, nPedido);
            
            if(ps.executeUpdate() != 1){
                ps = conn.prepareStatement("INSERT INTO pedidos VALUES (?, ?, ?)");

                ps.setInt(1, nPedido);
                ps.setString(2, fecha);
                ps.setString(3, cliente);

                if (ps.executeUpdate() == 1) rt ="Se ha Insertado Registro.";
                else rt = "No se Ha insertado Registro";
            } else rt ="Ya existe ese pedido.";
           
        } catch (SQLException ex) {
            return  ex.getMessage();
        } catch (NumberFormatException e) {
            return "Formato numero de pediodo no valido.";
        }
        return rt;
    }
    
    public String saveLineaPedido(int nPedido, String descripcion, double cantidad, double precio) {
        String rt="";
        try{
            
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT num_pedido FROM pedidos WHERE num_pedido = (?)");
            ps.setInt(1, nPedido);
            
            if(ps.executeUpdate() == 1){
                ps = conn.prepareStatement("INSERT INTO lineas_pedidos VALUES (?, ?, ?, ?, ?)");

                ps.setDouble(1, Math.random() * 999999 + 1);
                ps.setInt(2, nPedido);
                ps.setString(3, descripcion);
                ps.setDouble(4, cantidad);
                ps.setDouble(5, precio);

                if (ps.executeUpdate() == 1) rt ="Se ha Insertado Registro.";
                else rt = "No se Ha insertado Registro";
            } else rt ="No existe ese pedido.";
           
        } catch (SQLException ex) {
            return  ex.getMessage();
        } catch (NumberFormatException e) {
            return "Formato de datos no valido.";
        }
        return rt;
    }

}
