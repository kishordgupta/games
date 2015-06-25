//
//  AppDelegate.h
//  Zombian
//
//  Created by TheAppGuruz . on 15/05/12.
//  Copyright __MyCompanyName__ 2012. All rights reserved.
//

#import <UIKit/UIKit.h>
extern BOOL onGameSound;
extern int DeviceType;
extern int MaxLevels;

struct Level{
    int Score;
    int Star;
    BOOL isUnlock;
};

extern Level allLevels[21];


@class RootViewController;

@interface AppDelegate : NSObject <UIApplicationDelegate> {
	UIWindow			*window;
	RootViewController	*viewController;
}

@property (nonatomic, retain) UIWindow *window;

+(void)PlaySound:(NSString *)Name;
+(NSString *)getImgName:(NSString *)str;
+(Level)getLevelData:(int)no;
+(void)setLevelUnlock:(int)no;
+(void)setLevelStars:(int)no:(int)Stars;
+(void)setLevelScore:(int)no:(int)Score;
+(void)LoadLevelData;

@end
