package ec.edu.ups.appdis.jpa.bussiness;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.appdis.jpa.dao.PersonaDAO;
import ec.edu.ups.appdis.jpa.model.Persona;

@Stateless
public class PersonaBussiness {

	@Inject
	private PersonaDAO dao;
	
	public void save(Persona persona) throws Exception {
		Persona aux = dao.read(persona.getCedula());
		boolean ban = true;
		
		if(aux!=null)
			throw new Exception("Persona ya registrada");
		else
			dao.insert(persona);
	}
	
	public List<Persona> getPersonas(){
		return dao.getPersonas();
	}
	
	public void delete(String id) throws Exception {
		Persona aux = dao.read(id);
		if(aux==null)
			throw new Exception("Registro no existe");
		else
			dao.remove(id);
	}
	
	public void update(Persona persona) throws Exception {
		Persona aux = dao.read(persona.getCedula());
		if(aux==null)
			throw new Exception("Registro no existe");
		else
			dao.update(persona);
	}
	
	public boolean validar(String c) {
		
		return true;
		
	}
	
}
