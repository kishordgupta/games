//
//  Bullet.m
//  Zombian
//
//  Created by TheAppGuruz . on 21/05/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "cocos2d.h"
#import "Box2D.h"
#import "GLES-Render.h"
#import "Bullet.h"
#import "HelloWorldLayer.h"
#import "AppDelegate.h"

#define PTM_RATIO 32

#define WALL_CATAGORY_BIT 1
#define BULLETE_CATAGORY_BIT 2
#define ROGDALL_CATAGORY_BIT 4

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


@implementation Bullet

@synthesize count;
@synthesize name;
@synthesize particles;
@synthesize Sp=sp;
@synthesize Streak = myStreak;


-(id)init
{
    if( (self=[super init])) 
    {
        self.count = 10;
        self.name = @"B";
        self.particles = NULL;
        self.Sp = sp;
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

-(CGPoint)toParticle:(CGPoint)origin
{
    return CGPointMake(origin.x * 2400 / 480, origin.y * 1600 / 320);
}
-(id)makeParticles
{
    self.particles = [[CCParticleMeteor alloc]init];
    self.particles.totalParticles = 1000;
    self.particles.gravity = ccp(0,0);
    self.particles.position = ccp(0,0);
    self.particles.posVar = ccp(0,0);
    self.particles.startSize = 30;
    self.particles.endSize = 30;
    self.particles.speed = 0;
    self.particles.scale = 0.2;
    self.particles.life = 0.9;
    self.particles.lifeVar = 0;

    ccColor4F sColor;
    sColor.a = 100;
    sColor.b  = 255;
    sColor.g = 255 * 0.05;
    sColor.r = 0;
    
    ccColor4F eColor;
    sColor.a = 100;
    sColor.b  = 255;
    sColor.g = 255  * 0.05;
    sColor.r = 0;
    
    self.particles.startColor = sColor;
    self.particles.endColor = eColor;
    
    /*self.particles.texture = [[CCTextureCache sharedTextureCache] addImage: @"myball.png"];*/
    return self.particles;
}

-(void)update
{
    if(self.particles != NULL && BBody != NULL)
    {
        CGPoint pos = CGPointMake(mtp(BBody->GetPosition().x), mtp(BBody->GetPosition().y));
        self.particles.sourcePosition = [self toParticle:pos];
        float ang = CC_RADIANS_TO_DEGREES(atan2(-mtp(BBody->GetLinearVelocity().y), -mtp(BBody->GetLinearVelocity().x)));
        self.particles.angle =  ang;
    }

    CGPoint pos = CGPointMake(mtp(BBody->GetPosition().x), mtp(BBody->GetPosition().y));
    myStreak.position = pos;
    myStreak2.position = pos;
    //NSLog(@"in update");
}

-(void)glowAt:(CGPoint)position withScale:(CGSize)size withColor:(ccColor3B)color withRotation:(float)rotation withSprite:(CCSprite*)sprite {
    
    CCSprite *glowSprite = [CCSprite spriteWithFile:@"fire.png"];
    [glowSprite setColor:color];
    [glowSprite setPosition:position];
    [glowSprite setRotation:rotation];
    [glowSprite setBlendFunc: (ccBlendFunc) { GL_ONE, GL_ONE }];
    [glowSprite runAction: [CCRepeatForever actionWithAction:[CCSequence actions:[CCScaleTo actionWithDuration:0.9f scaleX:size.width scaleY:size.height], [CCScaleTo actionWithDuration:0.9f scaleX:size.width*0.75f scaleY:size.height*0.75f], nil] ] ];
    [glowSprite runAction: [CCRepeatForever actionWithAction:[CCSequence actions:[CCFadeTo actionWithDuration:0.9f opacity:150], [CCFadeTo actionWithDuration:0.9f opacity:255], nil]]];
    [sprite addChild:glowSprite];
}

-(void)addBullet:(b2World *)world:(CGPoint)pos:(float)angle:(CCLayer *)layer:(CCMotionStreak *)mStreak
{
    b2BodyDef bd;
    bd.type = b2_dynamicBody;
    
    b2PolygonShape box;
    b2FixtureDef fixtureDef;
    
    // Bullet ------
    b2CircleShape pinShape;
    pinShape.m_radius = ptm(2.0f);
    fixtureDef.shape = &pinShape;
    fixtureDef.density = 0.5f;
    fixtureDef.friction = 0.4f;
    fixtureDef.restitution = 0.5f;
    fixtureDef.filter.categoryBits = BULLETE_CATAGORY_BIT;
    fixtureDef.filter.maskBits = WALL_CATAGORY_BIT + ROGDALL_CATAGORY_BIT;
    bd.position.Set(ptm(pos.x),ptm(pos.y)); 
    sp = [CCSprite spriteWithFile:[self getImgName:@"bullet.png"]];
    sp.scale = 0.5f; if(DeviceType == 1) sp.scale *= 2;
    sp.userData = self;
    sp.tag = 5;
    [layer addChild:sp z:200];
    bd.userData = sp;
    BBody = world->CreateBody(&bd);
    BBody->CreateFixture(&fixtureDef);
    BBody->SetGravityEffect(false);
    
    float ang = CC_DEGREES_TO_RADIANS(angle);
    float velocityX = (float) (cos(ang)) * 15;
    float velocityY = (float) (sin(ang)) * 15;
    //BBody->ApplyLinearImpulse(b2Vec2(velocityX,-velocityY), BBody->GetWorldCenter());
    BBody->SetLinearVelocity(b2Vec2(velocityX,-velocityY));
    BBody->SetBullet(true);
    
    b2MassData mdBullet;
    BBody->GetMassData( &mdBullet );
    mdBullet.mass = 5;
    //md.center.Set(0,0);
    BBody->SetMassData( &mdBullet );
    
    BBody->SetLinearDamping(0);
    myStreak2 = [CCMotionStreak streakWithFade:0.5f minSeg:0 image:@"myball.png" width:1*(DeviceType+1) length:1*(DeviceType+1) color:ccc4(255,255,255,255)];
    [mStreak.parent addChild:myStreak2 z:mStreak.tag+1];
    myStreak = mStreak;
    myStreak.position = pos;
    myStreak2.position = pos;
    //[self glowAt:ccp(0,0) withScale:CGSizeMake(1,1) withColor:ccc3(255, 100, 100) withRotation:0 withSprite:sp]; 

}

-(b2Body *)getBody
{
    return (BBody);
}

-(void)dealloc
{
    [particles release];
    [super dealloc];
}
@end
