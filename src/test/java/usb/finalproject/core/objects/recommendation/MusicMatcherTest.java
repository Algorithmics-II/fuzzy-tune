package usb.finalproject.core.objects.recommendation;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import usb.finalproject.core.objects.Music;
import usb.finalproject.core.objects.MusicList;
import usb.finalproject.core.objects.User;
import usb.finalproject.core.objects.UserList;
import usb.finalproject.core.recommendation.fuzzy.matching.MusicMatcher;
import usb.finalproject.fuzzy.matcher.component.MatchService;
import usb.finalproject.fuzzy.matcher.domain.Document;
import usb.finalproject.utils.DataReader;

public class MusicMatcherTest {
  private MusicMatcher musicMatcher;
  private MatchService matchService;

  @Test
  public void recommendationOnPreferencesUser() throws Exception {
    matchService = new MatchService();
    musicMatcher = new MusicMatcher(matchService);

    UserList userList = DataReader.readUsersFromJson("src/main/resources/users.json");
    List<User> users = userList.getUsers();

    User primerUsuario = users.get(10);

    MusicList musicList = DataReader.readMusicListFromJson("src/main/resources/tracks.json");
    List<Music> tracks = musicList.getTracks();

    List<Document> recommendedMusic = musicMatcher.getTopMatchesMusic(primerUsuario, tracks);
    musicMatcher.printRecommendedMusic(recommendedMusic, tracks);
    ;
  }

  private User getUser1() {
    return new User(1,
        "Emily",
        28,
        Arrays.asList("Pop", "R&B","Electro"),
        Arrays.asList("Ed Sheeran", "Rihanna", "The Chainsmokers"),
        Arrays.asList("Eastside (with Halsey & Khalid)", "Something About The Way You Look Tonight - Edit Version",
            "Juke Box Hero"),
        Arrays.asList("You","some"));
  }

  private Music getMusic1() {
    return new Music(1,
        "Something About The Way You Look Tonight - Edit Version",
        "Elton John",
        "Candle In The Wind 1997 / Something About ...",
        "Elton John",
        "1997-01-01",
        "",
        "glam rock,mellow gold,piano rock,rock",
        1,
        3,
        216270,
        false,
        0,
        0.617,
        0.872,
        0.048,
        0.0158,
        0.112,
        0.408,
        0.504,
        "https://p.scdn.co/mp3-preview/3e5a8a62babcf10b5b4d11d1485f4ed452dbdc7b?cid=9950ac751e34487dbbe027c4fd7f8e99");
  }

  private Music getMusic2() {
    return new Music(2,
        "Somebody That I Used To Know",
        "Gotye, Kimbra",
        "Making Mirrors",
        "Gotye",
        "1997-01-01",
        "",
        "glam rock,mellow gold,piano rock,rock",
        1,
        3,
        216270,
        false,
        0,
        0.617,
        0.872,
        0.048,
        0.0158,
        0.112,
        0.408,
        0.504,
        "https://p.scdn.co/mp3-preview/8721423c664c8ec64df22cd2a85711437431a98c?cid=9950ac751e34487dbbe027c4fd7f8e99");
  }

  private Music getMusic3() {
    return new Music(3,
        "When Something Is Wrong With My Baby",
        "Jimmy Barnes",
        "Soul Deep",
        "Jimmy Barnes",
        "1997-01-01",
        "",
        "glam rock,mellow gold,piano rock,rock",
        1,
        3,
        216270,
        false,
        0,
        0.617,
        0.872,
        0.048,
        0.0158,
        0.112,
        0.408,
        0.504,
        "");
  }

  private Music getMusic4() {
    return new Music(4,
        "Some Type of Love",
        "Charlie Puth",
        "Some Type of Love",
        "Charlie Puth",
        "1997-01-01",
        "",
        "pop,viral pop",
        1,
        3,
        216270,
        false,
        0,
        0.617,
        0.872,
        0.048,
        0.0158,
        0.112,
        0.408,
        0.504,
        "https://p.scdn.co/mp3-preview/f4e108585faeef445a0135f1093dfc40ba0a0c3f?cid=9950ac751e34487dbbe027c4fd7f8e99");
  }

  private Music getMusic5() {
    return new Music(5,
        "Someday (feat. Meghan Trainor)",
        "Michael Bublé, Meghan Trainor",
        "Nobody but Me (Deluxe)",
        "Michael Bublé",
        "1997-01-01",
        "",
        "adult standards,canadian pop,jazz pop,lounge,hip pop,pop",
        1,
        3,
        216270,
        false,
        0,
        0.617,
        0.872,
        0.048,
        0.0158,
        0.112,
        0.408,
        0.504,
        "https://p.scdn.co/mp3-preview/92aa36d8fb56bc1483b308b62145ee4f4c90b84c?cid=9950ac751e34487dbbe027c4fd7f8e99");
  }

  private Music getMusic6() {
    return new Music(6,
        "Something's Got A Hold On Me",
        "Jessica Mauboy",
        "Something's Got A Hold On Me",
        "Jessica Mauboy",
        "1997-01-01",
        "",
        "australian indigenous,australian pop,australian talent show",
        1,
        3,
        216270,
        false,
        0,
        0.617,
        0.872,
        0.048,
        0.0158,
        0.112,
        0.408,
        0.504,
        "https://p.scdn.co/mp3-preview/35ded52807e14e69248898e3c7efd1a5d7a93574?cid=9950ac751e34487dbbe027c4fd7f8e99");
  }

  private Music getMusic7() {
    return new Music(7,
        "Hurt Somebody (With Julia Michaels)",
        "Noah Kahan, Julia Michaels",
        "Hurt Somebody",
        "Noah Kahan",
        "1997-01-01",
        "",
        "pov: indie,singer-songwriter pop,pop",
        1,
        3,
        216270,
        false,
        0,
        0.617,
        0.872,
        0.048,
        0.0158,
        0.112,
        0.408,
        0.504,
        "https://p.scdn.co/mp3-preview/3d575c16c4dd49b0984436e2e86990f506407a8c?cid=9950ac751e34487dbbe027c4fd7f8e99");
  }

  private Music getMusic8() {
    return new Music(8,
        "Say Something (feat. Chris Stapleton)",
        "Justin Timberlake, Chris Stapleton",
        "Man of the Woods",
        "Justin Timberlake",
        "1997-01-01",
        "",
        "dance pop,pop,contemporary country,outlaw country",
        1,
        3,
        216270,
        false,
        0,
        0.617,
        0.872,
        0.048,
        0.0158,
        0.112,
        0.408,
        0.504,
        "https://p.scdn.co/mp3-preview/32c1c015fdc14a2694a580b303853cfa2be6ba4f?cid=9950ac751e34487dbbe027c4fd7f8e99");
  }

  private Music getMusic9() {
    return new Music(9,
        "Some Girls",
        "Racey",
        "The Best Of Racey",
        "Racey",
        "1997-01-01",
        "",
        "australian rock,glam rock",
        1,
        3,
        216270,
        false,
        0,
        0.617,
        0.872,
        0.048,
        0.0158,
        0.112,
        0.408,
        0.504,
        "https://p.scdn.co/mp3-preview/a63627e0035320626777b13fc118ffd5572c4463?cid=9950ac751e34487dbbe027c4fd7f8e99");
  }

  private Music getMusic10() {
    return new Music(10,
        "Somebody That I Used To Know",
        "Gotye, Kimbra",
        "Making Mirrors",
        "Gotye",
        "1997-01-01",
        "",
        "australian pop,bergen indie,electropop,nz pop",
        1,
        3,
        216270,
        false,
        0,
        0.617,
        0.872,
        0.048,
        0.0158,
        0.112,
        0.408,
        0.504,
        "https://p.scdn.co/mp3-preview/8721423c664c8ec64df22cd2a85711437431a98c?cid=9950ac751e34487dbbe027c4fd7f8e99");
  }
}