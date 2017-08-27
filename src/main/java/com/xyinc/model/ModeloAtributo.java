package com.xyinc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name="modelo_atributo")
@Entity
public class ModeloAtributo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, max = 50)
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoAtributo tipo;
	
	@NotNull
	@Digits(fraction=0, integer=20)
	private Integer tamanho;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_modelo")
	private Modelo modelo;

}
