import pkg.Elemento;

import java.awt.*;

public class Barreira extends Elemento {
    @Override
    public void desenha(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillRect(getPx(), getPy(), 60, 30);
    }
}
