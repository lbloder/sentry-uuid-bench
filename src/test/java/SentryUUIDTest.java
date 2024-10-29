import io.sentry.SentryUUID;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SentryUUIDTest {
  @Test
  public void UUIDToStringMatchesSentryUUIDToSTring() {
    for(int i = 0; i < 1000; i++) {
      UUID uuid = UUID.randomUUID();
      String sentryIdString = uuid.toString().replace("-", "");
      assertEquals(sentryIdString, SentryUUID.toSentryIdString(uuid));
    }

  }

  @Test
  public void UUIDToStringMatchesSentryUUIDToSTringForSpan() {
    for(int i = 0; i < 1000; i++) {
      UUID uuid = UUID.randomUUID();
      String sentryIdString = uuid.toString().replace("-", "").substring(0, 16);
      assertEquals(sentryIdString, SentryUUID.toSentrySpanIdString(uuid));
    }
  }
}
