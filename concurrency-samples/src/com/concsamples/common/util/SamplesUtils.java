package com.concsamples.common.util;

import java.util.ArrayList;
import java.util.List;

public class SamplesUtils {

  public static List<String> getActiveThreadNames() {
    return Thread.getAllStackTraces().keySet().stream()
        .collect(
            ArrayList::new,
            (names, thread) -> names.add(thread.getName()),
            ArrayList::addAll
        );
  }

  public static List<String> getActiveThreadNamesWithPrefix(String prefix) {
    return Thread.getAllStackTraces().keySet().stream()
        .collect(
            ArrayList::new,
            (names, thread) -> {
              String name = thread.getName();
              if (name.startsWith(prefix)) {
                names.add(name);
              }
            },
            ArrayList::addAll
        );
  }

  public static void printNowActiveWithPrefix(String prefix) {
    System.out.println("\nNow active '" + prefix + "-...' threads: ");
    List<String> threads = getActiveThreadNamesWithPrefix(prefix);
    if (threads.isEmpty()) {
      System.out.println("no threads...");
    } else {
      threads.forEach(System.out::println);
    }
    System.out.println();
  }
}
