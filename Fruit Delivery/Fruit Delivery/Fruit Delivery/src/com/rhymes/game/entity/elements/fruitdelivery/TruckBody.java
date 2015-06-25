package com.rhymes.game.entity.elements.fruitdelivery;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.rhymes.game.entity.elements.physical.Car;
import com.rhymes.game.entity.elements.physical.PhysicsHelper;
import com.rhymes.game.entity.elements.ui.PhysicsBody2;
import com.rhymes.game.stage.menus.stick.LevelInfo;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.helpers.Helper;

public class TruckBody extends PhysicsBody2 {

	Body carHead;
	public void setTruckHead(Body carHead)
	{
		this.carHead = carHead; 
	}
	@Override
	public void render() {
		super.render();
//		pos = carHead.getPosition();
////		pos.x -= Helper.w2p(this.width);
//		if(s==null)
//			this.init();
////		PhysicsHelper.ConvertToWorld(player
////				.getWorldCenter().x)
////				+ PhysicsHelper.ConvertToWorld(carWidth / 3.5f),
////				PhysicsHelper.ConvertToWorld(player.getPosition().y)+10/* * LevelInfo.ratioY*/
////		s.setPosition(Helper.p2w(pos.x)* LevelInfo.ratioX - PhysicsHelper.ConvertToWorld(Car.carWidth / 3.5f) * LevelInfo.ratioX ,
////				Helper.p2w(pos.y) * LevelInfo.ratioX-10 * LevelInfo.ratioX) ;
//		s.setPosition(Helper.p2w(pos.x)* LevelInfo.ratioX - (width-60)* LevelInfo.ratioX/*- PhysicsHelper.ConvertToWorld(Car.carWidth / 3.5f*/ ,
//				Helper.p2w(pos.y) * LevelInfo.ratioX) ;
//		s.setOrigin(0, 0);
//		s.setRotation(vialModel.getAngle() * MathUtils.radiansToDegrees);
//		s.draw(GlobalVars.ge.getScreen().getBatch(),1f);
	}

	public TruckBody(float x, float y, float width, float height,
			String binPath, String imagePath, String fixturePath, World world,
			float density, float friction, float restitution, short groupIndex,
			short categoryBits, short maskBits, String bodyID, int zIndex,
			BodyType bodyType) {
		super(x, y, width, height, binPath, imagePath, fixturePath, world, density,
				friction, restitution, groupIndex, categoryBits, maskBits, bodyID,
				zIndex, bodyType);
		// TODO Auto-generated constructor stub
	}

	public TruckBody(float x, float y, float width, float height,
			String binPath, String imagePath, String fixturePath, World world,
			short categoryBits, int zIndex) {
		super(x, y, width, height, binPath, imagePath, fixturePath, world,
				categoryBits, zIndex);
		// TODO Auto-generated constructor stub
	}

	public TruckBody(float x, float y, float width, float height,
			String binPath, String imagePath, String fixturePath, World world,
			short groupIndex, short categoryBits, short maskBits,
			String bodyID, int zIndex, BodyType bodyType, float density) {
		super(x, y, width, height, binPath, imagePath, fixturePath, world, groupIndex,
				categoryBits, maskBits, bodyID, zIndex, bodyType, density);
		// TODO Auto-generated constructor stub
	}

	public TruckBody(float x, float y, float width, float height,
			String binPath, String imagePath, String fixturePath, World world,
			short groupIndex, short categoryBits, short maskBits,
			String bodyID, int zIndex, BodyType bodyType) {
		super(x, y, width, height, binPath, imagePath, fixturePath, world, groupIndex,
				categoryBits, maskBits, bodyID, zIndex, bodyType);
		// TODO Auto-generated constructor stub
	}

	public TruckBody(float x, float y, float width, float height,
			String binPath, String imagePath, String fixturePath, World world,
			short categoryBits, short maskBits, String bodyID, int zIndex,
			BodyType bodyType) {
		super(x, y, width, height, binPath, imagePath, fixturePath, world,
				categoryBits, maskBits, bodyID, zIndex, bodyType);
		// TODO Auto-generated constructor stub
	}

	public TruckBody(float x, float y, float width, float height,
			String binPath, String imagePath, String fixturePath, World world,
			short categoryBits, String bodyID, int zIndex, BodyType bodyType) {
		super(x, y, width, height, binPath, imagePath, fixturePath, world,
				categoryBits, bodyID, zIndex, bodyType);
		// TODO Auto-generated constructor stub
	}

}
