//
//  MainMenu.m
//  Ancient Bullet War
//
//  Created by TheAppGuruz . on 15/06/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "MainMenu.h"
#import "cocos2d.h"
#import "HelloWorldLayer.h"
#import "LevelSelection.h"
#import "AppDelegate.h"

@implementation MainMenu

+(CCScene *) scene
{
	// 'scene' is an autorelease object.
	CCScene *scene = [CCScene node];
	
	// 'layer' is an autorelease object.
	MainMenu *layer = [MainMenu node];
	
	// add layer as a child to scene
	[scene addChild: layer];
	
	// return the scene
	return scene;
}

-(id) init
{
	if( (self=[super init])) 
    {
        if (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad) DeviceType = 1;

        CGSize pos = [[CCDirector sharedDirector] winSize];
        
        //set back Ground
        CCSprite *bgSprite = [CCSprite spriteWithFile:[AppDelegate getImgName:@"MainMenuImg.png"]];
        bgSprite.position = ccp(pos.width/2, pos.height/2);
        [self addChild:bgSprite z:0];
            
        [self addMenu];
        
    }
    
    return self;
}

-(void)addMenu
{
    CGSize pos = [[CCDirector sharedDirector] winSize];
    
    CCMenuItemImage *Play = [CCMenuItemImage itemFromNormalImage:[AppDelegate getImgName:@"PlayButton1.png"] selectedImage:[AppDelegate getImgName:@"PlayButton1.png"] target:self selector:@selector(goNext:)];
    CCMenuItemImage *MusicOn = [CCMenuItemImage itemFromNormalImage:[AppDelegate getImgName:@"Music-On1.png"] selectedImage:[AppDelegate getImgName:@"Music-On1.png"] target:self selector:@selector(goNext:)];
    CCMenuItemImage *MusicOff = [CCMenuItemImage itemFromNormalImage:[AppDelegate getImgName:@"Music-Off1.png"] selectedImage:[AppDelegate getImgName:@"Music-Off1.png"] target:self selector:@selector(goNext:)];
    
    CCMenuItemToggle *Music = [CCMenuItemToggle itemWithTarget:self selector:@selector(goNext:) items:MusicOn,MusicOff,nil];
    
    Music.selectedIndex = onGameSound ? 0:1;
    
    Play.tag = 1;
    Music.tag = 2;
    
    CCMenu* menu = [CCMenu menuWithItems:Music,Play, nil];
    
    menu.position = CGPointMake(0,0);
    
    Play.position = CGPointMake(pos.width/2 + 15,pos.height/2 - 50);
    Music.position = ccp(pos.width - 30,20);
    
    menu.position = CGPointMake(0,0);
    
    if(DeviceType == 1)
    {
        Play.position = CGPointMake(pos.width/2 + 50,pos.height/2 - 165);
        Music.position = ccp(pos.width - 70,50);
    }
    [self addChild:menu];
}

-(void)goNext:(id)sender
{
    CCTransitionFade *fade;
    
    if([sender tag] == 1)
    {
        fade = [CCTransitionFade transitionWithDuration:0.5 scene:[LevelSelection scene] withColor:ccBLACK];
        [[CCDirector sharedDirector] replaceScene:fade];
    }
    else if([sender tag] == 2) {
        
        onGameSound = !onGameSound;
        
    }
}

@end
