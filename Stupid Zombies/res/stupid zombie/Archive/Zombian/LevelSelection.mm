//
//  LevelSelection.m
//  Zombian
//
//  Created by TheAppGuruz . on 31/05/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "HelloWorldLayer.h"
#import "LevelSelection.h"
#import "AppDelegate.h"
#import "MainMenu.h"

@implementation LevelSelection

+(CCScene *) scene
{
	// 'scene' is an autorelease object.
	CCScene *scene = [CCScene node];
	
	// 'layer' is an autorelease object.
	LevelSelection *layer = [LevelSelection node];
	
	// add layer as a child to scene
	[scene addChild: layer];
	
	// return the scene
	return scene;
}

-(id) init
{
	if( (self=[super init])) 
    {
        
        [AppDelegate LoadLevelData];
        
        CGSize size = [[CCDirector sharedDirector] winSize];
        
        CCSprite *bgSprite = [CCSprite spriteWithFile:[AppDelegate getImgName:@"newbg2.png"]];
        bgSprite.position = ccp(size.width/2, size.height/2);
        [self addChild:bgSprite];
        
        CCSprite *HeadSprite = [CCSprite spriteWithFile:[AppDelegate getImgName:@"Level.png"]];
        HeadSprite.position = ccp(size.width/2, size.height - 55);
        if(DeviceType == 1) HeadSprite.position = ccp(size.width/2, size.height - 120);
        [self addChild:HeadSprite];

        [CCMenuItemFont setFontName:@"Helvetica-BoldOblique"];
        [CCMenuItemFont setFontSize:13 * (DeviceType+1)];
        
        
        CCMenu *menu = [CCMenu menuWithItems: nil];
        menu.position = CGPointMake(size.width / 2, size.height / 2 - 45);
        if(DeviceType == 1)menu.position = CGPointMake(size.width / 2, size.height / 2 - 80);
        [self addChild:menu];
        
        aLevels = 10;
        
        int t = 1;
        for(int i=1;i<=4;i++)
        {
            for (int j=1; j<=5; j++) 
            {
                    [self addLevel:t++ :menu];
            }
        }
        
        int total = menu.children.count;
        int rows = 4;
        
        [menu alignItemsInColumns:[NSNumber numberWithUnsignedInt:total/rows],[NSNumber numberWithUnsignedInt:total/rows],[NSNumber numberWithUnsignedInt:total/rows],[NSNumber numberWithUnsignedInt:total/rows], nil];
        
        
        [self addBackButton];
         
    }
    
    return self;
}

-(void)addBackButton
{
    CGSize size = [[CCDirector sharedDirector] winSize];
    CCMenuItemLabel *back = [CCMenuItemImage itemFromNormalImage:[AppDelegate getImgName:@"Back-Button.png"] selectedImage:[AppDelegate getImgName:@"Back-Button.png"] target:self selector:@selector(goBack)];
    CCMenu *menu = [CCMenu menuWithItems:back, nil];
    menu.position = CGPointMake(15, size.height - 15);
    if(DeviceType == 1)menu.position = CGPointMake(30, size.height - 30);
    [self addChild:menu];
}
-(void)goBack
{
    CCTransitionFade *fade = [CCTransitionFade transitionWithDuration:0.5 scene:[MainMenu scene] withColor:ccBLACK];
    [[CCDirector sharedDirector] replaceScene:fade];
}
-(void)addLevel:(int)no:(CCMenu *)Menu
{
    Level Level =  [AppDelegate getLevelData:no];
    //NSLog(@"level is %d - %@",no,Level.isUnlock ? @"yes":@"no");
    
    NSString *name = [AppDelegate getImgName:@"Level-BG.png"];  if(!Level.isUnlock) name = [AppDelegate getImgName:@"Level-Lock.png"];
    
    CCMenuItemImage *Item = [CCMenuItemImage itemFromNormalImage:name selectedImage:name target:self selector:@selector(startGame:)];
    
    //if(no <= aLevels)
    if(Level.isUnlock)
    {
        CCLabelTTF *lbl = [CCLabelTTF labelWithString:[NSString stringWithFormat:@"%d",no] fontName:@"Helvetica-Bold" fontSize:30* (DeviceType+1)];
        lbl.position  = ccp(Item.contentSize.width/2,Item.contentSize.height-20);
        if(DeviceType == 1)
        lbl.position  = ccp(Item.contentSize.width/2,Item.contentSize.height-40);
        lbl.color = ccc3(140,88,33);
        [Item addChild:lbl];
        
        for(int i=1;i<=3;i++)
        {
            NSString *name = i <= Level.Star ? @"Star-Active.png":@"Star-Deactive.png";
            //NSLog(@"%d %@",i,name);
            CCSprite *star = [CCSprite spriteWithFile:[AppDelegate getImgName:name]];
            star.position = ccp(15 * i + 5,10);
            if(DeviceType == 1) star.position = ccp(32 * i + 10,22);
            [Item addChild:star];
        }
    }
    
    [Menu addChild:Item];
    
    Item.tag = no;
}

-(void)startGame:(id)sender
{
    Level Level =  [AppDelegate getLevelData:[sender tag]];
    if(!Level.isUnlock) return;
    
    myGlobalVariable = [sender tag];
    //NSLog(@"in l selection %d",myGlobalVariable);
    CCTransitionFade *fade = [CCTransitionFade transitionWithDuration:0.5 scene:[HelloWorldLayer scene] withColor:ccBLACK];
    [[CCDirector sharedDirector] replaceScene:fade];
}

@end
