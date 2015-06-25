//
//  PaushMenu.h
//  Zombian
//
//  Created by TheAppGuruz . on 12/06/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "CCLayer.h"
#import "cocos2d.h"

@interface PaushMenu : CCLayer {
    
    CCSprite *NHS;
    
}

+(CCScene *) scene;
-(void)updateDataOfLevel;

@end
