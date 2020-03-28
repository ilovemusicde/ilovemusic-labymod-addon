package com.ilovemusic.labymod.ilovemusic;

import com.ilovemusic.labymod.MusicPlayer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public final class StreamSelectionListEntry implements IGuiListEntry {
  private static final List<String> LOADED_TEXTURES = new ArrayList<>();

  @Inject
  private static MusicPlayer musicPlayer;
  private final StreamSelectionListGui parent;
  private final Minecraft mc = Minecraft.getMinecraft();
  private final ResourceLocation textureKey;
  private final Stream stream;

  public StreamSelectionListEntry(
      StreamSelectionListGui parent,
      Stream stream
  ) {
    this.parent = parent;
    this.stream = stream;
    this.textureKey = new ResourceLocation("ilovemusic",
        "streampicture." + stream.id() + "." + stream.cover().hashCode());
  }

  public void prepareImage() throws MalformedURLException {
    URL resourceLocation = new URL(stream.cover());
    DownloadedTexture downloadedTexture = new DownloadedTexture(resourceLocation);
    mc.getTextureManager().loadTexture(textureKey, downloadedTexture);
  }

  @Override
  public void updatePosition(int slotIndex, int x, int y, float partialTicks) {

  }

  @Override
  public void drawEntry(
      int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX,
      int mouseY, boolean isHovered, float partialTicks
  ) {
    mc.fontRenderer.drawString(stream.name(), x + 32 + 3, y + 1,
        Integer.parseInt(stream.color().substring(1), 16));

    int fontHeigth = mc.fontRenderer.FONT_HEIGHT;
    List<String> titleLines = mc.fontRenderer
        .listFormattedStringToWidth(stream.artist() + " - " + stream.title(), listWidth - 32 - 2);

    int index = 0;
    for (String titelLine : titleLines) {
      mc.fontRenderer.drawString(titelLine, x + 32 + 3, y + 12 + fontHeigth * index++, 0x808080);
    }

    drawStreamIcon(x, y);

    loadImage();
  }

  private void loadImage() {
    if (LOADED_TEXTURES.contains(textureKey.getPath())) {
      return;
    }
    try {
      prepareImage();
      LOADED_TEXTURES.add(textureKey.getPath());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean mousePressed(
      int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX,
      int relativeY
  ) {
    parent.setSelected(this);
    musicPlayer.play(stream.streamUrl());
    return false;
  }

  @Override
  public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX,
      int relativeY
  ) {

  }

  private void drawStreamIcon(int x, int y) {
    mc.getTextureManager().bindTexture(textureKey);
    GlStateManager.enableBlend();
    Gui.drawModalRectWithCustomSizedTexture(x, y, 0f, 0f, 32, 32, 32, 32);
    GlStateManager.disableBlend();
  }
}
