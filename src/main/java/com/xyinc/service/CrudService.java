package com.xyinc.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudService<T, ID extends Serializable> {

	protected abstract JpaRepository<T, ID> getRepository();
	
	public List<T> listarTodos() {
		return getRepository().findAll();
	}
	
	public T incluir(T t) {
		return getRepository().save(t);
	}
	
	public T editar(ID id, T t) {
		T modelSalvo = buscarPeloCodigo(id);
		copiarModel(t, modelSalvo);
		return getRepository().save(modelSalvo);
	}
	
	protected abstract void copiarModel(T t, T tSalvo);
	
	public T buscarPeloCodigo(ID codigo) {
		T modeloSalva = getRepository().findOne(codigo);
		if (modeloSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return modeloSalva;
	}

	public void deletar(ID codigo) {
		getRepository().delete(codigo);
	}
}
