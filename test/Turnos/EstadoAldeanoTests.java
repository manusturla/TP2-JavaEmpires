package Turnos;

import Exceptions.*;
import Jugador.Jugador;
import Tablero.Mapa;
import Tablero.Posicion;
import Ubicables.Unidades.Aldeano;
import org.junit.jupiter.api.Test;
import Jugador.Faccion;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EstadoAldeanoTests {
    Faccion faccion = new Faccion();

    @Test
    void estadoInicialEsDesocupado() throws PosicionFueraDeRangoException, PosicionNoDisponibleException {
        Mapa mapa = new Mapa(5, 5);
        Posicion posicion = new Posicion(mapa, 3, 3);
        Aldeano aldeano = new Aldeano(posicion, faccion);
        assertFalse(aldeano.estaOcupado());
    }

    @Test
    void estaOcupadoLuegoDeMoverPosicion() throws UbicableEstaOcupadoException, PosicionFueraDeRangoException, MovimientoNoPermitidoException, PosicionNoDisponibleException {
        Mapa mapa = new Mapa(5, 5);
        Posicion posicion = new Posicion(mapa, 3, 3);
        Aldeano aldeano = new Aldeano(posicion, faccion);
        aldeano.moverDerecha();
        assertTrue(aldeano.estaOcupado());
    }

    @Test
    void realizarDosAccionesEnElMismoTurnoLevantaExcepcion() throws UbicableEstaOcupadoException, PosicionFueraDeRangoException, MovimientoNoPermitidoException, PosicionNoDisponibleException {
        Mapa mapa = new Mapa(10, 10);
        Posicion posicion = new Posicion(mapa, 3, 3);
        Aldeano aldeano = new Aldeano(posicion, faccion);
        aldeano.moverDerecha();
        assertThrows(UbicableEstaOcupadoException.class,() -> {
            aldeano.moverDerecha();
        });
    }

    @Test
    void estaDesocupadoLuegoDeMoverPosicionDesocuparUnTurno() throws UbicableEstaOcupadoException, PosicionFueraDeRangoException, MovimientoNoPermitidoException, PosicionNoDisponibleException {
        Mapa mapa = new Mapa(5, 5);
        Posicion posicion = new Posicion(mapa, 3, 3);
        Aldeano aldeano = new Aldeano(posicion, faccion);
        aldeano.moverDerecha();
        aldeano.desocuparUnTurno();
        assertFalse(aldeano.estaOcupado());
    }

    @Test
    void estaOcupadoLuegoDeCrearEdificio() throws UbicableEstaOcupadoException, PosicionFueraDeRangoException, NoSePudoConstruirException, PosicionNoDisponibleException {
        Mapa mapa = new Mapa(10, 10);
        Posicion posicion = new Posicion(mapa, 3, 3);
        Posicion posicionCostruccion = new Posicion(mapa,4,3);
        Aldeano aldeano = new Aldeano(posicion, faccion);
        aldeano.crearPlazaCentral(posicionCostruccion);
        assertTrue(aldeano.estaOcupado());
    }

    @Test
    void estaOcupadoLuegoDeConstruirEdificioDesocuparUnTurno() throws UbicableEstaOcupadoException, PosicionFueraDeRangoException, NoSePudoConstruirException, PosicionNoDisponibleException {
        Mapa mapa = new Mapa(10, 10);
        Posicion posicion = new Posicion(mapa, 3, 3);
        Posicion posicionCostruccion = new Posicion(mapa,4,3);
        Aldeano aldeano = new Aldeano(posicion, faccion);
        aldeano.crearPlazaCentral(posicionCostruccion);
        aldeano.desocuparUnTurno();
        assertTrue(aldeano.estaOcupado());
    }

    @Test
    void estaDesocupadoLuegoDeConstruirEdificioDesocuparUnTurno3Veces() throws UbicableEstaOcupadoException, PosicionNoDisponibleException, PosicionFueraDeRangoException, MovimientoNoPermitidoException {
        Mapa mapa = new Mapa(10, 10);
        Posicion posicion = new Posicion(mapa, 3, 3);
        Aldeano aldeano = new Aldeano(posicion, faccion);
        aldeano.moverDerecha();
        aldeano.desocuparUnTurno();
        aldeano.desocuparUnTurno();
        aldeano.desocuparUnTurno();
        assertFalse(aldeano.estaOcupado());
    }

    @Test
    void realizarUnaAccionUnTurnoDespuesDeConstruirEdificioLevantaExcepcion() throws UbicableEstaOcupadoException, PosicionNoDisponibleException, PosicionFueraDeRangoException, NoSePudoConstruirException {
        Mapa mapa = new Mapa(10, 10);
        Posicion posicion = new Posicion(mapa, 3, 3);
        Posicion posicionCostruccion = new Posicion(mapa,4,3);
        Aldeano aldeano = new Aldeano(posicion, faccion);
        aldeano.crearPlazaCentral(posicionCostruccion);
        aldeano.desocuparUnTurno();
        assertThrows(UbicableEstaOcupadoException.class, aldeano::moverIzquierda);
    }


}
