package benchmark;

import io.sentry.SentryUUID;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.UUID;

@State(Scope.Thread)
public class UUIDBenchmark {

  public UUID uuid;
  public String uuidString;

  //Generate new random UUID before each method invocation
  @Setup(Level.Invocation)
  public void setup() {
    uuid = UUID.randomUUID();
    uuidString = UUID.randomUUID().toString();
  }

  @Benchmark
  public void uuidGenerationSecureRandom(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(UUID.randomUUID());
  }

  @Benchmark
  public void uuidGenerationRandom(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(SentryUUID.randomUUID());
  }

  @Threads(10)
  @Benchmark
  public void uuidGenerationRandomMultiThreaded(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(SentryUUID.randomUUID());
  }

  @Threads(10)
  @Benchmark
  public void uuidGenerationThreadLocalRandomMultiThreaded(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(SentryUUID.randomUUIDThreadLocal());
  }


  @Benchmark
  public void uuidToString(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(uuid.toString());
  }

  @Benchmark
  public void uuidPreparedStringReplace(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(uuidString.replace("-", ""));
  }

  @Benchmark
  public void uuidPreparedStringSubstring(Blackhole blackhole) throws InterruptedException {
    StringBuilder builder = new StringBuilder();
    builder.append(uuidString, 0, 8);
    builder.append(uuidString, 9, 13);
    builder.append(uuidString, 14, 18);
    builder.append(uuidString, 19, 23);
    builder.append(uuidString, 24, 36);
    blackhole.consume(builder.toString());
  }

  @Benchmark
  public void uuidToStringAndReplace(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(uuid.toString().replace("-", ""));
  }

  @Benchmark
  public void uuidToStringAndStringBuilder(Blackhole blackhole) throws InterruptedException {
    String localUuidString = uuid.toString();
    StringBuilder builder = new StringBuilder();
    builder.append(localUuidString, 0, 8);
    builder.append(localUuidString, 9, 13);
    builder.append(localUuidString, 14, 18);
    builder.append(localUuidString, 19, 23);
    builder.append(localUuidString, 24, 36);
    blackhole.consume(builder.toString());
  }

  @Benchmark
  public void uuidToSentryStringFastUUIDString(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(SentryUUID.toSentryIdString(uuid));
  }

  @Benchmark
  public void generateSentryIdSecureRandomToStringAndReplace(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(UUID.randomUUID().toString().replace("-", ""));
  }

  @Benchmark
  public void generateSentryIdSecureRandomToStringAndStringBuilder(Blackhole blackhole) throws InterruptedException {

    String localUuidString = UUID.randomUUID().toString();
    StringBuilder builder = new StringBuilder();
    builder.append(localUuidString, 0, 8);
    builder.append(localUuidString, 9, 13);
    builder.append(localUuidString, 14, 18);
    builder.append(localUuidString, 19, 23);
    builder.append(localUuidString, 24, 36);
    blackhole.consume(builder.toString());
  }

  @Benchmark
  public void generateSentryIdSecureRandomFastUUIDString(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(SentryUUID.toSentryIdString(UUID.randomUUID()));
  }

  @Benchmark
  public void generateSentryIdRandomToStringAndReplace(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(SentryUUID.randomUUID().toString().replace("-", ""));
  }

  @Benchmark
  public void generateSentryIdRandomToStringAndStringBuilder(Blackhole blackhole) throws InterruptedException {

    String localUuidString = SentryUUID.randomUUID().toString();
    StringBuilder builder = new StringBuilder();
    builder.append(localUuidString, 0, 8);
    builder.append(localUuidString, 9, 13);
    builder.append(localUuidString, 14, 18);
    builder.append(localUuidString, 19, 23);
    builder.append(localUuidString, 24, 36);
    blackhole.consume(builder.toString());
  }


  @Benchmark
  public void generateSentryIdRandomFastUUIDString(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(SentryUUID.generateSentryId());
  }


  @Threads(1)
  @Benchmark
  public void generateSentryIdRandomFastUUIDStringSingle(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(SentryUUID.generateSentryId());
  }


  @Benchmark
  public void generateSpanIdSecureRandomToStringReplaceAndSubstring16(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(UUID.randomUUID().toString().replace("-", "").substring(0, 16));
  }


  @Benchmark
  public void generateSpanIdRandomFastUUIDString(Blackhole blackhole) throws InterruptedException {
    blackhole.consume(SentryUUID.generateSpanId());
  }
}