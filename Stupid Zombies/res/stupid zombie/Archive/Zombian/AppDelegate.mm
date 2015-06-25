//
//  AppDelegate.m
//  Zombian
//
//  Created by TheAppGuruz . on 15/05/12.
//  Copyright __MyCompanyName__ 2012. All rights reserved.
//

#import "cocos2d.h"

#import "AppDelegate.h"
#import "GameConfig.h"
#import "HelloWorldLayer.h"
#import "LevelSelection.h"
#import "RootViewController.h"
#import "SimpleAudioEngine.h"
#import "MainMenu.h"

BOOL onGameSound = true;
int DeviceType = 0;
int MaxLevels = 0;
Level allLevels[21];


@implementation AppDelegate


@synthesize window;

- (void) removeStartupFlicker
{

	//
	// THIS CODE REMOVES THE STARTUP FLICKER
	//
	// Uncomment the following code if you Application only supports landscape mode
	//
#if GAME_AUTOROTATION == kGameAutorotationUIViewController
	
	//	CC_ENABLE_DEFAULT_GL_STATES();
	//	CCDirector *director = [CCDirector sharedDirector];
	//	CGSize size = [director winSize];
	//	CCSprite *sprite = [CCSprite spriteWithFile:@"Default.png"];
	//	sprite.position = ccp(size.width/2, size.height/2);
	//	sprite.rotation = -90;
	//	[sprite visit];
	//	[[director openGLView] swapBuffers];
	//	CC_ENABLE_DEFAULT_GL_STATES();
	
#endif // GAME_AUTOROTATION == kGameAutorotationUIViewController	
}

- (void) applicationDidFinishLaunching:(UIApplication*)application
{
	// Init the window
	window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
	
	// Try to use CADisplayLink director
	// if it fails (SDK < 3.1) use the default director
	if( ! [CCDirector setDirectorType:kCCDirectorTypeDisplayLink] )
		[CCDirector setDirectorType:kCCDirectorTypeDefault];
	
	
	CCDirector *director = [CCDirector sharedDirector];
	
	// Init the View Controller
	viewController = [[RootViewController alloc] initWithNibName:nil bundle:nil];
	viewController.wantsFullScreenLayout = YES;
	
	//
	// Create the EAGLView manually
	//  1. Create a RGB565 format. Alternative: RGBA8
	//	2. depth format of 0 bit. Use 16 or 24 bit for 3d effects, like CCPageTurnTransition
	//
	//
	EAGLView *glView = [EAGLView viewWithFrame:[window bounds]
								   pixelFormat:kEAGLColorFormatRGB565	// kEAGLColorFormatRGBA8
								   depthFormat:0						// GL_DEPTH_COMPONENT16_OES
						];
	
	// attach the openglView to the director
	[director setOpenGLView:glView];
	
//	// Enables High Res mode (Retina Display) on iPhone 4 and maintains low res on all other devices
//	if( ! [director enableRetinaDisplay:YES] )
//		CCLOG(@"Retina Display Not supported");
	
	//
	// VERY IMPORTANT:
	// If the rotation is going to be controlled by a UIViewController
	// then the device orientation should be "Portrait".
	//
	// IMPORTANT:
	// By default, this template only supports Landscape orientations.
	// Edit the RootViewController.m file to edit the supported orientations.
	//
#if GAME_AUTOROTATION == kGameAutorotationUIViewController
	[director setDeviceOrientation:kCCDeviceOrientationPortrait];
#else
	[director setDeviceOrientation:kCCDeviceOrientationLandscapeLeft];
#endif
	
	[director setAnimationInterval:1.0/60];
	[director setDisplayFPS:NO];
	
	
	// make the OpenGLView a child of the view controller
	[viewController setView:glView];
	
	// make the View Controller a child of the main window
	[window addSubview: viewController.view];
	
	[window makeKeyAndVisible];
	
	// Default texture format for PNG/BMP/TIFF/JPEG/GIF images
	// It can be RGBA8888, RGBA4444, RGB5_A1, RGB565
	// You can change anytime.
	[CCTexture2D setDefaultAlphaPixelFormat:kCCTexture2DPixelFormat_RGBA8888];

	
	// Removes the startup flicker
	[self removeStartupFlicker];
	
	// Run the intro Scene
	//[[CCDirector sharedDirector] runWithScene: [HelloWorldLayer scene]];
    [[CCDirector sharedDirector] runWithScene: [MainMenu scene]];
    
    onGameSound = true;
    
    NSString *strPath = [[NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0] 
                         stringByAppendingPathComponent:@"LevelData.plist"];
    NSFileManager *fileManager = [NSFileManager defaultManager];
    
    if(![fileManager fileExistsAtPath:strPath])
    {
        NSString *fromPath = [[[NSBundle mainBundle] resourcePath] stringByAppendingPathComponent:@"LevelData.plist"];
        [fileManager copyItemAtPath:fromPath toPath:strPath error:nil];
        [fileManager release];
    }
}


- (void)applicationWillResignActive:(UIApplication *)application {
	[[CCDirector sharedDirector] pause];
}

- (void)applicationDidBecomeActive:(UIApplication *)application {
	[[CCDirector sharedDirector] resume];
}

- (void)applicationDidReceiveMemoryWarning:(UIApplication *)application {
	[[CCDirector sharedDirector] purgeCachedData];
}

-(void) applicationDidEnterBackground:(UIApplication*)application {
	[[CCDirector sharedDirector] stopAnimation];
}

-(void) applicationWillEnterForeground:(UIApplication*)application {
	[[CCDirector sharedDirector] startAnimation];
}

- (void)applicationWillTerminate:(UIApplication *)application {
	CCDirector *director = [CCDirector sharedDirector];
	
	[[director openGLView] removeFromSuperview];
	
	[viewController release];
	
	[window release];
	
	[director end];	
}

- (void)applicationSignificantTimeChange:(UIApplication *)application {
	[[CCDirector sharedDirector] setNextDeltaTimeZero:YES];
}

- (void)dealloc {
	[[CCDirector sharedDirector] end];
	[window release];
	[super dealloc];
}

+(void)LoadLevelData
{
    NSArray *arrPath = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *path = [[arrPath objectAtIndex:0] stringByAppendingPathComponent:@"LevelData.plist"];
    //NSString *path = [[NSBundle mainBundle] pathForResource:@"LevelData" ofType:@"plist"];
    NSMutableDictionary *data = [[NSMutableDictionary alloc] initWithContentsOfFile:path];
    NSDictionary *arr = [data valueForKey:@"Levels"];
    for(int i=0;i<arr.count;i++)
    {
        NSString *str = [NSString stringWithFormat:@"Level%d",i+1];
        NSMutableDictionary *Ldata = [arr valueForKey:str];

        bool isUnlock = [[Ldata valueForKey:@"isUnlock"] boolValue];
        int Score = [[Ldata valueForKey:@"Score"] intValue];
        int Stars = [[Ldata valueForKey:@"Stars"] intValue];
        
        allLevels[i+1].Score = Score;
        allLevels[i+1].Star = Stars;
        allLevels[i+1].isUnlock = isUnlock;

    }
    
    MaxLevels = arr.count;

}

+(void)setLevelUnlock:(int)no
{
    NSArray *arrPath = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *path = [[arrPath objectAtIndex:0] stringByAppendingPathComponent:@"LevelData.plist"];
    
    NSMutableDictionary *data = [[NSMutableDictionary alloc] initWithContentsOfFile:path];
    NSDictionary *arr = [data valueForKey:@"Levels"];
    NSString *str = [NSString stringWithFormat:@"Level%d",no];
    NSMutableDictionary *dict = [arr valueForKey:str];
    [dict setValue:[NSNumber numberWithBool:YES] forKey:@"isUnlock"];
    [arr setValue:dict forKey:str];
    [data setValue:arr forKey:@"Levels"];
    [data writeToFile:path atomically:YES];

}

+(void)setLevelStars:(int)no:(int)Stars
{
    NSArray *arrPath = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *path = [[arrPath objectAtIndex:0] stringByAppendingPathComponent:@"LevelData.plist"];
    
    NSMutableDictionary *data = [[NSMutableDictionary alloc] initWithContentsOfFile:path];
    NSDictionary *arr = [data valueForKey:@"Levels"];
    NSString *str = [NSString stringWithFormat:@"Level%d",no];
    NSMutableDictionary *dict = [arr valueForKey:str];
    [dict setValue:[NSNumber numberWithInt:Stars] forKey:@"Stars"];
    [arr setValue:dict forKey:str];
    [data setValue:arr forKey:@"Levels"];
    [data writeToFile:path atomically:YES];
}

+(void)setLevelScore:(int)no:(int)Score
{
    NSArray *arrPath = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *path = [[arrPath objectAtIndex:0] stringByAppendingPathComponent:@"LevelData.plist"];

    NSMutableDictionary *data = [[NSMutableDictionary alloc] initWithContentsOfFile:path];
    NSDictionary *arr = [data valueForKey:@"Levels"];
    NSString *str = [NSString stringWithFormat:@"Level%d",no];
    NSMutableDictionary *dict = [arr valueForKey:str];
    [dict setValue:[NSNumber numberWithInt:Score] forKey:@"Score"];
    [arr setValue:dict forKey:str];
    [data setValue:arr forKey:@"Levels"];
    [data writeToFile:path atomically:YES];
}

+(Level)getLevelData:(int)no
{
    return allLevels[no];
}

+(void)PlaySound:(NSString *)Name
{
    if(onGameSound)
    {
        //NSString *path = [[NSBundle mainBundle] pathForResource:Name ofType:@"wav"];
        //AVAudioPlayer *bulletMusic = [[AVAudioPlayer alloc] initWithContentsOfURL:[NSURL fileURLWithPath:path] error:NULL];
        //[bulletMusic play];
        NSString *File = [NSString stringWithFormat:@"%@.wav",Name];
        [[SimpleAudioEngine sharedEngine] playEffect:File];
    }
}

+(NSString *)getImgName:(NSString *)str
{
    if (DeviceType == 1)
    {
        return [NSString stringWithFormat:@"ipad-%@",str];
    }
    return str;
}

@end