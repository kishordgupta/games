package com.rhymes.game.entity.elements.ui;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.testtile.HeroTile;
import com.rhymes.game.entity.elements.testtile.TileSet;
import com.rhymes.game.stage.levels.TestTileLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;
import com.rhymes.sundayLawn.ResultTileScore;

public class NumericText extends ElementBase{

	private long number = 1242;
	char[] d;
	private boolean isShow=true;
	
//	private HeroTile hero;

	@Override
	public void render() {
		renderScore();
	}
	static int charsPadding[] = {0, 4, 1, 2, 0, 2, 0, 0, 1, 0};
	int i;
	TextureRegion t;
	float digitWidth = 0;
	float dx;
	public void renderScore() {
		d = ("" + number).toCharArray();
		dx = x;
	if(!isShow)
		return;
		for (i = 0; i < d.length; i++) {
			t = selectFont(d[i]);
			digitWidth = width + charsPadding[d[i]-'0'];
			dx = dx + digitWidth;
			GlobalVars.ge.getRenderer().render(t, dx , y, width, height);
		}
	}
	
	
	public void setActive(boolean b){
		this.isShow = b;
	}
	
	public boolean isActive(){
		return isShow;
	}
	

	public TextureRegion selectFont(char a) {
		TextureRegion texture = null;
		if (a == '0')
			texture = Helper.getImageFromAssets(AssetConstants.SCORE_FONT_00);
		else if (a == '1')
			texture = Helper.getImageFromAssets(AssetConstants.SCORE_FONT_01);
		else if (a == '2')
			texture = Helper.getImageFromAssets(AssetConstants.SCORE_FONT_02);
		else if (a == '3')
			texture = Helper.getImageFromAssets(AssetConstants.SCORE_FONT_03);
		else if (a == '4')
			texture = Helper.getImageFromAssets(AssetConstants.SCORE_FONT_04);
		else if (a == '5')
			texture = Helper.getImageFromAssets(AssetConstants.SCORE_FONT_05);
		else if (a == '6')
			texture = Helper.getImageFromAssets(AssetConstants.SCORE_FONT_06);
		else if (a == '7')
			texture = Helper.getImageFromAssets(AssetConstants.SCORE_FONT_07);
		else if (a == '8')
			texture = Helper.getImageFromAssets(AssetConstants.SCORE_FONT_08);
		else if (a == '9')
			texture = Helper.getImageFromAssets(AssetConstants.SCORE_FONT_09);

		return texture;
	}
	public NumericText(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.SCORE_FONT_00);
		assetPack.addTexture(AssetConstants.SCORE_FONT_01);
		assetPack.addTexture(AssetConstants.SCORE_FONT_02);
		assetPack.addTexture(AssetConstants.SCORE_FONT_03);
		assetPack.addTexture(AssetConstants.SCORE_FONT_04);
		assetPack.addTexture(AssetConstants.SCORE_FONT_05);
		assetPack.addTexture(AssetConstants.SCORE_FONT_06);
		assetPack.addTexture(AssetConstants.SCORE_FONT_07);
		assetPack.addTexture(AssetConstants.SCORE_FONT_08);
		assetPack.addTexture(AssetConstants.SCORE_FONT_09);
		assetPack.addTexture(AssetConstants.SCORE_FONT_BY);
		assetPack.addTexture(AssetConstants.SCORE_FONT_EQUAL);
		assetPack.addTexture(AssetConstants.SCORE_FONT_PERCENT);

	}

	@Override
	public void init() {
	}
	
	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
}
