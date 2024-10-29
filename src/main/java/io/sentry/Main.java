package io.sentry;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
  public static void main(String[] args) {

    List<String> list = new ArrayList<>();
    for(int i = 0; i<100000; i++) {
//      String sentryIdString = UUID.randomUUID().toString().replace("-", "");
      String sentryIdString = SentryUUID.generateSentryId();
//      list.add(sentryIdString);
//      System.out.println(sentryIdString);
//      SentryUUID.toSentryIdString(uuid);
//      SentryUUID.toSentrySpanIdString(uuid);
    }
  }
}
