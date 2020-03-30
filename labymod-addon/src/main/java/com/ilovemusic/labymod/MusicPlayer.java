package com.ilovemusic.labymod;

import com.ilovemusic.labymod.player.BasicPlayer;
import com.ilovemusic.labymod.player.BasicPlayerException;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class MusicPlayer extends Observable {
  private final BasicPlayer basicPlayer;
  private double currentVolume = 0.5;
  private Stream currentStream;

  @Inject
  private MusicPlayer(BasicPlayer basicPlayer) {
    this.basicPlayer = basicPlayer;
  }

  public Optional<Stream> currentStream() {
    return Optional.ofNullable(currentStream);
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

  public synchronized void play(Stream stream) {
    try {
      basicPlayer.stop();
      basicPlayer.open(stream.streamUrl().openStream());
      basicPlayer.play();
      basicPlayer.setGain(currentVolume);
      currentStream = stream;
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
