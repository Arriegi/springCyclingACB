package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.authentication.Role;
import eus.arriegi.cyclingacb.repository.RoleDao;

@Component
public class SimpleRoleManager implements RoleManager{

	@Autowired
	private RoleDao roleDao;
	
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

	public Role getRole(Long id) {
		return roleDao.getRole(id);
	}

	public Role addRole(Role role) {
		return roleDao.saveRole(role);
	}

	public Role updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	public void removeRole(Role role) {
		roleDao.removeRole(role);
	}

	public void removeRole(Long id) {
		roleDao.removeRole(id);
	}

}
