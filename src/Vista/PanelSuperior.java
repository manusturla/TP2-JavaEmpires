package Vista;

import Controladores.ControladorTerminadorDeTurnos;
import Modelo.Exceptions.PosicionInvalidaException;
import Modelo.Juego.ControladorDeTurnos;
import Modelo.Juego.Juego;
import Modelo.Jugador.Jugador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Observable;
import java.util.Observer;

public class PanelSuperior extends HBox{

    ControladorDeTurnos controladorDeTurnos;
    Jugador jugado1;
    Jugador jugado2;


    public PanelSuperior(Juego juego){

        jugado1 = juego.getJugador1();
        jugado2 = juego.getJugador2();
        controladorDeTurnos = juego.getControladorDeTurnos();

        VBox informacionJugaddor1 = new VBox();
        informacionJugaddor1.setAlignment(Pos.CENTER);
        VBox informacionJugaddor2 = new VBox();
        informacionJugaddor2.setAlignment(Pos.CENTER);

        Button terminarTurnoBoton = new Button("Terminar turno.");
        terminarTurnoBoton.setOnAction(new ControladorTerminadorDeTurnos(controladorDeTurnos));

        Label nombreJugador1 = new Label("Jugador1");
        Label nombreJugador2 = new Label("Jugador2");
        Label oroJugador1 = new Label("Oro: "+ jugado1.getOro());
        Label oroJugador2 = new Label("Oro: "+ jugado2.getOro());
        Label poblacionJugador1 = new Label("Poblacion: "+ jugado2.getPoblacion() +"/50");
        Label poblacionJugador2 = new Label("Poblacion: "+ jugado2.getPoblacion() +"/50");

        this.setSpacing(220);
        this.setAlignment(Pos.CENTER);
        informacionJugaddor1.getChildren().addAll(nombreJugador1,oroJugador1,poblacionJugador1);
        informacionJugaddor2.getChildren().addAll(nombreJugador2,oroJugador2,poblacionJugador2);
        this.getChildren().addAll(informacionJugaddor1,terminarTurnoBoton,informacionJugaddor2);

    }


}
