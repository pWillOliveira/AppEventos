package com.evento.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.evento.app.model.Event;

public interface EventRepository extends CrudRepository<Event, String> {

}
