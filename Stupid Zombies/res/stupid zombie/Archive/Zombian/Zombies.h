//
//  Zombies.h
//  Zombian
//
//  Created by TheAppGuruz . on 19/05/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#include "Box2D.h"
#include "cocos2d.h"

@interface Zombies : NSObject {
    
    b2Body *Head,*Body,*LHand,*RHand,*LLeg,*RLeg;
    b2RevoluteJoint *HJoint,*LHJoint,*RHJoint,*LLJoint,*RLJoint;
    BOOL isAlive;
    NSString *name;
    b2World *myWorld;
}
@property(assign,nonatomic)BOOL IsAlive;
@property(assign,nonatomic)bool isHead;
@property(assign,nonatomic)bool isHand;
@property(assign,nonatomic)bool isLeg;
@property(assign,nonatomic)b2Body *HEAD;
- (void)addNewRagDollAtPosition:(CGPoint)_ragDollPosition:(b2World *)world:(CCLayer *)layer;
- (bool)setDied;
- (void)cutHead;
- (void)cutHand;
- (void)cutLeg;


@end
