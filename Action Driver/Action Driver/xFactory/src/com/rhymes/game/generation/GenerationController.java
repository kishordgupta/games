package com.rhymes.game.generation;

import com.badlogic.gdx.utils.Array;

public class GenerationController {
	protected Array<GenerationLine> generationLines;
	
	public Array<GenerationLine> getGenerationLines() {
		return generationLines;
	}

	public void setGenerationLines(Array<GenerationLine> generationLines) {
		this.generationLines = generationLines;
	}
	
	public void tick(float stepTime)
	{
		for(int i = 0; i < generationLines.size; i++)
			generationLines.get(i).tick(stepTime);
	}
}
