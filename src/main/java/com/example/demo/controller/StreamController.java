package com.example.demo.controller;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Friend;
import com.example.demo.service.StreamService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stream")
public class StreamController {
	
	@Autowired
	StreamService streamService;
	
	@GetMapping(value = "/{id}")
	public Mono<Friend> byId(@PathVariable Long id) {
		return streamService.byId(id);
	}
	
	@GetMapping(value = "/all")
	public Flux<Friend> allFriends() {
		return streamService.all();
	}
	
	@GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Friend> events() {
		return streamService.events();
	}

}
