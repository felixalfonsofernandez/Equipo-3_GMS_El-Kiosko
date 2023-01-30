
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarProveedor(Proveedor pr){
        // Se establecera los limites para validar a un proveedor
        if(pr.getRuc().length()>0 && pr.getNombre().length()>0 && pr.getTelefono().length()>0 && pr.getDireccion().length()>0 ){
            if(pr.getTelefono().length()==9){
                if(pr.getRuc().length()==11){
                    String sql = "INSERT INTO proveedor(ruc, nombre, telefono, direccion) VALUES (?,?,?,?)";
                    try {
                       con = cn.getConnection();
                       ps = con.prepareStatement(sql);
                       ps.setString(1, pr.getRuc());
                       ps.setString(2, pr.getNombre());
                       ps.setString(3, pr.getTelefono());
                       ps.setString(4, pr.getDireccion());
                       ps.execute();
                       return true;
                    } catch (SQLException e) {
                        System.out.println(e.toString());
                        return false;
                    }finally{
                        try {
                            con.close();
                        } catch (SQLException e) {
                            System.out.println(e.toString());
                        }
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        
        // se procedio a realizar la validación de los datos segun cada proveedor
    }
    
    public List ListarProveedor(){
        //Listar proveedor se realizo un cambio para mostrar solo las que tengan sus campos llenos
        List<Proveedor> Listapr = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setRuc(rs.getString("ruc"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                Listapr.add(pr);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapr;
    }
    
    public boolean EliminarProveedor(int id){
        String sql = "DELETE FROM proveedor WHERE id = ? ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean ModificarProveedor(Proveedor pr){
        //Se establecio un limite para modificar proveedor
        if(pr.getRuc().length()>0 && pr.getNombre().length()>0 && pr.getTelefono().length()>0 && pr.getDireccion().length()>0 ){
            if(pr.getTelefono().length()==9){
                if(pr.getRuc().length()==11){
                    String sql = "UPDATE proveedor SET ruc=?, nombre=?, telefono=?, direccion=? WHERE id=?";
                    try {
                       con = cn.getConnection();
                       ps = con.prepareStatement(sql);
                       ps.setString(1, pr.getRuc());
                       ps.setString(2, pr.getNombre());
                       ps.setString(3, pr.getTelefono());
                       ps.setString(4, pr.getDireccion());
                       ps.setInt(5, pr.getId());
                       ps.execute();
                       return true;
                    } catch (SQLException e) {
                        System.out.println(e.toString());
                        return false;
                    }finally{
                        try {
                            con.close();
                        } catch (SQLException e) {
                            System.out.println(e.toString());
                        }
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
