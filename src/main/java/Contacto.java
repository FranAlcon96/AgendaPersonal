public class Contacto {
	
	private String nombre;
	private String apellido;
	private int tlf;
	private String dir;
	private String descripcion;
	
	public Contacto(String nombre, String apellido, int tlf, String dir, String descripcion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tlf = tlf;
		this.dir = dir;
		this.descripcion = descripcion;
	}
	
	public Contacto() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", apellido=" + apellido + ", tlf=" + tlf + ", dir=" + dir
				+ ", descripcion=" + descripcion + "]";
	}
	
	
}
