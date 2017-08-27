package com.xyinc.resource;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.xyinc.service.CrudService;

public abstract class CrudResource<T, ID extends Serializable> {

	protected abstract CrudService<T, ID> getCrudService();

	//GET /xxx - Lista todos os elementos do Modelo XXX
	//GET /xxx/{id} - Busca um registro do modelo XXX por id
	//POST /xxx - Cria um novo registro do modelo XXX
	//PUT /xxx/{id} - Edita um registro do modelo XXX
	//DELETE /xxx/{id} - Deleta um registo do modelo XXX

	@GetMapping
	public List<T> listarTodos() {
		return getCrudService().listarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<T> buscarPeloCodigo(@PathVariable ID id) {
		T model = getCrudService().buscarPeloCodigo(id);
		return model != null ? ResponseEntity.ok(model) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<T> criar(@Valid @RequestBody T model, HttpServletResponse response) {
		T modelSalvo = getCrudService().incluir(model);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<T> editar(@PathVariable ID id, @Valid @RequestBody T model) {
		T t = getCrudService().editar(id, model);
		return ResponseEntity.ok(t);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable ID id) {
		getCrudService().deletar(id);
	}

}