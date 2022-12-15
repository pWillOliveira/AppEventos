package com.evento.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.evento.app.model.Convidado;
import com.evento.app.model.Event;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	Iterable<Convidado> findByEvento(Event evento);
	Convidado findByRg(String rg);
	

}
