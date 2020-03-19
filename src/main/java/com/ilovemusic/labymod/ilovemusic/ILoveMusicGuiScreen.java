package com.ilovemusic.labymod.ilovemusic;

import java.io.IOException;
import java.net.URL;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class ILoveMusicGuiScreen extends GuiScreen {

  @Override
  public void initGui() {
    super.initGui();
  }

  @Override
  public boolean doesGuiPauseGame() {
    return false;
  }

  @Override
  protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
    URL url = new URL("http://streams.ilovemusic.de/iloveradio1.mp3");
    try {
      AdvancedPlayer advancedPlayer = new AdvancedPlayer(url.openStream());
      advancedPlayer.play();
    } catch (JavaLayerException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
    URL url = new URL("http://streams.ilovemusic.de/iloveradio1.mp3");
    try {
      AdvancedPlayer advancedPlayer = new AdvancedPlayer(url.openStream());
      advancedPlayer.play();
    } catch (JavaLayerException e) {
      e.printStackTrace();
    }
  }
}
