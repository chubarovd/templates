package com.templates.akka.actor;

import static org.junit.jupiter.api.Assertions.assertTrue;

import akka.actor.testkit.typed.javadsl.BehaviorTestKit;
import akka.actor.testkit.typed.javadsl.TestInbox;
import com.templates.akka.actor.message.ActorMessage;
import com.templates.akka.actor.message.Command;
import org.junit.jupiter.api.Test;

public class MainActorTest {

  private final TestInbox<ActorMessage> testSlaveActor = TestInbox.create("test-slave-actor");
  private final BehaviorTestKit<ActorMessage> testMainActor = BehaviorTestKit
      .create(MainActor.createForTests(testSlaveActor.getRef()));

  @Test
  public void onReceiveCommandIsOk() {
    testMainActor.run(Command.HEARTBEAT);
    assertTrue(testSlaveActor.hasMessages());
    testSlaveActor.expectMessage(Command.HEARTBEAT);
  }
}
