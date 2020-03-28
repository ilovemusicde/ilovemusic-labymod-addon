package com.ilovemusic.labymod;

import com.google.common.base.Preconditions;
import com.ilovemusic.labymod.player.BasicPlayer;
import com.ilovemusic.labymod.player.BasicPlayerException;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class MusicPlayer extends Observable {
  private final BasicPlayer basicPlayer;
  private double currentVolume = 0.5;

  @Inject
  private MusicPlayer(BasicPlayer basicPlayer) {
    this.basicPlayer = basicPlayer;
  }

  public synchronized void play() {
    try {
      basicPlayer.resume();
      basicPlayer.setGain(currentVolume);
    } catch (BasicPlayerException e) {
      e.printStackTrace();
    }
    this.setChanged();
    this.notifyObservers();
  }

  public boolean isPlaying() {
    return basicPlayer.getStatus() == BasicPlayer.PLAYING;
  }

  public float getVolume() {
    return (float) currentVolume;
  }

  public void setVolume(double gain) {
    try {
      basicPlayer.pause();
      currentVolume = gain;
      basicPlayer.setGain(gain);
      basicPlayer.resume();
    } catch (BasicPlayerException e) {
      e.printStackTrace();
    }
  }

  public synchronized void play(URL url) {
    Preconditions.checkNotNull(url);
    try {
      basicPlayer.stop();
      basicPlayer.open(url.openStream());
      basicPlayer.play();
      basicPlayer.setGain(currentVolume);
      this.setChanged();
      this.notifyObservers();
    } catch (BasicPlayerException | IOException e) {
      e.printStackTrace();
    }
  }

  public synchronized void stop() {
    try {
      basicPlayer.pause();
      this.setChanged();
      this.notifyObservers();
    } catch (BasicPlayerException e) {
      e.printStackTrace();
    }
  }
}
