package com.spsamples.exchanger;

import com.spsamples.common.AbstractSampleExecutor;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Exchanger;

/**
 * Sample of simple {@link java.util.concurrent.Exchanger} usage.
 *
 * There is {@link Payload} - it represents any packages exchanged between sender and receiver.
 *
 * Sender (it is a first thread) sends specific number of {@link Payload} objects. When all packages
 * will sent, sender sends stop-signal - just {@link Payload} with specific service message {@code
 * _STOP_} and terminates.
 *
 * Receiver (it is a second thread) receives packages sent by sender. When
 * stop-signal received - receiver also terminates.
 */
public class ExchangerExecutor extends AbstractSampleExecutor {

  public ExchangerExecutor(String sampleName) {
    super(sampleName);
  }

  @Override
  protected void custom() {
    String stopSignal = "_STOP_";
    String okSignal = "_OK_";
    Exchanger<Payload> exchanger = new Exchanger<>();

    Thread receiver = new Thread(() -> {
      try {
        Payload received;
        do {
          String id = UUID.randomUUID().toString();
          Payload ok = new Payload(id, okSignal);
          received = exchanger.exchange(ok);
          System.out.println("Received payload: " + received);
        } while (!Objects.equals(stopSignal, received.getMessage()));
        System.out.println("Receiving finished");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread sender = new Thread(() -> {
      try {
        for (int i = 0; i < 5; i++) {
          String id = UUID.randomUUID().toString();
          Payload toSend = new Payload(id, "iteration: " + i);
          System.out.println("Sending payload: " + toSend);
          Payload fake = exchanger.exchange(toSend);
          System.out.println("Send successfully. Response: " + fake);
          Thread.sleep(500);
          System.out.println();
        }
        Payload stop = new Payload("", stopSignal);
        exchanger.exchange(stop);
        System.out.println("Sending finished");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    receiver.start();
    sender.start();
  }
}
