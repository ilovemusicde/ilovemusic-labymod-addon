package com.ilovemusic.labymod;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.List;
import javax.inject.Inject;
import net.labymod.api.LabyModAPI;
import net.labymod.api.LabyModAddon;
import net.labymod.gui.elements.Tabs;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.gui.GuiScreen;

public final class ILoveMusicAddon extends LabyModAddon {
  private static final String TAB_TITLE = "ILoveMusic";
  private static final Class<? extends GuiScreen> GUI_IMPLEMENTATION = ILoveMusicGuiScreen.class;
  private static final String DEFAULT_BASE_URL = "https://api.ilovemusic.team/traffic/";
  @Inject
  private StreamModule streamModule;
  @Inject
  private LabyModAPI labyModApi;

  @Override
  public void onEnable() {
    Injector injector = Guice.createInjector(ILoveMusicModule.create(DEFAULT_BASE_URL));
    injector.injectMembers(this);
    registerTab();
    registerModule();
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
  }

  @Override
  protected void fillSettings(List<SettingsElement> settings) {
  }
}
