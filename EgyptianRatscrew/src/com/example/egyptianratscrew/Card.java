package com.example.egyptianratscrew;

public class Card
{
	private final int suit;
	private final int value;

	public Card(int suit, int value)
	{
		this.suit = suit;
		this.value = value;
	}

	public int getSuit()
	{
		return suit;
	}

	public int getValue()
	{
		return value;
	}
	
	public int numCardsOwed()
	{
		if (this.value == 12) return 2; //queen
		else if (this.value == 13) return 3; //king
		else if (this.value == 1) return 4; //ace
		
		return 1; //only 1 card required for any other card
	}
}