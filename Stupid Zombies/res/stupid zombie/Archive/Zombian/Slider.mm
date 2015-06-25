//
//  Slider.m
//  Zombian
//
//  Created by TheAppGuruz . on 06/06/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "Slider.h"
#import "HelloWorldLayer.h"
#import "AppDelegate.h"

#define PTM_RATIO 32
/** Convert the given position into the box2d world. */
static inline float ptm(float d)
{
    return d / PTM_RATIO;
}

/** Convert the given position into the cocos2d world. */
static inline float mtp(float d)
{
    return d * PTM_RATIO;
}

@implementation Slider

@synthesize Speed = spd;
@synthesize MaxDist = maxDist;
@synthesize Type = type;


-(id)init
{
    if(self = [super init])
    {
        
    }
    return self;
}

-(id)initWithPos:(CGPoint)point:(CCLayer *)Layer:(b2World *)world:(BOOL)isVerticle

{
    if(self = [super init])
    {
        [self addSliderAtPos:point:Layer:world:isVerticle];
        spd = 0.05;
        maxDist = 100;
    }
    return self;
}

-(id)initWithPosAtSpeed:(CGPoint)point:(float)speed:(CCLayer *)Leyer:(b2World *)world:(BOOL)isVerticle
{
    if(self = [super init])
    {
        [self addSliderAtPos:point:Leyer:world:isVerticle];
        spd = speed;
        maxDist = 100;
    }
    return self;
}

-(int)getRatioY:(int)val
{
    if(DeviceType == 1)
    {
        return (wSize.height * val / 320);
    }
    
    return val;
}

-(int)getRatioX:(int)val
{
    if(DeviceType == 1)
    {
        return (wSize.width * val / 480);
    }
    
    return val;
}

-(NSString *)getImgName:(NSString *)str
{
    if (DeviceType == 1)
    {
        return [NSString stringWithFormat:@"ipad-%@",str];
    }
    return str;
}

-(void)addSliderAtPos:(CGPoint)pos:(CCLayer *)Layer:(b2World *)world:(BOOL)isVerticle
{
    b2BodyDef bd;
    bd.type = b2_kinematicBody;
    
    b2PolygonShape box;
    b2FixtureDef fixtureDef;
    
    fixtureDef.density = 1.0f;
    fixtureDef.friction = 1.0f;
    fixtureDef.restitution = 0.0f;
    
    NSString *fname = [self getImgName:@"mbar.png"];
    
    CCSprite *barSprite = [CCSprite spriteWithFile:fname];
    barSprite.position = pos;
    barSprite.tag = 32;
    [Layer addChild:barSprite];
    
    //CGSize size = CGSizeMake(15, 15);
    box.SetAsBox(ptm([self getRatioX:30]), ptm([self getRatioY:5]));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(pos.x), ptm(pos.y)); if(isVerticle)bd.angle = CC_DEGREES_TO_RADIANS(90);
    bd.userData = barSprite;
    b2Body *b = world->CreateBody(&bd);
    b->CreateFixture(&fixtureDef);
    b->SetBullet(true);
    Body = b;
    oPos = pos;
    
    toLeft = true;
    type = 0;

}

-(void)UpdateMe:(ccTime)dt
{
    b2Vec2 pos = Body->GetPosition();
    if(toLeft)
    {
        if(type == 0) pos = b2Vec2(pos.x+spd,pos.y);
        else          pos = b2Vec2(pos.x,pos.y + spd);
        
        Body->SetTransform(pos, Body->GetAngle());
    }
    else 
    {
        if(type == 0) pos = b2Vec2(pos.x-spd,pos.y);
        else          pos = b2Vec2(pos.x,pos.y - spd);
        
        Body->SetTransform(pos, Body->GetAngle());
    }
    
    if(type == 0)
    {
        if((mtp(pos.x) >= (oPos.x+maxDist)) || (mtp(pos.x) < oPos.x))
        {
            if((mtp(pos.x) >= (oPos.x+maxDist)))    pos.x = ptm(oPos.x+maxDist);
            else                                    pos.x = ptm(oPos.x);
            
            Body->SetTransform(pos, Body->GetAngle());
            toLeft = !toLeft;

        }
    }
    else
    {
        if((mtp(pos.y) >= (oPos.y+maxDist)) || (mtp(pos.y) < oPos.y))
        {
            if((mtp(pos.y) >= (oPos.y+maxDist)))    pos.y = ptm(oPos.y+maxDist);
            else                                    pos.y = ptm(oPos.y);
            
            Body->SetTransform(pos, Body->GetAngle());
            toLeft = !toLeft;
        }
    }
}

@end
