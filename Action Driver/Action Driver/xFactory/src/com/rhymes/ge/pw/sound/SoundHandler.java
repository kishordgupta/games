package com.rhymes.ge.pw.sound;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.rhymes.game.data.AssetConstants;
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
	}
	
	public static enum soundType {
		SOUND_SCORE, 
		SOUND_OBSTACLES,
		SOUND_DIE, 
		SOUND_CAR
	}
	
	public static enum musicType{
		MUSIC_BACK
	}
	
	public void initSound(){
		musicCache.put(musicType.MUSIC_BACK, Gdx.audio.newMusic(Gdx.files.internal(AssetConstants.S_MENU)));
		soundCache.put(soundType.SOUND_SCORE, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.S_SCORE)));
		soundCache.put(soundType.SOUND_DIE, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.S_DIE)));
		soundCache.put(soundType.SOUND_OBSTACLES, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.S_OBSTACLES)));
		soundCache.put(soundType.SOUND_OBSTACLES, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.S_CAR)));
	}
	
	public void playSound(soundType soundtype){
		if(is_sound_active())
		{
//			Helper.println("Playing sound");
//			if(sound != null)
//				sound.stop();
		
			sound = soundCache.get(soundtype);
			sound.play(1f);	
		}
	}
	
	public void playSoundLooping(soundType soundtype){
		if(is_sound_active())
		{
//			Helper.println("Playing sound ");
			sound = soundCache.get(soundtype);
			long id = sound.play(1f);	
			sound.setLooping(id, true);
		}
	}
	
	public void stopSound(soundType soundtype){
		if(is_sound_active())
		{
			sound = soundCache.get(soundtype);
			if(sound != null)
				sound.play(1f);	
		}
	}

	
	public void playMusic(musicType musictype){
		if(is_music_active()){
			if(gamemusic != null)
				gamemusic.stop();
			gamemusic = musicCache.get(musictype);
			gamemusic.play();
			gamemusic.setVolume(0.5f);
			gamemusic.setLooping(true);
		}
	}
	
	public void pauseMusic(){
		gamemusic.pause();
	}
	
	public void stopMusic(){
		gamemusic.stop();
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
		soundCache.clear();
		musicCache.clear();
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
