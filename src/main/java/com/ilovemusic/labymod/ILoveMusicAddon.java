package com.ilovemusic.labymod;

import java.util.List;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.SettingsCategory;
import net.labymod.settings.elements.CategorySettingsElement;
import net.labymod.settings.elements.CategorySettingsElement.ClickedCallback;
import net.labymod.settings.elements.SettingsElement;

public final class ILoveMusicAddon extends LabyModAddon {
  @Override
  public void onEnable() {

  }

  @Override
  public void loadConfig() {

  }

  @Override
  protected void fillSettings(List<SettingsElement> settings) {
    settings.add(new CategorySettingsElement(new SettingsCategory("ILoveMusic"),
        settingsCategory -> {
        }));
  }
}
