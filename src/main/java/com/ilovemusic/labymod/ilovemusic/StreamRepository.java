package com.ilovemusic.labymod.ilovemusic;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

public final class StreamRepository {
  private final ILoveMusicClient musicClient;

  @Inject
  private StreamRepository(ILoveMusicClient musicClient) {
    this.musicClient = musicClient;
  }

  public CompletableFuture<List<Stream>> findAll() {
    return musicClient.fetchStreams()
        .thenApply(StreamResponse::channels);
  }
}
