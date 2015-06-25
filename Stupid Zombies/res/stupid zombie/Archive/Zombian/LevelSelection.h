//
//  LevelSelection.h
//  Zombian
//
//  Created by TheAppGuruz . on 31/05/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "CCLayer.h"
#import "cocos2d.h"
#import "Box2D.h"
#import "HelloWorldLayer.h"
#import "LevelSelection.h"

@interface LevelSelection : CCLayer {
    
        int aLevels;
    
}

+(CCScene *) scene;
-(void)addLevel:(int)no:(CCMenu *)Menu;
-(void)addBackButton;

@end
