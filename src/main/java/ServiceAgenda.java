import java.sql.Connection;
import java.sql.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

@ManagedBean(name="agenda")
@SessionScoped
public class ServiceAgenda {
	private String nombre;
	private String passwd;
	private List<Contacto> contactos;	
	private Connection con;
	private Statement stmt;
	private ResultSet rset;
	
	private String nombreC;
	private String apellido;
	private int tlf;
	private String direccion;
	private String descripcion;

public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	public String getNombreC() {
		return nombreC;
	}

	public void setNombreC(String nombreC) {
		this.nombreC = nombreC;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getTlf() {
		return tlf;
	}

	public void setTlf(int tlf) {
		this.tlf = tlf;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

@SuppressWarnings("finally")
public Connection conn() {

try {
    
    Class.forName("com.mysql.jdbc.Driver");
    String servidor = "jdbc:mysql://localhost:3306/tp2?autoReconnect=true&useSSL=false";
    con = DriverManager.getConnection(servidor, "root", "fran");

	} catch (ClassNotFoundException ex) {
	    JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	    con = null;
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	    con = null;
	} catch (Exception ex) {
	    JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	    con = null;
	}finally {
	    return con;
		}
}

public String login () {
	con = conn();	

	try {
		Statement stmt=con.createStatement();
		String sql="Select * from usuario";
		rset=stmt.executeQuery(sql);
		
		while(rset.next()) {
			if(rset.getString("usuario").equals(this.nombre)) {
				if(rset.getString("passwd").equals(this.passwd)) {
				    JOptionPane.showMessageDialog(null, "Hola "+this.nombre);
					return "menu";
				}
			}
		}
	    
		JOptionPane.showMessageDialog(null, "Parámetros incorrectos, no se pudo realizar el login");
		
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e.getMessage());

	}
	
	return "login";
}

public String logout() {
	this.nombre="";
	this.passwd="";
	
	return "login";
}

public List<Contacto> showAll(){
    contactos = new ArrayList<Contacto>();
	con = conn();	

  try{
		Statement stmt=con.createStatement();
		String sql="Select * from contacto where usuario='"+this.nombre+"'";
		rset=stmt.executeQuery(sql);
    
      while (rset.next()){
	Contacto c = new Contacto();

	c.setNombre(rset.getString("nombre"));
	c.setApellido(rset.getString("apellido"));
	c.setTlf(rset.getInt("tlf"));
	c.setDir(rset.getString("direccion"));
	c.setDescripcion(rset.getString("descripcion"));;
	contactos.add(c);
	}

	rset.close();
	stmt.close();
     
    } catch (SQLException sqle) { 
      System.out.println("Error en la ejecución:" 
        + sqle.getErrorCode() + " " + sqle.getMessage());    
	return contactos;
    }
	return contactos;
	}

public void addContacto(){
  	
	con = conn();
	Contacto c = new Contacto(this.nombreC,this.apellido,this.tlf,this.direccion,this.descripcion);

	try{
	PreparedStatement stmt = con.prepareStatement("INSERT INTO contacto VALUES (0,?,?,?,?,?,?)");	
	
	stmt.setString(1,c.getNombre());
	stmt.setString(2,c.getApellido());
	stmt.setString(3, c.getDir());
	stmt.setInt(4, c.getTlf());	
	stmt.setString(5, c.getDescripcion());
	stmt.setString(6, this.nombre);
	
	int numfilas = stmt.executeUpdate();


	if (numfilas==1) {
		JOptionPane.showMessageDialog(null, "Registro insertado correctamente.");
		}
	
  } catch (SQLException sqle) { 
		JOptionPane.showMessageDialog(null, sqle.getErrorCode() +" - " + sqle.getMessage());
  	}

	}

public void setContacto(){
  	
	con = conn();
	Contacto c = new Contacto(this.nombreC,this.apellido,this.tlf,this.direccion,this.descripcion);

	try{
	PreparedStatement stmt = con.prepareStatement("UPDATE contacto SET apellido=?,direccion=?,tlf=?,descripcion=? where nombre='"+this.nombreC+"' and usuario='"+this.nombre+"'");	
	
	stmt.setString(1,c.getApellido());
	stmt.setString(2, c.getDir());
	stmt.setInt(3, c.getTlf());	
	stmt.setString(4, c.getDescripcion());
	
	int numfilas = stmt.executeUpdate();


	if (numfilas==1) {
		JOptionPane.showMessageDialog(null, "Registro modificado correctamente.");

		}
	
  } catch (SQLException sqle) { 
		JOptionPane.showMessageDialog(null, sqle.getErrorCode() +" - " + sqle.getMessage());
  	}

	}

public void deleteContacto(){
  	
	con = conn();

	try{
	PreparedStatement stmt = con.prepareStatement("delete from contacto where nombre='"+this.nombreC+"' and usuario='"+this.nombre+"'");	
	
	int numfilas = stmt.executeUpdate();

	if (numfilas==1) {
		JOptionPane.showMessageDialog(null, "Registro borrado correctamente.");
		this.nombreC="";
		}
	
  } catch (SQLException sqle) { 
		JOptionPane.showMessageDialog(null, sqle.getErrorCode() +" - " + sqle.getMessage());
  		}
	
	}

public void registro() {
	con = conn();
	Usuario us = new Usuario(this.nombre,this.passwd);

	try{
	PreparedStatement stmt = con.prepareStatement("INSERT INTO usuario VALUES (?,?)");	
	
	stmt.setString(1,us.getNombre());
	stmt.setString(2,us.getPasswd());
	
	int numfilas = stmt.executeUpdate();


	if (numfilas==1) {
		JOptionPane.showMessageDialog(null, "Usuario creado correctamente, proceda a hacer login.");
		}
	
  } catch (SQLException sqle) { 
		JOptionPane.showMessageDialog(null, sqle.getErrorCode() +" - " + sqle.getMessage());
  	}

}

}
