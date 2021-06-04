package com.templates.akka.actor;


import static com.templates.akka.actor.message.Command.HEARTBEAT;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.SupervisorStrategy;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.actor.typed.javadsl.TimerScheduler;
import com.templates.akka.actor.message.ActorMessage;
import com.templates.akka.actor.message.Command;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * Main super actor.
 */
@Slf4j
public class MainActor extends AbstractBehavior<ActorMessage> {

  private ActorRef<ActorMessage> slaveActor;

  /**
   * Creates an actor.
   *
   * @param applicationContext {@link ApplicationContext}
   * @return actor behavior
   */
  public static Behavior<ActorMessage> create(ApplicationContext applicationContext) {
    return Behaviors
        .setup(context -> Behaviors
            .withTimers(timers -> new MainActor(context, applicationContext, timers)));
  }

  private MainActor(ActorContext<ActorMessage> context, ApplicationContext applicationContext,
      TimerScheduler<ActorMessage> timers) {
    super(context);
    slaveActor = context.spawn(
        Behaviors.supervise(SlaveActor.create()).onFailure(SupervisorStrategy.restart()),
        "slave-actor"
    );
    timers.startTimerAtFixedRate(HEARTBEAT, Duration.ofMillis(3_000L));
  }

  public Receive<ActorMessage> createReceive() {
    return newReceiveBuilder().onMessage(Command.class, this::onCommand).build();
  }

  private Behavior<ActorMessage> onCommand(Command cmd) {
    log.info("Main received content:> " + cmd.getContent());
    slaveActor.tell(cmd);
    return this;
  }

  /**
   * Creates an actor for tests.
   *
   * @param slaveActor {@link SlaveActor}
   * @return actor behavior
   */
  public static Behavior<ActorMessage> createForTests(ActorRef<ActorMessage> slaveActor) {
    return Behaviors
        .setup(context -> new MainActor(context, slaveActor));
  }

  private MainActor(ActorContext<ActorMessage> context, ActorRef<ActorMessage> slaveActor) {
    super(context);
    this.slaveActor = slaveActor;
  }
}
