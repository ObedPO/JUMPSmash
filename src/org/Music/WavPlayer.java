package org.Music;


import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;


// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class WavPlayer {

    private String wavMusicFile;
    private Clip clip;

    /* Static method call to play simple .wav music background
     *
     */
    public static void play(String w) {

        try {
            // Open an audio input stream.
            //URL url = this.getClass().getClassLoader().getResource(wavMusicFile);
            URL url = new File(w).toURI().toURL();

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip c = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            c.open(audioIn);
            c.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /* WavPlayer constructor for object-based music
     *
     */
    public WavPlayer(String track) {
        this.wavMusicFile = track;
        startSound();

    }


    /*
     * Starts (or restarts) playing current sound
     */
    public void startSound() {
        try {
            // Open an audio input stream.
            URL url = this.getClass().getResource("/"+wavMusicFile);
            //System.out.println("url: " + url);

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    /*
     *  Pauses current sound
     */
    public void pauseSound() {
        clip.stop();
    }


    /*
     * Continues playing sound if paused
     */
    public void continueSound() {
        clip.start();

    }

    /*
     * checks if song has ended and starts it again
     */
    public void keepLooping() {
        if(!clip.isActive()) {
            startSound();
        }
    }

}
