package org.familytree.design;

import org.familytree.ArbolBinario;
import org.familytree.Nodo;

import javax.swing.*;
import java.awt.*;

public class ArbolBinarioGrafico extends JFrame{
    private JPanel ventana;

    ArbolBinario arbol = new ArbolBinario();

    public ArbolBinarioGrafico() {
        super("Arbol Binario grafico");
        arbol.insert(8);
        arbol.insert(5);
        arbol.insert(6);
        arbol.insert(9);
        arbol.insert(3);
        arbol.insert(4);
        arbol.insert(2);
        arbol.insert(7);
        arbol.insert(10);

    }

    public int drawTree(Graphics g, Nodo x, int x0, int x1, int y) {
        int m = (x0 + x1) / 2;
        g.setColor(Color.blue);
        g.fillOval(m, y, 50, 40);
        g.setColor(Color.lightGray);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String t = String.valueOf(x.data);
        g.drawString(t, m+20, y+30);
        if(x.left != null) {
            int x2 = drawTree(g, x.left, x0, m, y+50);
            g.drawLine(m+25,y+40,x2+25,y+50);
        }
        if(x.right != null) {
            int x2 = drawTree(g, x.right, m, x1, y+50);
            g.drawLine(m+25,y+40,x2+25,y+50);
        }

        return m;
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawTree(g, arbol.getRoot(), 0, this.getWidth() - 25, 100);
    }

}
