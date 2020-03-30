package com.ilovemusic.labymod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public final class Stream {
  private final int channel_id;
  private final String name;
  private final String artist;
  private final String title;
  private final String cover;
  private final String color;
  private final String fontcolor;
  private final String stream_url;

  public Stream(
      int channel_id,
      String name,
      String artist,
      String title,
      String cover,
      String color,
      String fontcolor,
      String stream_url
  ) {
    this.channel_id = channel_id;
    this.name = name;
    this.artist = artist;
    this.title = title;
    this.cover = cover;
    this.color = color;
    this.fontcolor = fontcolor;
    this.stream_url = stream_url;
  }

  public int id() {
    return channel_id;
  }

  public String name() {
    return name;
  }

  public String artist() {
    return artist;
  }

  public String title() {
    return title;
  }

  public String cover() {
    return cover;
  }

  public String color() {
    return color;
  }

  public String fontcolor() {
    return fontcolor;
  }

  public URL streamUrl() {
    try {
      return new URL(stream_url.replace("https://", "http://"));
    } catch (MalformedURLException e) {
      return null;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Stream stream = (Stream) o;
    return channel_id == stream.channel_id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(channel_id);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Stream{");
    sb.append("id=").append(channel_id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", artist='").append(artist).append('\'');
    sb.append(", title='").append(title).append('\'');
    sb.append(", cover='").append(cover).append('\'');
    sb.append(", color='").append(color).append('\'');
    sb.append(", fontcolor='").append(fontcolor).append('\'');
    sb.append(", streamUrl=").append(stream_url);
    sb.append('}');
    return sb.toString();
  }
}
