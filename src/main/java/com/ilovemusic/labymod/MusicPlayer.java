package com.ilovemusic.labymod;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.inject.Inject;
import javax.inject.Singleton;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

@Singleton
public final class MusicPlayer {
  private final Lock lock = new ReentrantLock();
  private AdvancedPlayer currentPlayer;

  @Inject
  private MusicPlayer() {

  }

  public void play() {
    try {
      currentPlayer.play();
    } catch (JavaLayerException e) {
      e.printStackTrace();
    }
  }

  public void play(URL url) {
    Preconditions.checkNotNull(url);
    lock.lock();
    if (currentPlayer != null) {
      stopLocked();
    }
    new Thread(() -> {
      try {
        currentPlayer = new AdvancedPlayer(url.openStream());
        currentPlayer.play();
      } catch (JavaLayerException | IOException e) {
        e.printStackTrace();
      }
      lock.unlock();
    }).start();
  }

  public void stop() {
    lock.lock();
    if (currentPlayer == null) {
      lock.unlock();
      return;
    }
    stopLocked();
    lock.unlock();
  }

  private void stopLocked() {
    currentPlayer.stop();
    currentPlayer.close();
  }
}
