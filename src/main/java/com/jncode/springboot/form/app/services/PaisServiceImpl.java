package com.jncode.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jncode.springboot.form.app.models.domain.Pais;

@Service
public class PaisServiceImpl implements IPaisService {

	private List<Pais> lista;
	
	public PaisServiceImpl() {
		this.lista = Arrays.asList(
				new Pais(1, "Ec", "Ecuador"), 
				new Pais(2, "Br", "Brasil"), 
				new Pais(3, "Pe", "Peru"),
				new Pais(4, "Bo", "Bolivia"), 
				new Pais(5, "Mx", "Mexico"), 
				new Pais(6, "No", "Noruega"),
				new Pais(7, "Ca", "Canada"));
	}

	@Override
	public List<Pais> listar() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado = null;
		for(Pais pais: this.lista) {
			if(id==pais.getId()) {
				resultado = pais;
				break;
			}
		}
		return resultado;
	}

}
