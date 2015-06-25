package com.rhymes.ge.pw.sound;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.Constants;
import com.rhymes.helpers.Helper;

public class SoundHandler {
	private Sound sound;
	private Music gamemusic;
	private HashMap<soundType, Sound> soundCache = new HashMap<soundType, Sound>();
	private HashMap<musicType, Music> musicCache = new HashMap<musicType, Music>();
	
	private boolean sound_active;
	private boolean music_active;
	
	public boolean is_sound_active(){
		return sound_active;
	}
	
	public void set_sound_active(boolean active){
		this.sound_active = active;
	}
	

	public void set_music_active(boolean music_active) {
		this.music_active = music_active;
		if(!music_active)
			gamemusic.pause();
		else{
			if(gamemusic != null)
				gamemusic.play();
		}
	}

	public boolean is_music_active() {
		return music_active;
	}
	

	public SoundHandler(){
	
		initSound();
		
		set_sound_active(true);
		set_music_active(true);

	}
	
	public static enum soundType {
		SOUND_CLICK, 
		SOUND_HIT,
		SOUND_ENTER_EFFECT,
		SOUND_GOAL_EFFECT,
		SOUND_NO_CLICK
	}
	
	public static enum musicType{
		MUSIC_PLAYING,
		MUSIC_MENU
	}
	
	public void initSound(){
		
		musicCache.put(musicType.MUSIC_PLAYING, Gdx.audio.newMusic(Gdx.files.internal(AssetConstants.MUSIC_PLAYING)));
		musicCache.put(musicType.MUSIC_MENU, Gdx.audio.newMusic(Gdx.files.internal(AssetConstants.MUSIC_MENU)));

		soundCache.put(soundType.SOUND_CLICK, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_CLICK)));
		soundCache.put(soundType.SOUND_HIT, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_HIT)));
		soundCache.put(soundType.SOUND_ENTER_EFFECT, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_ENTRR_EFFECT)));
		soundCache.put(soundType.SOUND_GOAL_EFFECT, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_GOAL_EFFECT)));
		soundCache.put(soundType.SOUND_NO_CLICK, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_NO_CLICK)));

	}
	
	public void playSound(soundType soundtype){
		if(is_sound_active())
		{
			sound = soundCache.get(soundtype);
			sound.play(1f);			
		}
	}
	
	public void playMusic(musicType musictype){
		if(is_music_active()){
			gamemusic = musicCache.get(musictype);
			gamemusic.play();
			gamemusic.setLooping(true);
		}
	}
	
	public void pauseMusic(){
		gamemusic.pause();
	}
	
	public void stopMusic(){
		gamemusic.stop();
	}
	public void stopSound(){
		sound.stop();
	}
	public void stopSound(long soundId){
		sound.stop(soundId);
	}

	public void disposeSound(){
		Helper.println("Disposing Sound");
		for(Sound s:soundCache.values()){
			s.stop();
			s.dispose();
		}
		for(Music m:musicCache.values()){
			m.stop();
			m.dispose();
		}
	}
	

	public void pause()
	{
		set_music_active(false);
		set_sound_active(false);
	}
	
	public void resume()
	{
		set_music_active(true);
		set_sound_active(true);
	}
}
