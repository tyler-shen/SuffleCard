/**
 * Deck.java
 * 
 * @brief A class that represents a deck of playing cards
 * 
 * @details
 * This class is responsible for shuffling and dealing a deck of cards. The deck should
 * contain 52 cards A,2 - 10, J,Q K or four suits, but no jokers.
 */

import java.util.Random;

class Deck 
{
  private Card[] _deck;
  public Deck() 
  {
    _deck = new Card[52];
    var index = 0;
    for (var i = 0; i < 4; ++i)
    {
      for (var j = 1; j < 14; ++j)
      {
        _deck[index++] = new Card(i, j);
      }
    }
  }
  
  public void deal_hand( int sets, int cards ) 
  {
    var index = 0;
    var result = new StringBuilder();
    for (var i = 0; i < sets; ++i)
    {
      for (var j = 0; j < cards; ++j)
      {
        if (index > 51)
        {
          break;
        }
        result.append(_deck[index].get_suit());
        result.append('-');
        result.append(_deck[index++].get_value());
        result.append(' ');
      }
      result.append('\n');
    }
    System.out.print(result.toString());
  }
  
  public void print_deck() 
  {
    var count = 0;
    var result = new StringBuilder();
    for (var card : _deck)
    {
      if (card == null)
      {
        continue;
      }
      result.append(card.get_suit());
      result.append('-');
      result.append(card.get_value());
      result.append(' ');
      if (count++ == 12)
      {
        result.append('\n');
        count = 0;
      }
    }
    System.out.print(result.toString());
  }
  
  public void shuffle( int seed ) 
  {
    var generator = new Random(seed);
    for (var i = 0; i < 52; ++i)
    {
      var rand1 = generator.nextInt(52);
      var rand2 = generator.nextInt(52);
      swap(_deck, rand1, rand2);
      
    }
  }
  
  public void sort() 
  {
    quickSortSuit(_deck, 0, 51);

    for (var i = 0; i < 40; i += 13)
    {
      var j = i + 12;
      quickSortValue(_deck, i, j);
    }
  }

  private void swap(Card[] deck, int i, int j)
  {
    var tmp = deck[i];
    deck[i] = deck[j];
    deck[j] = tmp;
  }

  private void quickSortValue(Card[] deck, int low, int high)
  {
    if (low < high)
    {
        var pi = partition(deck, low, high);
        quickSortValue(deck, low, pi - 1);
        quickSortValue(deck, pi + 1, high);
    }
  }

  private void quickSortSuit(Card[] deck, int low, int high)
  {
    if (low < high)
    {
      var pi = suitPartition(deck, low, high);
      quickSortSuit(deck, low, pi - 1);
      quickSortSuit(deck, pi + 1, high);
    }
  }
  
  private int partition(Card[] deck, int low, int high)
  {
    var pivot = deck[high];
    var i = (low - 1);
    for(var j = low; j <= high - 1; j++)
    {
        if (deck[j].is_less_than(pivot))
        {
            ++i;
            swap(deck, i, j);
        }
    }
    swap(deck, i + 1, high);
    return (i + 1);
  }

  private int suitPartition(Card[] deck, int low, int high)
  {
    var pivot = deck[high];
    var i = (low - 1);
    for(var j = low; j <= high - 1; j++)
    {
        if (deck[j].suit_is_less_than(pivot))
        {
            ++i;
            swap(deck, i, j);
        }
    }
    swap(deck, i + 1, high);
    return (i + 1);
  }
}