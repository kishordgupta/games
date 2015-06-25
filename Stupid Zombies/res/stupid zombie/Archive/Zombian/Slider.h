//
//  Slider.h
//  Zombian
//
//  Created by TheAppGuruz . on 06/06/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "CCLayer.h"
#import "cocos2d.h"
#import "Box2D.h"

@interface Slider : CCLayer {
    
    float spd;
    int maxDist,type;
    CGPoint oPos;
    b2Body *Body;
    
    BOOL toLeft;
}

@property(assign,nonatomic)float Speed;
@property(assign,nonatomic)int MaxDist;
@property(assign,nonatomic)int Type;

-(id)initWithPosAtSpeed:(CGPoint)point:(float)speed:(CCLayer *)Leyer:(b2World *)world:(BOOL)isVerticle;
-(void)addSliderAtPos:(CGPoint)pos:(CCLayer *)Layer:(b2World *)world:(BOOL)isVerticle;
-(void)UpdateMe:(ccTime)dt;
@end
