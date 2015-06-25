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
		
		set_arnold_active(true);
		set_menu_active(true);
	}
	
	public static enum soundType {
//		SOUND_TOUCH, 
//		SOUND_DESTROY,
//		SOUND_TRANSFORM,
//		SOUND_TRANSPORT
		
//		SOUND_ARNOLD
	}
	
	public static enum musicType{
		MUSIC_ARNOLD,
		MUSIC_MENU
	}
	
	public void initSound(){
		
		musicCache.put(musicType.MUSIC_ARNOLD, Gdx.audio.newMusic(Gdx.files.internal(AssetConstants.sound_menu_bg)));
		musicCache.put(musicType.MUSIC_MENU, Gdx.audio.newMusic(Gdx.files.internal(AssetConstants.sound_playing_bg)));


//		musicCache.put(musicType.MUSIC_MENU, Gdx.audio.newMusic(Gdx.files.internal(AssetConstants.MUSIC_PLAYING)));
//
//		soundCache.put(soundType.SOUND_TOUCH, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_TOUCH)));
//		soundCache.put(soundType.SOUND_DESTROY, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_DESTROYER)));
//		soundCache.put(soundType.SOUND_TRANSFORM, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_TRANSFORMER)));
//		soundCache.put(soundType.SOUND_TRANSPORT, Gdx.audio.newSound(Gdx.files.internal(AssetConstants.SOUND_TRANSPORTER)));

	}
	
	boolean music_menu_active, music_arnold_active;
	
	public boolean is_arnold_active() {
		return music_arnold_active;
	}

	public void set_arnold_active(boolean isArnoldActive) {
		music_arnold_active = isArnoldActive;
	}

	public boolean is_menu_active() {
		return music_menu_active;
	}

	public void set_menu_active(boolean isMenuActive) {
		music_menu_active = isMenuActive;
	}

	public void playSound(soundType soundtype){
		if(is_sound_active())
		{
			if(sound != null)
				sound.stop();
			sound = soundCache.get(soundtype);
			sound.play(1f);
			
		
		if(true) return;
		}
	}
	
	public void playMusic(musicType musictype){
//		if(is_music_active()){
//			gamemusic = musicCache.get(musictype);
//			gamemusic.play();
//			gamemusic.setLooping(true);
//		}
		
		if(musictype == musicType.MUSIC_MENU){
			if(is_menu_active()){
				gamemusic = musicCache.get(musictype);
				gamemusic.play();
				gamemusic.setLooping(true);
			}
		}
		
		else if(musictype == musicType.MUSIC_ARNOLD){
			if(is_arnold_active()){
				gamemusic = musicCache.get(musictype);
				gamemusic.play();
				gamemusic.setLooping(true);
			}
		}
	}
	
	
	public void pauseMusic(musicType musictype){
		if(musictype == musicType.MUSIC_MENU){
//			if(is_menu_active()){
				gamemusic = musicCache.get(musictype);
				gamemusic.pause();
//			}
		}
		
		else if(musictype == musicType.MUSIC_ARNOLD){
//			if(is_arnold_active()){
				gamemusic = musicCache.get(musictype);
				gamemusic.pause();
//			}
		}
	}
	
	public void stopMusic(musicType musictype){
		if(musictype == musicType.MUSIC_MENU){
			if(is_menu_active()){
				gamemusic = musicCache.get(musictype);
				gamemusic.stop();
			}
		}
		
		else if(musictype == musicType.MUSIC_ARNOLD){
			if(is_arnold_active()){
				gamemusic = musicCache.get(musictype);
				gamemusic.stop();
			}
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

	
}
