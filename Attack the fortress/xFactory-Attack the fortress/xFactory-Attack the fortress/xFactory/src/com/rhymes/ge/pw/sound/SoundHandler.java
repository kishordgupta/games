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
	}

	public boolean is_music_active() {
		return music_active;
	}
	

	public SoundHandler(){
	
		initSound();
		
//		set_sound_active(true);
//		set_music_active(true);
	}
	
	public static enum soundType {
		SOUND_BOX, 
		SOUND_LOSE,
		SOUND_WIN,
		SOUND_TOWER_LAUNCH,
		SOUND_ENEMY_LAUNCH,
		SOUND_EXPLODE,
		SOUND_CRY_1,
		SOUND_CRY_2,
		SOUND_CRY_3,
		SOUND_CRY_4,
		SOUND_CRY_5,
		SOUND_CRY_6,
		SOUND_CRY_7,
		SOUND_CRY_8,
		SOUND_CRY_9,
		SOUND_CRY_0
	}
	
	public static enum musicType{
		MUSIC_BGM,
		MUSIC_LEVEL
	}
	
	public void initSound(){
		
		musicCache.put(musicType.MUSIC_BGM, Gdx.audio.newMusic(Gdx.files.internal(AssetConstants.MUSIC_BGM)));
		musicCache.put(musicType.MUSIC_LEVEL, Gdx.audio.newMusic(Gdx.files.internal(AssetConstants.MUSIC_LEVEL)));

		soundCache.put(soundType.SOUND_BOX, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_BOX)));
		soundCache.put(soundType.SOUND_WIN, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_WIN)));
		soundCache.put(soundType.SOUND_LOSE, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_LOSE)));
		soundCache.put(soundType.SOUND_TOWER_LAUNCH, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_TOWER_LAUNCH)));
		soundCache.put(soundType.SOUND_ENEMY_LAUNCH, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_ENEMY_LAUNCH)));
		soundCache.put(soundType.SOUND_EXPLODE, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_EXPLODE)));
		soundCache.put(soundType.SOUND_CRY_0, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_CRY_0)));
		soundCache.put(soundType.SOUND_CRY_1, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_CRY_1)));
		soundCache.put(soundType.SOUND_CRY_2, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_CRY_2)));
		soundCache.put(soundType.SOUND_CRY_3, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_CRY_3)));
		soundCache.put(soundType.SOUND_CRY_4, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_CRY_4)));
		soundCache.put(soundType.SOUND_CRY_5, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_CRY_5)));
		soundCache.put(soundType.SOUND_CRY_6, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_CRY_6)));
		soundCache.put(soundType.SOUND_CRY_7, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_CRY_7)));
		soundCache.put(soundType.SOUND_CRY_8, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_CRY_8)));
		soundCache.put(soundType.SOUND_CRY_9, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_CRY_9)));
	}
	
	public void playSound(soundType soundtype){
		if(is_sound_active())
		{
			Helper.println("Playing sound");
			if(sound != null)
				sound.stop();
		
			sound = soundCache.get(soundtype);
			sound.play(1f);			
		}
	}
	
	public void playMusic(musicType musictype){
		if(is_music_active()){
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
