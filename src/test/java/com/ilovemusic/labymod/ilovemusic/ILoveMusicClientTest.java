package com.ilovemusic.labymod.ilovemusic;

import static org.junit.jupiter.api.Assertions.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ilovemusic.labymod.ILoveMusicModule;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ILoveMusicClientTest {
  private static final String DEFAULT_BASE_URL = "https://www.ilovemusic.de/api/";

  @Inject
  private ILoveMusicClient iLoveMusicClient;

  @BeforeEach
  void setUp() {
    Injector injector = Guice.createInjector(ILoveMusicModule.create(DEFAULT_BASE_URL));
    injector.injectMembers(this);
  }

  @Test
  void testFetchStreams() throws ExecutionException, InterruptedException {
    CompletableFuture<StreamResponse> responseFuture = iLoveMusicClient.fetchStreams();
    StreamResponse streamResponse = responseFuture.get();

    assertTrue(responseFuture.isDone());
    assertNotNull(streamResponse);
    assertFalse(streamResponse.channels().isEmpty());
  }
}
