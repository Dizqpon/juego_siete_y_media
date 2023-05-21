package sieteymedia;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
import javafx.scene.image.Image;

/**
 *
 * @author profesorado
 */
public class Mazo {

  private String nombre;
  private int id;
  private LinkedList<Carta> mazo;
  // Declaramos las imagenes de las cartas
  Image basto1 = new Image(getClass().getResourceAsStream("../imagenes/basto1.png"));
  Image basto2 = new Image(getClass().getResourceAsStream("../imagenes/basto2.png"));
  Image basto3 = new Image(getClass().getResourceAsStream("../imagenes/basto3.png"));
  Image basto4 = new Image(getClass().getResourceAsStream("../imagenes/basto4.png"));
  Image basto5 = new Image(getClass().getResourceAsStream("../imagenes/basto5.png"));
  Image basto6 = new Image(getClass().getResourceAsStream("../imagenes/basto6.png"));
  Image basto7 = new Image(getClass().getResourceAsStream("../imagenes/basto7.png"));
  Image basto10 = new Image(getClass().getResourceAsStream("../imagenes/basto10.png"));
  Image basto11 = new Image(getClass().getResourceAsStream("../imagenes/basto11.png"));
  Image basto12 = new Image(getClass().getResourceAsStream("../imagenes/basto12.png"));
  Image copa1 = new Image(getClass().getResourceAsStream("../imagenes/copa1.png"));
  Image copa2 = new Image(getClass().getResourceAsStream("../imagenes/copa2.png"));
  Image copa3 = new Image(getClass().getResourceAsStream("../imagenes/copa3.png"));
  Image copa4 = new Image(getClass().getResourceAsStream("../imagenes/copa4.png"));
  Image copa5 = new Image(getClass().getResourceAsStream("../imagenes/copa5.png"));
  Image copa6 = new Image(getClass().getResourceAsStream("../imagenes/copa6.png"));
  Image copa7 = new Image(getClass().getResourceAsStream("../imagenes/copa7.png"));
  Image copa10 = new Image(getClass().getResourceAsStream("../imagenes/copa10.png"));
  Image copa11 = new Image(getClass().getResourceAsStream("../imagenes/copa11.png"));
  Image copa12 = new Image(getClass().getResourceAsStream("../imagenes/copa12.png"));
  Image espada1 = new Image(getClass().getResourceAsStream("../imagenes/espada1.png"));
  Image espada2 = new Image(getClass().getResourceAsStream("../imagenes/espada2.png"));
  Image espada3 = new Image(getClass().getResourceAsStream("../imagenes/espada3.png"));
  Image espada4 = new Image(getClass().getResourceAsStream("../imagenes/espada4.png"));
  Image espada5 = new Image(getClass().getResourceAsStream("../imagenes/espada5.png"));
  Image espada6 = new Image(getClass().getResourceAsStream("../imagenes/espada6.png"));
  Image espada7 = new Image(getClass().getResourceAsStream("../imagenes/espada7.png"));
  Image espada10 = new Image(getClass().getResourceAsStream("../imagenes/espada10.png"));
  Image espada11 = new Image(getClass().getResourceAsStream("../imagenes/espada11.png"));
  Image espada12 = new Image(getClass().getResourceAsStream("../imagenes/espada12.png"));
  Image oro1 = new Image(getClass().getResourceAsStream("../imagenes/oro1.png"));
  Image oro2 = new Image(getClass().getResourceAsStream("../imagenes/oro2.png"));
  Image oro3 = new Image(getClass().getResourceAsStream("../imagenes/oro3.png"));
  Image oro4 = new Image(getClass().getResourceAsStream("../imagenes/oro4.png"));
  Image oro5 = new Image(getClass().getResourceAsStream("../imagenes/oro5.png"));
  Image oro6 = new Image(getClass().getResourceAsStream("../imagenes/oro6.png"));
  Image oro7 = new Image(getClass().getResourceAsStream("../imagenes/oro7.png"));
  Image oro10 = new Image(getClass().getResourceAsStream("../imagenes/oro10.png"));
  Image oro11 = new Image(getClass().getResourceAsStream("../imagenes/oro11.png"));
  Image oro12 = new Image(getClass().getResourceAsStream("../imagenes/oro12.png"));

  private static final char[] palos
    = {Carta.PICA, Carta.TREBOL, Carta.CORAZON, Carta.DIAMANTE};
  private static final char[] simbolos
    = {'A', '2', '3', '4', '5', '6', '7', 'J', 'Q', 'K'};
  //private static final float[] valores =
  //    {1,2,3,4,5,6,7,0.5f,0.5f,0.5f};

  Mazo(String nombre, int id) {
    this.nombre = nombre;
    this.id = id;
    this.mazo = new LinkedList<>();
  }

  public String obtenerNombre() {
    return this.nombre;
  }

  public int obtenerId() {
    return this.id;
  }

  public void crearBaraja(float[] valores) {
    int p, s, orden;
    Carta nuevaCarta;

    orden = 0;
    for (p = 0; p < palos.length; p++) {
      for (s = 0; s < simbolos.length; s++) {
        nuevaCarta = new Carta(simbolos[s], palos[p], valores[s], orden);
        orden++;
        this.mazo.add(nuevaCarta);
      }
    }

  }

  public Image mostrar(Carta c) {
    HashMap<Character, Image> imagenesCartas = null;
    char simbolo = c.obtenerSimbolo();
    HashMap<Character, Image> imagenesCartasOro = new HashMap<>();
    imagenesCartasOro.put('A', oro1);
    imagenesCartasOro.put('2', oro2);
    imagenesCartasOro.put('3', oro3);
    imagenesCartasOro.put('4', oro4);
    imagenesCartasOro.put('5', oro5);
    imagenesCartasOro.put('6', oro6);
    imagenesCartasOro.put('7', oro7);
    imagenesCartasOro.put('J', oro10);
    imagenesCartasOro.put('Q', oro11);
    imagenesCartasOro.put('K', oro12);
    HashMap<Character, Image> imagenesCartasBasto = new HashMap<>();
    imagenesCartasBasto.put('A', basto1);
    imagenesCartasBasto.put('2', basto2);
    imagenesCartasBasto.put('3', basto3);
    imagenesCartasBasto.put('4', basto4);
    imagenesCartasBasto.put('5', basto5);
    imagenesCartasBasto.put('6', basto6);
    imagenesCartasBasto.put('7', basto7);
    imagenesCartasBasto.put('J', basto10);
    imagenesCartasBasto.put('Q', basto11);
    imagenesCartasBasto.put('K', basto12);
    HashMap<Character, Image> imagenesCartasEspada = new HashMap<>();
    imagenesCartasEspada.put('A', espada1);
    imagenesCartasEspada.put('2', espada2);
    imagenesCartasEspada.put('3', espada3);
    imagenesCartasEspada.put('4', espada4);
    imagenesCartasEspada.put('5', espada5);
    imagenesCartasEspada.put('6', espada6);
    imagenesCartasEspada.put('7', espada7);
    imagenesCartasEspada.put('J', espada10);
    imagenesCartasEspada.put('Q', espada11);
    imagenesCartasEspada.put('K', espada12);
    HashMap<Character, Image> imagenesCartasCopa = new HashMap<>();
    imagenesCartasCopa.put('A', copa1);
    imagenesCartasCopa.put('2', copa2);
    imagenesCartasCopa.put('3', copa3);
    imagenesCartasCopa.put('4', copa4);
    imagenesCartasCopa.put('5', copa5);
    imagenesCartasCopa.put('6', copa6);
    imagenesCartasCopa.put('7', copa7);
    imagenesCartasCopa.put('J', copa10);
    imagenesCartasCopa.put('Q', copa11);
    imagenesCartasCopa.put('K', copa12);
    if (!hayCartas()) {

    } else {

      switch (c.obtenerPalo()) {
        case 0x2660:
          imagenesCartas = imagenesCartasOro;
          break;
        case 0x2663:
          imagenesCartas = imagenesCartasBasto;
          break;
        case 0x2665:
          imagenesCartas = imagenesCartasEspada;
          break;
        case 0x2666:
          imagenesCartas = imagenesCartasCopa;
          break;
      }
    }
    return imagenesCartas.get(simbolo);
  }

  public void barajar() {
    Collections.shuffle(this.mazo);
  }

  public int cuantasCartas() {
    return this.mazo.size();
  }

  public boolean hayCartas() {
    return !(this.mazo.isEmpty());
  }

  public void vaciar() {
    this.mazo.clear();
  }

  public Carta extraerCarta() {
    // extrae la carta de arriba;
    return this.mazo.poll();
  }

  public void insertarCarta(Carta nueva) {
    this.mazo.add(nueva);
  }

  public void ordenar() {
    Collections.sort(this.mazo);
  }

  public void agrupar() {
    Collections.sort(this.mazo, new AgrupadorCartasSimbolo());
  }

  public float sumarPuntos() {
    Carta c;
    float suma = 0f;
    Iterator<Carta> it = this.mazo.iterator();

    while (it.hasNext()) { // Mientras que haya un siguiente elemento, seguiremos en el bucle.
      c = it.next(); // Escogemos el siguiente elemento.
      suma += c.obtenerValor();

    }
    return suma;
  }
}
