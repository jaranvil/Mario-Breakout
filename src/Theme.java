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

public class Theme {

	private AudioClip sound;
	
	public Theme()
	{
		sound = Applet.newAudioClip(Sounds.class.getResource("/sounds/theme4.wav"));
	}
    
	public void play()
	{
		new Thread(){
			public void run() {
				sound.loop();
			}
		}.start();
	}

}
