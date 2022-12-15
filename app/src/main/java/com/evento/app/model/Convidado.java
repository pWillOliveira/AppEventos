package com.evento.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Convidado {

	@Id
	@NotEmpty
	private String rg;

	@NotEmpty
	private String nomeConvidado;

	@ManyToOne
	private Event evento;

	public Event getEvento() {
		return evento;
	}

	public void setEvento(Event evento) {
		this.evento = evento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNomeConvidado() {
		return nomeConvidado;
	}

	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}

}
