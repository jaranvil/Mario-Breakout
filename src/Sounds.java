import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
 

import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {

	private AudioClip sound;
	
	public Sounds(String file)
	{
		sound = Applet.newAudioClip(Sounds.class.getResource(file));
	}
    
	public void play()
	{
		new Thread(){
			public void run() {
				sound.play();
			}
		}.start();
	}

}
