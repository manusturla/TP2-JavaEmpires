package Ubicables;

import Exceptions.NoSePudoConstruirException;
import Exceptions.PosicionFueraDeRangoException;
import Exceptions.UbicableEstaOcupadoException;
import Tablero.Posicion;
import Tablero.PosicionEdificio;
import Turnos.Estado;
import Turnos.Ocupado;
import Ubicables.Ubicable;

public class PlazaCentral implements Ubicable {
    PosicionEdificio posicion;
    Estado estado;
    private int vida= 450;
    private int vidaActual = vida;


    public PlazaCentral(Posicion posicionAldeano) throws PosicionFueraDeRangoException, NoSePudoConstruirException {
        posicion = new PosicionEdificio(posicionAldeano, 4);
        posicion.ocuparPosiciones(this);
        estado = new Ocupado(3);
    }

    public void desocuparUnTurno() {
        estado = estado.desocuparUnTurno();
    }

    public boolean estaOcupada() {
        return estado.estaOcupado();
    }

    public Aldeano crearAldeano() throws UbicableEstaOcupadoException, PosicionFueraDeRangoException {
        if(estado.estaOcupado()){
            throw new UbicableEstaOcupadoException();
        }
        Aldeano aldeano = new Aldeano(posicion.obtenerPosicionDeAldeano()); //Es posicion Despliegue
        return aldeano;
    }


    @Override
    public boolean estaMuerto() {
        return vidaActual<1;
    }

    @Override
    public void quitarVida(int dañoRecibido) {
        vidaActual -= dañoRecibido;
    }
}
