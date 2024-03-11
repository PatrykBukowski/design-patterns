abstract class Napoj {
  String opis = "Napój nieznany";

  public String pobierzOpis() {
    return opis;
  }

  public abstract double koszt();
}

abstract class SkladnikDekorator extends Napoj {
  public abstract String pobierzOpis();
}

class Espresso extends Napoj {
  public Espresso() {
    opis = "Kawa Espresso";
  }

  public double koszt() {
    return 1.99;
  }
}

class StarCafeSpecial extends Napoj {
  public StarCafeSpecial() {
    opis = "Kawa Star Cafe Sepcial";
  }

  public double koszt() {
    return 0.89;
  }
}

class MocnoPalona extends Napoj {
  public MocnoPalona() {
    opis = "Kawa Mocno Palona";
  }

  public double koszt() {
    return 0.99;
  }
}

class Bezkofeinowa extends Napoj {
  public Bezkofeinowa() {
    opis = "Kawa Bezkofeinowa";
  }

  public double koszt() {
    return 1.05;
  }
}

class Czekolada extends SkladnikDekorator {
  Napoj napoj;

  public Czekolada(Napoj napoj) {
    this.napoj = napoj;
  }

  public String pobierzOpis() {
    return napoj.pobierzOpis() + ", Czekolada";
  }

  public double koszt() {
    return napoj.koszt() + 0.20;
  }
}

class BitaSmietana extends SkladnikDekorator {
  Napoj napoj;

  public BitaSmietana(Napoj napoj) {
    this.napoj = napoj;
  }

  public String pobierzOpis() {
    return napoj.pobierzOpis() + ", Bita śmietana";
  }

  public double koszt() {
    return napoj.koszt() + 0.10;
  }
}

class Mleko extends SkladnikDekorator {
  Napoj napoj;

  public Mleko(Napoj napoj) {
    this.napoj = napoj;
  }

  public String pobierzOpis() {
    return napoj.pobierzOpis() + ", Mleko";
  }

  public double koszt() {
    return napoj.koszt() + 0.10;
  }
}

class MleczkoSojowe extends SkladnikDekorator {
  Napoj napoj;

  public MleczkoSojowe(Napoj napoj) {
    this.napoj = napoj;
  }

  public String pobierzOpis() {
    return napoj.pobierzOpis() + ", Mleczko Sojowe";
  }

  public double koszt() {
    return napoj.koszt() + 0.15;
  }
}

public class Decorator {
  public static void main(String[] args) {
    Napoj napoj = new Espresso();
    System.out.println(napoj.pobierzOpis() + "   " + napoj.koszt() + " zł");

    Napoj napoj2 = new MocnoPalona();
    napoj2 = new Czekolada(napoj2);
    napoj2 = new Czekolada(napoj2);
    napoj2 = new BitaSmietana(napoj2);
    System.out.println(napoj2.pobierzOpis() + "   " + napoj2.koszt() + " zł");

    Napoj napoj3 = new StarCafeSpecial();
    napoj3 = new MleczkoSojowe(napoj3);
    napoj3 = new Czekolada(napoj3);
    napoj3 = new BitaSmietana(napoj3);
    System.out.println(napoj3.pobierzOpis() + "   " + napoj3.koszt() + " zł");
  }
}
