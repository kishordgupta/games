package com.rhymes.game.bulletwar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferenceBullet {
	
	public static final String pref_name = "PREFERENCE_BULLET";
	
	public static final String pref_sound = "SOUND";
	
	public static final String pref_pack = "PACK_";
	public static final String pref_level = "LEVEL_";
	
	
	public static int pack_num = 8;
	public static int level_num = 10;
	
	
	public PreferenceBullet(){
		
	}
	
	
	 public Preferences getPrefs() {
		 return Gdx.app.getPreferences(pref_name);
	 }

	 /// sound
	 
	 public boolean isSoundEffectsEnabled(){
		 return getPrefs().getBoolean( pref_sound, true );
	 }
	 

	 public void setSoundEffectsEnabled(boolean soundEffectsEnabled){
		 getPrefs().putBoolean(pref_sound, soundEffectsEnabled);
		 getPrefs().flush();
	 }
	 
	 
	 /// pack
	 
	 public boolean isPackEnabled(String pfre_pack_name , int pack_id){
		 return getPrefs().getBoolean(pfre_pack_name+""+pack_id, false);
	 }
	 
	 public void setPackEnabled(String pref_pack_name, int pack_id, boolean pack_enabled){
		 getPrefs().putBoolean(pref_pack_name+""+pack_id, pack_enabled);
		 getPrefs().flush();
	 }
	 
	
	 ///level
	 
	 public boolean isLevelEnabled(String pfre_level_name, int pack_id, int level_id){
		 return getPrefs().getBoolean(pfre_level_name+""+pack_id+"_"+level_id, false);
	 }
	 
	 public void setLevelEnabled(String pref_level, int pack_id, int level_id, boolean level_enabled){
		 getPrefs().putBoolean(pref_level+""+pack_id+"_"+level_id, level_enabled);
		 getPrefs().flush();
	 }
	 
}
