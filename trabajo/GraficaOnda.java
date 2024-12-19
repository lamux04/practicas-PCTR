package trabajo;

// Clase para graficar la onda
import javax.swing.*;
import java.awt.*;

class GraficaOnda extends JPanel {
    private double[] u;

    public GraficaOnda(double[] u) {
        this.u = u;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        int width = getWidth();
        int height = getHeight();
        int nx = u.length;

        for (int i = 0; i < nx - 1; i++) {
            int x1 = i * width / nx;
            int y1 = height / 2 - (int) (u[i] * 100); // Escala para graficar
            int x2 = (i + 1) * width / nx;
            int y2 = height / 2 - (int) (u[i + 1] * 100);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public static void mostrarGrafica(double[] u) {
        JFrame frame = new JFrame("Onda Unidimensional");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraficaOnda grafica = new GraficaOnda(u);
        frame.add(grafica);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}
