interface LatanieInterfejs {
    public void lec();
}
class LatamBoMamSkrzydla implements LatanieInterfejs {
    public void lec() {
        System.out.println("O rany! Latam!");
    }
}
class NieLatam implements LatanieInterfejs {
    public void lec() {
        System.out.println("Nie umiem latać!");
    }
}
class LotZNapedemRakietowym implements LatanieInterfejs {
    public void lec() {
        System.out.println("Uuuuuaaaa! Lot z napędem rakietowym!");
    }
}

interface KwakanieInterfejs {
    public void kwacz();
}
class Kwacz implements KwakanieInterfejs{
    public void kwacz() {
        System.out.println("Kwa! Kurwa!");
    }
}
class NieKwacz implements KwakanieInterfejs {
    public void kwacz() {
        System.out.println("<<CISZA>>");
    }
}
class Piszcz implements KwakanieInterfejs {
    public void kwacz() {
        System.out.println("Piszczę!");
    }
}

abstract class Kaczka {
    LatanieInterfejs latanieInterfejs;
    KwakanieInterfejs kwakanieInterfejs;
    
    public Kaczka(){}
    
    public abstract void wyswietl();
    
    public void wykonajKwacz() {
        kwakanieInterfejs.kwacz();
    }
    
    public void wykonajLec() {
        latanieInterfejs.lec();
    }
    
    public void plywaj() {
        System.out.println("Wszystkie kaczki pływają, nawet te sztuczne!");
    }
    
    public void ustawLatanieInterfejs (LatanieInterfejs li) {
        latanieInterfejs = li;
    }
    public void ustawKwakanieInterfejs(KwakanieInterfejs ki) {
        kwakanieInterfejs = ki;
    }
}

class DzikaKaczka extends Kaczka {
    public DzikaKaczka() {
        kwakanieInterfejs = new Kwacz();
        latanieInterfejs = new LatamBoMamSkrzydla();
    }
    
    public void wyswietl() {
        System.out.println("Jestem prawdziwą Dziką Kaczką");
    }
}

class ModelKaczki extends Kaczka {
    public ModelKaczki() {
        latanieInterfejs = new NieLatam();
        kwakanieInterfejs = new Kwacz();
    }
    
    public void wyswietl() {
        System.out.println("Jestem modelem kaczki!");
    }
}

interface GwizdzenieInterfejs {
  public void gwizdz();
}

class GwizdzJakKaczka implements GwizdzenieInterfejs {
    KwakanieInterfejs kwakanieInterface;

    public GwizdzJakKaczka() {
      kwakanieInterface = new Kwacz();
    }

    public void ustawKwakanieInterfejs(KwakanieInterfejs ki) {
      kwakanieInterface = ki;
    }

    public void gwizdz() {
      kwakanieInterface.kwacz();
    }
}

class GwizdzJakZwyklyGwizdek implements GwizdzenieInterfejs {
  public void gwizdz() {
    System.out.println("Fiuuu Fiuuuu.");
  }
}

abstract class Gwizdek {
  GwizdzenieInterfejs gwizdzenieInterfejs;

  public Gwizdek (){}

  public void wykonajGwizdz(){
    gwizdzenieInterfejs.gwizdz();
  }
 
  public void ustawGwizdzenieInterfejs(GwizdzenieInterfejs gi){
    gwizdzenieInterfejs = gi;
  }
}

class Wabik extends Gwizdek {
 public Wabik(){
   gwizdzenieInterfejs = new GwizdzJakKaczka();
 } 
}

class ZwyklyGwizdek extends Gwizdek {
  public ZwyklyGwizdek(){
    gwizdzenieInterfejs = new GwizdzJakZwyklyGwizdek();
  }
}


public class Strategy {
    public static void main(String args[]) {
      Kaczka dzika = new DzikaKaczka();
      dzika.wykonajKwacz();
      dzika.wykonajLec();
      
      Kaczka model = new ModelKaczki();
      model.wykonajLec();
      model.ustawLatanieInterfejs(new LotZNapedemRakietowym());
      model.wykonajLec();

      Gwizdek wabik = new Wabik();
      wabik.wykonajGwizdz();
      
      Gwizdek zwykly = new ZwyklyGwizdek();
      zwykly.wykonajGwizdz();
    }
}
