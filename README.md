# ilovemusic-labymod-addon
Simple LabyMod Addon for playing I Love Music in Minecraft.

## Usage

## Example

## Libraries & Design Decisions
The addon uses JavaZoom's `JavaLayer` for playing music. This was an explicit design decision over 
Javas `JMF`, as `JMF` has a high start latency and would require additional libraries as the 
inbuilt `mpeg` support was removed. Additionally it was even older than `JavaLayer`

### JavaLayer
JavaLayer is a library by JavaZoom for decoding and playing mpeg sound. It has some downsides:
- Volume Management is ugly and has to be done via reflections (wtf?)
- It's required to be in classpath as an external library

### 
