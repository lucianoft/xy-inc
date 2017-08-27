package com.xyinc.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.xyinc.model.Modelo;
import com.xyinc.repository.ModeloRepository;

@Service
public class ModeloService extends CrudService<Modelo, Long> {

	@Autowired
	private ModeloRepository modeloRepository;
	
	@Override
	protected JpaRepository<Modelo, Long> getRepository() {
		return modeloRepository;
	}

	@Override
	protected void copiarModel(Modelo modelo, Modelo modeloSalvo) {
		BeanUtils.copyProperties(modelo, modeloSalvo, "id");
	}
	
}
