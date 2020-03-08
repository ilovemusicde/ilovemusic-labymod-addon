package com.ilovemusic.labymod;

import com.google.gson.annotations.SerializedName;
import java.net.URL;

public final class Stream {
  @SerializedName("channel_id")
  private final int id;
  private final String name;
  private final String artist;
  private final String title;
  private final String cover;
  private final String color;
  private final String fontcolor;
  @SerializedName("stream_url")
  private final URL streamUrl;

  public Stream(
      int id,
      String name,
      String artist,
      String title,
      String cover,
      String color,
      String fontcolor,
      URL streamUrl
  ) {
    this.id = id;
    this.name = name;
    this.artist = artist;
    this.title = title;
    this.cover = cover;
    this.color = color;
    this.fontcolor = fontcolor;
    this.streamUrl = streamUrl;
  }

  public int id() {
    return id;
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
    return streamUrl;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Stream{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", artist='").append(artist).append('\'');
    sb.append(", title='").append(title).append('\'');
    sb.append(", cover='").append(cover).append('\'');
    sb.append(", color='").append(color).append('\'');
    sb.append(", fontcolor='").append(fontcolor).append('\'');
    sb.append(", streamUrl=").append(streamUrl);
    sb.append('}');
    return sb.toString();
  }
}
