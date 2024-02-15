package org.familytree.design;

import org.familytree.ArbolBinario;
import org.familytree.Nodo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArbolBinarioGrafico extends JFrame {
    private JPanel ventana;
    private JButton insertButton;

    ArbolBinario arbol = new ArbolBinario();

    public ArbolBinarioGrafico() {
        super("Arbol Binario grafico");
        this.getContentPane().setBackground(Color.DARK_GRAY);
        ventana = new JPanel();
        this.setContentPane(ventana);
        insertButton = new JButton("Insertar");
        ventana.add(insertButton);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arbol.root == null) {
                    String parentNode = JOptionPane.showInputDialog("Ingrese el nodo padre");
                    arbol.insert(parentNode);
                   repaint();
                   return;
                }
                // Muestra un cuadro de diálogo para que el usuario ingrese el nodo padre
                String parentNode = JOptionPane.showInputDialog("Ingrese el nodo padre");

                // Muestra un cuadro de diálogo para que el usuario ingrese el nuevo nodo
                String newNode = JOptionPane.showInputDialog("Ingrese el nuevo nodo");

                // Inserta el nuevo nodo en el árbol
                arbol.insert(parentNode, newNode);
                paint(getGraphics());
            }
        });

    }


    public int drawTree(Graphics g, Nodo x, int x0, int x1, int y) {
        int m = (x0 + x1) / 2;
        g.setColor(Color.GREEN);
        g.fillOval(m, y, 120, 60);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String t = String.valueOf(x.data);
        g.drawString(t, m + 20, y + 30);
        if (x.left != null) {
            int x2 = drawTree(g, x.left, x0, m, y + 50);
            g.setColor(Color.RED);
            g.drawLine(m+25,y+40,x2+25,y+50);
        }
        if (x.right != null) {
            int x2 = drawTree(g, x.right, m, x1, y + 50);
            g.setColor(Color.RED);
            g.drawLine(m+25,y+40,x2+25,y+50);
        }

        return m;
    }

    public void paint(Graphics g) {
        if (arbol.root == null) {
            return;
        }
        super.paint(g);
        drawTree(g, arbol.getRoot(), 50, this.getWidth() - 75, 100);
    }

}
