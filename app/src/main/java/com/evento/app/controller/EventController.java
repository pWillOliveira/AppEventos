package com.evento.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.evento.app.model.Convidado;
import com.evento.app.model.Event;
import com.evento.app.repository.ConvidadoRepository;
import com.evento.app.repository.EventRepository;

@Controller
public class EventController {

	@Autowired
	private EventRepository er;

	@Autowired
	private ConvidadoRepository cr;

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String form(@Valid Event evento, BindingResult result, RedirectAttributes attributes) {

		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");

		return "redirect:/cadastrarEvento";
	}

	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Event> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Event evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		return mv;
	}

	@RequestMapping("/deletarEvento")
	public String deletarEvento(long codigo) {
		Event evento = er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/eventos";

	}

	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(String rg) {
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);

		Event evento = convidado.getEvento();
		long codigoLong = evento.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;

	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado,
			BindingResult result, RedirectAttributes attributes) {
		Event evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{codigo}";

	}

}
