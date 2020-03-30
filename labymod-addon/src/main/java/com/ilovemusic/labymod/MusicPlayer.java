package com.ilovemusic.labymod;

import com.ilovemusic.labymod.player.BasicPlayer;
import com.ilovemusic.labymod.player.BasicPlayerException;
import java.io.IOException;
import java.util.Observable;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class MusicPlayer extends Observable {
  private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors
      .newSingleThreadScheduledExecutor();
  private final BasicPlayer basicPlayer;
  private final StreamRepository streamRepository;
  private double currentVolume = 0.5;
  private Stream currentStream;

  @Inject
  private MusicPlayer(
      BasicPlayer basicPlayer,
      StreamRepository streamRepository
  ) {
    this.basicPlayer = basicPlayer;
    this.streamRepository = streamRepository;
    startUpdateTask();
  }

  private void startUpdateTask() {
    EXECUTOR_SERVICE.scheduleAtFixedRate(this::updateStream, 0L, 30L, TimeUnit.SECONDS);
  }

  private void updateStream() {
    if (currentStream == null) {
      return;
    }
    streamRepository.findAll().thenAccept(streams -> {
      for (Stream stream : streams) {
        if (stream.id() == currentStream.id()) {
          currentStream = stream;
        }
      }
    });
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
      currentVolume = gain;
      basicPlayer.setGain(gain);
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

  public void toggle() {
    try {
      if (basicPlayer.getStatus() == BasicPlayer.PAUSED || basicPlayer.getStatus() == BasicPlayer.STOPPED) {
        basicPlayer.resume();
        this.setChanged();
      } else if (basicPlayer.getStatus() == BasicPlayer.PLAYING) {
        basicPlayer.pause();
        this.setChanged();
      }
      this.notifyObservers();
    } catch (BasicPlayerException e) {
      e.printStackTrace();
    }
  }
}
