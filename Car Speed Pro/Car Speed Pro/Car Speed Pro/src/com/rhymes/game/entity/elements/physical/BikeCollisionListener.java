package com.rhymes.game.entity.elements.physical;

import java.awt.GraphicsDevice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.rhymes.game.stage.levels.BikeLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.helpers.Helper;

public class BikeCollisionListener  implements ContactListener {

	private boolean collided;
	private Body objectA=null;
	private Body objectB=null;

	public boolean isCollided() {
		return collided;
	}

	public void setCollided(boolean collided) {
		this.collided = collided;
	}
	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
//		if (isCollided() == true) {
//			Helper.println("collision happended at::"+Gdx.graphics.getDeltaTime());
//		}
		
		
		objectA = contact.getFixtureA().getBody();
		objectB = contact.getFixtureB().getBody();
		
//		Helper.println("objectA body is::::"+objectA.getUserData()+ "and objectB body is::"+objectB.getUserData()+"and collided bodyA position is::"+PhysicsHelper.ConvertToWorld(objectA.getPosition().x)+"and y is:::"+PhysicsHelper.ConvertToWorld(objectA.getPosition().y));
//		Helper.println("objectA body is::::"+objectA.getUserData()+ "and objectB body is::"+objectB.getUserData()+"and collided bodyB position is::"+PhysicsHelper.ConvertToWorld(objectB.getPosition().x)+"and y is:::"+(objectB.getPosition().y));
		
		if((objectA.getUserData() == "carbody")/*||(objectA.getUserData() == "leftAxleContainer")||(objectA.getUserData() == "rightAxleContainer")*/)
		{
			if(objectB.getUserData() == "elevator"){
				((BikeLevel) GlobalVars.ge.getCurrentStage()).bodyHitGround(false);
				Helper.println("Destoy 1");
			}
			else if(objectB.getUserData() instanceof Lift){
				((Lift)objectB.getUserData() ).start();
			}
		}
		else if((objectB.getUserData() == "carbody")/*||(objectB.getUserData() == "leftAxleContainer")||(objectB.getUserData() == "rightAxleContainer")*/)
		{
			if(objectA.getUserData() == "elevator"){
				Helper.println("Destoy 2");
				((BikeLevel) GlobalVars.ge.getCurrentStage()).bodyHitGround(false);
			}
			else if(objectA.getUserData() instanceof Lift){
				((Lift)objectA.getUserData() ).start();
			}
		}
		
		if((objectA.getUserData() instanceof Lift))
		{
				((Lift)objectA.getUserData() ).start();
		}
		else if((objectB.getUserData() instanceof Lift))
		{
			((Lift)objectB.getUserData() ).start();
		}

		if(true)
			return;
		
//		Helper.println("objectA body is::::"+objectA.getUserData()+ "and objectB body is::"+objectB.getUserData()+"and collided bodyA position is::"+PhysicsHelper.ConvertToWorld(objectA.getPosition().x)+"and y is:::"+PhysicsHelper.ConvertToWorld(objectA.getPosition().y));
//		Helper.println("objectA body is::::"+objectA.getUserData()+ "and objectB body is::"+objectB.getUserData()+"and collided bodyB position is::"+PhysicsHelper.ConvertToWorld(objectB.getPosition().x)+"and y is:::"+(objectB.getPosition().y));
		if (((objectA.getUserData()=="leftWheel")||(objectA.getUserData()=="rightWheel")||(objectB.getUserData()=="leftWheel")||(objectB.getUserData()=="rightWheel"))&&(objectA.getUserData()=="lift")||(objectB.getUserData()=="lift"))
		{
//			Helper.println("objectA body is::::"+objectA.getUserData()+ "and objectB body is::"+objectB.getUserData()+"and collided bodyA position is::"+PhysicsHelper.ConvertToWorld(objectA.getPosition().x)+"and y is:::"+PhysicsHelper.ConvertToWorld(objectA.getPosition().y));
//		Helper.println("objectA body is::::"+objectA.getUserData()+ "and objectB body is::"+objectB.getUserData()+"and collided bodyB position is::"+PhysicsHelper.ConvertToWorld(objectB.getPosition().x)+"and y is:::"+(objectB.getPosition().y));
		}
//		if()
				
//		if(((contact.getFixtureA().getFilterData().categoryBits == (8|9|10|11))&&(contact.getFixtureB().getFilterData().categoryBits !=7))||((contact.getFixtureA().getFilterData().categoryBits != 7)&&(contact.getFixtureB().getFilterData().categoryBits ==(8|9|10|11))))
//		{
//			Helper.println("object A userdara::"+objectA.getUserData());
//			Helper.println("object B user data::"+objectB.getUserData());
//			((BikeLevel) GlobalVars.ge.getCurrentStage()).setInGround(false);
//	
//		}
		
		if (
				((
				(objectA.getUserData() == "leftWheel")
				|| (objectA.getUserData() == "rightWheel")
				|| (objectB.getUserData() == "leftWheel") 
				|| (objectB.getUserData() == "rightWheel"))
				&& (
				(objectA.getUserData() == "elevator") 
				|| (objectB.getUserData() == "elevator")
				))
			)
		{
		Helper.println("objectA body is::::"+objectA.getUserData()+ "and objectB body is::"+objectB.getUserData()+"and collided bodyA position is::"+PhysicsHelper.ConvertToWorld(objectA.getPosition().x)+"and y is:::"+(objectA.getPosition().y));
		Helper.println("objectA body is::::"+objectA.getUserData()+ "and objectB body is::"+objectB.getUserData()+"and collided bodyB position is::"+PhysicsHelper.ConvertToWorld(objectB.getPosition().x)+"and y is:::"+(objectB.getPosition().y));
	
		if((objectB.getUserData() == "RiderBody")||(objectA.getUserData() == "riderBody"))	
	
			((BikeLevel) GlobalVars.ge.getCurrentStage()).bodyHitGround(false);
		}
	
		}

	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		// get how many contact points were detected
		int count = contact.getWorldManifold().getNumberOfContactPoints();
		
		float collissionImpulse  = 0;
		for (int i = 0; i < count; i++)
		{
			collissionImpulse = Math.max(collissionImpulse, impulse.getNormalImpulses()[i]);
//			Helper.println("impulse ::::"+collissionImpulse);
		}
		
		//dispatch a message indicating the collission if the force is over a certain threshold
		if (collissionImpulse > 1)
		{
                            //since we can't figure out what the objects are, we have to have a function that
                            //queries the list of game objects and checks the body against each body in the game object list
			objectA = contact.getFixtureA().getBody();
			objectB = contact.getFixtureB().getBody();	
			
                            //now that we have figured out which objects have collided, we have custom collission handling
			if (objectA!=null && objectB!=null)
			{
                                    //now that we have both bodies, have a custom function that handles the collission
                                    //pass in the collission impulse
//				objectA.handleCollission(objectB, collissionImpulse);
//				objectB.handleCollission(objectA, collissionImpulse);
//				Helper.println("impulse ::::"+collissionImpulse);
//				objectA.applyAngularImpulse(collissionImpulse*.10f);
//				objectB.applyAngularImpulse(collissionImpulse*.10f);
			}			
		}
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub

	}

}
