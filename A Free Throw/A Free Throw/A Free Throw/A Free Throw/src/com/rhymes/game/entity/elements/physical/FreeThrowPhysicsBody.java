package com.rhymes.game.entity.elements.physical;

import com.badlogic.gdx.physics.box2d.World;

public class FreeThrowPhysicsBody extends PhysicsBody {
	public int score = 100;
	public int collisionType = 10;

	public FreeThrowPhysicsBody(float x, float y, float width, float height,
			String binPath, String imagePath, String fixturePath, World world,
			short categoryBits, String bodyType, int score, int collisionType) {
		super(x, y, width, height, binPath, imagePath, fixturePath, world,
				categoryBits, bodyType);
		this.score = score;
		this.collisionType = collisionType;
	}

	public FreeThrowPhysicsBody(float x, float y, float width, float height,
			String binPath, String imagePath, String fixturePath, World world,
			short categoryBits, int score, int collisionType) {
		super(x, y, width, height, binPath, imagePath, fixturePath, world,
				categoryBits);
		this.score = score;
		this.collisionType = collisionType;
	}

}
