/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Maritza Vargas
 */
public class Docente extends Persona{
    private Conexion cn;
    private String codigo_docente,salario,fecha_ingreso,fecha_registro;
    private int id;
    
    public Docente(){}

    public Docente(int id,String codigo_docente, String salario, String fecha_ingreso, String fecha_registro, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nit, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo_docente = codigo_docente;
        this.salario = salario;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_registro = fecha_registro;
        this.id = id;
   
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo_docente() {
        return codigo_docente;
    }

    public void setCodigo_docente(String codigo_docente) {
        this.codigo_docente = codigo_docente;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
         @Override
    public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    try{
       cn = new Conexion ();
       cn.abrir_conexion();
       String query;
       query = "SELECT id AS id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento,codigo_docente,salario,fecha_ingreso,fecha_registro FROM persona;";
       ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
       String encabezado[] = 
       {"id","Nit","Nombres","Apellidos","Direccion","Telefono","Fecha_Nacimiento","Codigo_Docente","Salario","Fecha_Ingreso","Fecha_Registro"};
       tabla.setColumnIdentifiers(encabezado);
       String datos[] = new String[11];
       while (consulta.next()){
           datos[0] = consulta.getString("id");
           datos[1] = consulta.getString("nit");
           datos[2] = consulta.getString("nombres");
           datos[3] = consulta.getString("apellidos");
           datos[4] = consulta.getString("direccion");
           datos[5] = consulta.getString("telefono");
           datos[6] = consulta.getString("fecha_nacimiento");
           datos[7] = consulta.getString("codigo_docente");
           datos[8] = consulta.getString("salario");
           datos[9] = consulta.getString("fecha_ingreso");
           datos[10] = consulta.getString("fecha_registro");
           tabla.addRow(datos);
       }
       cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println("Error: " + ex.getMessage() );
    
    }
    
    return tabla;
    }
    
    @Override
public void crear() {
    try {
        PreparedStatement parametro;
        cn = new Conexion();
        cn.abrir_conexion();
        String query;
        query = "INSERT INTO persona(nit,nombres,apellidos,direccion,telefono,fecha_nacimiento,codigo_docente,salario,fecha_ingreso,fecha_registro) VALUES (?,?,?,?,?,?,?,?,?,NOW());";
        parametro = cn.conexionBD.prepareStatement(query);
        parametro.setString(1, this.getNit());
        parametro.setString(2, this.getNombres());
        parametro.setString(3, this.getApellidos());
        parametro.setString(4, this.getDireccion());
        parametro.setString(5, this.getTelefono());
        parametro.setString(6, this.getFecha_nacimiento());
        parametro.setString(7, this.getCodigo_docente());
        parametro.setString(8, this.getSalario());
        parametro.setString(9, this.getFecha_ingreso());
        int executar = parametro.executeUpdate();
        System.out.println(" Se inserto :" + Integer.toString(executar) + " Registro");
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error:" + ex.getMessage());
    }
}
   
         @Override

public void actualizar() {
    try {
        PreparedStatement parametro;
        cn = new Conexion();
        cn.abrir_conexion();
        String query;
        // Elimina fecha_registro de la consulta
        query = "UPDATE persona SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, codigo_docente = ?, salario = ? WHERE id = ?;";
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, this.getNit());
        parametro.setString(2, this.getNombres());
        parametro.setString(3, this.getApellidos());
        parametro.setString(4, this.getDireccion());
        parametro.setString(5, this.getTelefono());
        parametro.setString(6, this.getFecha_nacimiento());
        parametro.setString(7, this.getCodigo_docente());
        parametro.setString(8, this.getSalario());
        parametro.setInt(9, this.getId());
        int executar = parametro.executeUpdate();
        System.out.println("Se Actualizo :" + Integer.toString(executar) + " Registro");
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error:" + ex.getMessage());
    }
}
     @Override
    public void borrar (){
     try {
        PreparedStatement parametro;
        cn = new Conexion();
        cn.abrir_conexion();
        String query;
        // Elimina fecha_registro de la consulta
        query = "delete from persona WHERE id = ?";
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, this.getId());
        int executar = parametro.executeUpdate();
        System.out.println("Se Elimino :" + Integer.toString(executar) + " Registro");
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error:" + ex.getMessage());
    }
    }
    
}