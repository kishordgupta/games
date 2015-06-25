//
//  HelloWorldLayer.mm
//  Zombian
//
//  Created by TheAppGuruz . on 15/05/12.
//  Copyright __MyCompanyName__ 2012. All rights reserved.
//


// Import the interfaces
#import "HelloWorldLayer.h"
#import "newContact.h"
#import "Bullet.h"
#import "LevelSelection.h"
#import "Slider.h"
#import "PaushMenu.h"
#import "AppDelegate.h"

//Pixel to metres ratio. Box2D uses metres as the unit for measurement.
//This ratio defines how many pixels correspond to 1 Box2D "metre"
//Box2D is optimized for objects of 1x1 metre therefore it makes sense
//to define the ratio so that your most common object type is 1x1 metre.


// enums that will be used as tags
enum {
	kTagTileMap = 1,
	kTagBatchNode = 1,
	kTagAnimation1 = 1,
};

// World gravity.
#define WORLDGRAVITY 20.0f

#define WALL_CATAGORY_BIT 1
#define ROGDALL_CATAGORY_BIT 4
#define BULLETE_CATAGORY_BIT 2

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

// HelloWorldLayer implementation
int myGlobalVariable;
BOOL isLevelComplete;
int LevelStars = 0;
int LevelScore = 0;
CGSize wSize;

@implementation HelloWorldLayer

@synthesize currentStars;
@synthesize currentScore;
@synthesize sLevel;
@synthesize AllZombies;
@synthesize gBody = gb;

+(CCScene *) scene
{
	// 'scene' is an autorelease object.
	CCScene *scene = [CCScene node];
	
	// 'layer' is an autorelease object.
	HelloWorldLayer *layer = [HelloWorldLayer node];
	
	// add layer as a child to scene
	[scene addChild: layer];    
	
	// return the scene
	return scene;
}
// on "init" you need to initialize your instance
-(id) init
{
	// always call "super" init
	// Apple recommends to re-assign "self" with the "super" return value
	if( (self=[super init])) {
        
        winSize = [[CCDirector sharedDirector] winSize];
        wSize = winSize;
        
		// enable touches
		self.isTouchEnabled = YES;
		
		// enable accelerometer
		//self.isAccelerometerEnabled = YES;
        
        //Allocation-------------
        
        AllZombies = [[NSMutableArray alloc] init];
        bulletes = [[NSMutableArray alloc]init];
        sliders = [[NSMutableArray alloc]init];
        
        //Set Total Bulletes
        totalBullete = 5;
        
        //Setup The world--------
        
        [self setWorld];
        
        
        //Add Objects------------
        
        
        //Add Bounds
        [self addBounds];
        
        //-----------------------
        
        CCSprite *rider = [CCSprite spriteWithFile:[self getImgName:@"Cowboy-Body.png"]];
        rider.scale = 1.0f; if(DeviceType == 1) rider.scale = 0.8f;
        rider.position = ccp([self getRatioX:40],[self getRatioY:30]);
        //rider.position = ccp(50,55);
        [self addChild:rider z:10];
        
        gunSprite = [CCSprite spriteWithFile:[self getImgName:@"Cowboy-Hand.png"]];
        gunSprite.scale = 1.0f; if(DeviceType == 1) gunSprite.scale = 0.8f;
        gunSprite.position = ccp(rider.position.x - [self getRatioX:3],rider.position.y + [self getRatioY:16]);
        //gunSprite.position = ccp(55,55);
        gunSprite.anchorPoint = ccp(0.1,0.3);
        [self addChild:gunSprite z:10];
        
        //Add BackGround
        CCSprite *backGround = [CCSprite spriteWithFile:[self getImgName:@"newbg2.png"]];
        backGround.anchorPoint = ccp(0,0);
        [self addChild:backGround z:-1];
        
        //Add ShotSprite and Rider
        ShotSprite = [CCSprite spriteWithFile:@"Shot_black.png"];
        ShotSprite.scale = 0.8f; if(DeviceType == 1) ShotSprite.scale = 1.0f;
        ShotSprite.position = ccp(0,0);
        ShotSprite.visible =false;
        [self addChild:ShotSprite z:10];
        
    
        //Set Schedule Update------
        
        [self schedule: @selector(tick:) interval:1/60.0f];
        
        //No FPS Display-----------
        [[CCDirector sharedDirector] setDisplayFPS:false];
    
        sLevel = [NSString stringWithFormat:@"Level%d",myGlobalVariable];
        [self LoadLevel:sLevel];
        isLevelComplete = false;
        currentScore = 0;
        currentStars = 0;
        
        ScoreLabel = [CCLabelTTF labelWithString:[NSString stringWithFormat:@"%d",currentScore] fontName:@"Helvetica-Bold" fontSize:20* (DeviceType+1)];
        ScoreLabel.color = ccc3(140,88,33);
        ScoreLabel.position = ccp(winSize.width/2, winSize.height - 15);
        if(DeviceType == 1) ScoreLabel.position = ccp(winSize.width/2, winSize.height - 30);
        [self addChild:ScoreLabel];
        
        //Add BackButton
        CCMenuItemLabel *back = [CCMenuItemImage itemFromNormalImage:[AppDelegate getImgName:@"Back-Button.png"] selectedImage:[AppDelegate getImgName:@"Back-Button.png"] target:self selector:@selector(goBack)];
        CCMenu *backButton = [CCMenu menuWithItems:back, nil];
        backButton.position = CGPointMake(15,winSize.height - 15);
        backButton.tag = 15;
        if(DeviceType == 1)backButton.position = CGPointMake(30, winSize.height - 30);
        [self addChild:backButton];
        
        [self addBulletDisplay:totalBullete];
        [self schedule:@selector(checkLevel) interval:3];
    
        

    }
	return self;
}

-(void)showScoreLableAt:(CGPoint)pos:(int)Scr
{
    CCLabelTTF *lbl = [CCLabelTTF labelWithString:[NSString stringWithFormat:@"%d",Scr] fontName:@"Helvetica-Bold" fontSize:20* (DeviceType+1)];
    lbl.position = pos;
    //lbl.color = ccc3(140,88,33);
    lbl.color = ccBLACK;
    [self addChild:lbl z:100];
    
    [lbl runAction:[CCFadeOut actionWithDuration:1.5f]];

}

-(void)checkLevel
{
    
    if((totalBullete < 1 && bulletes.count < 1))
    {
        [self setLevelOverScene];
    }
    if ([self CheckAliveZombies] < 1 && bulletes.count < 1) 
    {
        isLevelComplete = true;
        [self setLevelOverScene];
    }
}
-(void)setLevelOverScene
{
         if(totalBullete >= 3) currentStars = 3;
    else if(totalBullete >= 2) currentStars = 2;
    else if(totalBullete >= 1) currentStars = 1;
    else                       currentStars = 0;
    
    currentScore += (2000*totalBullete);
    
    LevelStars = currentStars;
    LevelScore = currentScore;
    
    CCTransitionFade *fade = [CCTransitionFade transitionWithDuration:0.5 scene:[PaushMenu scene] withColor:ccBLACK];
    [[CCDirector sharedDirector] replaceScene:fade];
}

-(int)CheckAliveZombies
{
    int tmp = 0;
    for(int z=0;z<AllZombies.count;z++)
    {
        Zombies *zmb = (Zombies *)[AllZombies objectAtIndex:z];
        if(zmb.IsAlive) tmp++;
    }
    return tmp;
}

-(float)getRatioY:(float)val
{
    if(DeviceType == 1)
    {
        return ((val * 2)+((val * 128)/320));
        //return (winSize.height * val / 320)+5;
    }
    
    return val;
}

-(float)getRatioX:(float)val
{
    if(DeviceType == 1)
    {
        //return ((val * 2)+((val * 64)/480));
        return (winSize.width * val / 480);
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

-(void)goBack
{
    [(CCMenu *)self getChildByTag:15].visible = false;
    self.isTouchEnabled = false;
    [[CCDirector sharedDirector] pause];
    
    pauseLayer = [CCLayerColor layerWithColor: ccc4(150, 150, 150, 125) width:winSize.width height:winSize.height];
    pauseLayer.position = CGPointZero;
    [self addChild:pauseLayer z:200];
    
    CCSprite *ss = [CCSprite spriteWithFile:[AppDelegate getImgName:@"Blank-BG.png"]];
    ss.anchorPoint = CGPointZero;
    [pauseLayer addChild:ss];
    
    ss = [CCSprite spriteWithFile:[AppDelegate getImgName:@"paused.png"]];
    ss.position = ccp(winSize.width/2, winSize.height-55);
    if(DeviceType == 1) ss.position = ccp(winSize.width/2, winSize.height - 120);
    [pauseLayer addChild:ss];

    
    CCMenuItemImage *Retry = [CCMenuItemImage itemFromNormalImage:[AppDelegate getImgName:@"retry.png"] selectedImage:[AppDelegate getImgName:@"retry.png"] target:self selector:@selector(doOnMenu:)];
    CCMenuItemImage *Exit = [CCMenuItemImage itemFromNormalImage:[AppDelegate getImgName:@"exit.png"] selectedImage:[AppDelegate getImgName:@"exit.png"] target:self selector:@selector(doOnMenu:)];
    CCMenuItemImage *Resume = [CCMenuItemImage itemFromNormalImage:[AppDelegate getImgName:@"resume.png"] selectedImage:[AppDelegate getImgName:@"resume.png"] target:self selector:@selector(doOnMenu:)];
    
    Retry.tag = 1;
    Exit.tag = 2;
    Resume.tag = 3;

    CCMenu* menu = [CCMenu menuWithItems:Retry,Exit,Resume, nil];
    menu.position = CGPointMake(winSize.width/2,winSize.height/2 - 30);
    [menu alignItemsVerticallyWithPadding:20*(DeviceType+1)];
    [pauseLayer addChild:menu z:105];

}

-(void)doOnMenu:(id)sender
{
    if([sender tag] == 3)
    {
        [(CCMenu *)self getChildByTag:15].visible = true;
        self.isTouchEnabled = true;
        [[CCDirector sharedDirector] resume];
        [self removeChild:pauseLayer cleanup:true];

    }
    else if([sender tag] == 2)
    {
        [[CCDirector sharedDirector] resume];
        CCTransitionFade *fade = [CCTransitionFade transitionWithDuration:0.5 scene:[LevelSelection scene] withColor:ccBLACK];
        [[CCDirector sharedDirector] replaceScene:fade];
    }
    else if([sender tag] == 1)
    {
        [[CCDirector sharedDirector] resume];
        CCTransitionFade *fade = [CCTransitionFade transitionWithDuration:0.5 scene:[HelloWorldLayer scene] withColor:ccBLACK];
        [[CCDirector sharedDirector] replaceScene:fade];
    }
    
    
}

-(void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary *)attributeDict 
{ 
    if ([elementName isEqualToString:@"completeShape"]) aData = [[NSMutableDictionary alloc] init];
    aValue = [[NSMutableString alloc] init];
   
}

-(void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string
{ 
    [aValue appendString:string];
}

-(void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName 
{
    if ([elementName isEqualToString:@"completeShape"]) {
        [allData addObject:aData]; [aData release]; aData = nil;
    } else {        
        [aData setValue:aValue forKey:elementName];
        [aValue release]; aValue = nil;
    }
}

-(void) LoadLevel:(NSString *)Level
{
    NSLog(@"in load level slevel = %@ and level = %@",sLevel,Level);
    allData = [[NSMutableArray alloc] init];
    NSString *path = [[NSBundle mainBundle] pathForResource:Level ofType:@"xml"];
    NSInputStream *strem = [[NSInputStream alloc] initWithFileAtPath:path];
    NSXMLParser *xmlParser = [[NSXMLParser alloc] initWithStream:strem];
    xmlParser.delegate = self; 
    [xmlParser parse];
    
    for (int i=0; i < [allData count]; i++) {
        
        NSString *str = [[allData objectAtIndex:i] valueForKey:@"physicsandID"];
        if([str isEqualToString:@"'ragdoll'"])
        {
            int x = [[[allData objectAtIndex:i]valueForKey:@"xprop"] intValue];
            int y = [[[allData objectAtIndex:i]valueForKey:@"yprop"] intValue];
            
            x = [self getRatioX:x];
            y = [self getRatioY:y];
            
            if(DeviceType == 1)
                y+= 10;
                    
            NSLog(@"pos %d and %d",x,y);
            
            [self addZombieAtPos:CGPointMake(x, winSize.height-y)];
        }
        else if([str isEqualToString:@"'platform'"] || [str isEqualToString:@"'platform2'"])
        {
            int x = [[[allData objectAtIndex:i]valueForKey:@"xprop"] intValue];
            int y = [[[allData objectAtIndex:i]valueForKey:@"yprop"] intValue];
            int h = [[[allData objectAtIndex:i]valueForKey:@"height"] intValue];
            int w = [[[allData objectAtIndex:i]valueForKey:@"width"] intValue];
            float ang = [[[allData objectAtIndex:i]valueForKey:@"rotation"] floatValue];
            h = 7;
        
            x = [self getRatioX:x];
            y = [self getRatioY:y];
            w = [self getRatioX:w];
            h = [self getRatioY:h];
                        
            NSLog(@"pos %d and %d and size %d - %d",x,y,h,w);
            
            [self addStageAtPos:CGPointMake(x, winSize.height-y) :CGSizeMake(w, h):ang :self:([str isEqualToString:@"'platform2'"] ? 2:1)];
        }
        else if([str isEqualToString:@"'box'"] || [str isEqualToString:@"'boxSoft'"])
        {
            int x = [[[allData objectAtIndex:i]valueForKey:@"xprop"] intValue];
            int y = [[[allData objectAtIndex:i]valueForKey:@"yprop"] intValue];
            int h = [[[allData objectAtIndex:i]valueForKey:@"height"] intValue];
            int w = [[[allData objectAtIndex:i]valueForKey:@"width"] intValue];
            
            x = [self getRatioX:x];
            y = [self getRatioY:y];
            w = [self getRatioX:w];
            h = [self getRatioY:h];
            
            w = h;
            
            NSLog(@"pos %d and %d and size %d - %d",x,y,w,h);
            
            [self addBoxAtPos:CGPointMake(x, winSize.height-y):CGSizeMake(w, h) :self:([str isEqualToString:@"'boxSoft'"] ? true:false)];
        }
        else if([str isEqualToString:@"'slider'"])
        {
            int x = [[[allData objectAtIndex:i]valueForKey:@"xprop"] intValue];
            int y = [[[allData objectAtIndex:i]valueForKey:@"yprop"] intValue];
            int h = [[[allData objectAtIndex:i]valueForKey:@"height"] intValue];
            int w = [[[allData objectAtIndex:i]valueForKey:@"width"] intValue];
            int md = [[[allData objectAtIndex:i]valueForKey:@"maxdist"] intValue];
            float spd = [[[allData objectAtIndex:i]valueForKey:@"speed"] floatValue];
            int type = [[[allData objectAtIndex:i]valueForKey:@"type"] intValue];
            BOOL isVerti = [[[allData objectAtIndex:i]valueForKey:@"isVerticle"] boolValue];
            
            x = [self getRatioX:x];
            y = [self getRatioY:y];
            w = [self getRatioX:w];
            h = [self getRatioY:h];
            
            NSLog(@"Slider pos %d and %d and size %d - %d",x,y,h,w);
            
            [self addSliderAtPos:CGPointMake(x, winSize.height-y) :spd:md:type:isVerti];
        }
        else if([str isEqualToString:@"'bPlatform'"])
        {
            int x = [[[allData objectAtIndex:i]valueForKey:@"xprop"] intValue];
            int y = [[[allData objectAtIndex:i]valueForKey:@"yprop"] intValue];
            
            x = [self getRatioX:x];
            y = [self getRatioY:y];
            
            [self addBallAtPos:CGPointMake(x, winSize.height-y)];
        }
    }
    [self transform];
    
    /*
    NSString *path = [[NSBundle mainBundle] pathForResource:Level ofType:@"plist"];
    NSMutableDictionary *data = [[NSMutableDictionary alloc] initWithContentsOfFile:path];
    NSLog(@"%@",data);
    NSDictionary *arr = [data valueForKey:@"Ragdoll"];
    
    for(int i=0;i<arr.count;i++)
    {
        NSString *str = [NSString stringWithFormat:@"Doll%d",i];
        NSLog(@"String is %@",str);
        NSDictionary *pos = [arr valueForKey:str];
        NSLog(@"%@",pos);
        
        int x = [[pos valueForKey:@"X"] intValue];
        int y = [[pos valueForKey:@"Y"] intValue];
        
        NSLog(@"x = %d y = %d",x,y);
       [self addZombieAtPos:CGPointMake(x, y)];
    }

    arr = [data valueForKey:@"Platform"];
    NSLog(@"%@",arr);
    
    for(int i=0;i<arr.count;i++)
    {
        NSString *str = [NSString stringWithFormat:@"PF%d",i];
        NSLog(@"String is %@",str);
        NSDictionary *pos = [arr valueForKey:str];
        NSLog(@"%@",pos);
        
        int x = [[pos valueForKey:@"X"] intValue];
        int y = [[pos valueForKey:@"Y"] intValue];
        int h = [[pos valueForKey:@"H"] intValue];
        int w = [[pos valueForKey:@"W"] intValue];
        
        [self addStageAtPos:CGPointMake(x, y) :CGSizeMake(h, w) :self];
    }*/
}

-(void)setWorld
{
    CGSize screenSize = [CCDirector sharedDirector].winSize;
    CCLOG(@"Screen width %0.2f screen height %0.2f",screenSize.width,screenSize.height);
    
    // Define the gravity vector.
    b2Vec2 gravity;
    gravity.Set(0.0f, -5.0f);
    
    // Do we want to let bodies sleep?
    // This will speed up the physics simulation
    bool doSleep = true;
    
    // Construct a world object, which will hold and simulate the rigid bodies.
    world = new b2World(gravity, doSleep);
    
    cont = new newContact();
    cont->l = self;
    world->SetContactListener(cont);
    
    
    // Debug Draw functions
    m_debugDraw = new GLESDebugDraw( PTM_RATIO );
    //world->SetDebugDraw(m_debugDraw);
    world->SetContinuousPhysics(true);
    world->SetAutoClearForces(false);
    
    uint32 flags = 0;
    flags += b2DebugDraw::e_shapeBit;
    		flags += b2DebugDraw::e_jointBit;
    //		flags += b2DebugDraw::e_aabbBit;
    //		flags += b2DebugDraw::e_pairBit;
    //		flags += b2DebugDraw::e_centerOfMassBit;
    m_debugDraw->SetFlags(flags);		
    
    
    // Define the ground body.
    b2BodyDef groundBodyDef;
    groundBodyDef.position.Set(0, 0); // bottom-left corner
    
    // Call the body factory which allocates memory for the ground body
    // from a pool and creates the ground box shape (also from a pool).
    // The body is also added to the world.
    b2Body* groundBody = world->CreateBody(&groundBodyDef);
    gb = groundBody;
    // Define the ground box shape.
    b2PolygonShape groundBox;
    
    
    // bottom
    groundBox.SetAsEdge(b2Vec2(0,0), b2Vec2(screenSize.width/PTM_RATIO,0));
    groundBody->CreateFixture(&groundBox,0);
    
    // top
    groundBox.SetAsEdge(b2Vec2(0,screenSize.height/PTM_RATIO), b2Vec2(screenSize.width/PTM_RATIO,screenSize.height/PTM_RATIO));
    groundBody->CreateFixture(&groundBox,0);
    
    // left
    groundBox.SetAsEdge(b2Vec2(0,screenSize.height/PTM_RATIO), b2Vec2(0,0));
    groundBody->CreateFixture(&groundBox,0);
    
    // right
    groundBox.SetAsEdge(b2Vec2(screenSize.width/PTM_RATIO,screenSize.height/PTM_RATIO), b2Vec2(screenSize.width/PTM_RATIO,0));
    groundBody->CreateFixture(&groundBox,0);
    
    b2Filter wallFilter;
    wallFilter.categoryBits = WALL_CATAGORY_BIT;
    wallFilter.maskBits = WALL_CATAGORY_BIT + BULLETE_CATAGORY_BIT + ROGDALL_CATAGORY_BIT;
    groundBody->GetFixtureList()->SetFilterData(wallFilter);
}

-(void)addBounds
{
    b2BodyDef bd;
    bd.type = b2_staticBody;

    b2PolygonShape box;
    b2FixtureDef fixtureDef;
    
    b2Body *b;
    
    //Bounds
    
    
    fixtureDef.density = 0.0f;
    fixtureDef.friction = 1.0f;
    fixtureDef.restitution = 0.0f;
    
    //Bottom
    
    box.SetAsBox(ptm(winSize.width/2), ptm([self getRatioY:2]));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(winSize.width/2), ptm([self getRatioY:4]));
    //bd.position.Set(ptm(240), ptm(20));
    bd.angle = 0;
    b = world->CreateBody(&bd);
    b->CreateFixture(&fixtureDef);
    
    //Top
    
    box.SetAsBox(ptm(winSize.width/2), ptm([self getRatioY:2]));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(winSize.width/2), ptm([self getRatioY:300]));
    bd.angle = 0;
    b = world->CreateBody(&bd);
    b->CreateFixture(&fixtureDef);

    //Left
    //box.SetAsBox(ptm(2), ptm(winSize.height/2));
    box.SetAsBox(ptm([self getRatioY:2]), ptm(winSize.height/2));
    fixtureDef.shape = &box;
    //bd.position.Set(ptm([self getRatioX:15]), ptm(winSize.height/2));
    bd.position.Set(ptm([self getRatioX:15]), ptm(winSize.height/2));
    bd.angle = 0;
    b = world->CreateBody(&bd);
    b->CreateFixture(&fixtureDef);
    
    //Right
    
    box.SetAsBox(ptm([self getRatioY:2]), ptm(winSize.height/2));
    fixtureDef.shape = &box;
    bd.position.Set(ptm([self getRatioX:470]), ptm(winSize.height/2));
    bd.angle = 0;
    b = world->CreateBody(&bd);
    b->CreateFixture(&fixtureDef);
    
}

-(void)addBallAtPos:(CGPoint)pos
{
    //pos = CGPointMake([self getRatioX:pos.x], [self getRatioY:pos.y]);
    b2BodyDef bd;
    bd.type = b2_staticBody;
    b2FixtureDef fixtureDef;
    b2CircleShape headShape;
    
    fixtureDef.density =1.0f;
    fixtureDef.friction = 1.0f;
    fixtureDef.restitution = 0.0f;
    
    headShape.m_radius = ptm(30.0*(DeviceType+1));
    fixtureDef.shape = &headShape;
    bd.position.Set(ptm(pos.x), ptm(pos.y));
    
    CCSprite *Sp = [CCSprite spriteWithFile:[self getImgName:@"brick.png"]];
    Sp.position = ccp(pos.x,pos.y);
    Sp.userData = self;
    [self addChild:Sp];
    bd.userData = Sp;
    b2Body *Head = world->CreateBody(&bd);
    Head->CreateFixture(&fixtureDef);
}

-(void)addZombieAtPos:(CGPoint)pos
{
    //pos = CGPointMake([self getRatioX:pos.x], [self getRatioY:pos.y]);
    Zombies *z = [[Zombies alloc]init];
    [z addNewRagDollAtPosition:pos :world :self];
    [self.AllZombies addObject:z];
}
-(void)addSliderAtPos:(CGPoint)pos :(float)speed:(int)MaxDist:(int)type:(BOOL)isVerticle
{
    //pos = CGPointMake([self getRatioX:pos.x], [self getRatioY:pos.y]);
    if(DeviceType == 1)
    {
        MaxDist *= 2;
        speed *= 2;
    }
    Slider *sld = [[Slider alloc] initWithPosAtSpeed:pos :speed :self :world:isVerticle];
    sld.MaxDist = MaxDist;
    sld.Type = type;
    [sliders addObject:sld];
}
-(void)addBoxAtPos:(CGPoint)pos:(CGSize)size:(CCLayer *)Layer:(BOOL)isDynamic
{
    //pos = CGPointMake([self getRatioX:pos.x], [self getRatioY:pos.y]);
    //size = CGSizeMake([self getRatioX:size.width],[self getRatioY:size.height]);
    
    b2BodyDef bd;
    bd.type = b2_staticBody;
    
    if(isDynamic)bd.type = b2_dynamicBody;
    
    
    b2PolygonShape box;
    b2FixtureDef fixtureDef;
    
    fixtureDef.density = 1.0f;
    fixtureDef.friction = 1.0f;
    fixtureDef.restitution = 0.2f;
    
    NSString *fname = [self getImgName:@"hBox.png"];
    if(isDynamic) fname = [self getImgName:@"sBox.png"];
    
    CCSprite *barSprite = [CCSprite spriteWithFile:fname];
    barSprite.position = pos;
    barSprite.tag = (isDynamic ? 8:0);
    [Layer addChild:barSprite];
    
    //CGSize size = CGSizeMake(15, 15);
    box.SetAsBox(ptm(size.width), ptm(size.height));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(pos.x), ptm(pos.y));
    bd.userData = barSprite;
    b2Body *b = world->CreateBody(&bd);
    b->CreateFixture(&fixtureDef);
    
    [barSprite setScaleX: size.width*2/barSprite.contentSize.width];
    [barSprite setScaleY: size.height*2/barSprite.contentSize.height];
}
-(void)addCornerAtPos:(CGPoint)pos :(float)ang:(CCLayer *)Layer
{
    b2BodyDef bd;
    bd.type = b2_staticBody;
    
    b2PolygonShape box;
    b2FixtureDef fixtureDef;
    
    fixtureDef.density = 0.0f;
    fixtureDef.friction = 1.0f;
    fixtureDef.restitution = 0.0f;
    
    CCSprite *barSprite = [CCSprite spriteWithFile:@"corner.png"];
    barSprite.position = pos;
    barSprite.anchorPoint = CGPointMake(0, 0);
    [Layer addChild:barSprite];
    
    b2Vec2 vertisis[3]; 
    vertisis[0].Set(ptm(0),ptm(0));
    vertisis[1].Set(ptm(30),ptm(0));
    vertisis[2].Set(ptm(0),ptm(30));
    box.Set(vertisis,3);
    fixtureDef.shape = &box;
    bd.position.Set(ptm(pos.x), ptm(pos.y));
    bd.angle = CC_DEGREES_TO_RADIANS(ang);
    bd.userData = barSprite;
    b2Body *b = world->CreateBody(&bd);
    b->CreateFixture(&fixtureDef);
}

-(void)addStageAtPos:(CGPoint)pos :(CGSize)size:(float)ang:(CCLayer *)Layer:(int)type
{
    //pos = CGPointMake([self getRatioX:pos.x], [self getRatioY:pos.y]);
    //size = CGSizeMake([self getRatioX:size.width],[self getRatioY:size.height]);
    
    b2BodyDef bd;
    bd.type = b2_staticBody;
    
    b2PolygonShape box;
    b2FixtureDef fixtureDef;
    
    NSString *name = [self getImgName:@"nbar3.png"];
    
    switch (type) {
        case 1:
            if(size.width < 50*(DeviceType+1))
                name = [self getImgName:@"nbar1.png"];
            else if(size.width < 100*(DeviceType+1))
                name = [self getImgName:@"nbar2.png"];
            else if(size.width < 150*(DeviceType+1))
                name = [self getImgName:@"nbar6.png"];
            break;
            
        case 2:
            if(size.width < 50*(DeviceType+1))
                name = [self getImgName:@"sbar1.png"];
            else if(size.width < 100*(DeviceType+1))
                name = [self getImgName:@"sbar2.png"];
            else if(size.width < 150*(DeviceType+1))
                name = [self getImgName:@"sbar5.png"];
            break;
            
        default:
            break;
    }
    NSLog(@"s name %@",name);
    CCSprite *barSprite = [CCSprite spriteWithFile:name];
    [Layer addChild:barSprite];
    
    fixtureDef.density = 1.0f;
    fixtureDef.friction = 1.0f;
    fixtureDef.restitution = 0.0f;
    
    box.SetAsBox(ptm(size.width), ptm(size.height));
    fixtureDef.shape = &box;
    bd.position.Set(ptm(pos.x), ptm(pos.y));
    //bd.angle = CC_DEGREES_TO_RADIANS(ang);
    bd.angle = CC_DEGREES_TO_RADIANS(ang);
    bd.userData = barSprite;
    b2Body *b = world->CreateBody(&bd);
    b->CreateFixture(&fixtureDef);
    
    [barSprite setScaleX: size.width*2/barSprite.contentSize.width];
    //[barSprite setScaleY: size.height*2/barSprite.contentSize.height];
    
}

-(void) draw
{
	// Default GL states: GL_TEXTURE_2D, GL_VERTEX_ARRAY, GL_COLOR_ARRAY, GL_TEXTURE_COORD_ARRAY
	// Needed states:  GL_VERTEX_ARRAY, 
	// Unneeded states: GL_TEXTURE_2D, GL_COLOR_ARRAY, GL_TEXTURE_COORD_ARRAY
	glDisable(GL_TEXTURE_2D);
	glDisableClientState(GL_COLOR_ARRAY);
	glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	
	world->DrawDebugData();
	
	// restore default GL states
	glEnable(GL_TEXTURE_2D);
	glEnableClientState(GL_COLOR_ARRAY);
	glEnableClientState(GL_TEXTURE_COORD_ARRAY);

}


-(void) tick: (ccTime) dt
{
	//It is recommended that a fixed time step is used with Box2D for stability
	//of the simulation, however, we are using a variable time step here.
	//You need to make an informed choice, the following URL is useful
	//http://gafferongames.com/game-physics/fix-your-timestep/
	
	int32 velocityIterations = 8;
	int32 positionIterations = 1;
    
	// Instruct the world to perform a single step of simulation. It is
	// generally best to keep the time step and iterations fixed.
	world->Step(dt, velocityIterations, positionIterations);

	
	//Iterate over the bodies in the physics world
	for (b2Body* b = world->GetBodyList(); b; b = b->GetNext())
	{
		if (b->GetUserData() != NULL) {
			//Synchronize the AtlasSprites position and rotation with the corresponding body
			CCSprite *myActor = (CCSprite*)b->GetUserData();
			myActor.position = CGPointMake( b->GetPosition().x * PTM_RATIO, b->GetPosition().y * PTM_RATIO);
			myActor.rotation = -1 * CC_RADIANS_TO_DEGREES(b->GetAngle());
		}	
	}
    
    if([bulletes count] > 0)
    {
        
        for(int i=0;i<[bulletes count];i++)
        {
            Bullet *b = (Bullet *)[bulletes objectAtIndex:i];
            [b update];

            for (int x=0; x<self.AllZombies.count; x++) 
            {
                Zombies *z = [AllZombies objectAtIndex:x];
                if(z.isHead == false)
                {
                    [z cutHead];
                }
                if(z.isLeg == false)
                {
                    [z cutLeg];
                }
                if(z.isHand == false)
                {
                    [z cutHand];
                }
            }
            
            if([b count] > 0)
            {
                b2Body *bullete = [b getBody];
                float speed =  15.0f;                               if(DeviceType == 1) speed*=2;
                
                b2Vec2 curvel = bullete->GetLinearVelocity();
                if (curvel.Length() < speed  || curvel.Length() > speed+ 0.25f)
                {
                    float curspeed = curvel.Normalize();
                    float velChange = speed - curspeed;
                    float impulse = (bullete->GetMass())* velChange;
                    curvel *= (impulse);
                    bullete->ApplyLinearImpulse(curvel, bullete->GetPosition());
                }
                
                ((CCSprite *)bullete->GetUserData()).rotation = CC_RADIANS_TO_DEGREES(atan2f(-bullete->GetLinearVelocity().y, bullete->GetLinearVelocity().x));

                //NSLog(@"velocity x = %f, y = %f",bullete->GetLinearVelocity().x,bullete->GetLinearVelocity().y);
            }
            else 
            {
                world->DestroyBody([b getBody]);
                //[self removeChild:b.particles cleanup:true];
                b.particles.duration = 0;
                [self removeChild:b.Sp cleanup:true];
                [bulletes removeObject:b];
            }
            
        }
    }
    for(int t=0;t<sliders.count;t++)
    {
        Slider *sl = (Slider *)[sliders objectAtIndex:t];
        [sl UpdateMe:dt];
    }
    
    [self UpdateScoreBoadr];
}

-(void)UpdateScoreBoadr
{
    [ScoreLabel setString:[NSString stringWithFormat:@"%d",currentScore]];
}
-(void)removeBullet:(int)tag
{
    [self removeChildByTag:200+tag cleanup:true];
}
-(void)addBulletDisplay:(int)n
{
    int tZ = 200;
    CCSprite *bSprite;
    for(int i=0;i<n;i++)
    {
        int t = tZ + i;
        bSprite = [CCSprite spriteWithFile:@"displaybullet.png"];
        bSprite.scale = 0.5f; if(DeviceType == 1) bSprite.scale *= 2;
        bSprite.position = ccp([self getRatioX:415] + (i*[self getRatioX:10]),[self getRatioY:305]);
        bSprite.tag = t;
        [self addChild:bSprite z:i+10];
        [bSprite setColor:ccRED];
    }
}

-(void)ccTouchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    UITouch *touch = [touches anyObject];
    CGPoint location = [touch locationInView: [touch view]];
    location = CGPointMake(location.x - 30, location.y + 30);
    float xdif = (location.x - (gunSprite.position.x));
    float ydif = ((winSize.height-location.y) - (gunSprite.position.y));
    float ang = CC_RADIANS_TO_DEGREES(atan2f(-ydif, xdif));
    if(ang < -90)ang = -90;
    if(ang >  90)ang =  90;
    gunSprite.rotation = ang;
    ShotSprite.position = ccp(location.x, winSize.height-location.y);
    ShotSprite.visible = true;
}
-(void)ccTouchesMoved:(NSSet *)touches withEvent:(UIEvent *)event
{
    UITouch *touch = [touches anyObject];
    CGPoint location = [touch locationInView: [touch view]];
    location = CGPointMake(location.x - 30, location.y + 30);
    float xdif = (location.x - (gunSprite.position.x));
    float ydif = ((winSize.height-location.y) - (gunSprite.position.y));
    float ang = CC_RADIANS_TO_DEGREES(atan2f(-ydif, xdif));
    if(ang < -90)ang = -90;
    if(ang >  90)ang =  90;
    gunSprite.rotation = ang;
    ShotSprite.position = ccp(location.x, winSize.height-location.y);

}

- (void)ccTouchesEnded:(NSSet *)touches withEvent:(UIEvent *)event
{
	//Add a new body/atlas sprite at the touched location
	for( UITouch *touch in touches ) {
		CGPoint location = [touch locationInView: [touch view]];
		
		location = [[CCDirector sharedDirector] convertToGL: location];
    
        CCMotionStreak *st = [CCMotionStreak streakWithFade:0.5f minSeg:0 image:@"myball.png" width:5*(DeviceType+1) length:5*(DeviceType+1) color:ccc4(140,88,33,255)];
        [self addChild:st];
    
        if(totalBullete > 0)
        {
            Bullet *b = [[[Bullet alloc] init] autorelease];
            [b addBullet:world :ccp(gunSprite.position.x, gunSprite.position.y) :gunSprite.rotation:self:st];
            [bulletes addObject:b];
        //  [self addChild:[b makeParticles]];
            
            [self removeBullet:totalBullete-1];
            totalBullete--;
            
            //Music
            [AppDelegate PlaySound:@"bullet-fire"];
            
        }
        
        ShotSprite.position = location;
        ShotSprite.visible = false;
        
    }
    
}



- (void)accelerometer:(UIAccelerometer*)accelerometer didAccelerate:(UIAcceleration*)acceleration
{	
	static float prevX=0, prevY=0;
	
	//#define kFilterFactor 0.05f
#define kFilterFactor 1.0f	// don't use filter. the code is here just as an example
	
	float accelX = (float) acceleration.x * kFilterFactor + (1- kFilterFactor)*prevX;
	float accelY = (float) acceleration.y * kFilterFactor + (1- kFilterFactor)*prevY;
	
	prevX = accelX;
	prevY = accelY;
	
	// accelerometer values are in "Portrait" mode. Change them to Landscape left
	// multiply the gravity by 10
	b2Vec2 gravity( -accelY * 10, accelX * 10);
	
	world->SetGravity( gravity );
}

// on "dealloc" you need to release all your retained objects
- (void) dealloc
{
	// in case you have something to dealloc, do it in this method
	delete world;
	world = NULL;
	
	delete m_debugDraw;
    [zombies removeAllObjects];
    [bulletes removeAllObjects];
    [zombies release];
    [bulletes release];
    [self removeAllChildrenWithCleanup:true];
	// don't forget to call "super dealloc"
	[super dealloc];
}
@end
