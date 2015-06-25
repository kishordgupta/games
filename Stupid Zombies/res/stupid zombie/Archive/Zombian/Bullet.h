//
//  Bullet.h
//  Zombian
//
//  Created by TheAppGuruz . on 21/05/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Box2D.h"
#import "cocos2d.h"

@interface Bullet : NSObject{
    
    //int _count;
    b2Body *BBody;
    CCSprite *sp;
    CCMotionStreak *myStreak,*myStreak2;
}

@property(assign,nonatomic)int count;
@property(assign,nonatomic)NSString *name;
@property(assign,nonatomic)CCParticleSystem *particles;
@property(assign,nonatomic)CCSprite *Sp;
@property(assign,nonatomic)CCMotionStreak *Streak;

-(id)makeParticles;
-(void)update;
-(void)addBullet:(b2World *)world:(CGPoint)pos:(float)ang:(CCLayer *)layer:(CCMotionStreak *)mStreak;
-(b2Body *)getBody;
-(CGPoint)toParticle:(CGPoint)origin;



@end
