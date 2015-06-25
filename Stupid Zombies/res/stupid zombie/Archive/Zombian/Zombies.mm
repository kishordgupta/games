//
//  Zombies.m
//  Zombian
//
//  Created by TheAppGuruz . on 19/05/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "Zombies.h"
#import "HelloWorldLayer.h"
#import "AppDelegate.h"

#define PTM_RATIO 32

#define WALL_CATAGORY_BIT 1
#define BULLETE_CATAGORY_BIT 2
#define ROGDALL_CATAGORY_BIT 4
#define BOX_CATAGORY_BIT 8
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

@implementation Zombies

@synthesize isHead;
@synthesize isLeg;
@synthesize isHand;
@synthesize HEAD = Head;
@synthesize IsAlive = isAlive;

-(id)init
{
    if( (self=[super init])) {
        isAlive = true;
        name = @"Zombi";
        isHead = true;
        isLeg = true;
        isHand = true;
    }
    return self;
}

-(NSString *)getImgName:(NSString *)str
{
    if (DeviceType == 1)
    {
        return [NSString stringWithFormat:@"ipad-%@",str];
    }
    return str;
}

-(void)addNewRagDollAtPosition:(CGPoint)_ragDollPosition:(b2World *)world:(CCLayer *)layer
{
    float scale = 0.3f;
    if(DeviceType == 1) scale = 0.6f;
    
    b2BodyDef bd;
    bd.type = b2_dynamicBody;
    
    b2PolygonShape box;
    b2FixtureDef fixtureDef;
    
    fixtureDef.filter.categoryBits = ROGDALL_CATAGORY_BIT;
    fixtureDef.filter.maskBits = WALL_CATAGORY_BIT + BULLETE_CATAGORY_BIT;
    
    CCSprite *Sp;
    
    b2CircleShape headShape;
    // Body ------
    b2CircleShape BodyShape;
    
    fixtureDef.density =1.0f;
    fixtureDef.friction = 1.0f;
    fixtureDef.restitution = 0.0f;
    //bd.type = b2_staticBody;

    //Body
    
    box.SetAsBox(ptm(20.0f * scale), ptm(30.0f * scale));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(_ragDollPosition.x), ptm(_ragDollPosition.y - (50.0f * scale)));
    bd.angle = 0;
    Sp = [CCSprite spriteWithFile:[self getImgName:@"nBdy.png"]];
    //Sp.scale = 0.1f;
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y - (50.0f * scale));
    Sp.userData = self;
    Sp.tag = 2;
    [layer addChild:Sp];
    bd.userData = Sp;
    Body = world->CreateBody(&bd);
    Body->CreateFixture(&fixtureDef);
    
    bd.type = b2_dynamicBody;
    // UpperArm --
    
    // Left
    box.SetAsBox(ptm(25.0f * scale), ptm(5.0f * scale));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(_ragDollPosition.x - (40.0f * scale)), ptm(_ragDollPosition.y - (30.0f * scale)));
    bd.angle = CC_DEGREES_TO_RADIANS(20);
    Sp = [CCSprite spriteWithFile:[self getImgName:@"nHand.png"]];
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y);
    Sp.anchorPoint = ccp(0.6,0.45);
    Sp.userData = self;
    Sp.tag = 2;
    [layer addChild:Sp];
    bd.userData = Sp;
    LHand = world->CreateBody(&bd);
    LHand->CreateFixture(&fixtureDef);
    
    // Right
    box.SetAsBox(ptm(25.0f * scale), ptm(5.0f * scale));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(_ragDollPosition.x + (40.0f * scale)), ptm(_ragDollPosition.y - (30.0f * scale)));
    bd.angle = CC_DEGREES_TO_RADIANS(-20);
    Sp = [CCSprite spriteWithFile:[self getImgName:@"nHand.png"]];
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y);
    Sp.anchorPoint = ccp(0.4,0.45);
    Sp.flipX = true;
    Sp.userData = self;
    Sp.tag = 2;
    [layer addChild:Sp];
    bd.userData = Sp;
    RHand = world->CreateBody(&bd);
    RHand->CreateFixture(&fixtureDef);
    
    // UpperLeg --
    
    bd.angle = 0;
    
    // Left
    box.SetAsBox(ptm(8.0f * scale), ptm(25.0f * scale));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(_ragDollPosition.x - (12.0f * scale)), ptm(_ragDollPosition.y - (105.0f * scale)));
    //bd.angle = CC_DEGREES_TO_RADIANS(10);
    Sp = [CCSprite spriteWithFile:[self getImgName:@"nLeg.png"]];
    //Sp.scale = 0.1f;
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y);
    Sp.anchorPoint = ccp(0.5,0.4);
    //Sp.flipX = true;
    Sp.userData = self;
    Sp.tag = 2;
    [layer addChild:Sp];
    bd.userData = Sp;
    LLeg = world->CreateBody(&bd);
    LLeg->CreateFixture(&fixtureDef);
    
    // Right
    box.SetAsBox(ptm(8.0f * scale), ptm(25.0f * scale));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(_ragDollPosition.x + (12.0f * scale)), ptm(_ragDollPosition.y - (105.0f * scale)));
    //bd.angle = CC_DEGREES_TO_RADIANS(-10);
    Sp = [CCSprite spriteWithFile:[self getImgName:@"nLeg.png"]];
    //Sp.scale = 0.1f;
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y);
    Sp.anchorPoint = ccp(0.5,0.4);
    Sp.flipX = true;
    Sp.userData = self;
    Sp.tag = 2;
    [layer addChild:Sp];
    bd.userData = Sp;
    
    
    RLeg = world->CreateBody(&bd);
    RLeg->CreateFixture(&fixtureDef);
    
    
    //bd.type = b2_staticBody;
    // Head -----
    headShape.m_radius = ptm(28.0f * scale);
    fixtureDef.shape = &headShape;
    bd.position.Set(ptm(_ragDollPosition.x), ptm(_ragDollPosition.y));
    
    Sp = [CCSprite spriteWithFile:[self getImgName:@"nHead.png"]];
    Sp.scale = 0.8f;
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y);
    Sp.userData = self;
    Sp.tag = 2;
    [layer addChild:Sp];
    bd.userData = Sp;
    Head = world->CreateBody(&bd);
    Head->CreateFixture(&fixtureDef);
    
    //Joints ----
    
    b2RevoluteJointDef jd;
    jd.enableLimit = true;
    
    // Head to shoulders
    jd.lowerAngle = -0.0f / (180.0f / M_PI);
    jd.upperAngle = 0.0f / (180.0f / M_PI);
    jd.Initialize(Head,Body, b2Vec2(ptm(_ragDollPosition.x), ptm(_ragDollPosition.y + (0.0f * scale))));
    HJoint =(b2RevoluteJoint *)world->CreateJoint(&jd);
    
    jd.lowerAngle = -60.0f / (180.0f / M_PI);
    jd.upperAngle = 60.0f / (180.0f / M_PI);
    jd.Initialize(Body,LHand, b2Vec2(ptm(_ragDollPosition.x - (18.0f * scale)), ptm(_ragDollPosition.y - (25.0f * scale))));
    jd.localAnchorB = b2Vec2(ptm(10),ptm(0));
    LHJoint =(b2RevoluteJoint *)world->CreateJoint(&jd);
    
    jd.lowerAngle = -60.0f / (180.0f / M_PI);
    jd.upperAngle = 60.0f / (180.0f / M_PI);
    jd.Initialize(Body,RHand, b2Vec2(ptm(_ragDollPosition.x + (18.0f * scale)), ptm(_ragDollPosition.y - (25.0f * scale))));
    jd.localAnchorB = b2Vec2(ptm(-10),ptm(0));
    RHJoint =(b2RevoluteJoint *)world->CreateJoint(&jd);
    
    jd.lowerAngle = -25.0f / (180.0f / M_PI);
    jd.upperAngle = 45.0f / (180.0f / M_PI);
    jd.Initialize(Body,LLeg, b2Vec2(ptm(_ragDollPosition.x - (12.0f * scale)), ptm(_ragDollPosition.y - (75.0f * scale))));
    //jd.localAnchorA = b2Vec2(ptm(-3),ptm(-15));
    LLJoint =(b2RevoluteJoint *)world->CreateJoint(&jd);
    
    // Right
    jd.lowerAngle = -45.0f / (180.0f / M_PI);
    jd.upperAngle = 25.0f / (180.0f / M_PI);
    jd.Initialize(Body, RLeg, b2Vec2(ptm(_ragDollPosition.x + (12.0f * scale)), ptm(_ragDollPosition.y - (75.0f * scale))));
    //jd.localAnchorA = b2Vec2(ptm(3),ptm(-15));
    RLJoint =(b2RevoluteJoint *)world->CreateJoint(&jd);
    
    myWorld = world;

}
/*-(void)addNewRagDollAtPosition1:(CGPoint)_ragDollPosition:(b2World *)world:(CCLayer *)layer
{
    float scale = 0.3f;
    
    b2BodyDef bd;
    bd.type = b2_dynamicBody;
    
    b2PolygonShape box;
    b2FixtureDef fixtureDef;
   
    fixtureDef.filter.categoryBits = ROGDALL_CATAGORY_BIT;
    fixtureDef.filter.maskBits = WALL_CATAGORY_BIT + BULLETE_CATAGORY_BIT;
    
    CCSprite *Sp;
    
    
    // Body ------
    b2CircleShape BodyShape;
    
    b2CircleShape headShape;
    
    fixtureDef.density =1.0f;
    fixtureDef.friction = 1.0f;
    fixtureDef.restitution = 0.0f;
    bd.type = b2_staticBody;
    // Head -----
    
    headShape.m_radius = ptm(20.0f * scale);
    fixtureDef.shape = &headShape;
    bd.position.Set(ptm(_ragDollPosition.x), ptm(_ragDollPosition.y));
    
    Sp = [CCSprite spriteWithFile:@"Head.png"];
    //Sp.scale = 0.1f;
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y);
    Sp.userData = self;
    [layer addChild:Sp];
    Sp.tag = 2;
    bd.userData = Sp;
    Head = world->CreateBody(&bd);
    Head->CreateFixture(&fixtureDef);
    
    bd.type = b2_dynamicBody;
    // UpperArm --
    
    // Left
    box.SetAsBox(ptm(25.0f * scale), ptm(5.0f * scale));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(_ragDollPosition.x - (40.0f * scale)), ptm(_ragDollPosition.y - (30.0f * scale)));
    
    Sp = [CCSprite spriteWithFile:@"Hand.png"];
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y);
    Sp.anchorPoint = ccp(0.6,0.45);
    Sp.userData = self;
    [layer addChild:Sp];
    Sp.tag = 2;
    bd.userData = Sp;
    LHand = world->CreateBody(&bd);
    LHand->CreateFixture(&fixtureDef);
    
    // Right
    box.SetAsBox(ptm(25.0f * scale), ptm(5.0f * scale));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(_ragDollPosition.x + (40.0f * scale)), ptm(_ragDollPosition.y - (30.0f * scale)));
    
    Sp = [CCSprite spriteWithFile:@"Hand.png"];
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y);
    Sp.anchorPoint = ccp(0.4,0.45);
    Sp.flipX = true;
    Sp.userData = self;
    [layer addChild:Sp];
    Sp.tag = 2;
    bd.userData = Sp;
    RHand = world->CreateBody(&bd);
    RHand->CreateFixture(&fixtureDef);
    
    // UpperLeg --
    
    // Left
    box.SetAsBox(ptm(8.0f * scale), ptm(35.0f * scale));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(_ragDollPosition.x - (12.0f * scale)), ptm(_ragDollPosition.y - (130.0f * scale)));
    //bd.angle = CC_DEGREES_TO_RADIANS(-10);
    Sp = [CCSprite spriteWithFile:@"Leg.png"];
    //Sp.scale = 0.1f;
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y);
    Sp.anchorPoint = ccp(0.5,0.4);
    //Sp.flipX = true;
    Sp.userData = self;
    Sp.tag = 2;
    [layer addChild:Sp];
    bd.userData = Sp;
    
    LLeg = world->CreateBody(&bd);
    LLeg->CreateFixture(&fixtureDef);
    
    // Right
    box.SetAsBox(ptm(8.0f * scale), ptm(35.0f * scale));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(_ragDollPosition.x + (12.0f * scale)), ptm(_ragDollPosition.y - (130.0f * scale)));
    //bd.angle = CC_DEGREES_TO_RADIANS(10);
    Sp = [CCSprite spriteWithFile:@"Leg.png"];
    //Sp.scale = 0.1f;
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y);
    Sp.anchorPoint = ccp(0.5,0.4);
    Sp.flipX = true;
    Sp.userData = self;
    Sp.tag = 2;
    [layer addChild:Sp];
    bd.userData = Sp;

    
    RLeg = world->CreateBody(&bd);
    RLeg->CreateFixture(&fixtureDef);
    
    
    
    //Body
    
    box.SetAsBox(ptm(20.0f * scale), ptm(30.0f * scale));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(_ragDollPosition.x), ptm(_ragDollPosition.y - (50.0f * scale)));
    bd.angle = 0;
    Sp = [CCSprite spriteWithFile:@"Body.png"];
    //Sp.scale = 0.1f;
    Sp.position = ccp(_ragDollPosition.x,_ragDollPosition.y - (50.0f * scale));
    Sp.userData = self;
    [layer addChild:Sp];
    Sp.tag = 2;
    bd.userData = Sp;
    Body = world->CreateBody(&bd);
    Body->CreateFixture(&fixtureDef);
    
    //Joints ----
    
    b2RevoluteJointDef jd;
    jd.enableLimit = true;
    
     // Head to shoulders
     jd.lowerAngle = -0.0f / (180.0f / M_PI);
     jd.upperAngle = 0.0f / (180.0f / M_PI);
     jd.Initialize(Head,Body, b2Vec2(ptm(_ragDollPosition.x), ptm(_ragDollPosition.y + (0.0f * scale))));
     HJoint =(b2RevoluteJoint *)world->CreateJoint(&jd);
     
     jd.lowerAngle = -60.0f / (180.0f / M_PI);
     jd.upperAngle = 60.0f / (180.0f / M_PI);
     jd.Initialize(LHand,Body, b2Vec2(ptm(_ragDollPosition.x - (20.0f * scale)), ptm(_ragDollPosition.y - (25.0f * scale))));
     LHJoint =(b2RevoluteJoint *)world->CreateJoint(&jd);
     
     jd.lowerAngle = -60.0f / (180.0f / M_PI);
     jd.upperAngle = 60.0f / (180.0f / M_PI);
     jd.Initialize(RHand,Body, b2Vec2(ptm(_ragDollPosition.x + (20.0f * scale)), ptm(_ragDollPosition.y - (25.0f * scale))));
     RHJoint =(b2RevoluteJoint *)world->CreateJoint(&jd);
     
     jd.lowerAngle = -25.0f / (180.0f / M_PI);
     jd.upperAngle = 45.0f / (180.0f / M_PI);
     jd.Initialize(Body,LLeg, b2Vec2(ptm(_ragDollPosition.x - (16.0f * scale)), ptm(_ragDollPosition.y - (75.0f * scale))));
    //jd.localAnchorA = b2Vec2(ptm(-3),ptm(-15));
    LLJoint =(b2RevoluteJoint *)world->CreateJoint(&jd);
     
     // Right
     jd.lowerAngle = -45.0f / (180.0f / M_PI);
     jd.upperAngle = 25.0f / (180.0f / M_PI);
     jd.Initialize(Body, RLeg, b2Vec2(ptm(_ragDollPosition.x + (16.0f * scale)), ptm(_ragDollPosition.y - (75.0f * scale))));
    //jd.localAnchorA = b2Vec2(ptm(3),ptm(-15));
    RLJoint =(b2RevoluteJoint *)world->CreateJoint(&jd);

    myWorld = world;

}*/



-(bool)setDied
{
    if(isAlive)
    {
        b2Filter f;
        
        Head->SetType(b2_dynamicBody);
        f = Head->GetFixtureList()->GetFilterData();
        f.maskBits -= BULLETE_CATAGORY_BIT;
        Head->GetFixtureList()->SetFilterData(f);
        
        f = Body->GetFixtureList()->GetFilterData();
        f.maskBits -= BULLETE_CATAGORY_BIT;
        Body->GetFixtureList()->SetFilterData(f);
        
        f = LHand->GetFixtureList()->GetFilterData();
        f.maskBits -= BULLETE_CATAGORY_BIT;
        LHand->GetFixtureList()->SetFilterData(f);
        
        f = RHand->GetFixtureList()->GetFilterData();
        f.maskBits -= BULLETE_CATAGORY_BIT;
        RHand->GetFixtureList()->SetFilterData(f);
        
        f = LLeg->GetFixtureList()->GetFilterData();
        f.maskBits -= BULLETE_CATAGORY_BIT;
        LLeg->GetFixtureList()->SetFilterData(f);
        
        f = RLeg->GetFixtureList()->GetFilterData();
        f.maskBits -= BULLETE_CATAGORY_BIT;
        RLeg->GetFixtureList()->SetFilterData(f);
        
        //HJoint->EnableLimit(false);
        LHJoint->SetLimits(-80.0f / (180.0f / M_PI), 80.0f / (180.0f / M_PI));
        RHJoint->SetLimits(-80.0f / (180.0f / M_PI), 80.0f / (180.0f / M_PI));
        //LLJoint->EnableLimit(false);
        //RLJoint->EnableLimit(false);
        
        isAlive = false;
        
        [AppDelegate PlaySound:@"zombie-died"];
        
        return true;
    }
    
    return false;
}

-(void)cutHead
{
    if(HJoint != NULL && !myWorld->IsLocked())
    {
        myWorld->DestroyJoint(HJoint);
        HJoint = NULL;
    }
}

-(void)cutHand
{
    if(RHJoint != NULL && LHJoint !=NULL && !myWorld->IsLocked())
    {
        myWorld->DestroyJoint(RHJoint);
        myWorld->DestroyJoint(LHJoint);
        RHJoint = NULL;
        LHJoint = NULL;
    }
}

-(void)cutLeg
{
    if(RLJoint != NULL && LLJoint !=NULL && !myWorld->IsLocked())
    {
        myWorld->DestroyJoint(RLJoint);
        myWorld->DestroyJoint(LLJoint);
        RLJoint = NULL;
        LLJoint = NULL;
    }
}

-(void)dealloc
{
    [super dealloc];
}

@end
