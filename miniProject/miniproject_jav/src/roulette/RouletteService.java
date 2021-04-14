package roulette;

import java.util.Scanner;

import player.Player;

public interface RouletteService {
	
	void betStraight(Scanner sc, Player p);
	
	void betSplit(Scanner sc, Player p);
	
	void betStreet(Scanner sc, Player p);
	
	void betCorner(Scanner sc, Player p);
	
	void betLine(Scanner sc, Player p);
	
	void betColumn(Scanner sc, Player p);
	
	void betDozen(Scanner sc, Player p);
	
	void betColor(Scanner sc, Player p);
	
	void betEO(Scanner sc, Player p);

	void betLH(Scanner sc, Player p);
	
	
}
