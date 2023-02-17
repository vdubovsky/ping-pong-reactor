package com.example.pingpongreactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoTest {


    @Test
    void monoTest1(){
        Mono<String> hello = Mono.just("Hello mono");
        hello.subscribe(System.out::println);
    }

    @Test
    void monoTest2(){
        Mono<String> hello = Mono
                .just("Hello mono")
                .then(Mono.error(new RuntimeException("Error in mono")));
        hello.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }

    @Test
    void monoTest3(){
        Mono<String> hello = Mono
                .just("Hello mono");

        if (Math.random() > 0.5){
            hello = hello.then(Mono.error(new RuntimeException("Error in mono")));
        }

        hello.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }

    @Test
    void fluxTest1(){
        Flux<String> hello = Flux
                .just("Hello mono", "Cheść")
                .concatWithValues("¡Hola!")
                .concatWith(Flux.error(new RuntimeException("Hello exception!")))
                .concatWithValues("Привет")
                ;

        hello.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }
}
