//
//  HelloWorldLayer.h
//  Zombian
//
//  Created by TheAppGuruz . on 15/05/12.
//  Copyright __MyCompanyName__ 2012. All rights reserved.
//


// When you import this file, you import all the cocos2d classes
#import <AVFoundation/AVAudioPlayer.h>
#import "cocos2d.h"
#import "Box2D.h"
#import "GLES-Render.h"
#import "newContact.h"
#import "Zombies.h"
#import "Slider.h"

extern BOOL isLevelComplete;
extern int myGlobalVariable;
extern int LevelStars;
extern int LevelScore;
extern CGSize wSize;
// HelloWorldLayer
@interface HelloWorldLayer : CCLayer <NSXMLParserDelegate,UIGestureRecognizerDelegate>
{
	b2World* world;
	GLESDebugDraw *m_debugDraw;
    newContact *cont;
    NSString *s;
    CCSprite *gunSprite,*ShotSprite;
    
    NSMutableArray *bulletes,*zombies,*sliders;
    
    NSMutableArray *allData; NSDictionary *aData; NSMutableString *aValue;
    b2Body *gB;
    int totalBullete;
    CGSize winSize;
    
    AVAudioPlayer *bgMusic,*bulletMusic;
    CCLayer *pauseLayer;
    CCLabelTTF *ScoreLabel;
    
}
@property (nonatomic,assign)int currentScore;
@property (nonatomic,assign)int currentStars;
@property (nonatomic,assign)b2Body *gBody;
@property (nonatomic,retain)NSMutableArray *AllZombies;
@property (nonatomic,assign)NSString *sLevel;
// returns a CCScene that contains the HelloWorldLayer as the only child
+(CCScene *) scene;
// adds a new sprite at a given coordinate
-(void)LoadLevel:(NSString *)Level;
-(void)setWorld;
-(void)addZombieAtPos:(CGPoint)pos;
-(void)addBoxAtPos:(CGPoint)pos:(CGSize)size:(CCLayer *)Layer:(BOOL)isDynamic;
-(void)addStageAtPos:(CGPoint)pos:(CGSize)size:(float)ang:(CCLayer *)Layer:(int)type;
-(void)addCornerAtPos:(CGPoint)pos :(float)ang:(CCLayer *)Layer;
-(void)addSliderAtPos:(CGPoint)pos:(float)speed:(int)MaxDist:(int)type:(BOOL)isVerticle;
-(void)addBounds;
-(void)addBallAtPos:(CGPoint)pos;

-(void)UpdateScoreBoadr;
-(void)addBulletDisplay:(int)n;
-(NSString *)getImgName:(NSString *)str;
-(float)getRatioX:(float)val;
-(float)getRatioY:(float)val;
-(void)setLevelOverScene;
-(int)CheckAliveZombies;

-(void)showScoreLableAt:(CGPoint)pos:(int)Scr;
@end
