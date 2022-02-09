package com.spsamples.exchanger;

public class Payload {

  private final String id;
  private final String message;

  public Payload(String id, String message) {
    this.id = id;
    this.message = message;
  }

  public String getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "Payload{" +
        "id=" + id +
        ", message='" + message + '\'' +
        '}';
  }
}
