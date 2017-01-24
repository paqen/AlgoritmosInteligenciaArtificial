/*
 */

package othello.algoritmo;


import othello.Utils.Casilla;
import othello.Utils.Heuristica;
import othello.Utils.Tablero;
import java.util.ArrayList;


public class AlgoritmoMiniMax extends Algoritmo{
// ----------------------------------------------------------------------

// ----------------------------------------------------------------------

    /** Constructores **************************************************/
    private int playerColor;
    public AlgoritmoMiniMax(){

    }
    /*******************************************************************/
    

    @Override
    public Tablero obtenerNuevaConfiguracionTablero(Tablero tablero, short turno ){

        System.out.println( "analizando siguiente jugada con ALFABETA" );
        this.playerColor=turno;
        Tablero tableroJugada=tablero.copiarTablero();
         try{
             int beta = Integer.MAX_VALUE;
             int alfa = Integer.MIN_VALUE;
             MiniMax(tableroJugada, this.getProfundidad(), playerColor, alfa, beta);
            Thread.sleep( 1000 );
        }
        catch( Exception e ){
            e.printStackTrace();
        }

        return( tableroJugada );
    }

     /**
     *
     * Éste es el método que tenemos que implementar.
     * 
     * Algoritmo MiniMax para determinar cuál es el posible mejor movimiento
     * 
     * @param tablero
     * Configuración actual del tablero
     * @param prof
     * Profundidad de búsqueda
     * @param jugadorActual
     * Nos indica a qué jugador (FICHA_BLANCA ó FICHA_NEGRA) le toca
     * @param alfa
     * @param beta
     * Parámetros alfa y beta del algoritmo
     * @return
     */
    public int MiniMax(Tablero tablero, int prof, int jugadorActual, int alfa, int beta)
    {       
        //En caso de que no se pueda mover ninguna ficha,se devuelve el valor de la heurística
        //Heuristica h2 se basa en la cantidad de fichas de cada color
        //Heuristica h1 se encuentra basada en la anterior pero además le añade un determinado valor a las esquinas
            if (tablero.EsFinalDeJuego()||prof==0)
            {
                int valor= Heuristica.h1(tablero, playerColor);
                return valor;
            }
      
           //en caso de que este jugador no pueda jugar, pasará turno  
            if (!tablero.PuedeJugar(jugadorActual))
            {
                int valor = MiniMax(tablero, prof, -jugadorActual, alfa, beta);
                return valor;
            }
            
        
            ArrayList<Casilla> posicionPosible = tablero.generarMovimiento(jugadorActual);
            
        
        //Buscamos la mejor casilla posible dentro del array posicionPosible
            Casilla posMejor = null;
          
            for (Casilla celda : posicionPosible)
            {
                
                Tablero copiaTablero = tablero.copiarTablero();
                
                //Asignamos valor dependiendo del jugador y la introducimos en el tablero
                if (jugadorActual == 1)
                {
                    celda.asignarFichaBlanca();
                }
                else if (jugadorActual == -1)
                        {
                            celda.asignarFichaNegra();
                        }
                copiaTablero.ponerFicha(celda);
                copiaTablero.imprimirTablero();
                
                
                int valoracionActual = MiniMax(copiaTablero, prof - 1, -jugadorActual, alfa, beta);
                
                //Nodo MAX
                //Si el nuevo valor supera al anterior, lo sustituimos
                if (jugadorActual == this.playerColor)
                {
                    if (valoracionActual > alfa)
                    {
                        alfa = valoracionActual;
                        posMejor = celda;
                      }
                 }
                
                //Nodo MIN
                //Si el nuevo valor es menor que el anterior, lo sustituimos
                else
                {
                    if (valoracionActual < beta)
                    {
                        beta = valoracionActual;
                        posMejor = celda;
                       
                    }
                  }
            }    
                //Si hemos obtenido un valor mejorado, añadimos dicha ficha
                if (posMejor != null)
                {
                    tablero.ponerFicha(posMejor);
                }     


        // Retornar el valor asignado al movimiento
        if (jugadorActual == this.playerColor)
            return alfa;

        else
            return beta;
    }
}
