package com.ilovemusic.labymod;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.List;
import javax.inject.Inject;
import net.labymod.api.LabyModAPI;
import net.labymod.api.LabyModAddon;
import net.labymod.gui.elements.Tabs;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.settings.elements.KeyElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.lwjgl.input.Keyboard;

public final class ILoveMusicAddon extends LabyModAddon {
  private static final String TAB_TITLE = "ILoveMusic";
  private static final Class<? extends GuiScreen> GUI_IMPLEMENTATION = ILoveMusicGuiScreen.class;
  private static final String DEFAULT_BASE_URL = "https://api.ilovemusic.team/traffic/";
  @Inject
  private StreamModule streamModule;
  @Inject
  private LabyModAPI labyModApi;
  @Inject
  private MusicPlayer musicPlayer;

  private int toggleMusicKey;

  @Override
  public void onEnable() {
    Injector injector = Guice.createInjector(ILoveMusicModule.create(DEFAULT_BASE_URL));
    injector.injectMembers(this);
    registerTab();
    registerModule();
    registerForgeListeners();
  }

  private void registerForgeListeners() {
    labyModApi.registerForgeListener(this);
  }

  private void registerModule() {
    labyModApi.registerModule(streamModule);
  }

  private void registerTab() {
    Tabs.getTabUpdateListener().add(
        tabs -> tabs.put(TAB_TITLE, new Class[]{GUI_IMPLEMENTATION})
    );
  }

  @Override
  public void loadConfig() {
    this.toggleMusicKey = (this.getConfig().has("toggleMusicKey") ? this.getConfig()
        .get("toggleMusicKey").getAsInt() : -1);
  }

  @Override
  protected void fillSettings(List<SettingsElement> settings) {
    KeyElement toggleMusicKeyElement = new KeyElement(
        "Start/Stop Taste",
        this,
        new IconData(Material.LEVER),
        "toggleMusicKey",
        toggleMusicKey
    );
    settings.add(toggleMusicKeyElement);
  }

  private long toggleMusicCooldown = 20;

  @SubscribeEvent
  public void onTick(ClientTickEvent event) {
    toggleMusicCooldown--;
    if (toggleMusicKey != -1 && Keyboard.isKeyDown(toggleMusicKey) && toggleMusicCooldown < 10) {
      toggleMusic();
      toggleMusicCooldown = 20;
    }
  }

  private void toggleMusic() {
    musicPlayer.toggle();
  }
}
