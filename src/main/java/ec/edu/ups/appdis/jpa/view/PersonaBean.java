package ec.edu.ups.appdis.jpa.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.jpa.bussiness.PersonaBussiness;
import ec.edu.ups.appdis.jpa.model.Persona;
import ec.edu.ups.appdis.jpa.model.Telefono;

@ManagedBean
public class PersonaBean {

	@Inject
	private PersonaBussiness pBussiness;
	
	@Inject
	private FacesContext facesContext;
	
	private Persona newPersona;
	private List<Persona> personas;
	
	private boolean edit = false;
	
	@PostConstruct
	public void init() {
		newPersona = new Persona();
		newPersona.addTelefono(new Telefono());
		personas = pBussiness.getPersonas();
	}
		
	public Persona getNewPersona() {
		return newPersona;
	}
	public void setNewPersona(Persona newPersona) {
		this.newPersona = newPersona;
	}
	
	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public String guardar() {
		
		System.out.println(edit);
		
		try {
			if(edit==true)
				pBussiness.update(newPersona);
			else
				pBussiness.save(newPersona);
			System.out.println("registro guardado");
			return "list-personas?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(
            		FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            facesContext.addMessage(null, m);
            
            
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String borrar(String id) {
		try {
			pBussiness.delete(id);
			System.out.println("registro eliminado");
			return "list-personas?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(
            		FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            facesContext.addMessage(null, m);
            
            
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String editar(Persona per) {
		newPersona = per;
		edit = true;
		return "create-persona";
	}
	
	public String addTelefono() {
		newPersona.addTelefono(new Telefono());
		return null;
	}
	
	
	
}
