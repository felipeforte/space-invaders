import pkg.Elemento;

import java.awt.*;

public class Barreira extends Elemento {
    private int saude;

    public void setSaude(int saude) {
        this.saude = saude;
    }

    public int getSaude() {
        return this.saude;
    }

    public void dimSaude(int qt) {
        this.saude -= qt;
    }
    public Barreira() {
        setLargura(60);
        setAltura(30);
        setSaude(5);
    }
    @Override
    public void desenha(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillRect(getPx(), getPy(), getLargura(), getAltura());
    }
}
