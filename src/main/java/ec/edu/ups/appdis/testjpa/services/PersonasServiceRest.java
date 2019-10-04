package ec.edu.ups.appdis.testjpa.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ec.edu.ups.appdis.jpa.bussiness.PersonaBussiness;
import ec.edu.ups.appdis.jpa.model.Persona;

@Path("/personas")
public class PersonasServiceRest {
	
	@Inject
	private PersonaBussiness pBussiness;
	
	@GET
	@Path("/list")
	@Produces("application/json")
		public List<Persona> getPersonas(){
			return pBussiness.getPersonas();
		}
	
	@POST
	@Path("/insert")
	@Consumes("application/json")
	@Produces("application/json")
	public  Response insertPersona(Persona persona) {
		
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		
		try {
			pBussiness.save(persona);
			data.put("code", "1");
			data.put("message", "OK");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(data);
		}
		
		return builder.build();
		
		
	}

	@POST
	@Path("/update")
	@Consumes("application/json")
	@Produces("application/json")
	public  Response updatePersona(Persona persona) {
		
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		
		try {
			pBussiness.update(persona);
			data.put("code", "1");
			data.put("message", "OK");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(data);
		}
		
		return builder.build();
		
		
	}
	
	@POST
	@Path("/remove")
	@Consumes("application/json")
	@Produces("application/json")
	public  Response removePersona(String cedula) {
		
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		
		try {
			pBussiness.delete(cedula);
			data.put("code", "1");
			data.put("message", "OK");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(data);
		}
		
		return builder.build();
		
		
	}


}
