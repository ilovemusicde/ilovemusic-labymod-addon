package com.ilovemusic.labymod;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.ilovemusic.labymod.ilovemusic.ILoveMusicClient;
import com.ilovemusic.labymod.ilovemusic.StreamResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ILoveMusicModule extends AbstractModule {
  private final String iLoveMusicApiBaseUrl;

  private ILoveMusicModule(String iLoveMusicApiBaseUrl) {
    this.iLoveMusicApiBaseUrl = iLoveMusicApiBaseUrl;
  }

  @Override
  protected void configure() {
  }

  @Provides
  @Singleton
  ILoveMusicClient provideILoveMusicClient(Retrofit retrofit) {
    return () -> CompletableFuture.completedFuture(new Gson().fromJson("{\n"
        + "\t\t\"channels\": [\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 1,\n"
        + "\t\t\t\"name\":\"I LOVE RADIO\",\n"
        + "\t\t\t\"artist\":\"DJ KHALED\",\n"
        + "\t\t\t\"title\":\"WILD THOUGHTS (FEAT. RIHANNA & BRYSON TILLER)\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/djkhaled_wildthoughts.jpg\",\n"
        + "\t\t\t\"color\":\"#ffffff\",\n"
        + "            \"fontcolor\":\"#000000\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio1.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 2,\n"
        + "\t\t\t\"name\":\"I LOVE 2 DANCE\",\n"
        + "\t\t\t\"artist\":\"DIPLO & SIDEPIECE\",\n"
        + "\t\t\t\"title\":\"ON MY MIND\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/diplosidepiece_onmymind.jpg\",\n"
        + "\t\t\t\"color\":\"#00d5ff\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio2.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 6,\n"
        + "\t\t\t\"name\":\"I LOVE DEUTSCHRAP BESTE\",\n"
        + "\t\t\t\"artist\":\"PRINZ PI\",\n"
        + "\t\t\t\"title\":\"DU BIST\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/prinzpi_dubist.jpg\",\n"
        + "\t\t\t\"color\":\"#8fcc00\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio6.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 21,\n"
        + "\t\t\t\"name\":\"I LOVE GREATEST HITS\",\n"
        + "\t\t\t\"artist\":\"FELIX JAEHN\",\n"
        + "\t\t\t\"title\":\"COOL\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/felixjaehn_cool.jpg\",\n"
        + "\t\t\t\"color\":\"#db377e\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio16.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 31,\n"
        + "\t\t\t\"name\":\"I LOVE HARDSTYLE\",\n"
        + "\t\t\t\"artist\":\"COONE & SUB ZERO PROJECT\",\n"
        + "\t\t\t\"title\":\"FACE OF A CHAMPION\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/coonesubzeroproject_faceofachampion.jpg\",\n"
        + "\t\t\t\"color\":\"#2d2d38\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio21.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 15,\n"
        + "\t\t\t\"name\":\"I LOVE HITS HISTORY\",\n"
        + "\t\t\t\"artist\":\"SEAN PAUL\",\n"
        + "\t\t\t\"title\":\"SHE DOESN'T MIND\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/seanpaul_shedoesntmind.jpg\",\n"
        + "\t\t\t\"color\":\"#b34f4f\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio12.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 32,\n"
        + "\t\t\t\"name\":\"I LOVE MAINSTAGE\",\n"
        + "\t\t\t\"artist\":\"EL PROFESOR\",\n"
        + "\t\t\t\"title\":\"BELLA CIAO (HUGEL REMIX)\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/elprofesor_bellaciaohugelremix.jpg\",\n"
        + "\t\t\t\"color\":\"#85edc0\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio22.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 5,\n"
        + "\t\t\t\"name\":\"I LOVE MASHUP\",\n"
        + "\t\t\t\"artist\":\"FRANKCARMINE\",\n"
        + "\t\t\t\"title\":\"I WANT YOU\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/frankcarmine_iwantyou.jpg\",\n"
        + "\t\t\t\"color\":\"#9900ff\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio5.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 10,\n"
        + "\t\t\t\"name\":\"I LOVE MUSIC&CHILL\",\n"
        + "\t\t\t\"artist\":\"TREVOR DANIEL\",\n"
        + "\t\t\t\"title\":\"FALLING\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/trevordaniel_falling.jpg\",\n"
        + "\t\t\t\"color\":\"#d086d1\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio10.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 18,\n"
        + "\t\t\t\"name\":\"I LOVE PARTY HARD\",\n"
        + "\t\t\t\"artist\":\"DUCK SAUCE\",\n"
        + "\t\t\t\"title\":\"BARBRA STREISAND\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/ducksauce_barbrastreisand.jpg\",\n"
        + "\t\t\t\"color\":\"#D4AF37\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio14.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 16,\n"
        + "\t\t\t\"name\":\"I LOVE POPSTARS\",\n"
        + "\t\t\t\"artist\":\"LENA & NICO SANTOS\",\n"
        + "\t\t\t\"title\":\"BETTER\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/lenanicosantos_better.jpg\",\n"
        + "\t\t\t\"color\":\"#ff00ea\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio11.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 23,\n"
        + "\t\t\t\"name\":\"I LOVE ROBIN SCHULZ\",\n"
        + "\t\t\t\"artist\":\"ROBIN SCHULZ\",\n"
        + "\t\t\t\"title\":\"SUGAR RADIO\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/robinschulz_sugarradio206.jpg\",\n"
        + "\t\t\t\"color\":\"#00f291\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio23.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 4,\n"
        + "\t\t\t\"name\":\"I LOVE THE BATTLE\",\n"
        + "\t\t\t\"artist\":\"Y2K & BBNO$\",\n"
        + "\t\t\t\"title\":\"LALALA\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/y2k_lalala.jpg\",\n"
        + "\t\t\t\"color\":\"#4a5746\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio3.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 7,\n"
        + "\t\t\t\"name\":\"I LOVE THE BEACH\",\n"
        + "\t\t\t\"artist\":\"GORGON CITY\",\n"
        + "\t\t\t\"title\":\"READY FOR YOUR LOVE (FEAT. MNEK)\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/gorgoncity_readyforyourlove.jpg\",\n"
        + "\t\t\t\"color\":\"#ffc400\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio7.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 30,\n"
        + "\t\t\t\"name\":\"I LOVE THE CLUB\",\n"
        + "\t\t\t\"artist\":\"ANUEL AA & DADDY YANKEE & KAROL G\",\n"
        + "\t\t\t\"title\":\"CHINA (DJ MARIO ANDREETTI CLUB EDIT)\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/anuelaa_china.jpg\",\n"
        + "\t\t\t\"color\":\"#7a4687\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio20.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 3,\n"
        + "\t\t\t\"name\":\"I LOVE THE DJ BY DJ MAG\",\n"
        + "\t\t\t\"artist\":\"ARMIN VAN BUUREN\",\n"
        + "\t\t\t\"title\":\"THERAPY (FEAT. JAMES NEWMAN)\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/arminvanbuuren_therapy.jpg\",\n"
        + "\t\t\t\"color\":\"#ff0000\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio4.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 19,\n"
        + "\t\t\t\"name\":\"I LOVE THE SUN\",\n"
        + "\t\t\t\"artist\":\"JENNIFER LOPEZ\",\n"
        + "\t\t\t\"title\":\"ON THE FLOOR (FEAT. PITBULL)\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/jenniferlopezpitbull_onthefloor.jpg\",\n"
        + "\t\t\t\"color\":\"#edd839\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio15.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 9,\n"
        + "\t\t\t\"name\":\"I LOVE TOP 100 CHARTS\",\n"
        + "\t\t\t\"artist\":\"RIN & BAUSA\",\n"
        + "\t\t\t\"title\":\"KEINE LIEBE\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/rinbausa_keineliebe.jpg\",\n"
        + "\t\t\t\"color\":\"#ff0000\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio9.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 17,\n"
        + "\t\t\t\"name\":\"I LOVE US ONLY RAP RADIO\",\n"
        + "\t\t\t\"artist\":\"FRENCH MONTANA\",\n"
        + "\t\t\t\"title\":\"UNFORGETTABLE\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/frenchmontana_unforgettable.jpg\",\n"
        + "\t\t\t\"color\":\"#343857\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio13.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 8,\n"
        + "\t\t\t\"name\":\"I LOVE X-MAS\",\n"
        + "\t\t\t\"artist\":\"MACKLEMORE\",\n"
        + "\t\t\t\"title\":\"IT'S CHRISTMAS TIME (FEAT. DAN CAPLEN)\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/macklemore_itschristmastime.jpg\",\n"
        + "\t\t\t\"color\":\"#800000\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio8.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 109,\n"
        + "\t\t\t\"name\":\"TOP 100 HITS 2020\",\n"
        + "\t\t\t\"artist\":\"TOPIC\",\n"
        + "\t\t\t\"title\":\"BREAKING ME (FEAT. A7S)\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/topic_breakingme.jpg\",\n"
        + "\t\t\t\"color\":\"#ff0000\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio109.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 103,\n"
        + "\t\t\t\"name\":\"TOP 40 DANCE & DJS\",\n"
        + "\t\t\t\"artist\":\"DOM DOLLA\",\n"
        + "\t\t\t\"title\":\"SAN FRANDISCO\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/domdolla_sanfrandisco.jpg\",\n"
        + "\t\t\t\"color\":\"#ff0000\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio103.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 105,\n"
        + "\t\t\t\"name\":\"TOP 40 DEUTSCHPOP\",\n"
        + "\t\t\t\"artist\":\"LEA\",\n"
        + "\t\t\t\"title\":\"110 (PROLOG)\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/lea_110prolog.jpg\",\n"
        + "\t\t\t\"color\":\"#ff0000\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio105.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 104,\n"
        + "\t\t\t\"name\":\"TOP 40 DEUTSCHRAP\",\n"
        + "\t\t\t\"artist\":\"ELIAS\",\n"
        + "\t\t\t\"title\":\"REVENGE\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/elias_revenge.jpg\",\n"
        + "\t\t\t\"color\":\"#ff0000\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio104.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 108,\n"
        + "\t\t\t\"name\":\"TOP 40 HIP HOP\",\n"
        + "\t\t\t\"artist\":\"BLACK EYED PEAS & J BALVIN\",\n"
        + "\t\t\t\"title\":\"RITMO\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/blackeyedpeasjbalvin_ritmo.jpg\",\n"
        + "\t\t\t\"color\":\"#ff0000\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio108.mp3\"\n"
        + "\t\t},\n"
        + "\t\t{\n"
        + "\t\t\t\"channel_id\": 106,\n"
        + "\t\t\t\"name\":\"TOP 40 HIP HOP US\",\n"
        + "\t\t\t\"artist\":\"DABABY\",\n"
        + "\t\t\t\"title\":\"BOP\",\n"
        + "\t\t\t\"cover\":\"https://www.ilovemusic.de/fileadmin/coverbilder/dababy_bop.jpg\",\n"
        + "\t\t\t\"color\":\"#ff0000\",\n"
        + "            \"fontcolor\":\"#ffffff\",\n"
        + "\t\t\t\"stream_url\":\"https://streams.ilovemusic.de/iloveradio106.mp3\"\n"
        + "\t\t}\n"
        + "\t]\n"
        + "}", new TypeLiteral<StreamResponse>(){}.getType()));
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(OkHttpClient httpClient, GsonConverterFactory converterFactory) {
    return new Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(converterFactory)
        .baseUrl(iLoveMusicApiBaseUrl)
        .build();
  }

  @Provides
  @Singleton
  GsonConverterFactory provideGsonConverterFactory(Gson gson) {
    return GsonConverterFactory.create(gson);
  }

  @Provides
  @Singleton
  Gson provideGson() {
    return new GsonBuilder()
        .setPrettyPrinting()
        .setLenient()
        .create();
  }

  @Provides
  @Singleton
  OkHttpClient provideHttpClient() {
    return new OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .build();
  }

  public static ILoveMusicModule create(String iLoveMusicApiBaseUrl) {
    Preconditions.checkNotNull(iLoveMusicApiBaseUrl);
    return new ILoveMusicModule(iLoveMusicApiBaseUrl);
  }
}
