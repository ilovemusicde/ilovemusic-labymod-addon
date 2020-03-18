package com.ilovemusic.labymod;

import com.google.common.base.Preconditions;
import com.ilovemusic.labymod.ilovemusic.Stream;
import net.labymod.settings.SettingsCategory;
import net.labymod.settings.elements.CategorySettingsElement;

/**
 *
 */
public final class StreamSettingsElement extends CategorySettingsElement {
  private final Stream stream;

  public StreamSettingsElement(
      SettingsCategory category,
      ClickedCallback callback,
      Stream stream) {
    super(category, callback);
    this.stream = stream;
  }

  public static StreamSettingsElement forStream(Stream stream) {
    Preconditions.checkNotNull(stream);
    return new StreamSettingsElement(new SettingsCategory(stream.name()), new ClickedCallback() {
      @Override
      public void clicked(SettingsCategory settingsCategory) {

      }
    }, stream);
  }
}
