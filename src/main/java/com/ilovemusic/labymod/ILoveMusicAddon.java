package com.ilovemusic.labymod;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ilovemusic.labymod.ilovemusic.ILoveMusicGuiModule;
import com.ilovemusic.labymod.ilovemusic.ILoveMusicGuiScreen;
import com.ilovemusic.labymod.ilovemusic.Stream;
import com.ilovemusic.labymod.ilovemusic.StreamRepository;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import javax.inject.Inject;
import net.labymod.api.LabyModAddon;
import net.labymod.gui.elements.Tabs;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.settings.elements.SliderElement;
import net.labymod.utils.Material;

public final class ILoveMusicAddon extends LabyModAddon {
  private static final Logger log = Logger.getLogger(ILoveMusicAddon.class.getSimpleName());
  private static final String DEFAULT_BASE_URL = "https://www.ilovemusic.de/api/";
  @Inject
  private StreamRepository streamRepository;
  @Inject
  private ILoveMusicGuiModule iLoveMusicGuiModule;

  @Override
  public void onEnable() {
    log.info("Initializing i love music addon");
    Injector injector = Guice.createInjector(ILoveMusicModule.create(DEFAULT_BASE_URL));
    injector.injectMembers(this);

    this.getApi().registerModule(iLoveMusicGuiModule);
    Tabs.getTabUpdateListener().add(tabs -> {
      tabs.put("ILoveMusic <33", new Class[]{ILoveMusicGuiScreen.class});
    });
  }

  @Override
  public void loadConfig() {

  }

  @Override
  protected void fillSettings(List<SettingsElement> settings) {
    log.info("Loading setting elements");

    settings.add(new HeaderElement("Einstellungen"));
    settings.add(new ControlElement("Einstellung Demo", new IconData()));

    try {
      List<Stream> streams = streamRepository.findAll().get();

      settings.add(new HeaderElement("Streams"));
      for (Stream stream : streams) {
        settings.add(new ControlElement(stream.name(), new IconData(stream.cover())));
      }

    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
}
