package com.rhymes.game.entity.elements.ui;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.entity.elements.ElementBase;
import com.rhymes.ge.pw.assets.AssetPack;
import com.rhymes.helpers.Helper;

public class NumericText extends ElementBase{

	private long number = 1242;
	char[] d;

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
//		System.out.println("Number: " + new String(d));
		dx = x;
		for (i = 0; i < d.length; i++) {
			t = selectFont(d[i]);
			digitWidth = width + charsPadding[d[i]-'0'];
			dx = dx + digitWidth;
			GlobalVars.ge.getRenderer().render(t, dx, y - height, width, height);
		}
	}

	public TextureRegion selectFont(char a) {
		TextureRegion texture = null;
		if (a == '0')
			texture = Helper.getImageFromAssets(AssetConstants.FONT_00);
		else if (a == '1')
			texture = Helper.getImageFromAssets(AssetConstants.FONT_01);
		else if (a == '2')
			texture = Helper.getImageFromAssets(AssetConstants.FONT_02);
		else if (a == '3')
			texture = Helper.getImageFromAssets(AssetConstants.FONT_03);
		else if (a == '4')
			texture = Helper.getImageFromAssets(AssetConstants.FONT_04);
		else if (a == '5')
			texture = Helper.getImageFromAssets(AssetConstants.FONT_05);
		else if (a == '6')
			texture = Helper.getImageFromAssets(AssetConstants.FONT_06);
		else if (a == '7')
			texture = Helper.getImageFromAssets(AssetConstants.FONT_07);
		else if (a == '8')
			texture = Helper.getImageFromAssets(AssetConstants.FONT_08);
		else if (a == '9')
			texture = Helper.getImageFromAssets(AssetConstants.FONT_09);

		return texture;
	}
	public NumericText(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
	}

	@Override
	public void getAssets(AssetPack assetPack) {
		assetPack.addTexture(AssetConstants.FONT_00);
		assetPack.addTexture(AssetConstants.FONT_01);
		assetPack.addTexture(AssetConstants.FONT_02);
		assetPack.addTexture(AssetConstants.FONT_03);
		assetPack.addTexture(AssetConstants.FONT_04);
		assetPack.addTexture(AssetConstants.FONT_05);
		assetPack.addTexture(AssetConstants.FONT_06);
		assetPack.addTexture(AssetConstants.FONT_07);
		assetPack.addTexture(AssetConstants.FONT_08);
		assetPack.addTexture(AssetConstants.FONT_09);
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
