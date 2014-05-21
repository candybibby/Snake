package sounds;


import java.io.*;

import sun.audio.*;

/**
 * Created by Anni on 21.5.2014.
 */
public class SoundMaker {


    private String loop0 = "src/sounds/MainMenuMusic.wav";
    private String loop1 = "src/sounds/gameMusicLoop1.wav";
    private String loop2 = "src/sounds/gameMusicLoop2.wav";
    private String loop3 = "src/sounds/gameMusicLoop3.wav";
    private String loop4 = "src/sounds/gameMusicLoop4.wav";
    private String buttonForward = "src/sounds/buttonClickForward2.wav";
    private String buttonBack = "src/sounds/buttonClickBack2.wav";
    private static String eatFood = "src/sounds/snakeFoodEatNoise.wav";
    private String gameOver = "src/sounds/snakeGameOverMusic.wav";
    private String highScore = "src/sounds/snakeHighScoreTune.wav";

    private String[] music;

    private boolean musicIsOn = true;
    private boolean thisSongPlaying = true;
    public int numberOfSong = 0;

    Thread musicThread;
    AudioStream audioStream;


    public SoundMaker() {

    }




    public void loopSound(int fileNumber) {
        thisSongPlaying = true;
        numberOfSong = fileNumber;

        final String  currentSong = music[numberOfSong];
        musicThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {


                    InputStream in = new FileInputStream(currentSong);
                    audioStream = new AudioStream(in);
                    AudioPlayer.player.start(audioStream);

                    while(thisSongPlaying){

                        if (audioStream.available() <= 0) {
                            in = new FileInputStream(currentSong);
                            audioStream = new AudioStream(in);
                            AudioPlayer.player.start(audioStream);
                        }}

                } catch (IOException e)

                {
                    e.printStackTrace();
                }
            }
        });

        musicThread.start();


    }


public void stopMusic(){
    //musicThread.interrupt();
    thisSongPlaying = false;
    AudioPlayer.player.stop(audioStream);

}




    public void setupMusic(){
        music = new String[]{loop0, loop1, loop2, loop3, loop4};
    }

    public void setupsoundFX(){

    }

    public void clickButton(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    InputStream in = new FileInputStream(buttonForward);
                    AudioStream effect= new AudioStream(in);
                    AudioPlayer.player.start(effect);}
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public void backButton(){

        try{
            InputStream in = new FileInputStream(buttonBack);
            AudioStream effect= new AudioStream(in);
            AudioPlayer.player.start(effect);}
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void eatFood(){

        try{
            InputStream in = new FileInputStream(eatFood);
            AudioStream effect= new AudioStream(in);
            AudioPlayer.player.start(effect);}
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
