package com.ilovemusic.labymod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class StreamRepository {
  CompletableFuture<List<Stream>> findAll() {
    return CompletableFuture.completedFuture(new ArrayList<>());
  }
}
