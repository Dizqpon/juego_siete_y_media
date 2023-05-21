package sieteymedia;


import static java.lang.String.format;

/**
 *
 * @author profesorado
 */
public class Carta implements Comparable<Carta>{
 // símbolos de las cartas
    public static final char PICA = 0x2660; // ♠
    public static final char TREBOL = 0x2663; // ♣
    public static final char CORAZON = 0x2665; // ♥ 
    public static final char DIAMANTE = 0x2666; // ♦
    
    private final char ARR_IZQ = '+';
    private final char ARR_DER = '+';
    private final char ABJ_IZQ = '+';
    private final char ABJ_DER = '+';
    private final char ARR_ABJ = '-';
    private final char IZQ_DER = '|';
    
    private char palo;       // palo de la carta
    private char simbolo;   // símbolo de la carta
    private float valor;
    private int orden;
    
    Carta(char simbolo, char palo, float valor, int orden) {
        this.palo = palo;
        this.simbolo = simbolo;
        this.valor = valor;
        this.orden = orden;
    }
    
    public char obtenerPalo() {
        return this.palo;
    }
    
    char obtenerSimbolo() {
        return this.simbolo;
    }
    
    public float obtenerValor() {
        return this.valor;
    }
    
    int obtenerOrden() {
        return this.orden;
    }
    
    String mostrar() {
        String cadena="";
        
        cadena = format("[ %c %c ]", this.simbolo, this.palo);
        
        return cadena;
    }
    
    String mostrarGrafico() {
        String cadena = "";
        String aux = "";
        int f,F=7,c,C=9;
        char[][] tabla;
        
        tabla = new char[F][C];
        
        // se limpia la tabla
        for(f=0;f<F;f++)
        {
            for(c=0;c<C;c++)
            {
            tabla[f][c] = ' ';
            }
        }
        
        // Se almacena el borde de la carta
        // primero las esquinas
        tabla[0][0] = ARR_IZQ;
        tabla[0][8] = ARR_DER;
        tabla[6][0] = ABJ_IZQ;
        tabla[6][8] = ABJ_DER;
        
        // Arriba y abajo
        for(c=1;c<C-1;c++)
            {
              tabla[0][c] = ARR_ABJ;
              tabla[6][c] = ARR_ABJ;
            }
            
        // Laterales
        for(f=1;f<F-1;f++)
            {
              tabla[f][0] = IZQ_DER;
              tabla[f][8] = IZQ_DER;
            }
        
        tabla[1][1] = this.simbolo;
        tabla[5][7] = this.simbolo;
        
        // y la carta
        switch (this.simbolo)
        {
            case 'A':
            case 'J':
            case 'Q':
            case 'K': tabla[3][4] = this.palo;   
            break;
            case '2': tabla[2][4] = this.palo; 
                      tabla[4][4] = this.palo; 
            break;
            case '3': tabla[3][4] = this.palo;
                      tabla[2][4] = this.palo; 
                      tabla[4][4] = this.palo;
            break;
            case '4': tabla[2][2] = this.palo;
                      tabla[2][6] = this.palo; 
                      tabla[4][2] = this.palo;
                      tabla[4][6] = this.palo;
            break;
            case '5': tabla[3][4] = this.palo;
                      tabla[2][2] = this.palo;
                      tabla[2][6] = this.palo; 
                      tabla[4][2] = this.palo;
                      tabla[4][6] = this.palo;
            break;
            case '6': tabla[2][2] = this.palo;
                      tabla[2][6] = this.palo; 
                      tabla[4][2] = this.palo;
                      tabla[4][6] = this.palo;
                      tabla[3][2] = this.palo;
                      tabla[3][6] = this.palo;
            break;
            case '7': tabla[3][4] = this.palo;
                      tabla[2][2] = this.palo;
                      tabla[2][6] = this.palo; 
                      tabla[4][2] = this.palo;
                      tabla[4][6] = this.palo;
                      tabla[3][2] = this.palo;
                      tabla[3][6] = this.palo;
            break;
        }
        
        // guardamos en la cadena
        for(f=0;f<F;f++)
        {
            for(c=0;c<C;c++)
            {
            aux = String.format("%c",tabla[f][c]);
            cadena = cadena.concat((String)aux);
            }
            
            cadena = cadena.concat("\n");
        }
        
    return cadena;
    }  
    
    @Override
    public int compareTo(Carta carta2){
       int resultado;
       if (this.obtenerOrden() > carta2.obtenerOrden()){
             resultado=1; 
       }else{  
           if (this.obtenerOrden()<carta2.obtenerOrden()){ 
                resultado=-1;
           }else{
               resultado=0;
           }
       }
       return resultado;
   }

}
