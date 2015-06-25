package com.rhymes.game.stage.menus;

import com.badlogic.gdx.Gdx;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.menu.ButtonLevel;
import com.rhymes.game.entity.elements.menu.ButtonLevelPack;
import com.rhymes.game.entity.elements.menu.ButtonPearl;
import com.rhymes.game.entity.elements.menu.ButtonStar;
import com.rhymes.game.entity.elements.ui.ButtonRestartGOver;
import com.rhymes.game.entity.elements.ui.NumericText;
import com.rhymes.game.entity.elements.unsorted.Background;
import com.rhymes.game.interactions.inputs.InteractionTouch;
import com.rhymes.game.stage.result.Result;
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.game.stage.result.ResultBTRMAP;
import com.rhymes.game.stage.result.ResultBTRTime;
import com.rhymes.ge.core.stage.StageBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class GameOverScreen extends StageBase {

	private int level_id;
	private int pack_id;
	
	private Result result;
	private int levelStarNumber;
	private int starCollected;
	
	private long expireTime;
	private int elapsedTime;
	
	private int levelPearlNumber;
	private int pearlCollected;
	
	private NumericText score_total;
	private NumericText score_elapsed;
	private NumericText score_result;

	private ButtonStar[] star = new ButtonStar[3];
	private ButtonPearl[] pearl = new ButtonPearl[4];
	float x, y;
	
	int i, j;
	
	public GameOverScreen(int level_id, Result res){
		this.level_id = level_id;
		this.result = res;
	}

	@Override
	public AssetPack getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.IMG_GAMEOVER_SCREEN);
		assetPack.addTexture(AssetConstants.IMG_STARS);
		assetPack.addTexture(AssetConstants.IMG_EMPTY_STAR);
		assetPack.addTexture(AssetConstants.IMG_BACK_TO);
		assetPack.addTexture(AssetConstants.IMG_RELOAD_LEVEL);
		assetPack.addTexture(AssetConstants.IMG_NEXT);
		assetPack.addTexture(AssetConstants.IMG_EXIT_GAME);
		assetPack.addTexture(AssetConstants.IMG_PEARL);
		assetPack.addTexture(AssetConstants.SCORE_FONT_00);
		assetPack.addTexture(AssetConstants.SCORE_FONT_BY);
		assetPack.addTexture(AssetConstants.SCORE_FONT_EQUAL);
		assetPack.addTexture(AssetConstants.SCORE_FONT_PERCENT);


		return assetPack;
	}

	
	String star_imagepath;
	
	@Override
	public void loadElements() {
		this.interactions.add(new InteractionTouch());
		this.gameControlInteractions.add(new InteractionTouch());

		this.elements.add(new Background(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), 1, AssetConstants.IMG_GAMEOVER_SCREEN));

		
		
		///star & score
		loadScore(this.result);
		
		
		
		////go to pack menu
		x = 23;
		y = 375;
		
		ButtonLevelPack level_pack = new ButtonLevelPack(x*GameMenuInfo.ratio_wl, y* GameMenuInfo.ratio_hl, 
				Helper.getImageFromAssets(AssetConstants.IMG_BACK_TO).getRegionWidth()/2*GameMenuInfo.ratio_w_img,
				Helper.getImageFromAssets(AssetConstants.IMG_BACK_TO).getRegionHeight()/2*GameMenuInfo.ratio_h_img, 
				1, AssetConstants.IMG_BACK_TO, select_pack(level_id), this.result);
		this.elements.add(level_pack);
		subscribeToControllingInteraction(level_pack,
					InteractionTouch.class);

		
		////reload
		x = 23;
		y = 308;
		ButtonRestartGOver reload = new ButtonRestartGOver(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.IMG_RELOAD_LEVEL).getRegionWidth()/2*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_RELOAD_LEVEL).getRegionHeight()/2*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_RELOAD_LEVEL, this.level_id, this.result);
		this.elements.add(reload);
		subscribeToControllingInteraction(reload,
					InteractionTouch.class);
			
		////next level
		if(this.level_id <	GameMenuInfo.num_of_total_level - 1){
			this.level_id++;
			
			x = 23;
			y = 236;
			ButtonLevel next_level = new ButtonLevel(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.IMG_NEXT).getRegionWidth()/2*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_NEXT).getRegionHeight()/2*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_NEXT, this.level_id, this.result);
			this.elements.add(next_level);
			subscribeToControllingInteraction(next_level,
					InteractionTouch.class);
		}
		
		
		

	}
	
	public void loadScore(Result res){
		
		if(res instanceof ResultBTRClassic){
			
			this.levelStarNumber = ((ResultBTRClassic)res).getLevelStarNumber();
			this.starCollected = ((ResultBTRClassic)res).getStarCollected();

			x = 100;
			y = 370;
			
			for(int i = 0; i < levelStarNumber; i++)
			{
				if( i < starCollected)
				{
					Helper.println("i: " + i + "Adding star: width-> " + (float)Helper.getImageFromAssets(AssetConstants.IMG_STARS).getRegionWidth()/10f*GameMenuInfo.ratio_w_img);
					star[i] = new ButtonStar(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, 
							100*GameMenuInfo.ratio_w_img,100 * GameMenuInfo.ratio_w_img,
							1, AssetConstants.IMG_STARS);
					this.elements.add(star[i]);
				}
				else{
					Helper.println("i: " + i + "Adding empty star");
					star[i] = new ButtonStar(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, 
							100*GameMenuInfo.ratio_w_img,100 * GameMenuInfo.ratio_w_img, 
							1, AssetConstants.IMG_EMPTY_STAR);
					this.elements.add(star[i]);
				}
				y -= (100f * GameMenuInfo.ratio_w_img + 15f);
			}
			
		}
		
		else if(res instanceof ResultBTRTime){
			
			this.levelStarNumber = ((ResultBTRTime)res).getLevelStarNumber();
			this.starCollected = ((ResultBTRTime)res).getStarCollected();
			this.expireTime = ((ResultBTRTime)res).getExpireTime();
			this.elapsedTime = ((ResultBTRTime)res).getElapsedTime();
			
			levelPearlNumber = ((ResultBTRTime)res).getLevelPearlNumber();
			pearlCollected = ((ResultBTRTime)res).getPearlCollected();
			
			x = 150;
			y = 370;
			
			for(int i = 0; i < levelStarNumber; i++)
			{
				if( i < starCollected)
				{
					Helper.println("i: " + i + "Adding star: width-> " + (float)Helper.getImageFromAssets(AssetConstants.IMG_STARS).getRegionWidth()/10f*GameMenuInfo.ratio_w_img);
					star[i] = new ButtonStar(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, 
							100*GameMenuInfo.ratio_w_img,100 * GameMenuInfo.ratio_w_img,
							1, AssetConstants.IMG_STARS);
					this.elements.add(star[i]);
				}
				else{
					Helper.println("i: " + i + "Adding empty star");
					star[i] = new ButtonStar(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, 
							100*GameMenuInfo.ratio_w_img,100 * GameMenuInfo.ratio_w_img, 
							1, AssetConstants.IMG_EMPTY_STAR);
//					star[i] = new ButtonStar(100, 200, 50,60, 1, AssetConstants.IMG_EMPTY_STAR);
					this.elements.add(star[i]);
				}
				y -= (100f * GameMenuInfo.ratio_w_img + 15f);
			}
			

			///pearl
			x = 100;
			y = 433;
			pearl[0] = new ButtonPearl(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.IMG_PEARL).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_PEARL).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_PEARL);
			this.elements.add(pearl[0]);
			
			///pearl collected
			y = y - Helper.getImageFromAssets(AssetConstants.IMG_PEARL).getRegionHeight()/20*GameMenuInfo.ratio_h_img;
			y += 10;
			score_elapsed = new NumericText(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img, 1);	
			score_elapsed.setNumber(pearlCollected);
			this.elements.add(score_elapsed);
			
			///by
			y = y - 2*(Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img);
			y -= 30;
//			y = 350;
			pearl[1] = new ButtonPearl(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_BY).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_BY).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.SCORE_FONT_BY);
			this.elements.add(pearl[1]);
			
			///pearl total
			y = y - Helper.getImageFromAssets(AssetConstants.SCORE_FONT_BY).getRegionHeight()*GameMenuInfo.ratio_h_img;
			y+=30;
			score_total = new NumericText(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img, 1);
			score_total.setNumber(levelPearlNumber);
			this.elements.add(score_total);

			///equal
//			y = 290;
			y = y - 2*(Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img);
			y -= 30;
			pearl[2] = new ButtonPearl(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_EQUAL).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_EQUAL).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.SCORE_FONT_EQUAL);
			this.elements.add(pearl[2]);
			
			///percentage of score
			y = y - Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img;
			y += 20;
			score_result = new NumericText(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img, 1);
			score_result.setNumber( (long) ((float)pearlCollected/levelPearlNumber * 100f));
			this.elements.add(score_result);
			
			///percent
//			y = 240;
			y = y - 3*(Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img);
			y -= 30;
			pearl[3] = new ButtonPearl(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_PERCENT).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_PERCENT).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.SCORE_FONT_PERCENT);
			this.elements.add(pearl[3]);
			
		}
		
		
		if(res instanceof ResultBTRMAP){
			
			this.levelPearlNumber = ((ResultBTRMAP)res).getLevelPearlNumber();
			this.pearlCollected = ((ResultBTRMAP)res).getPearlCollected();
			
			Helper.println("Pearl total = "+levelPearlNumber+" , consumed = "+pearlCollected);
			
			
			///pearl
			x = 100;
			y = 433;
			pearl[0] = new ButtonPearl(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.IMG_PEARL).getRegionWidth()/10*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.IMG_PEARL).getRegionHeight()/10*GameMenuInfo.ratio_h_img, 1, AssetConstants.IMG_PEARL);
			this.elements.add(pearl[0]);
			
			///pearl collected
			y = y - Helper.getImageFromAssets(AssetConstants.IMG_PEARL).getRegionHeight()/20*GameMenuInfo.ratio_h_img;
			y += 10;
			score_elapsed = new NumericText(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img, 1);	
			score_elapsed.setNumber(pearlCollected);
			this.elements.add(score_elapsed);
			
			///by
			y = y - 2*(Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img);
			y -= 30;
//			y = 350;
			pearl[1] = new ButtonPearl(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_BY).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_BY).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.SCORE_FONT_BY);
			this.elements.add(pearl[1]);
			
			///pearl total
			y = y - Helper.getImageFromAssets(AssetConstants.SCORE_FONT_BY).getRegionHeight()*GameMenuInfo.ratio_h_img;
			y+=30;
			score_total = new NumericText(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img, 1);
			score_total.setNumber(levelPearlNumber);
			this.elements.add(score_total);

			///equal
//			y = 290;
			y = y - 2*(Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img);
			y -= 30;
			pearl[2] = new ButtonPearl(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_EQUAL).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_EQUAL).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.SCORE_FONT_EQUAL);
			this.elements.add(pearl[2]);
			
			///percentage of score
			y = y - Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img;
			y += 20;
			score_result = new NumericText(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img, 1);
			score_result.setNumber( (long) ((float)pearlCollected/levelPearlNumber * 100f));
			this.elements.add(score_result);
			
			///percent
//			y = 240;
			y = y - 3*(Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00).getRegionHeight()*GameMenuInfo.ratio_h_img);
			y -= 30;
			pearl[3] = new ButtonPearl(x*GameMenuInfo.ratio_wl, y*GameMenuInfo.ratio_hl, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_PERCENT).getRegionWidth()*GameMenuInfo.ratio_w_img, Helper.getImageFromAssets(AssetConstants.SCORE_FONT_PERCENT).getRegionHeight()*GameMenuInfo.ratio_h_img, 1, AssetConstants.SCORE_FONT_PERCENT);
			this.elements.add(pearl[3]);
			
		}
		
	}
	
	public int select_pack(int level_id){
		if(level_id < GameMenuInfo.num_of_level_in_a_pack)
			pack_id = 0;
		else if(level_id >= GameMenuInfo.num_of_level_in_a_pack && level_id < 2*GameMenuInfo.num_of_level_in_a_pack)
			pack_id = 1;
		else if(level_id >= 2*GameMenuInfo.num_of_level_in_a_pack && level_id < 3*GameMenuInfo.num_of_level_in_a_pack)
			pack_id = 2;
		
		return pack_id;
	}
	

	@Override
	public void tick(long stepTime) {
//		 Helper.println("FPS: " + Gdx.graphics.getFramesPerSecond());
	}
}