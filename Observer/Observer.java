// INFO: Pozostałe interface

import java.util.ArrayList;

 interface ZaktualizujInterface{
}

 class StandardZaktualizuje implements ZaktualizujInterface {
  float temp;
  float wilgotnosc;
  float cisnienie;

  public StandardZaktualizuje(float temp, float wilgotnosc, float cisnienie){
    this.temp = temp;
    this.wilgotnosc = wilgotnosc;
    this.cisnienie = cisnienie;
  }
}

// INFO: Interface dla observer
 interface Podmiot {
  public void zarejestrujObserwatora (Obserwator o);
  public void usunObserwatora(Obserwator o);
  public void powiadomObserwatorow();
}

 interface Obserwator {
  public void aktualizacja(float temp, float wilgotnosc, float cisnienie);
}

 interface WyswietlElement {
  public void wyswietl();
}

 class DanePogodowe implements Podmiot {
  private ArrayList obserwatorzy;
  private float temperatura;
  private float wilgotnosc;
  private float cisnienie;

  public DanePogodowe() {
    obserwatorzy = new ArrayList();
  }

  public void zarejestrujObserwatora(Obserwator o){
    obserwatorzy.add(o);
  }

  public void usunObserwatora(Obserwator o) {
    int i = obserwatorzy.indexOf(o);
    if (i >= 0) {
      obserwatorzy.remove(i);
    }
  }

  public void powiadomObserwatorow() {
    for (int i = 0; i < obserwatorzy.size(); i++) {
      Obserwator obs = (Obserwator)obserwatorzy.get(i);
      obs.aktualizacja(temperatura, wilgotnosc, cisnienie);
    }
  }

  public void odczytyZmiana() {
    powiadomObserwatorow();
  }

  public void ustawOdczyty(float temperatura, float wilgotnosc, float cisnienie) {
    this.temperatura = temperatura;
    this.wilgotnosc = wilgotnosc;
    this.cisnienie = cisnienie;
    odczytyZmiana();
  }
}

 class WarunkiBiezaceWyswietl implements Obserwator, WyswietlElement {
  private float temperatura;
  private float wilgotnosc;
  private Podmiot danePogodowe;

  public WarunkiBiezaceWyswietl(Podmiot danePogodowe) {
    this.danePogodowe = danePogodowe;
    danePogodowe.zarejestrujObserwatora(this);
  }

  public void aktualizacja(float temperatura, float wilgotnosc, float cisnienie) {
    this.temperatura = temperatura;
    this.wilgotnosc = wilgotnosc;
    wyswietl();
  }

  public void wyswietl() {
    System.out.println("Warunki bieżące " + temperatura + " stopni C oraz " + wilgotnosc + "% wilgotnosc");
  }
}

 class StatystykaWyswietl implements Obserwator, WyswietlElement {
  private ArrayList<Float> temperatura;
  private ArrayList<Float> wilgotnosc;
  private Podmiot danePogodowe;

  public StatystykaWyswietl(Podmiot danePogodowe) {
    temperatura = new ArrayList();
    wilgotnosc = new ArrayList();
    this.danePogodowe = danePogodowe;
    danePogodowe.zarejestrujObserwatora(this);
  }

  public void aktualizacja(float temperatura, float wilgotnosc, float cisnienie) {
    this.temperatura.add(temperatura);
    this.wilgotnosc.add(wilgotnosc);
    wyswietl();
  }

  public void wyswietl() {
    float temp = 0;
    for(int i = 0; i < temperatura.size(); i++){
      temp += temperatura.get(i);
    }
    if (temperatura.size() > 0) {
      temp = temp / temperatura.size();
    }

    float wilg = 0;
    for(int i = 0; i < wilgotnosc.size(); i++){
      wilg += wilgotnosc.get(i);
    }
    if (wilgotnosc.size() > 0) {
      wilg = wilg / wilgotnosc.size();
    }
    System.out.println("Statystyka: " + temperatura + " stopni C oraz " + wilgotnosc + "% wilgotnosc");
  }
}

 class PrognozaWyswietl implements Obserwator, WyswietlElement {
  private float temperaturaPrev;
  private float temperatura;
  private float wilgotnoscPrev;
  private float wilgotnosc;
  private Podmiot danePogodowe;

  public PrognozaWyswietl(Podmiot danePogodowe) {
    temperatura = 0;
    wilgotnosc = 0;
    this.danePogodowe = danePogodowe;
    danePogodowe.zarejestrujObserwatora(this);
  }

  public void aktualizacja(float temperatura, float wilgotnosc, float cisnienie) {
    this.temperaturaPrev = this.temperatura;
    this.wilgotnoscPrev = this.wilgotnosc;
    this.temperatura = temperatura;
    this.wilgotnosc = wilgotnosc;
    wyswietl();
  }

  public void wyswietl() {
    float nextWilgotnosc = this.wilgotnosc + (this.wilgotnosc - this.wilgotnoscPrev);
    float nextTemperatura = this.temperatura + (this.temperatura - this.temperaturaPrev);
    System.out.println("Statystyka: " + nextTemperatura + " stopni C oraz " + nextWilgotnosc + "% wilgotnosc");
  }
}

public class Observer {
  public static void main(String[] args){
    DanePogodowe danePogodowe = new DanePogodowe();

    WarunkiBiezaceWyswietl warunkiBiezaceWyswietl = new WarunkiBiezaceWyswietl(danePogodowe);
    StatystykaWyswietl statystykaWyswietl = new StatystykaWyswietl(danePogodowe);
    PrognozaWyswietl prognozaWyswietl = new PrognozaWyswietl(danePogodowe);

    danePogodowe.ustawOdczyty(26.6f, 65f, 1013.1f);
    danePogodowe.ustawOdczyty(27.7f, 70f, 997.0f);
    danePogodowe.ustawOdczyty(25.5f, 90f, 997.0f);
  }
}
