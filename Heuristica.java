/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello.Utils;

public class Heuristica {

//heurística que nosotros hemos implementado
    public static int h1(Tablero tablero,int playerColor)
    {
        int score = Puntos(playerColor, tablero) - Puntos(-playerColor, tablero);
        
        int columnas = tablero.getCanidadColumnas();
        int filas = tablero.getCantidadFilas();
        
        // If the game is over
        if (tablero.EsFinalDeJuego())
        {
            // if player has won
            if (score > 0)
                return 100;
            // if player has lost (or tied)
            else
                return -100;
        }
        else{
            Casilla[][] matriz = tablero.getMatrizTablero();
            int puntuacion =0;
            
            //Si el jugador tiene las fichas blancas, aumenta heuristica con sus esquinas
            if(playerColor > 0){
                if (matriz[0][0].esBlanca()){
                    puntuacion = puntuacion+10;
                }
                if (matriz[0][columnas-1].esBlanca()){
                    puntuacion = puntuacion+10;
                }
                if (matriz[filas-1][0].esBlanca()){
                    puntuacion = puntuacion+10;
                }
                if (matriz[filas-1][columnas-1].esBlanca()){
                    puntuacion = puntuacion+10;
                }
            }
            
            //Si el jugador tiene las fichas negras, aumenta heuristica con sus esquinas
            if(playerColor < 0){
                if (matriz[0][0].esNegra()){
                    puntuacion = puntuacion+10;
                }
                if (matriz[0][columnas-1].esNegra()){
                    puntuacion = puntuacion+10;
                }
                if (matriz[filas-1][0].esNegra()){
                    puntuacion = puntuacion+10;
                }
                if (matriz[filas-1][columnas-1].esNegra()){
                    puntuacion = puntuacion+10;
                }
            }
            return score+puntuacion;
        }
        // if game isn't over, return relative advatage
        
        
    }


    //Una heuristica posible a usar
    public static int h2(Tablero tablero,int playerColor)
    {
        int score = Puntos(playerColor, tablero) - Puntos(-playerColor, tablero);

        // If the game is over
        if (tablero.EsFinalDeJuego())
        {
            // if player has won
            if (score > 0)
                return 100;
            // if player has lost (or tied)
            else
                return -100;
        }

        // if game isn't over, return relative advatage
        return score;
    }

    public static int Puntos(int playerColor, Tablero tablero)
    {
        int points = 0;

        for (int x = 0; x < Tablero.CANTIDAD_FILAS_DEFECTO; x++)
            for (int y = 0; y < Tablero.CANTIDAD_COLUMNAS_DEFECTO; y++)
                if (tablero.getMatrizTablero()[x][y].obtenerColorFicha() == playerColor)
                    points++;

        return points;
    }



}
