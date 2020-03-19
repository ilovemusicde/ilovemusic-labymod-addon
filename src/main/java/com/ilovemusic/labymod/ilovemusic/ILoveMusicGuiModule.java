package com.ilovemusic.labymod.ilovemusic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
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
  public void onMouseClick(int mouseX, int mouseY, int mouseButton) {
    URL url = null;
    try {
      url = new URL("http://streams.ilovemusic.de/iloveradio1.mp3");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    try {
      AdvancedPlayer advancedPlayer = new AdvancedPlayer(url.openStream());
      advancedPlayer.play();
    } catch (JavaLayerException | IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getSettingName() {
    return "ILoveMusic <3";
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
