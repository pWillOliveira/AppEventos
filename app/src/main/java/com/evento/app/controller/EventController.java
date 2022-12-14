package com.evento.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.evento.app.model.Event;
import com.evento.app.repository.EventRepository;

@Controller
public class EventController {

	@Autowired
	private EventRepository er;

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String form(Event evento) {

		er.save(evento);

		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Event> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}

}
