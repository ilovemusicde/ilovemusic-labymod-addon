package com.ilovemusic.labymod.ilovemusic;

import net.labymod.ingamegui.Module;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;

public class ILoveMusicGuiModule extends Module {
  @Override
  public IconData getIconData() {
    return new IconData(Material.NOTE_BLOCK);
  }

  @Override
  public double getHeight() {
    return 100;
  }

  @Override
  public double getWidth() {
    return 0;
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
    return "Desc";
  }

  @Override
  public int getSortingId() {
    return 10;
  }
}
