import java.util.ArrayList;
import java.util.List;
public class Jugador {
    private String nombre;
    private List<Carta> mano;
    private List<CartaMonstruo> campoMonstruos; 
    private Mazo mazo;
    private int puntosVida;
    private final int LP_INICIAL = 8000;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
        this.campoMonstruos = new ArrayList<>();
        this.mazo = new Mazo();
        this.puntosVida = LP_INICIAL;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public Carta robarCarta() {
        if (mazo.getCartasRestantes() == 0) {
            throw new IllegalStateException("¡El mazo está vacío!");
        }
        Carta carta = mazo.robarCarta();
        mano.add(carta);
        return carta;
    }
    
    public Carta jugarCarta(int indice) {
        if (indice < 0 || indice >= mano.size()) {
            throw new IndexOutOfBoundsException("Índice de carta inválido");
        }
        return mano.remove(indice);
    }
    
    public void invocarMonstruo(CartaMonstruo monstruo) {
        campoMonstruos.add(monstruo);
    }
    
    public List<CartaMonstruo> getCampoMonstruos() {
        return new ArrayList<>(campoMonstruos);
    }
    
    public boolean destruirMonstruo(CartaMonstruo monstruo) {
        return campoMonstruos.remove(monstruo);
    }
    
    public List<Carta> getMano() {
        return new ArrayList<>(mano);
    }
    
    public int getTamanoMano() {
        return mano.size();
    }
    
    public Carta obtenerCartaMano(int indice) {
        if (indice < 0 || indice >= mano.size()) {
            throw new IndexOutOfBoundsException("Índice de carta inválido");
        }
        return mano.get(indice);
    }
    
    public int getPuntosVida() {
        return puntosVida;
    }
    
    public void recibirDano(int cantidad) {
        this.puntosVida = Math.max(0, puntosVida - cantidad);
    }
    
    public void recuperarVida(int cantidad) {
        this.puntosVida = Math.min(LP_INICIAL, puntosVida + cantidad);
    }
    
    public boolean haPerded() {
        return puntosVida <= 0 || mazo.getCartasRestantes() == 0;
    }
    
    public int getCartasRestantesMazo() {
        return mazo.getCartasRestantes();
    }
    
    public void mostrarEstado() {
        System.out.println(""+ nombre +"");
        System.out.println("Puntos de Vida: " + puntosVida + "/8000");
        System.out.println("Cartas en Mano: " + mano.size());
        System.out.println("Cartas en Mazo: " + mazo.getCartasRestantes());
        System.out.println("Monstruos en Campo: " + campoMonstruos.size());
    }
    
    public void mostrarMano() {
        System.out.println("\nMano de " + nombre + " (" + mano.size() + " cartas):");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + mano.get(i));
        }
    }
    
    public void mostrarCampo() {
        System.out.println("\nCampo de " + nombre + " (" + campoMonstruos.size() + " monstruos):");
        if (campoMonstruos.isEmpty()) {
            System.out.println("  Sin monstruos en el campo");
        } else {
            for (int i = 0; i < campoMonstruos.size(); i++) {
                System.out.println("  [" + (i + 1) + "] " + campoMonstruos.get(i));
            }
        }
    }
}
