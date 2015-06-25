//
//  PaushMenu.m
//  Zombian
//
//  Created by TheAppGuruz . on 12/06/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "PaushMenu.h"
#import "cocos2d.h"
#import "LevelSelection.h"
#import "HelloWorldLayer.h"
#import "AppDelegate.h"


@implementation PaushMenu

+(CCScene *) scene
{
	// 'scene' is an autorelease object.
	CCScene *scene = [CCScene node];
	
	// 'layer' is an autorelease object.
	PaushMenu *layer = [PaushMenu node];
	
	// add layer as a child to scene
	[scene addChild: layer];
	
	// return the scene
	return scene;
}

-(id) init
{
	if( (self=[super init])) 
    {
        CGSize pos = [[CCDirector sharedDirector] winSize];
        
        CCSprite *bgSprite = [CCSprite spriteWithFile:[AppDelegate getImgName:@"newbg2.png"]];
        bgSprite.position = ccp(pos.width/2, pos.height/2);
        [self addChild:bgSprite];
        
        NSString *name = [AppDelegate getImgName:@"Level-Complete.png"];
        
        if(isLevelComplete == false)
            name = [AppDelegate getImgName:@"Level-Failed.png"];
        
        CCSprite *HeadSprite = [CCSprite spriteWithFile:name];
        HeadSprite.position = ccp(pos.width/2, pos.height - 55);
        if(DeviceType == 1) HeadSprite.position = ccp(pos.width/2, pos.height - 120);
        [self addChild:HeadSprite];

        
        CCMenuItemImage *Retry = [CCMenuItemImage itemFromNormalImage:[AppDelegate getImgName:@"retry.png"] selectedImage:[AppDelegate getImgName:@"retry.png"] target:self selector:@selector(goBack:)];
        CCMenuItemImage *Exit = [CCMenuItemImage itemFromNormalImage:[AppDelegate getImgName:@"exit.png"] selectedImage:[AppDelegate getImgName:@"exit.png"] target:self selector:@selector(goBack:)];
        CCMenuItemImage *Next = [CCMenuItemImage itemFromNormalImage:[AppDelegate getImgName:@"nextlevel.png"] selectedImage:[AppDelegate getImgName:@"nextlevel.png"] target:self selector:@selector(goBack:)];
        
        Retry.tag = 1;
        Exit.tag = 2;
        Next.tag = 3;
        
        //CCMenu* menu = [CCMenu menuWithItems:f1,f2,f3, nil];
        CCMenu* menu = [CCMenu menuWithItems:Retry,Exit,Next, nil];
        menu.position = CGPointMake(pos.width/2,pos.height/2 - 30);
        [menu alignItemsVerticallyWithPadding:20*(DeviceType+1)];
        [self addChild:menu];
        
        [self updateDataOfLevel];
        
        if (isLevelComplete == false) {
            Next.visible = false;
        }
        if((myGlobalVariable+1) > MaxLevels)
            Next.visible = false;
        else
        {
            Level nL = [AppDelegate getLevelData:myGlobalVariable+1];
            if(nL.isUnlock)
                Next.visible = true;
        }
        
        
                
        Level LData = [AppDelegate getLevelData:myGlobalVariable];
        
        CCSprite *SBG = [CCSprite spriteWithFile:[AppDelegate getImgName:@"HScore-BG.png"]];
        SBG.position = ccp(pos.width/2 - 150*(DeviceType+1), pos.height/2);
        SBG.anchorPoint = ccp(0.5,0.75);
        [self addChild:SBG];
        
        CCSprite *HSBG = [CCSprite spriteWithFile:[AppDelegate getImgName:@"Score-BG.png"]];
        HSBG.position = ccp(pos.width/2 + 150*(DeviceType+1), pos.height/2);
        HSBG.anchorPoint = ccp(0.5,0.75);
        [self addChild:HSBG];
        
        if(DeviceType == 0)
            SBG.scale = HSBG.scale = 0.4f;
    
        
        CCLabelTTF *HScore = [CCLabelTTF labelWithString:@"Highscore" fontName:@"Helvetica-Bold" fontSize:20*(DeviceType+1)];
        CCLabelTTF *CScore = [CCLabelTTF labelWithString:@"Score" fontName:@"Helvetica-Bold" fontSize:20*(DeviceType+1)];
        
        CScore.color = HScore.color = ccc3(140,88,33);
        CScore.color = HScore.color = ccc3(0,0,0);
        
        HScore.position = ccp(pos.width/2 - 150*(DeviceType+1),pos.height/2);
        CScore.position = ccp(pos.width/2 + 150*(DeviceType+1),pos.height/2);
        
        //[self addChild:HScore];
        //[self addChild:CScore];
        
        CCLabelTTF *lblHScore = [CCLabelTTF labelWithString:[NSString stringWithFormat:@"%d",LData.Score] fontName:@"Helvetica-Bold" fontSize:20* (DeviceType+1)];
        CCLabelTTF *lblCScore = [CCLabelTTF labelWithString:[NSString stringWithFormat:@"%d",LevelScore] fontName:@"Helvetica-Bold" fontSize:20* (DeviceType+1)];
        
        lblHScore.position = ccp(HScore.position.x, HScore.position.y - 25*(DeviceType+1));
        lblCScore.position = ccp(CScore.position.x, CScore.position.y - 25*(DeviceType+1));
        
        lblCScore.color = lblHScore.color = ccc3(140,88,33);
        
        [self addChild:lblCScore];
        [self addChild:lblHScore];
        
        int t = lblHScore.position.x - (40*(DeviceType+1));
        
        for(int i=1;i<=3;i++)
        {
            NSString *name = i <= LData.Star ? @"Star-Active.png":@"Star-Deactive.png";
            CCSprite *star = [CCSprite spriteWithFile:[AppDelegate getImgName:name]];
            star.position = ccp(t + (i * 20*(DeviceType+1)),lblHScore.position.y - (25*(DeviceType+1)));
            [self addChild:star];
        }
        
        t = lblCScore.position.x - (40*(DeviceType+1));
        
        for(int i=1;i<=3;i++)
        {
            NSString *name = i <= LevelStars ? @"Star-Active.png":@"Star-Deactive.png";
            CCSprite *star = [CCSprite spriteWithFile:[AppDelegate getImgName:name]];
            star.position = ccp(t + (i * 20*(DeviceType+1)),lblCScore.position.y - (25*(DeviceType+1)));
            [self addChild:star];
        }
        
        
    }
    return self;
}
-(void)goBack:(id)sender
{
    CCTransitionFade *fade;
    switch ([sender tag]) {
        case 1:
            //NSLog(@"in l retry %d",myGlobalVariable);
            fade = [CCTransitionFade transitionWithDuration:0.5 scene:[HelloWorldLayer scene] withColor:ccBLACK];
            [[CCDirector sharedDirector] replaceScene:fade];
            break;
            
        case 2:
            //NSLog(@"in l exit %d",myGlobalVariable);
            fade = [CCTransitionFade transitionWithDuration:0.5 scene:[LevelSelection scene] withColor:ccBLACK];
            [[CCDirector sharedDirector] replaceScene:fade];
            break;
            
        case 3:
            myGlobalVariable += 1;
            //NSLog(@"in l next level %d",myGlobalVariable);
            fade = [CCTransitionFade transitionWithDuration:0.5 scene:[HelloWorldLayer scene] withColor:ccBLACK];
            [[CCDirector sharedDirector] replaceScene:fade];
            break;
            
        default:
            break;
    }
    
}
-(void)updateDataOfLevel
{
    if (isLevelComplete) 
    {
        [AppDelegate setLevelUnlock:myGlobalVariable+1];
        Level LData = [AppDelegate getLevelData:myGlobalVariable];
        
        if(LData.Score < LevelScore)
        {
            [AppDelegate setLevelScore:myGlobalVariable :LevelScore];
            NHS = [CCSprite spriteWithFile:[AppDelegate getImgName:@"NewHScore.png"]];
            CGSize pos = [[CCDirector sharedDirector] winSize];
            NHS.position = ccp(pos.width/2,35*(DeviceType+1));
            [self addChild:NHS];
            [NHS runAction:[CCRepeatForever actionWithAction:[CCBlink actionWithDuration:1.0f blinks:1]]];

        }
        
        
        if(LData.Star < LevelStars)
            [AppDelegate setLevelStars:myGlobalVariable :LevelStars];
        
    }
    
    [AppDelegate LoadLevelData];
}
@end
