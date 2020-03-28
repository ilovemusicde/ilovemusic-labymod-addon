package com.ilovemusic.labymod.ilovemusic;

import com.ilovemusic.labymod.MusicPlayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.swing.JOptionPane;
import net.labymod.gui.elements.Tabs;
import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiPageButtonList.GuiResponder;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ILoveMusicGuiScreen extends GuiScreen {
  private final Logger log = LogManager.getLogger("ILoveMusic");
  @Inject
  private static MusicPlayer musicPlayer;
  @Inject
  private static DrawUtils drawUtils;
  @Inject
  private static StreamRepository streamRepository;

  private GuiSlider VOLUME_SLIDER;
  private GuiButton START_BUTTON;
  private GuiButton STOP_BUTTON;

  private StreamSelectionListGui streamSelection;

  @Override
  public void initGui() {
    super.initGui();
    List<StreamSelectionListEntry> streamSelectionListEntries = new ArrayList<>();
    this.streamSelection = new StreamSelectionListGui(mc, width, height, 32, height - 64, 36, streamSelectionListEntries);
    streamSelectionListEntries.addAll(createStreamEntries());

    Tabs.initGuiScreen(this.buttonList, this);

    VOLUME_SLIDER = new GuiSlider(new GuiResponder() {
      @Override
      public void setEntryValue(int id, boolean value) {
      }

      @Override
      public void setEntryValue(int id, float value) {
        log.info((value / 100) + "");
        musicPlayer.setVolume((value / 100));
      }

      @Override
      public void setEntryValue(int id, String value) {
      }
    }, 0xC0DE_0, this.width / 2 - 74, this.height - 52, "Lautstärke", 0, 100, musicPlayer.getVolume(),
        (id, name, value) -> String.valueOf((int) value));

    START_BUTTON = new GuiButton(0xC0DE_1, this.width / 2 - 74, this.height - 28, 70, 20, "Start");
    STOP_BUTTON = new GuiButton(0xC0DE_2, this.width / 2 + 4, this.height - 28, 70, 20,"Stop");

    START_BUTTON.enabled = !musicPlayer.isPlaying();
    STOP_BUTTON.enabled = musicPlayer.isPlaying();

    addButton(START_BUTTON);
    addButton(STOP_BUTTON);
    addButton(VOLUME_SLIDER);
  }

  private List<StreamSelectionListEntry> createStreamEntries() {
    try {
      return streamRepository.findAll().get().stream()
          .map(stream -> new StreamSelectionListEntry(streamSelection, stream))
          .collect(Collectors.toList());
    } catch (InterruptedException | ExecutionException e) {
      return new ArrayList<>();
    }
  }

  @Override
  public void handleMouseInput() throws IOException {
    super.handleMouseInput();
    streamSelection.handleMouseInput();
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    drawDefaultBackground();
    streamSelection.drawScreen(mouseX, mouseY, partialTicks);
    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  @Override
  protected void mouseClicked(
      int mouseX,
      int mouseY,
      int mouseButton
  ) throws IOException {
    super.mouseClicked(mouseX, mouseY, mouseButton);
    streamSelection.mouseClicked(mouseX, mouseY, mouseButton);
  }

  @Override
  protected void mouseClickMove(
      int mouseX,
      int mouseY,
      int clickedMouseButton,
      long timeSinceLastClick
  ) {
    super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
  }

  @Override
  protected void mouseReleased(int mouseX, int mouseY, int state) {
    super.mouseReleased(mouseX, mouseY, state);
    streamSelection.mouseReleased(mouseX, mouseY, state);
  }

  @Override
  protected void keyTyped(char typedChar, int keyCode) throws IOException {
    super.keyTyped(typedChar, keyCode);
  }

  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
      super.actionPerformed(button);
      Tabs.actionPerformedButton(button);

      int buttonId = button.id;

      if(!button.enabled) {
        return;
      }

      switch (buttonId) {
        case 0xC0DE1: //started
          musicPlayer.play();
          break;
        case 0xC0DE2: //stopped
          musicPlayer.stop();
          break;
      }

    START_BUTTON.enabled = !musicPlayer.isPlaying();
    STOP_BUTTON.enabled = musicPlayer.isPlaying();
  }
}
