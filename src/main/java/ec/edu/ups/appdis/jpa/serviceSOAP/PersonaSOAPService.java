package ec.edu.ups.appdis.jpa.serviceSOAP;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ec.edu.ups.appdis.jpa.bussiness.PersonaBussiness;
import ec.edu.ups.appdis.jpa.model.Persona;

@WebService
public class PersonaSOAPService {

	@Inject
	private PersonaBussiness pBussiness;
	
	@WebMethod
	public List<Persona> getPersonas(){
		return pBussiness.getPersonas();
		
	}
	
	@WebMethod
	public void save(Persona persona) {
		try {
			pBussiness.save(persona);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@WebMethod
	public void remove(String cedula) {
		try {
			pBussiness.delete(cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@WebMethod
	public int suma(int a, int b) {
		return a + b;
	}

}
