package biz.allrounder.jee7sample.resources;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
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

@Path("/departments")
//@RequestScoped
@Stateless
//@RunAs("batch")
@RolesAllowed("test")
public class DepartmentResouce {

	@Inject
	private DepartmentService departmentService;
	@Inject
	private CDIService cdiService;
	@Inject
	private UserId userId;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Logging(operationName="find")
@RolesAllowed("test")
	public Collection<DepartmentJsonView> find() {
		cdiService.test();
		System.out.println(userId.get());
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
	@Logging
	public void save(@Valid DepartmentJsonView department) {
		departmentService.persist(department.buildDepartment());
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{deptId}")
	public void update(@PathParam("deptId") long deptId, @Valid DepartmentJsonView department) {
		departmentService.update(deptId, department.buildDepartment());
	}
}
