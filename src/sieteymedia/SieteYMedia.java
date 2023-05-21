package sieteymedia;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author profesorado
 */
public class SieteYMedia {

  static final float[] valores = {1, 2, 3, 4, 5, 6, 7, 0.5f, 0.5f, 0.5f};
  public Mazo jugador1, jugador2, jugador3, jugador4;
  public Mazo baraja;

  public SieteYMedia() {

    baraja = new Mazo("Baraja", 0);
    jugador1 = new Mazo("Sonic", 1);
    jugador2 = new Mazo("Tails", 2);
    jugador3 = new Mazo("Knuckles", 3);
    jugador4 = new Mazo("Eggman", 4);

    baraja.crearBaraja(valores);
    baraja.barajar();
  }

  private boolean Turno(Mazo jugador) {
    Carta carta;
    float puntos;
    boolean r = true;
    String nombre;

    nombre = jugador.obtenerNombre();

    //System.out.printf("%s",jugador.mostrar());
    System.out.println("¿Quiere carta?");
    if (quiereJugar()) {
      carta = this.baraja.extraerCarta();
      System.out.printf("%s", carta.mostrarGrafico());
      jugador.insertarCarta(carta);
      //System.out.printf("%s",jugador.mostrar());
      puntos = jugador.sumarPuntos();
      System.out.printf("%2.1f\n", puntos);
      if (puntos > 7.5) {
        System.out.println("Fin, te pasaste" + nombre);
        r = false;
      } else if (puntos == 7.5) {
        System.out.println("Fin, SIETE y MEDIA" + nombre + "!!!");
        r = false;
      }
    } else {
      System.out.println("Fin" + nombre);
      r = false;
    }

    return r;
  }

  public void Jugar() {
    boolean juega1, juega2, juega3, juega4;

    juega1 = true;
    juega2 = true;
    juega3 = true;
    juega4 = true;

    do {
      if (juega1) {
        juega1 = Turno(jugador1);
      }
      if (juega2) {
        juega2 = Turno(jugador2);
      }
      if (juega3) {
        juega3 = Turno(jugador3);
      }
      if (juega4) {
        juega4 = Turno(jugador4);
      }
    } while (juega1 || juega2 || juega3 || juega4);

    if (jugador1.sumarPuntos() == jugador2.sumarPuntos()) {
      System.out.println("Empate");
    } else {
      if (jugador1.sumarPuntos() > 7.5 && jugador2.sumarPuntos() > 7.5) {
        if (jugador1.sumarPuntos() < jugador2.sumarPuntos()) {
          System.out.printf("Ganador %s", jugador1.obtenerNombre());
        } else {
          System.out.printf("Ganador %s", jugador2.obtenerNombre());
        }
      } else if (jugador1.sumarPuntos() < 7.5 && jugador2.sumarPuntos() < 7.5) {
        if (jugador1.sumarPuntos() > jugador2.sumarPuntos()) {
          System.out.printf("Ganador %s", jugador1.obtenerNombre());
        } else {
          System.out.printf("Ganador %s", jugador2.obtenerNombre());
        }
      } else if (jugador1.sumarPuntos() < jugador2.sumarPuntos()) {
        System.out.printf("Ganador %s", jugador1.obtenerNombre());
      } else {
        System.out.printf("Ganador %s", jugador2.obtenerNombre());
      }
    }

  }

  private boolean quiereJugar() {
    int r = 0;

    try {
      do {
        System.out.println("1. Sí - 0. No");
//        
      } while (r < 0 || r > 1);
    } catch (Exception e) {
      System.out.println("Ocurrió un error");
    }
    if (r == 1) {
      return (true);
    } else {
      return (false);
    }
  }

  public List<Mazo> getJugadores() {
    List<Mazo> jugadores = new ArrayList<>();
    jugadores.add(jugador1);
    jugadores.add(jugador2);
    jugadores.add(jugador3);
    jugadores.add(jugador4);
    return jugadores;
  }

}
