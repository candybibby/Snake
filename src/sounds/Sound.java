package sounds;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;

/**
 * Created by Anni on 21.5.2014.
 */
public class Sound {

    private AudioClip song;
    private URL songPath;

    String path;
    Path path2;

    /*public Sound(String filename) {
        sounds / ButtonForward.wav

        path = getClass().getResource("").getPath();
        songPath = new URL(path, "ButtonForward.wav");
        song = Applet.newAudioClip()

        System.out.println(path);

        File relative = new File("html/javafaq/index.html");

        System.out.println("relative: ");
        System.out.println(relative.getName());
        System.out.println(relative.getPath());
        
        /*try{
            songPath = new URL(getCode)
        }*/
    }


