package com.ilovemusic.labymod;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;

public final class DownloadedTexture extends AbstractTexture {
  private final URL resourceLocation;

  public DownloadedTexture(URL resourceLocation) {
    this.resourceLocation = resourceLocation;
  }

  @Override
  public void loadTexture(IResourceManager resourceManager) throws IOException {
    try (InputStream inputStream = openInputStream(resourceLocation)) {
      BufferedImage image = TextureUtil.readBufferedImage(inputStream);
      int glTextureId = getGlTextureId();
      TextureUtil.uploadTextureImageAllocate(glTextureId, image, false, false);
    }
  }

  private InputStream openInputStream(URL url) throws IOException {
    return url.openStream();
  }
}
