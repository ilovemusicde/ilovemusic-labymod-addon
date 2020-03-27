package com.ilovemusic.labymod.ilovemusic;

import com.ilovemusic.labymod.MusicPlayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.inject.Inject;
import net.labymod.gui.elements.Tabs;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public final class ILoveMusicGuiScreen extends GuiScreen {
  @Inject
  private static MusicPlayer musicPlayer;
  @Inject
  private static DrawUtils drawUtils;
  @Inject
  private static StreamRepository streamRepository;

  private Stream selectedStream;

  private StreamSelectionListGui streamSelection;

  @Override
  public void initGui() {
    super.initGui();
    List<StreamSelectionListEntry> streamSelectionListEntries = new ArrayList<>();
    this.streamSelection = new StreamSelectionListGui(mc, width, height, 32, height - 64, 36, streamSelectionListEntries);
    streamSelectionListEntries.addAll(createStreamEntries());

    Tabs.initGuiScreen(this.buttonList, this);

    GuiButton startButton = new GuiButton(0xC0DE_1, this.width / 2 - 74, this.height - 28, 70, 20,
        "Start");
    startButton.enabled = false;
    addButton(startButton);
    addButton(new GuiButton(0xC0DE_2, this.width / 2 + 4, this.height - 28, 70, 20,"Stop"));
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
          musicPlayer.play(selectedStream.streamUrl());
          break;
        case 0xC0DE2: //stopped
          musicPlayer.stop();
          break;
      }
  }
}
