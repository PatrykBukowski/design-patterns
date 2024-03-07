package com.mtk.app;

import java.util.Observable;
import java.util.Observer;

class DanePogodowe extends Observable {
  private float temperatura;
  private float wilgotnosc;
  private float cisnienie;

  public DanePogodowe() {
  }

  public void odczytyZmiana() {
    setChanged();
    notifyObservers();
  }

  public void ustawOdczyty(float temperatura, float wilgotnosc, float cisnienie) {
    this.temperatura = temperatura;
    this.wilgotnosc = wilgotnosc;
    this.cisnienie = cisnienie;
    odczytyZmiana();
  }

  public float pobierzTemperature() {
    return temperatura;
  }

  public float pobierzWilgotnosc() {
    return wilgotnosc;
  }

  public float pobierzCisnienie() {
    return cisnienie;
  }
}

class WarunkiBiezaceWyswietl implements Observer {
  Observable observable;
  private float temperatura;
  private float wilgotnosc;
  private String name;

  public WarunkiBiezaceWyswietl(String name, Observable observable) {
    this.observable = observable;
    this.name = name;
    observable.addObserver(this);
  }

  public void update(Observable obs, Object arg) {
    if (obs instanceof DanePogodowe) {
      DanePogodowe danePogodowe = (DanePogodowe) obs;
      this.temperatura = danePogodowe.pobierzTemperature();
      this.wilgotnosc = danePogodowe.pobierzWilgotnosc();
      System.out.println("Warunki biezace " + name + " | temp: " + temperatura + ", wilg: " + wilgotnosc);
    }
  }

}

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    DanePogodowe obs = new DanePogodowe();

    WarunkiBiezaceWyswietl observer = new WarunkiBiezaceWyswietl("Observer 1", obs);
    WarunkiBiezaceWyswietl observer2 = new WarunkiBiezaceWyswietl("Observer 2", obs);

    obs.ustawOdczyty(0, 0, 0);
    obs.ustawOdczyty(2, 4, 6);
    obs.ustawOdczyty(1, 2, 2);
    System.out.println("Hello World!");
  }
}
