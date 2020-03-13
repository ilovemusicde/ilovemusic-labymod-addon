package com.ilovemusic.labymod.ilovemusic;

import java.util.List;
import java.util.Objects;

public final class StreamResponse {
  private final List<Stream> channels;

  public StreamResponse(List<Stream> channels) {
    this.channels = channels;
  }

  public List<Stream> channels() {
    return channels;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StreamResponse that = (StreamResponse) o;
    return Objects.equals(channels, that.channels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(channels);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("StreamResponse{");
    sb.append("channels=").append(channels);
    sb.append('}');
    return sb.toString();
  }
}
