package com.templates.akka.actor.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Actor system command messages.
 */
@AllArgsConstructor
public enum Command implements ActorMessage {

  HEARTBEAT("Hello, it's me!");

  @Getter
  private String content;
}
