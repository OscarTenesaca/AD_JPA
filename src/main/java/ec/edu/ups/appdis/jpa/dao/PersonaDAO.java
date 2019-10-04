package ec.edu.ups.appdis.jpa.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.jpa.model.Persona;

@Stateless
public class PersonaDAO {
	
	@Inject
	private EntityManager em;
	
	public void insert(Persona persona) {
		em.persist(persona);
	}
	
	public void update(Persona persona) {
		em.merge(persona);
	}
	
	public void remove(String id) {
		em.remove(read(id));
	}
	
	public Persona read(String cedula) {
		Persona aux = em.find(Persona.class, cedula);
		return aux;
	}
	
	public List<Persona> getPersonas(String param){
		//String jqpl = "SELECT p FROM Persona p WHERE p.email LIKE '%"+param+"%'";
		/** Opcion 1**/
		String jpq0 = "SELECT p FROM Persona p WHERE p.email LIKE '%"+param+"%' AND nombre = 'Juan'";		
		Query query0 = em.createQuery(jpq0, Persona.class);
		
		/** Opcion 2**/
		String jpql = "SELECT p FROM Persona p WHERE p.email LIKE :paramEmail AND nombre = :nombre";		
		Query query = em.createQuery(jpql, Persona.class);
		query.setParameter("paramEmail", "%"+param+"%");
		query.setParameter("nombre", "%"+param+"%");
		
		
		/** Opcion 3**/
		String jpql2 = "SELECT p FROM Persona p WHERE p.email LIKE ? AND nombre = ?";		
		Query query2 = em.createQuery(jpql2, Persona.class);
		query2.setParameter(1, "%"+param+"%");
		query2.setParameter(2, "Juan");
		
		List<Persona> lista = query0.getResultList();
		
		
		return lista;
	}
	
	
	public List<Persona> getPersonas(){
		String jpql = "SELECT p FROM Persona p";		
		Query query = em.createQuery(jpql, Persona.class);
		
		List<Persona> lista = query.getResultList();
		return lista;
	}
	
}
