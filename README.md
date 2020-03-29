# ilovemusic-labymod-addon
Simple LabyMod Addon for playing I Love Music in Minecraft.

## Build Status
![Java CI with Gradle](https://github.com/klauke-enterprises/ilovemusic-labymod-addon/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)

## Usage

### CI builds
The CI build needs a few flags depending on the laby mod version we are building for. The 
settings for 1.12 are used by default.

#### LabyMod 1.8
```text
FORGE_VERSION=1.8.9-11.15.1.1777
FORGE_MAPPINGS=stable_20
```

#### LabyMod 1.12
```text
FORGE_VERSION=1.12.2-14.23.0.2512
FORGE_MAPPINGS=stable_39
```

## Example

## Libraries & Design Decisions
The addon uses JavaZoom's `JavaLayer` for playing music. This was an explicit design decision over 
Javas `JMF`, as `JMF` has a high start latency and would require additional libraries as the 
inbuilt `mpeg` support was removed. Additionally it was even older than `JavaLayer`

### JavaLayer GUI
JavaLayer is a library by JavaZoom for decoding and playing mpeg sound. It has some downsides:
- Volume Management is ugly and has to be done in the gui extension
- It's required to be in classpath as an external library

### DownloadedTexture
I had to write a custom way of integrating textures. It's called a downloaded texture and as the name 
indicated is downloaded via internet. It takes an URL as a resource location and is really easy to 
use:
```java
URL resourceLocation = new URL(myUrl);
DownloadedTexture downloadedTexture = new DownloadedTexture(resourceLocation);
```

### Including BasicPlayer of JavaLayer
As BasicPlayer uses a version of slf4j that is incompatiblt with the one used in the minecraft client
and as BasicPlayer needed some adjustments anyways the source was directly included in `com.ilovemusic.labymod.player`.
