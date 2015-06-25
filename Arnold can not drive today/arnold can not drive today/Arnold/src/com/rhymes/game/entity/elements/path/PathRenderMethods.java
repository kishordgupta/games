package com.rhymes.game.entity.elements.path;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;
import com.rhymes.helpers.Helper;

public class PathRenderMethods {
	
	public boolean onceDrawn = false;

	float rheight, rwidth, rangle;
	Path path;
	public PathRenderMethods(Path path)
	{
		this.path = path;	
	}

	public void renderPathSegment(Point n1, Point n2,
			TextureRegion ropeTexture, float ropeWidth) {
		
		

//		if (!this.onceDrawn)
//			Helper.println("Drawing Point: (X,Y) ==>  ( " + n1.getX()
//					+ ", " + n1.getY() + " )   to   " + "(X,Y) ==>  ( "
//					+ n2.getX() + ", " + n2.getY() + " )");

//		rwidth = ropeWidth;
		rheight = (float)Math.abs(Math.sqrt(((n2.getX() - n1.getX()) * (n2.getX() - n1
				.getX()))
				+ ((n2.getY() - n1.getY()) * (n2.getY() - n1.getY()))));

		rangle = Helper.getAngle(n1.getX(), n1.getY(), n2.getX(), n2.getY());
		
//		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,0.3f);
//
//		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,0.5f);
		
		GlobalVars.ge.getRenderer().render(ropeTexture, n1.getX(), n1.getY(),
				ropeWidth, rheight, 0, 0, rangle);
		
//		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
//		GlobalVars.ge.getRenderer().getBatch().setColor(1,1,1,1f);
		
//		if(!this.onceDrawn)
//			Helper.printKeyVal("rangle", "" + rangle);
//		GlobalVars.ge.getRenderer().render(ropeTexture, n1.getX() + Gdx.graphics.getWidth()/2f , n1.getY() + Gdx.graphics.getHeight()/2f ,
//				rwidth, rheight, 0, 0, rangle);
//		GlobalVars.ge.getRenderer().render(ropeTexture,  Gdx.graphics.getWidth()/2f,  Gdx.graphics.getHeight()/2f,
//				10,10, 0, 0, 0);

	}
	
	public void renderPathSegment(Point n1, Point n2,
			TextureRegion ropeTexture) {
		renderPathSegment(n1, n2, ropeTexture, path.getDefaultPathWidth());
	}
	
	public void renderPathSegment(PathNode n1, PathNode n2,
			TextureRegion ropeTexture) {
		renderPathSegment(n1.getLocation(), n2.getLocation(), ropeTexture, path.getDefaultPathWidth());
	}
	
	public void renderPathSegment(PathNode n1, PathNode n2,
			TextureRegion ropeTexture, float pathWidth) {
//		renderPathSegment(n1.getLocation(), n2.getLocation(), ropeTexture, pathWidth);
		rangle = Helper.getAngle(n1.getX(), n1.getY(), n2.getX(), n2.getY());
		GlobalVars.ge.getRenderer().render(ropeTexture, n1.getX(), n1.getY(),
				pathWidth, n1.getRightDistance(), 0, 0, n1.getRightAngle());
	}
}
