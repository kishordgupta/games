package com.rhymes.attackTheFortress;

import java.text.BreakIterator;
import java.util.Locale;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.rhymes.game.entity.elements.ui.Button;
import com.rhymes.game.entity.elements.ui.Text;
import com.rhymes.game.entity.elements.ui.TextArea;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.helpers.Helper;

public class NoticeView extends Button {
private BitmapFont font;
private int lev=-1;
private TextArea noticeText;
private String notice;
	public NoticeView(float x, float y, float width, float height, int zIndex) {
		super(x, y, width, height, zIndex);
		// TODO Auto-generated constructor stub
	}

	public NoticeView(float x, float y, float width, float height, int zIndex,
			String imagePath,BitmapFont font,int level) {
		super(x, y, width, height, zIndex, imagePath);
		this.font=font;
		this.lev=level;
	}
	public void Destroy()
	{
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(this);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).postDestroyed(noticeText);
	}

	@Override
	public void init() {
		super.init();
		
			
			 
		notice = xmlEnemyReader.levelEnemy.get(lev).decs;
		 noticeText=new TextArea(x+50f*LevelInfo.ratioX,y+32f*LevelInfo.ratioY,
				 0.3f*LevelInfo.ratioX, 0.3f*LevelInfo.ratioY, this.font, 
				 notice, this.width * 0.8f, HAlignment.LEFT);
		 noticeText.getFont().setColor(Color.RED);
		((AttackTheFortressLevel)GlobalVars.ge.getCurrentStage()).addElement(noticeText);
	}
	 public void WordSplit() throws Exception{
		   

//		    System.out.println(java.util.Arrays.toString(notice.split("\\s+")));
		   
		 BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		 String source = notice;
		 iterator.setText(source);
		 int start = iterator.first();
		 for (int end = iterator.next();
		     end != BreakIterator.DONE;
		     start = end, end = iterator.next()) {
		   System.out.println(source.substring(start,end));
		 }

		    }
}