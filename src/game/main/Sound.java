package game.main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL sounlURL[] = new URL[30];
	public Sound() {
		sounlURL[0] = getClass().getResource("/resources/sound/BlueBoyAdventure.wav");
		sounlURL[1] = getClass().getResource("/resources/sound/coin.wav");
		sounlURL[2] = getClass().getResource("/resources/sound/powerup.wav");
		sounlURL[3] = getClass().getResource("/resources/sound/unlock.wav");
		sounlURL[4] = getClass().getResource("/resources/sound/fanfare.wav");
		
		sounlURL[5] = getClass().getResource("/resources/sound/hitmonster.wav");
		sounlURL[6] = getClass().getResource("/resources/sound/receiveddamage.wav");
		sounlURL[7] = getClass().getResource("/resources/sound/swingweaspon.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(sounlURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
}























