package com.rhymes.game.entity.elements.arnold.stage.menu.gameovermenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.arnold.ArnoldGameConstants;
import com.rhymes.game.entity.elements.arnold.stage.menu.gameovermenu.button.BackToPreviousLevelArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.gameovermenu.button.NextArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.gameovermenu.button.Score_Gameover_UI_Arnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.gameovermenu.button.SelectLevelArnold;
import com.rhymes.game.entity.elements.arnold.stage.menu.levelmenu.LevelMenuArnold;
import com.rhymes.game.entity.elements.ui.buttonlabel.Text;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.ge.pw.sound.SoundHandler.musicType;
import com.rhymes.helpers.Helper;

public class GameOverScreenArnold extends StageBase{
	
	public InteractionTouch interaction_touch_over_menu;
	float x, y;
	
	public int played_level;
	public boolean is_game_over;
	public int score;
	
	public Text score_label;
	
	public GameOver_Arnold_B_G bg;
	
	public Score_Gameover_UI_Arnold score_ui;
	public NextArnold next_button;
	public BackToPreviousLevelArnold previous_button;
	public SelectLevelArnold select_level;
	
	
	public GameOverScreenArnold(int played_level, boolean is_game_over, int score){
		this.played_level = played_level;
		this.is_game_over = is_game_over;
		this.score = score;
	}
	
	
	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.score_over_arnold);
		
		assetPack.addTexture(AssetConstants.select_level_from_over_arnold_d);
		assetPack.addTexture(AssetConstants.select_level_from_over_arnold_n);

		assetPack.addTexture(AssetConstants.next_arnold_d);
		assetPack.addTexture(AssetConstants.next_arnold_n);

		assetPack.addTexture(AssetConstants.back_from_over_arnold_menu_d);
		assetPack.addTexture(AssetConstants.back_from_over_arnold_menu_n);

		return assetPack;
	}

	@Override
	public void loadElements() {
		this.interaction_touch_over_menu = new InteractionTouch();
		this.interactions.add(this.interaction_touch_over_menu);
		loadFonts();
		
		set_over_menu_elements();
		
		StartupInfo.sound_handler.stopMusic(musicType.MUSIC_ARNOLD);
		StartupInfo.sound_handler.playMusic(musicType.MUSIC_MENU);

	}
	
	
	private void loadFonts() {
		font = TrueTypeFontFactory.createBitmapFont  
		(Gdx.files.internal(AssetConstants.font_arnold), Text.getFrontChars(),
				12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		font.setColor(1f, 1f, 1f, 1f);
	}
	
	

	private void set_over_menu_elements() {
		x = 0f;
		y = 0f;
		bg = new GameOver_Arnold_B_G(x, y, 
				Gdx.graphics.getWidth(), 
				Gdx.graphics.getHeight(), 
				1, 
				AssetConstants.game_over_menu_arnold_bg, 
				AssetConstants.game_clear_menu_arnold_bg, 
				this.is_game_over);
		this.elements.add(bg);
		
		x = Gdx.graphics.getWidth()/2f - 290f * ArnoldGameConstants.ratio_w -
		/*Helper.getImageFromAssets(AssetConstants.select_level_from_over_arnold_d).getRegionWidth()/2f **/ ArnoldGameConstants.button_width/2f;
		y = Gdx.graphics.getHeight()/2f - 60f * ArnoldGameConstants.ratio_h - 
		/*Helper.getImageFromAssets(AssetConstants.select_level_from_over_arnold_d).getRegionHeight() **/ ArnoldGameConstants.button_height/2f;
		
		select_level = new SelectLevelArnold(x, y, 
				Helper.getImageFromAssets(AssetConstants.select_level_from_over_arnold_d).getRegionWidth() *ArnoldGameConstants.ratio_w  /*ArnoldGameConstants.button_width*/, 
				Helper.getImageFromAssets(AssetConstants.select_level_from_over_arnold_d).getRegionHeight() *ArnoldGameConstants.ratio_h /*ArnoldGameConstants.button_height*/, 
				1, 
				AssetConstants.select_level_from_over_arnold_n, 
				AssetConstants.select_level_from_over_arnold_d);
		this.topElements.add(select_level);
		this.interaction_touch_over_menu.subscribe(select_level);


		
		y = y - 80f * ArnoldGameConstants.ratio_h;
		
		previous_button = new BackToPreviousLevelArnold(x, y, 
				Helper.getImageFromAssets(AssetConstants.back_from_over_arnold_menu_d).getRegionWidth() *ArnoldGameConstants.ratio_w  /*ArnoldGameConstants.button_width*/,
				Helper.getImageFromAssets(AssetConstants.back_from_over_arnold_menu_d).getRegionHeight() * ArnoldGameConstants.ratio_h/*ArnoldGameConstants.button_height*/, 
				1, 
				AssetConstants.back_from_over_arnold_menu_d,
				AssetConstants.back_from_over_arnold_menu_n,
				this.played_level);
		this.topElements.add(previous_button);
		this.interaction_touch_over_menu.subscribe(previous_button);
		
		
		if(this.played_level != LevelMenuArnold.num_of_level - 1 && !is_game_over){
			y = y - 80f * ArnoldGameConstants.ratio_h;
			
			next_button = new NextArnold(x, y, 
					Helper.getImageFromAssets(AssetConstants.next_arnold_d).getRegionWidth() *ArnoldGameConstants.ratio_w  /*ArnoldGameConstants.button_width*/, 
					Helper.getImageFromAssets(AssetConstants.next_arnold_d).getRegionHeight() *ArnoldGameConstants.ratio_h/* ArnoldGameConstants.button_height*/,
					1, 
					AssetConstants.next_arnold_d,
					AssetConstants.next_arnold_n,
					this.played_level+1);
			this.topElements.add(next_button);
			this.interaction_touch_over_menu.subscribe(next_button);
		}
		
		if(!is_game_over){
			x = Gdx.graphics.getWidth()/2f - ArnoldGameConstants.score_width_ui;
			y = Gdx.graphics.getHeight()/2f + 100f * ArnoldGameConstants.ratio_h;
			
			score_ui = new Score_Gameover_UI_Arnold(x, y,
					ArnoldGameConstants.score_width_ui, 
					ArnoldGameConstants.score_height_ui,
					1, 
					AssetConstants.score_over_arnold);
			this.topElements.add(score_ui);
	
			
			x = x + ArnoldGameConstants.button_width - 10f * ArnoldGameConstants.ratio_w;
			y = y + 8 * ArnoldGameConstants.ratio_h;

			score_label = new Text(x, y, 1f , 1f , font, ""+this.score);
			this.topElements.add(score_label);
		}
		
		else if(is_game_over){
			x = Gdx.graphics.getWidth()/2f;
			y = Gdx.graphics.getHeight()/2f - 16f * ArnoldGameConstants.ratio_h;
			
			score_label = new Text(x, y, 
					1f,
					1f,
					font, 
					""+this.score);
			this.topElements.add(score_label);
			
		}
	}


	@Override
	public void tick(long stepTime) {
		
	}

}
