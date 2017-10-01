package com.example.demo.service;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.demo.model.Friend;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class StreamService {
	
	Stream<Friend> returnAStream() {
		return Stream.of(new Friend(1l, "Mario"),
				new Friend(2l, "Luigi"),
				new Friend(3l, "Princess"));
	}
	
	public Flux<Friend> all() {
		return Flux.fromStream(returnAStream());
	}
	
	public Mono<Friend> byId(Long id) {
		return Mono.justOrEmpty( returnAStream().filter( 
				friend -> friend.getId().equals(id) ).findFirst());
	}
	
	public Flux<Friend> events() {
		Flux<Friend> friends =  Flux.fromStream(returnAStream());
		return Flux.zip(friends, Flux.interval(Duration.ofSeconds(1))).map(Tuple2::getT1);
	}
 
}
