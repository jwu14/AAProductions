package com.example.egyptianratscrew;

import java.util.*;

public class Deck
{
	private ArrayList<Card> deck;

	public Deck(ArrayList<Card> arrayList)
	{
		deck = arrayList;
	}
	
	public int getSize()
	{
		return deck.size();
	}

	public Card draw()
	{
		if (deck.size()==0)
			return null;
		else
			return deck.remove(0);
	}
	
	public void add(ArrayList<Card> cards)
	{
		deck.addAll(cards);
	}
}