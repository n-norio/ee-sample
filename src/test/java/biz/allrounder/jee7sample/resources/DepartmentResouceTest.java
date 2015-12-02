package biz.allrounder.jee7sample.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentResouceTest {

//	@Test
//	public void badRequestInPathParam() {
//		Response response = ClientBuilder.newClient()
//			    .target("http://localhost:8080/jee7sample/api/departments/j")
//			    .request(MediaType.APPLICATION_JSON)
//			    .get();
//		System.out.println(response.getStatus());
//		System.out.println(response.readEntity(String.class));
//		
//		response = ClientBuilder.newClient()
//			    .target("http://localhost:8080/jee7sample/api/departments/99")
//			    .request(MediaType.APPLICATION_JSON)
//			    .get();
//		System.out.println(response.getStatus());
//		System.out.println(response.readEntity(String.class));
//	}
	
	@Test
	public void add() throws JsonProcessingException {

		Map<Object, Object> dept = new HashMap<>();
		dept.put("name", "");
		List<Object> persons = new ArrayList<>();
		Map<Object, Object> director = new HashMap<>();
		director.put("name", "");
		director.put("position", "boss");
		persons.add(director);
		dept.put("persons", persons);
		
		Response response = ClientBuilder.newClient()
			    .target("http://localhost:8080/jee7sample/api/departments")
			    .request(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON)
			    .post(Entity.entity(dept, MediaType.APPLICATION_JSON_TYPE));
		System.out.println(response.getStatus());
		System.out.println(new ObjectMapper().writeValueAsString(response.getStringHeaders()));
		System.out.println(response.readEntity(String.class));
		
		
//		String json = ClientBuilder.newClient()
//			    .target("http://localhost:8080/jee7sample/api/departments")
//			    .request()
//			    .get(String.class);
//		System.out.println(json);
//		
		response = ClientBuilder.newClient()
			    .target("http://localhost:8080/jee7sample/api/departments/d")
			    .request(MediaType.APPLICATION_JSON)
			    .get();
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	
//	@Test
//	public void update() {
//		Map<Object, Object> dept = ClientBuilder.newClient()
//			    .target("http://localhost:8080/jee7sample/api/departments/1")
//			    .request()
//			    .get(new GenericType<Map<Object, Object>>() {});
//		dept.put("name", "");
//		List<Object> persons = ArrayList.class.cast(dept.get("persons"));
//		Map<Object, Object> john = HashMap.class.cast(persons.get(0));
//		john.put("position", "bigBoss");
//		
//		Map<Object, Object> director2 = new HashMap<>();
//		director2.put("name", "person2");
//		director2.put("position", "assistant");
//		persons.add(director2);
//		
//		Response res = ClientBuilder.newClient()
//			    .target("http://localhost:8080/jee7sample/api/departments/" + dept.get("id"))
//			    .request()
//			    .put(Entity.entity(dept, MediaType.APPLICATION_JSON_TYPE));
//		System.out.println(res.getStatus());
//		System.out.println(res.readEntity(String.class));
//		
//		String json = ClientBuilder.newClient()
//			    .target("http://localhost:8080/jee7sample/api/departments/1")
//			    .request()
//			    .get(String.class);
//		System.out.println(json);
//	}

}
