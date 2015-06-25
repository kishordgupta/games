package com.rhymes.game.generation.actiondriver;

import com.badlogic.gdx.utils.Array;
import com.rhymes.game.generation.GenerationController;
import com.rhymes.game.generation.GenerationLine;

public class ADGenerationController extends GenerationController {
	public static final String scoreTypeScore = "SCORE";
	public static final String scoreTypeHeroDeath = "DEATH";
	public static final String scoreTypeCAR = "CAR";
	
	public ADGenerationController() {
		this.generationLines = new Array<GenerationLine>();
		this.generationLines.add(new ADGenLineCar(scoreTypeCAR));
		this.generationLines.add(new ADGenLineScore(scoreTypeScore));
		this.generationLines.add(new ADGenLineDeath(scoreTypeHeroDeath));
		
	}

}
