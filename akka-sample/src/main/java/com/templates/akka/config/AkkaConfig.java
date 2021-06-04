package com.templates.akka.config;

import akka.actor.typed.ActorSystem;
import com.templates.akka.actor.MainActor;
import com.templates.akka.actor.message.ActorMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {

  @Bean
  public ActorSystem<ActorMessage> actorSystem(ApplicationContext applicationContext) {
    return ActorSystem.create(MainActor.create(applicationContext), "sample-actor-system");
  }
}
