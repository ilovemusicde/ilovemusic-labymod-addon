import java.io.IOException;
import javax.media.CannotRealizeException;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;

public class PlayerTest {
  public static void main(String[] args)
      throws IOException, NoPlayerException {

    System.out.println("LOL!");

    Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
    Format input2 = new AudioFormat(AudioFormat.MPEG);
    Format output = new AudioFormat(AudioFormat.LINEAR);
    PlugInManager.addPlugIn(
        "com.sun.media.codec.audio.mp3.JavaDecoder",
        new Format[]{input1, input2},
        new Format[]{output},
        PlugInManager.CODEC);

    MediaLocator mediaLocator = new MediaLocator("http://streams.ilovemusic.de/iloveradio1.mp3");
    System.out.println(mediaLocator.getProtocol());


    Player player = Manager.createPlayer(mediaLocator);

    player.start();
  }
}
