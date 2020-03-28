package com.ilovemusic.labymod.ilovemusic;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public final class StreamSelectionListGui extends GuiListExtended {
  private StreamSelectionListEntry selected;
  private List<StreamSelectionListEntry> entryList;

  public StreamSelectionListGui(
      Minecraft mcIn,
      int widthIn,
      int heightIn,
      int topIn,
      int bottomIn, int slotHeightIn,
      List<StreamSelectionListEntry> inputList
  ) {
    super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
    entryList = inputList;
  }

  public void setSelected(StreamSelectionListEntry selected) {
    this.selected = selected;
  }

  @Override
  protected boolean isSelected(int slotIndex) {
    return entryList.get(slotIndex) == selected;
  }

  public StreamSelectionListEntry selected() {
    return selected;
  }

  @Override
  public IGuiListEntry getListEntry(int index) {
    return entryList.get(index);
  }

  @Override
  protected int getSize() {
    return entryList.size();
  }
}
