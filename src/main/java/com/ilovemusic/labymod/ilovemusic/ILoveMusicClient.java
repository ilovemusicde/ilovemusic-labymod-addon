package com.ilovemusic.labymod.ilovemusic;

import java.util.concurrent.CompletableFuture;
import retrofit2.http.GET;

public interface ILoveMusicClient {
  @GET("labymod.php")
  CompletableFuture<StreamResponse> fetchStreams();
}
