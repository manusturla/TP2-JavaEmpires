package Tablero;

import Exceptions.PosicionFueraDeRangoException;
import Exceptions.PosicionNoDisponibleException;
import Ubicables.Ubicable;

public class Mapa {
    Celda mapa[][];
    int largo;
    int ancho;

    public Mapa(int largoRecibido, int anchoRecibido){
        largo = largoRecibido;
        ancho = anchoRecibido;
        mapa = new Celda[largo][ancho];
        for(int i = 0;i < largo; i++){
            for(int j = 0; j < ancho;j++){
                mapa[i][j] = new Celda();
            }
        }
    }

    public boolean celdaEstaOcupada(Posicion posicion) throws PosicionFueraDeRangoException {
        return (this.obtenerCelda(posicion).estaOcupada());
    }

    public void ocuparCelda(Ubicable elementoMapa, Posicion posicion) throws PosicionFueraDeRangoException {
        this.obtenerCelda(posicion).ocuparCelda(elementoMapa);
    }

    public void desocuparCelda(Posicion posicion) throws PosicionFueraDeRangoException {
        this.obtenerCelda(posicion).desocuparCelda();
    }

    private Celda obtenerCelda(Posicion posicion) throws PosicionFueraDeRangoException {
        int coordenadaHorizontal = posicion.getCoordenadaHorizontal();
        int coordenadaVertical = posicion.getCoordenadaVertical();
        try{
            return mapa[coordenadaHorizontal][coordenadaVertical];
        }catch (ArrayIndexOutOfBoundsException e){
            throw new PosicionFueraDeRangoException();
        }

    }
/* CREO QUE YA NO ES NECESARIA
    private boolean estaEnMapa(Posicion posicion) throws PosicionNoDisponibleException {
        if (largo > posicion.getCoordenadaHorizontal() && posicion.getCoordenadaHorizontal() >= 0 && posicion.getCoordenadaVertical() >=0 && ancho > posicion.getCoordenadaVertical()) {
            return  true;
        }
        return false;
    }
*/
    public void moverElemento(Posicion posicion, Posicion posicion_llegada) throws PosicionNoDisponibleException, PosicionFueraDeRangoException {
        Ubicable elemento = this.obtenerCelda(posicion).getElemento();
        if (this.celdaEstaOcupada(posicion_llegada)){
            throw new PosicionNoDisponibleException();
        }
        this.obtenerCelda(posicion_llegada).ocuparCelda(elemento);
        this.obtenerCelda(posicion).desocuparCelda();
    }


}
