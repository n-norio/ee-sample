package biz.allrounder.jee7sample.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import biz.allrounder.jee7sample.application.CDIService;
import biz.allrounder.jee7sample.application.DepartmentService;
import biz.allrounder.jee7sample.domain.model.Department;
import biz.allrounder.jee7sample.domain.model.session.UserSession;

@Path("/departments")
@RequestScoped
public class DepartmentResouce {

	@Inject
	private DepartmentService departmentService;
	@Inject
	private CDIService cdiService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<DepartmentJsonView> find() {
		cdiService.test();
		Collection<DepartmentJsonView> departments = new ArrayList<>();
		for (Department department: departmentService.find()) {
			departments.add(new DepartmentJsonView(department.id(), department.name()));
		}
		return departments;
	}
	
	@GET
	@Path("/{deptId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DepartmentJsonView get(@PathParam("deptId") long deptId) {
		Department department = departmentService.get(deptId);
		return DepartmentJsonView.valueOf(department);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(@Valid DepartmentJsonView department) {
		departmentService.persist(department.buildProject());
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{deptId}")
	public void update(@PathParam("deptId") long deptId, @Valid DepartmentJsonView department) {
		departmentService.update(deptId, department.buildProject());
	}
}
