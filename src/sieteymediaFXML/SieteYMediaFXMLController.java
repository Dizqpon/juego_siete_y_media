package sieteymediaFXML;

import sieteymedia.Carta;
import sieteymedia.SieteYMedia;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sieteymedia.Mazo;

public class SieteYMediaFXMLController implements Initializable {

  private SieteYMedia juego; // instancia para almacenar la lógica del juego 
  private int jugadorTurno; // a quién le toca el turno
  private Queue<Integer> jugadoresActivos = new LinkedList<>();// Cola de jugadores
  private Queue<ImageView> marcoCartaJug1 = new LinkedList<>();
  private Queue<ImageView> marcoCartaJug2 = new LinkedList<>();
  private Queue<ImageView> marcoCartaJug3 = new LinkedList<>();
  private Queue<ImageView> marcoCartaJug4 = new LinkedList<>();

  // controles FXML relacionados con la interfaz
  @FXML private AnchorPane main;  // contenedor principal
  /* define a continuación otros contenedores y elementos FXML */
  @FXML private Pane panelJugador1,panelJugador2,panelJugador3,panelJugador4;
  @FXML private RadioButton check1,check2,check3,check4;
  @FXML private ToggleGroup grupoCheck;
  //Declaramos los contenedores para las imágenes de los jugadores y del banner
  @FXML private ImageView banner;
  @FXML private ImageView imgJug1,imgJug2,imgJug3,imgJug4;
  // Marcos para las cartas
  @FXML private ImageView cart1Jug1,cart2Jug1,cart3Jug1,cart4Jug1,cart5Jug1,cart6Jug1,cart7Jug1,
                          cart1Jug2,cart2Jug2,cart3Jug2,cart4Jug2,cart5Jug2,cart6Jug2,cart7Jug2,
                          cart1Jug3,cart2Jug3,cart3Jug3,cart4Jug3,cart5Jug3,cart6Jug3,cart7Jug3,
                          cart1Jug4,cart2Jug4,cart3Jug4,cart4Jug4,cart5Jug4,cart6Jug4,cart7Jug4;
  @FXML private ImageView contenedorCabecera;
  @FXML private TextField cajaTexInfo;
  @FXML private Timeline timelineText = new Timeline(new KeyFrame(
            Duration.seconds(3), event -> cajaTexInfo.setText("")));
  @FXML private Timeline timelineImg = new Timeline(new KeyFrame(
            Duration.seconds(3), event -> contenedorCabecera.setVisible(false)));
  Image imgJug1Rep,imgJug1Win,imgJug2Rep,imgJug2Win,imgJug3Rep,imgJug3Win,imgJug4Rep,imgJug4Win,imgJugLose;
  Image imagenBanner = new Image(getClass().getResourceAsStream("../imagenes/imagenBanner.png")); 
  // Las rutas a los archivos de audio
  String rutaSonidoFondo = getClass().getResource("../sonidos/fondo.mp3").toString();
  String rutaSonidoCarta = getClass().getResource("../sonidos/carta.mp3").toString();
  String rutaSonidoGanaste = getClass().getResource("../sonidos/ganaSonic.mp3").toString();
  String rutaSonidoPerdiste = getClass().getResource("../sonidos/perdiste.mp3").toString();
  // Las pistas que se van a reproducir en nuestro juego
  Media pistaFondo = new Media(rutaSonidoFondo);
  Media pistaCarta = new Media(rutaSonidoCarta);
  Media pistaGanaste = new Media(rutaSonidoGanaste);
  Media pistaPerdiste = new Media(rutaSonidoPerdiste);
  MediaPlayer reproductorFondo;
  // Método para inicializar la interfaz de usuario y prepara la partida reiniciando los controles.
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Bloqueamos la escritura del  usuario en los cuadros de texto
    cajaTexInfo.setEditable(false);
    for (int i = 1; i <= 4; i++) {
      ((TextField) main.lookup("#cuadroTextoJugador" + i)).setEditable(false);
      ((TextField) main.lookup("#cuadroTextoJugador" + i)).setMouseTransparent(true);
    }
    try {
      // Iniciamos la música de fondo con un volumen moderado para que los efectos destaquen
      reproductorFondo = new MediaPlayer(pistaFondo);
      reproductorFondo.setCycleCount(MediaPlayer.INDEFINITE);
      reproductorFondo.setVolume(0.3);
      reproductorFondo.play();
      // Declaramos las imágenes de los jugadores y de el fondo
      imgJug1Rep = new Image(getClass().getResourceAsStream("../imagenes/imgJug1Rep.png"));
      imgJug1Win = new Image(getClass().getResourceAsStream("../imagenes/imgJug1Win.png"));
      imgJug2Rep = new Image(getClass().getResourceAsStream("../imagenes/imgJug2Rep.png"));
      imgJug2Win = new Image(getClass().getResourceAsStream("../imagenes/imgJug2Win.png"));
      imgJug3Rep = new Image(getClass().getResourceAsStream("../imagenes/imgJug3Rep.png"));
      imgJug3Win = new Image(getClass().getResourceAsStream("../imagenes/imgJug3Win.png"));
      imgJug4Rep = new Image(getClass().getResourceAsStream("../imagenes/imgJug4Rep.png"));
      imgJug4Win = new Image(getClass().getResourceAsStream("../imagenes/imgJug4Win.png"));
      imgJugLose = new Image(getClass().getResourceAsStream("../imagenes/imgJugLose.png"));
    } catch (NullPointerException e) {
      System.out.println("Fallo al cargar alguna imágen: " + e.getMessage());
    }
    jugadorTurno = 1;
    banner.setImage(imagenBanner);
    FadeTransition fadeIn;
    contenedorCabecera.setImage(new Image(getClass().getResourceAsStream("../imagenes/cabeceraInicial.png")));
    fadeIn = new FadeTransition(Duration.seconds(1), contenedorCabecera);
    timelineImg.setCycleCount(1);
    timelineImg.play();
    fadeIn.setFromValue(0);
    fadeIn.setToValue(1);
    fadeIn.play();

    // Ocultamos los paneles de los jugdores
    panelJugador1.setVisible(false);
    panelJugador2.setVisible(false);
    panelJugador3.setVisible(false);
    panelJugador4.setVisible(false);
    // Inicializamos el grupo de radioButton para que solo se pueda seleccioar 1
    grupoCheck = new ToggleGroup();
    // Añadimos los radioButton al grupo
    check1.setToggleGroup(grupoCheck);
    check2.setToggleGroup(grupoCheck);
    check3.setToggleGroup(grupoCheck);
    check4.setToggleGroup(grupoCheck);
    reiniciaJuego(); //Comenzamos la partida reiniciando controles
  }

  // Creamos un método para reproducir los efectos de sonido
  private void reproductorDeEfectos(Media m) {
    try {
      MediaPlayer reproductorEfectos = new MediaPlayer(m);
      reproductorEfectos.play();
    } catch (Exception e) {
      System.out.println("Fallo al cargar el archivo de sonido");
    }
  }

  /**
   * @param event Evento que desencadena el inicio del juego (clic en botón
   * Comenzar)
   * @throws Excepción si no se ha podido comenzar la partida (no se
   * seleccionaron jugadores)
   */
  @FXML
  private void comenzarJuego(ActionEvent event) throws RuntimeException {
    jugadoresActivos.clear(); // organizamos la cola de jugadores para que empiece el primer jugador
    manejoMarcosCartas(); // Vaciar los marcos de las cartas
    reiniciaJuego(); // Al comenzar cada juego se reinician todos los controles, botones, etc.
    juego = new SieteYMedia();
    /* Creación del juego (en la variable juego se lleva 
                                el control de la baraja, jugadores, cartas, etc).*/
    try {
      // comprobar si se han elegido los jugadores totales que participarán 
      // meter IDs de jugadores en la cola (1, 2, 3 ...)
      // hacer visibles los paneles principales de los jugadores que están participando
      if (check1.isSelected()) {
        panelJugador1.setVisible(true);
        imgJug1.setImage(imgJug1Rep);
        jugadoresActivos.add(1);
      } else if (check2.isSelected()) {
        panelJugador1.setVisible(true);
        panelJugador2.setVisible(true);
        imgJug1.setImage(imgJug1Rep);
        imgJug2.setImage(imgJug2Rep);
        jugadoresActivos.add(1);
        jugadoresActivos.add(2);
      } else if (check3.isSelected()) {
        panelJugador1.setVisible(true);
        panelJugador2.setVisible(true);
        panelJugador3.setVisible(true);
        imgJug1.setImage(imgJug1Rep);
        imgJug2.setImage(imgJug2Rep);
        imgJug3.setImage(imgJug3Rep);
        jugadoresActivos.add(1);
        jugadoresActivos.add(2);
        jugadoresActivos.add(3);
      } else if (check4.isSelected()) {
        panelJugador1.setVisible(true);
        panelJugador2.setVisible(true);
        panelJugador3.setVisible(true);
        panelJugador4.setVisible(true);
        imgJug1.setImage(imgJug1Rep);
        imgJug2.setImage(imgJug2Rep);
        imgJug3.setImage(imgJug3Rep);
        imgJug4.setImage(imgJug4Rep);
        jugadoresActivos.add(1);
        jugadoresActivos.add(2);
        jugadoresActivos.add(3);
        jugadoresActivos.add(4);
      } else {
        // si no se han elegido los jugadores, mostrar un mensaje de error
        cajaTexInfo.setText("Seleccione Nº de Jugadores");
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), cajaTexInfo);
        timelineText.setCycleCount(1);
        timelineText.play();
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
      }
    } catch (RuntimeException e) {
      cajaTexInfo.setText("Ha ocurrido un error");
      FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), cajaTexInfo);
      timelineText.setCycleCount(1);
      timelineText.play();
      fadeIn.setFromValue(0);
      fadeIn.setToValue(1);
      fadeIn.play();
    }
    siguienteTurno(); // pasamos el turno al siguiente jugador
  }

  private void siguienteTurno() {

    // deshabilitar los controles de todos los jugadores
    deshabilitarTodosControles();
    // si hay jugadores aún jugando (su ID está en la cola)
    // obtener de la cola el ID de jugador en posesión del turno
    // activar solo los controles de este jugador
    // si no quedan jugadores aún jugando (cola vacía) se puede anunciar el ganador
    if (!jugadoresActivos.isEmpty()) {
      jugadorTurno = jugadoresActivos.poll();
      // busca los botones carta1, carta2, carta3, ... y los desactiva
      ((Button) main.lookup("#btnPide" + jugadorTurno)).setDisable(false);
      ((Button) main.lookup("#btnPlantar" + jugadorTurno)).setDisable(false);
      // Se coloca el color de fondo original a cada panel
      BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(20, 20, 20, 0.3),
        new CornerRadii(30), null);
      Background bg = new Background(backgroundFill);
      ((Pane) main.lookup("#panelJugador" + jugadorTurno)).setBackground(bg);
      jugadoresActivos.offer(jugadorTurno);
    } else {
      anunciaGanador();
    }
  }

  private void anunciaGanador() {
    deshabilitarTodosControles();
    List<Mazo> jugadoresActivosNoPasados = new ArrayList<>();
    List<Mazo> jugadoresSieteYMedia = new ArrayList<>();

    // Recorre todos los jugadores y evalúa su puntuación
    for (Mazo jugador : juego.getJugadores()) {
      float puntosJugador = jugador.sumarPuntos();

      if (puntosJugador == 7.5) {
        jugadoresSieteYMedia.add(jugador);
      } else if (puntosJugador < 7.5) {
        jugadoresActivosNoPasados.add(jugador);
      }
    }
    // Determina el jugador ganador si no hay ningún jugador que haya hecho 7.5
    if (jugadoresSieteYMedia.isEmpty()) {
      Mazo jugadorGanador = obtenerJugadorGanador(jugadoresActivosNoPasados);
      if (jugadorGanador != null) {
        reproductorDeEfectos(pistaGanaste);
        cambiarFotoJugadorGanador(jugadorGanador.obtenerId());
      }
    }
  }

  private Mazo obtenerJugadorGanador(List<Mazo> jugadoresActivos) {
    Mazo jugadorGanador = null;
    float maxPuntos = 0;
    for (Mazo jugador : jugadoresActivos) {
      float puntosJugador = jugador.sumarPuntos();

      if (puntosJugador > maxPuntos) {
        maxPuntos = puntosJugador;
        jugadorGanador = jugador;
      }
    }
    return jugadorGanador;
  }

  private void deshabilitarTodosControles() {
    // para los 4 jugadores desactivar sus controles de juego
    // Ejemplo para deshabilitar elementos llamados carta1, carta2, plantar1, plantar2, panel1, panel2...
    for (int i = 1; i <= 4; i++) {
      // busca los botones carta1, carta2, carta3, ... y los desactiva
      ((Button) main.lookup("#btnPide" + i)).setDisable(true);
      // busca los botones plantar1, plantar2, plantar3, ... y los desactiva
      ((Button) main.lookup("#btnPlantar" + i)).setDisable(true);
      // se coloca el color de fondo original a cada panel
      BackgroundFill backgroundFill = new BackgroundFill(Color.web("#4E6E81"), new CornerRadii(30), null);
      Background bg = new Background(backgroundFill);
      // busca cada panel panel1, panel2, panel3, ... y les cambia el fondo 
      ((Pane) main.lookup("#panelJugador" + i)).setBackground(bg);
    }
  }

  public void reiniciaJuego() {
    // para los 4 jugadores reiniciar todos sus elementos:
    // desactivar los botones Carta/Plantarse
    deshabilitarTodosControles();
    // decimos que en el cuadro de imagén de cada jugador, se cargue la imagen en reposo
    // ocultar las imagenes ganador/perdedor
    imgJug1.setImage(imgJug1Rep);
    imgJug2.setImage(imgJug2Rep);
    imgJug3.setImage(imgJug3Rep);
    imgJug4.setImage(imgJug4Rep);
    // vaciar el contenido de su caja de texto
    // ocultar el panel completo
    for (int i = 1; i <= 4; i++) {
      ((TextField) main.lookup("#cuadroTextoJugador" + i)).setText("");
      ((Pane) main.lookup("#panelJugador" + i)).setVisible(false);
    }
    // resetear el número de jugadores total
    for (int i = 0; i < jugadoresActivos.size(); i++) {
      jugadoresActivos.remove(i);
    }
  }

  /**
   * @param event Evento que desencadena la acción (clic en el botón Carta)
   */
  @FXML
  private void accionCarta(ActionEvent event) {
    reproductorDeEfectos(pistaCarta);
    Mazo jugadorActual; // para almacenar referencia a un jugador del juego.
    // extraer una carta de la baraja del juego
    Carta carta = juego.baraja.extraerCarta();
    // identificar el jugador actual (el que tiene el turno).
    // insertar la carta extraida en el mazo del jugador actual
    switch (jugadorTurno) {
      case 1:
        jugadorActual = juego.jugador1;
        jugadorActual.insertarCarta(carta);
        ImageView marco1 = marcoCartaJug1.poll();// obtenemos el primer marco de la lista
        marco1.setImage(jugadorActual.mostrar(carta)); // insertamos la imagen en el marco
        marcoCartaJug1.offer(marco1);// Lo ponemos al final de la listapara luego
        break;
      case 2:
        jugadorActual = juego.jugador2;
        jugadorActual.insertarCarta(carta);
        ImageView marco2 = marcoCartaJug2.poll();// obtenemos el primer marco de la lista
        marco2.setImage(jugadorActual.mostrar(carta)); // insertamos la imagen en el marco
        marcoCartaJug2.offer(marco2);// Lo ponemos al final de la listapara luego
        break;
      case 3:
        jugadorActual = juego.jugador3;
        jugadorActual.insertarCarta(carta);
        ImageView marco3 = marcoCartaJug3.poll();// obtenemos el primer marco de la lista
        marco3.setImage(jugadorActual.mostrar(carta)); // insertamos la imagen en el marco
        marcoCartaJug1.offer(marco3);// Lo ponemos al final de la listapara luego
        break;
      case 4:
        jugadorActual = juego.jugador4;
        jugadorActual.insertarCarta(carta);
        ImageView marco4 = marcoCartaJug4.poll(); // obtenemos el primer marco de la lista
        marco4.setImage(jugadorActual.mostrar(carta)); // insertamos la imagen en el marco
        marcoCartaJug1.offer(marco4);// Lo ponemos al final de la listapara luego
        break;
      default:
        throw new AssertionError();
    }
    // calcular los puntos totales del jugador y determinar si el jugador puede seguir jugando o no 
    // si su puntuación pasa de 7.5 --> finaliza su participación (mostrar un mensaje)
    // si su puntuación es 7.5 --> finaliza su participación (mostrar un mensaje)
    // en cualquier otra situación --> continua en el juego (meter el ID del jugador en la última posición de la cola)
    if (jugadorActual.sumarPuntos() > 7.5) {
      ((TextField) main.lookup("#cuadroTextoJugador" + jugadorTurno))
        .setText("Te pasaste " + jugadorActual.obtenerNombre() + "!!!");
      cambiarFotoJugadorPerdedor(jugadorTurno);
      reproductorDeEfectos(pistaPerdiste);
      jugadoresActivos.remove(jugadorTurno);
      siguienteTurno();
    } else if (jugadorActual.sumarPuntos() == 7.5) {
      ((TextField) main.lookup("#cuadroTextoJugador" + jugadorTurno)).setText("Enhorabuena, 7.5!!!");
      reproductorDeEfectos(pistaGanaste);
      cambiarFotoJugadorGanador(jugadorTurno);
      jugadoresActivos.remove(jugadorTurno);
      siguienteTurno();
    } else {
      // pasamos el turno al siguiente jugador
      siguienteTurno();
    }
  }

  /**
   * @param event Evento que desencadena la acción (clic en el botón Plantar)
   */
  @FXML
  private void accionPlantar(ActionEvent event) {
    Mazo jugadorActual;
    // identificar el jugador actual (el que tiene el turno).
    switch (jugadorTurno) {
      case 1:
        jugadorActual = juego.jugador1;
        jugadoresActivos.remove(jugadorTurno);
        break;
      case 2:
        jugadorActual = juego.jugador2;
        jugadoresActivos.remove(jugadorTurno);
        break;
      case 3:
        jugadorActual = juego.jugador3;
        jugadoresActivos.remove(jugadorTurno);
        break;
      case 4:
        jugadorActual = juego.jugador4;
        jugadoresActivos.remove(jugadorTurno);
        break;
      default:
        throw new AssertionError();
    }
    // calcular los puntos totales del jugador
    // localizar la caja de texto del jugador y añadimos la puntuación final
    ((TextField) main.lookup("#cuadroTextoJugador" + jugadorTurno))
      .setText(jugadorActual.obtenerNombre() + ", Pts: " + jugadorActual.sumarPuntos());
    // Si la lista de jugadores está vacía cantamos el ganador, si no, pasamos el turno al siguiente jugador
    if (jugadoresActivos.isEmpty()) {
      anunciaGanador();
    } else {
      siguienteTurno();
    }
  }

  /**
   * @param event Evento que desencadena la acción (clic en el botón Salir)
   */
  @FXML
  private void salirJuego(ActionEvent event) {
    System.exit(0); // cierra el programa
  }

  // Método que maneja los marcos donde se van poniendo las carta de cada jugador
  private void manejoMarcosCartas() {
    // reiniciar cola de marcos de cada jugador
    marcoCartaJug1.clear();
    marcoCartaJug2.clear();
    marcoCartaJug3.clear();
    marcoCartaJug4.clear();
    //Metemos los marcos para las imagenes de las cartas en una lista para poder recorrerla
    marcoCartaJug1.add(cart1Jug1);
    marcoCartaJug1.add(cart2Jug1);
    marcoCartaJug1.add(cart3Jug1);
    marcoCartaJug1.add(cart4Jug1);
    marcoCartaJug1.add(cart5Jug1);
    marcoCartaJug1.add(cart6Jug1);
    marcoCartaJug1.add(cart7Jug1);
    marcoCartaJug2.add(cart1Jug2);
    marcoCartaJug2.add(cart2Jug2);
    marcoCartaJug2.add(cart3Jug2);
    marcoCartaJug2.add(cart4Jug2);
    marcoCartaJug2.add(cart5Jug2);
    marcoCartaJug2.add(cart6Jug2);
    marcoCartaJug2.add(cart7Jug2);
    marcoCartaJug3.add(cart1Jug3);
    marcoCartaJug3.add(cart2Jug3);
    marcoCartaJug3.add(cart3Jug3);
    marcoCartaJug3.add(cart4Jug3);
    marcoCartaJug3.add(cart5Jug3);
    marcoCartaJug3.add(cart6Jug3);
    marcoCartaJug3.add(cart7Jug3);
    marcoCartaJug4.add(cart1Jug4);
    marcoCartaJug4.add(cart2Jug4);
    marcoCartaJug4.add(cart3Jug4);
    marcoCartaJug4.add(cart4Jug4);
    marcoCartaJug4.add(cart5Jug4);
    marcoCartaJug4.add(cart6Jug4);
    marcoCartaJug4.add(cart7Jug4);
    // Vaciamos los marcos de los jugadores 
    for (ImageView marco : marcoCartaJug1) {
      marco.setImage(null);
    }
    for (ImageView marco : marcoCartaJug2) {
      marco.setImage(null);
    }
    for (ImageView marco : marcoCartaJug3) {
      marco.setImage(null);
    }
    for (ImageView marco : marcoCartaJug4) {
      marco.setImage(null);
    }
  }

  private void cambiarFotoJugadorGanador(int id) {
    switch (id) {
      case 1:
        imgJug1.setImage(imgJug1Win);
        break;
      case 2:
        imgJug2.setImage(imgJug2Win);
        break;
      case 3:
        imgJug3.setImage(imgJug3Win);
        break;
      case 4:
        imgJug4.setImage(imgJug4Win);

        break;
      default:
        throw new AssertionError();
    }
  }

  private void cambiarFotoJugadorPerdedor(int id) {
    switch (id) {
      case 1:
        imgJug1.setImage(imgJugLose);
        break;
      case 2:
        imgJug2.setImage(imgJugLose);
        break;
      case 3:
        imgJug3.setImage(imgJugLose);
        break;
      case 4:
        imgJug4.setImage(imgJugLose);

        break;
      default:
        throw new AssertionError();
    }
  }
}
