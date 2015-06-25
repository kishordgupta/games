/**
 * 
 */
package com.rhymes.game.entity.elements.physical;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.rhymes.game.data.Constants;
import com.rhymes.game.data.StartupInfo;
import com.rhymes.game.entity.elements.nonphysical.ResultBounce;
import com.rhymes.game.interactions.ICSingleCollisionCallbacks;
import com.rhymes.game.stage.levels.BounceTest;
import com.rhymes.game.stage.result.ResultBTRClassic;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.pw.sound.SoundHandler;
import com.rhymes.helpers.Helper;

/**
 * 
 * @author Tamas Jano on 12/08/11
 * 
 */
public class CollisionListener implements ContactListener {

	private boolean collided;

	Ball collidedBall = null;
	PhysicsBody collidedBallStick = null;
	PhysicsBody collidedGround = null;
	PhysicsBody collidedPhysicsBody = null;
//	ArrayList<PhysicsBody> bounceBody;

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#beginContact(com.badlogic
	 * .gdx.physics.box2d.Contact)
	 */
	@Override
	public void beginContact(Contact contact) {
		
/*				Helper.println("CB: x: "
				+ contact.getFixtureB().getBody().getPosition().x);
		Helper.println("CB: y: "
				+ contact.getFixtureB().getBody().getPosition().y);

		Helper.println("FixB: user data: "
				+ contact.getFixtureB().getBody().getUserData());
		Helper.println("Fixa: user data: "
				+ contact.getFixtureA().getBody().getUserData());
*/
		Fixture otherFixture = null;
		if (contact.getFixtureA().getFilterData().categoryBits == 1) {
			collidedBall = (Ball) contact.getFixtureA().getBody().getUserData();
			otherFixture = contact.getFixtureB();
			collidedPhysicsBody = (PhysicsBody)otherFixture.getBody().getUserData();
			if(collidedPhysicsBody.categoryBits !=3)
				try {
					((BounceTest) GlobalVars.ge.getCurrentStage()).consumers.add(collidedPhysicsBody);
				} catch (Exception e) {
				}
		} else if (contact.getFixtureB().getFilterData().categoryBits == 1) {
			collidedBall = (Ball) contact.getFixtureB().getBody().getUserData();
			otherFixture = contact.getFixtureA();
			
			collidedPhysicsBody = (PhysicsBody)otherFixture.getBody().getUserData();
			if(collidedPhysicsBody.categoryBits !=3)
				try {
					((BounceTest) GlobalVars.ge.getCurrentStage()).consumers.add(collidedPhysicsBody);
				} catch (Exception e) {
				}
		}
		
//		if (contact.getFixtureA().getFilterData().categoryBits == 4) {
//			collidedPhysicsBody = (PhysicsBody)otherFixture.getBody().getUserData();
//			((BounceTest) GlobalVars.ge.getCurrentStage()).consumers.add(collidedPhysicsBody);
////			otherFixture = contact.getFixtureB();
////			collidedPhysicsBody = (PhysicsBody)otherFixture.getBody().getUserData();
////			((BounceTest) GlobalVars.ge.getCurrentStage()).consumers.add(collidedPhysicsBody);
//		} else if (contact.getFixtureB().getFilterData().categoryBits == 4) {
//			collidedPhysicsBody = (PhysicsBody)contact.getFixtureB().getBody().getUserData();
////			otherFixture = contact.getFixtureA();
////			
////			collidedPhysicsBody = (PhysicsBody)otherFixture.getBody().getUserData();
////			((BounceTest) GlobalVars.ge.getCurrentStage()).consumers.add(collidedPhysicsBody);
//		}
//	
//
//		if (otherFixture.getFilterData().categoryBits == 2) {
//			collidedBallStick =  (PhysicsBody)otherFixture.getBody().getUserData();
////			Helper.println("ballstick" +collidedBallStick);
//			((BounceTest) GlobalVars.ge.getCurrentStage()).consumers.add(collidedBallStick);		
//		} else if (otherFixture.getFilterData().categoryBits == 3) {
////			Helper.println("VODA 3");
//			collidedGround =  (PhysicsBody)otherFixture.getBody().getUserData();
////			Helper.println("ground" +collidedGround.toString());
//			((BounceTest) GlobalVars.ge.getCurrentStage()).consumers.add(collidedGround);
//		
//		}


		// if(true)
		// return;
		if (contact.getFixtureB().getBody().getUserData() != null) {
//			if (Constants.levelSelected==1) {
				// this.collided = true;
				//			Helper.println("collided:::::::::" + this.isCollided());
			
		
				if (isCollided() == true) {

					// this.collided = collidedBall.virgin;
					((ResultBounce) ((BounceTest) GlobalVars.ge
							.getCurrentStage()).result)
							.setBall(this.collidedBall);

					if (((BounceTest) GlobalVars.ge.getCurrentStage()).result instanceof ResultBounce) {
						// Helper.println("Collected Bounce");
						if (otherFixture != null) {
//							Helper.println("collidedcheckfor showBounceScore::"
//									+ this.isCollided());
							
							((BounceTest) GlobalVars.ge.getCurrentStage())
									.showBounceScore(collidedPhysicsBody,
											collidedBall);
						}

						// this.collided = true;
					}
					if(StartupInfo.sound_handler.is_sound_active())
					StartupInfo.sound_handler.playSound(SoundHandler.soundType.SOUND_HIT);
				}
//			}
/*			else if (Constants.levelSelected==2) {
				// this.collided = true;
				//			Helper.println("collided:::::::::" + this.isCollided());
				if (isCollided() == true) {

					// this.collided = collidedBall.virgin;
					((ResultBounce) ((BounceTest) GlobalVars.ge
							.getCurrentStage()).result)
							.setBall(this.collidedBall);

					if (((BounceTest) GlobalVars.ge.getCurrentStage()).result instanceof ResultBounce) {
						// Helper.println("Collected Bounce");
						if (otherFixture != null) {
							Helper.println("collidedcheckfor showBounceScore::"
									+ this.isCollided());
							((BounceTest) GlobalVars.ge.getCurrentStage())
									.showBounceScore(collidedPhysicsBody,
											collidedBall);
						}

						// this.collided = true;
					}
					//					
				}
			}*/
		}
//		this.setCollided(false);
	}

	public boolean isCollided() {
		return collided;
	}

	public void setCollided(boolean collided) {
		this.collided = collided;
	}

	public Ball getCollidedBall() {
		return collidedBall;
	}

	public void setCollidedBall(Ball collidedBall) {
		this.collidedBall = collidedBall;
	}

	public PhysicsBody getCollidedBallStick() {
		return collidedBallStick;
	}

	public void setCollidedBallStick(PhysicsBody collidedBallStick) {
		this.collidedBallStick = collidedBallStick;
	}

	public PhysicsBody getCollidedGround() {
		return collidedGround;
	}

	public void setCollidedGround(PhysicsBody collidedGround) {
		this.collidedGround = collidedGround;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#endContact(com.badlogic
	 * .gdx.physics.box2d.Contact)
	 */
	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#postSolve(com.badlogic
	 * .gdx.physics.box2d.Contact,
	 * com.badlogic.gdx.physics.box2d.ContactImpulse)
	 */
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#preSolve(com.badlogic.
	 * gdx.physics.box2d.Contact, com.badlogic.gdx.physics.box2d.Manifold)
	 */
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

}
