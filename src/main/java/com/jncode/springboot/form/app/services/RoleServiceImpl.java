package com.jncode.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jncode.springboot.form.app.models.domain.Role;

@Service
public class RoleServiceImpl implements IRoleService {
	
	private List<Role> roles;
	
	public RoleServiceImpl() {
		this.roles = new ArrayList<>();
		this.roles.add(new Role(1,"Administrador","Role_admin"));
		this.roles.add(new Role(2,"Usuario","Role_user"));
		this.roles.add(new Role(3,"Moderador","Role_moderator"));
	}
	
	@Override
	public List<Role> listar() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public Role obtenerPorId(Integer id) {
		Role resultado= null;
		for(Role role: roles) {
			if(id==role.getId()) {
				resultado = role;
				break;
			}
		}
		return resultado;
	}

}
