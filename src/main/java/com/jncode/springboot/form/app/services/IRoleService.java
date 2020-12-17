package com.jncode.springboot.form.app.services;

import java.util.List;

import com.jncode.springboot.form.app.models.domain.Role;

public interface IRoleService {

	public List<Role> listar();
	public Role obtenerPorId(Integer id);
	
}
