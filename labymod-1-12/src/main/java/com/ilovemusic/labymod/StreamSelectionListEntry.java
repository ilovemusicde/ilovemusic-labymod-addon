package com.ilovemusic.labymod;

import java.util.List;
import javax.inject.Inject;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;

public final class StreamSelectionListEntry implements IGuiListEntry {
  @Inject
  private static MusicPlayer musicPlayer;
  @Inject
  private static DrawUtils drawUtils;

  private final StreamSelectionListGui parent;
  private final Minecraft mc = Minecraft.getMinecraft();
  private Stream stream;

  public StreamSelectionListEntry(
      StreamSelectionListGui parent,
      Stream stream
  ) {
    this.parent = parent;
    setStream(stream);
  }

  public Stream stream() {
    return stream;
  }

  public void setStream(Stream stream) {
    this.stream = stream;
  }

  @Override
  public void updatePosition(int slotIndex, int x, int y, float partialTicks) {

  }

  @Override
  public void drawEntry(
      int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX,
      int mouseY, boolean isHovered, float partialTicks
  ) {
    drawStreamName(x, y);
    drawStreamArtistAndSong(x, y, listWidth);
    drawStreamIcon(x, y);
  }

  private void drawStreamArtistAndSong(int x, int y, int listWidth) {
    int fontHeigth = mc.fontRenderer.FONT_HEIGHT;
    List<String> titleLines = mc.fontRenderer.listFormattedStringToWidth(
        stream.artist() + " - " + stream.title(),
        listWidth - 32 - 2
    );
    for (int currentIndex = 0; currentIndex < titleLines.size(); currentIndex++) {
      String titelLine = titleLines.get(currentIndex);
      mc.fontRenderer.drawString(
          titelLine,
          x + 32 + 3,
          y + 12 + fontHeigth * currentIndex,
          0x808080
      );
    }
  }

  private void drawStreamName(int x, int y) {
    mc.fontRenderer.drawString(
        stream.name(),
        x + 32 + 3,
        y + 1,
        Integer.parseInt(stream.color().substring(1), 16)
    );
  }

  @Override
  public boolean mousePressed(
      int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX,
      int relativeY
  ) {
    parent.setSelected(this);
    musicPlayer.play(stream);
    return false;
  }

  @Override
  public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX,
      int relativeY
  ) {

  }

  private void drawStreamIcon(int x, int y) {
    drawUtils.drawImageUrl(stream.cover(), x, y, 240, 240, 32, 32);
  }
}
