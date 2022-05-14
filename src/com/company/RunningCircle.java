package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RunningCircle extends JPanel {
    private final Timer timer;
    private Point runTo, currPos;

    public RunningCircle() {
        runTo = currPos = new Point(0, 0);
        timer = new Timer(1000 / 30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int x = (4 * currPos.x + runTo.x) / 5;
                int y = (4 * currPos.y + runTo.y) / 5;
                currPos = new Point(x, y);
                timer.restart();
                repaint();
            }
        });
        timer.start();
        addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                runTo = e.getPoint();
            }
            public void mouseMoved(MouseEvent e) {
                runTo = e.getPoint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(currPos.x - 4, currPos.y - 4, 8, 8);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Running Circle");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new RunningCircle());
        window.pack();
        window.setVisible(true);
    }
}
