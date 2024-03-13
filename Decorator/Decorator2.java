import java.util.ArrayList;
import java.util.HashMap;

interface Drink {
  ArrayList<String> getNameList();

  Double getPrice();
}

class Coffee implements Drink {
  ArrayList<String> name;

  public Coffee() {
    name = new ArrayList<String>();
    name.add("Coffee");
  }

  public ArrayList<String> getNameList() {
    return name;
  }

  public Double getPrice() {
    return 1.0;
  }
}

class Tea implements Drink {
  ArrayList<String> name;

  public Tea() {
    name = new ArrayList<String>();
    name.add("Tea");
  }

  public ArrayList<String> getNameList() {
    return name;
  }

  public Double getPrice() {
    return 0.5;
  }
}

abstract class DrinkDecorator implements Drink {
  Drink drink;
}

class WithSugar extends DrinkDecorator {
  public WithSugar(Drink drink) {
    this.drink = drink;
  }

  public ArrayList<String> getNameList() {
    ArrayList<String> nameList = new ArrayList<String>(drink.getNameList());
    nameList.add("Sugar");
    return nameList;
  }

  public Double getPrice() {
    return drink.getPrice() + 0.2;
  }
}

class WithBrownSugar extends DrinkDecorator {
  public WithBrownSugar(Drink drink) {
    this.drink = drink;
  }

  public ArrayList<String> getNameList() {
    ArrayList<String> nameList = new ArrayList<String>(drink.getNameList());
    nameList.add("Brown sugar");
    return nameList;
  }

  public Double getPrice() {
    return drink.getPrice() + 0.3;
  }
}

class WithMilk extends DrinkDecorator {
  public WithMilk(Drink drink) {
    this.drink = drink;
  }

  public ArrayList<String> getNameList() {
    ArrayList<String> nameList = new ArrayList<String>(drink.getNameList());
    nameList.add("Milk");
    return nameList;
  }

  public Double getPrice() {
    return drink.getPrice() + 0.5;
  }
}

interface IDrinkParser {
  String parse(Drink drink);
}

class ParseOnlyName implements IDrinkParser {
  public String parse(Drink drink) {
    return drink.getNameList().get(0);
  }
}

class ParseAsJustString implements IDrinkParser {
  public String parse(Drink drink) {
    return String.join("; ", drink.getNameList()) + " || Price: " + drink.getPrice();
  }
}

class ParseDistinct implements IDrinkParser {
  public String parse(Drink drink) {
    HashMap<String, Integer> valuesMap = new HashMap<String, Integer>();
    ArrayList<String> newStringList = new ArrayList<String>();

    ArrayList<String> lis = drink.getNameList();

    for (int i = 1; i < lis.size(); i++) {
      if (valuesMap.containsKey(lis.get(i))) {
        Integer num = valuesMap.get(lis.get(i));
        num++;
        valuesMap.put(lis.get(i), num);
      } else {
        valuesMap.put(lis.get(i), 1);
      }
    }

    for (var entry : valuesMap.entrySet()) {
      newStringList.add(entry.getValue() + "x " + entry.getKey());
    }

    String result = "\n" + lis.get(0);

    if (newStringList.size() > 0) {
      result += "\n=============\n";
      result += String.join(",\n", newStringList);
    }
    result += "\n-------------\nPrice: " + drink.getPrice() + "\n-------------";
    return result;
  }
}

public class Decorator2 {
  public static void main(String[] args) {
    IDrinkParser parser1 = new ParseOnlyName();
    IDrinkParser parser2 = new ParseAsJustString();
    IDrinkParser parser3 = new ParseDistinct();

    System.out.println("drink1");
    Drink drink1 = new Coffee();
    System.out.println(parser1.parse(drink1));
    System.out.println(parser2.parse(drink1));
    System.out.println(parser3.parse(drink1));

    System.out.println("drink2");
    Drink drink2 = new Coffee();
    drink2 = new WithSugar(drink2);
    System.out.println(parser1.parse(drink2));
    System.out.println(parser2.parse(drink2));
    System.out.println(parser3.parse(drink2));

    System.out.println("drink3");
    Drink drink3 = new Tea();
    drink3 = new WithSugar(drink3);
    drink3 = new WithSugar(drink3);
    drink3 = new WithMilk(drink3);
    System.out.println(parser1.parse(drink3));
    System.out.println(parser2.parse(drink3));
    System.out.println(parser3.parse(drink3));
  }
}
