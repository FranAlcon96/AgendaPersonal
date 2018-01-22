public class Usuario {
  private String nombre;
  private String passwd;
  
 public Usuario(String nombre, String passwd) {
	this.nombre = nombre;
	this.passwd = passwd;
}

public void setNombre(String n) {
     nombre=n;
  }
  public void setPasswd(String d) {
     passwd=d;
  }
  public String getNombre() {
     return nombre;
  }
  public String getPasswd() {
     return passwd;
  }
} 
