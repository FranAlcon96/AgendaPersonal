import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;

import javax.servlet.http.HttpServletRequest;
import javax.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

@ManagedBean(name="vista")
public class BasicView implements Serializable {
	
	@ManagedProperty("#{agenda}")	
	private ServiceAgenda agenda;
	private String nombre;
	private List<Contacto> contactos;
	
	public ServiceAgenda getAgenda() {
		return agenda;
	}

	public void setAgenda(ServiceAgenda agenda) {
		this.agenda = agenda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	@PostConstruct
	public void init() {
		nombre = agenda.getNombre();
	}
	
	//menú de la aplicación
	
	public String mostrarTodos() {
		contactos = agenda.showAll();
		return "showAll";
	}
	
	public String add() {
		
		return "add";
	}
	
	public String set() {
		
		return "set";
	}

	public String delete() {
		
		return "delete";
	}	
	
	public String volver() {
		
		return "menu";
	}
	
	public String registro() {
		return "registro";
	}
	
	public String login() {
		
		return "login";
	}

	 
}
