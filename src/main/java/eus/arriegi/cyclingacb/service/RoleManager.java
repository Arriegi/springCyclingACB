package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.authentication.Role;;

public interface RoleManager {

	public List<Role> getRoles();

	public Role getRole(Long id);

	public Role addRole(Role role);

	public Role updateRole(Role role);

	public void removeRole(Role role);

	public void removeRole(Long id);

}
