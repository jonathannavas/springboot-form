package com.jncode.springboot.form.app.services;

import java.util.List;

import com.jncode.springboot.form.app.models.domain.Pais;

public interface IPaisService {

	public List<Pais> listar();
	
	public Pais obtenerPorId(Integer id);
	
}
