package com.totuiral.main;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Manages the Audio of this <br>
 * project using the <code><b><u>lwgl</u></code></b> JAR lib
 */
public class AudioPlayer {
	/**
	 * A Hash Maps represing the all of the sounds {@link Sound} using
	 * {@link String}
	 */
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	/**
	 * A Hash Maps represing the all of the musics {@link Music} using
	 * {@link String}
	 */
	public static Map<String, Music> musicMap = new HashMap<String, Music>();

	/**
	 * Loads all the audio files (supported formats are .ogg .wav) inside the "res"
	 * folder inside the project itself.
	 * 
	 * @throws org.newdawn.slick.SlickException
	 * @throws java.io.FileNotFoundException
	 */
	public static void load() {

		try {
			soundMap.put("Click", new Sound("res/mixkit-select-click-1109.ogg"));
			soundMap.put("Music", new Sound("res/MEGALOVANIA (Camellia Remix)_48k.ogg"));
			soundMap.put("Done", new Sound("res/Done.ogg"));
			soundMap.put("Hit", new Sound("res/Hit.ogg"));
			soundMap.put("SUSSY", new Sound("res/SUS.ogg"));
			musicMap.put("SUSSY", new Music("res/SUS.ogg"));
			musicMap.put("Pause", new Music("res/PauseMusic.ogg"));

			musicMap.put("Music2", new Music("res/MEGALOVANIA (Camellia Remix)_48k.ogg"));
			musicMap.put("Menu", new Music("res/Menues.ogg"));

			soundMap.put("money", new Sound("res/silence.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Takes a filename for an audio file in "res" folder and returns the Music
	 * object which allows to play it, pause it etc.
	 * 
	 * @param key the filename itself
	 * @return Returns a <code><b>Music</b><code> object representing the audio file
	 *         "key".
	 */
	public static Music getMusic(String key) {
		if (musicMap.get(key) != null)
			return musicMap.get(key);
		return null;
	}

	/**
	 * Takes a filename for an audio file in "res" folder and returns the Sound
	 * object which allows to play it, pause it etc. given it's loaded and found in
	 * {@link #soundMap}
	 * 
	 * @param key the filename itself
	 * @return Returns a <code><b>Sound</b></code> object representing the audio
	 *         file "key".
	 */
	public static Sound getSound(String key) {
		if (soundMap.get(key) != null)
			return soundMap.get(key);
		return null;
	}

}
