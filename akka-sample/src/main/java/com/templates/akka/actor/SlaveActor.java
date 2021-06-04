package com.templates.akka.actor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.templates.akka.actor.message.ActorMessage;
import com.templates.akka.actor.message.Command;
import lombok.extern.slf4j.Slf4j;

/**
 * Any slave actor.
 */
@Slf4j
public class SlaveActor extends AbstractBehavior<ActorMessage> {

  private SlaveActor(ActorContext<ActorMessage> context) {
    super(context);
  }

  /**
   * Creates an actor.
   *
   * @return actor behavior
   */
  public static Behavior<ActorMessage> create() {
    return Behaviors.setup(SlaveActor::new);
  }

  @Override
  public Receive<ActorMessage> createReceive() {
    return newReceiveBuilder().onMessage(Command.class, this::onCommand).build();
  }

  private Behavior<ActorMessage> onCommand(Command cmd) {
    log.info("Slave received command:> " + cmd.getContent());
    return this;
  }
}
