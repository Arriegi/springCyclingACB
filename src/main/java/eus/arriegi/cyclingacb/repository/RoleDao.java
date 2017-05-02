package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.authentication.Role;

public interface RoleDao {
	
	public List<Role> getRoles();
	
	public Role getRole(Long id);
	
    public Role saveRole(Role userRole);
    
    public void removeRole(Role userRole);
    
    public void removeRole(Long id);
    
    public Role updateRole(Role userRole);
    
}
