package com.ilovemusic.labymod;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleTextModule;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;

@Singleton
public final class StreamModule extends SimpleTextModule {
  private static final String NO_CONTENT = " / ";
  private static final String DESCRIPTION = "Zeigt den aktuellen ILoveMusic Stream an";
  private static final ModuleCategory MODULE_CATEGORY = ModuleCategoryRegistry.CATEGORY_EXTERNAL_SERVICES;
  private final MusicPlayer musicPlayer;

  @Inject
  private StreamModule(
      MusicPlayer musicPlayer
  ) {
    this.musicPlayer = musicPlayer;
  }

  @Override
  public IconData getIconData() {
    return new IconData(Material.NOTE_BLOCK);
  }

  @Override
  public void loadSettings() {

  }

  @Override
  public String getSettingName() {
    return "ILoveMusic";
  }

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }

  @Override
  public int getSortingId() {
    return 0;
  }

  @Override
  public ModuleCategory getCategory() {
    return MODULE_CATEGORY;
  }

  private static final String[] KEYS = new String[] {
      "Channel",
      "Interpret",
      "Song"
  };

  @Override
  public String[] getValues() {
    Optional<Stream> currentStream = musicPlayer.currentStream();
    return new String[] {
        currentStream.map(Stream::name).orElse(NO_CONTENT),
        currentStream.map(Stream::artist).orElse(NO_CONTENT),
        currentStream.map(Stream::title).orElse(NO_CONTENT)
    };
  }

  @Override
  public String[] getDefaultValues() {
    return getValues();
  }

  @Override
  public String[] getKeys() {
    return KEYS;
  }

  @Override
  public String[] getDefaultKeys() {
    return getKeys();
  }
}
