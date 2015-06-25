package com.rhymes.attackTheFortress;

public class RoundResult {
private int life;
private int gold;
private int kill;
private int score;
private int mode;
public RoundResult(){
	life=0;
	gold=0;
	kill=0;
	score=0;
	mode=0;
}
public int getLife()
{
	return this.life;
}
public void setLife(int L){
	this.life=L;
}
public int getGold()
{
	return this.gold;
}
public void setGold(int G){
	this.gold=G;
}
public int getKill()
{
	return this.kill;
}
public void setKill(int K){
	this.kill=K;
}
public int getScore()
{
	return this.score;
}
public void setScore(int S){
	this.score=S;
}
public int getMode()
{
	return this.mode;
}
public void setMode(int M){
	this.mode=M;
}

}
