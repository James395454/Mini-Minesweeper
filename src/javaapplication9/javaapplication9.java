/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication9;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.*;
import java.util.Random;

/**
 *
 * @author Jawes
 */
public class javaapplication9 implements ActionListener {

    Field field[][];
    int size = 5;
    boolean gameover = false;
    int count = 0;
    String diff;
    boolean select = false;
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    int countbombs = 0;

    public javaapplication9() {

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000);
        JButton b1 = new JButton(), b2 = new JButton(), b3 = new JButton();
        b1.setText("Easy");
        b2.setText("Medium");
        b3.setText("Hard");
        b1.setActionCommand("Easy");
        b2.setActionCommand("Medium");
        b3.setActionCommand("Hard");
        panel.setLayout(new FlowLayout());
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        frame.setContentPane(panel);

    }

    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new javaapplication9();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameover) {

            if ((e.getActionCommand().equals("Easy")) || (e.getActionCommand().equals("Medium")) || (e.getActionCommand().equals("Hard"))) {
                if (e.getActionCommand().equals("Medium")) {
                    size = 10;
                } else if (e.getActionCommand().equals("Hard")) {
                    size = 15;
                }
                Random random = new Random();

                field = new Field[size][size];
                int k = 0;
                frame.remove(panel);
                panel1.setLayout(new GridLayout(size, size, 3, 3));
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        int state;
                        if (size == 5) {
                            state = random.nextInt(4);
                        } else if (size == 10) {
                            state = random.nextInt(3);
                        } else {
                            state = random.nextInt(2);
                        }
                        if (state == 1) {
                            field[i][j] = new Field(true);
                            countbombs++;
                        } else {
                            field[i][j] = new Field(false);
                        }
                        field[i][j].Button.setBackground(Color.green);
                        field[i][j].Button.setText("");
                        field[i][j].Button.setActionCommand(Integer.toString(k));
                        field[i][j].Button.addActionListener(this);
                        panel1.add(field[i][j].Button);
                        k++;
                        //frame=new JFrame();

                    }
                }
                frame.setContentPane(panel1);
                frame.revalidate();
                frame.repaint();
                select = true;
            } else {
                if (!select) {
                    frame.setContentPane(panel);
                }
                int x = 0, y = 0;
                String cmd = e.getActionCommand();
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (field[i][j].Button.getActionCommand().equals(cmd)) {
                            x = i;
                            y = j;
                        }
                    }
                }
                if (field[x][y].state) {
                    gameover = true;
                    field[x][y].Button.setText("BOOM!");
                    field[x][y].Button.setBackground(Color.red);

                } else {
                    field[x][y].Button.setText("Clean!");
                    if (!field[x][y].pressed) {
                        count++;
                    }

                }
                field[x][y].pressed = true;
                if (count == (size * size) - countbombs) {
                    gameover = true;

                }
                if (gameover) {
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if ((field[i][j].state) && (!field[i][j].pressed)) {
                                field[i][j].Button.setText("X");
                            }
                        }
                    }
                }
                if ((x - 1 >= 0) && (y - 1 >= 0)) {
                    if (field[x - 1][y - 1].state == true) {
                        field[x - 1][y - 1].Button.setText("X");
                    }
                }
                if ((x - 1 >= 0) && (y >= 0)) {
                    if (field[x - 1][y].state == true) {
                        field[x - 1][y].Button.setText("X");
                    }
                }
                if ((x - 1 >= 0) && (y + 1 < size)) {
                    if (field[x - 1][y + 1].state == true) {
                        field[x - 1][y + 1].Button.setText("X");
                    }
                }
                if (y - 1 >= 0) {
                    if (field[x][y - 1].state == true) {
                        field[x][y - 1].Button.setText("X");
                    }
                }
                if (y + 1 < size) {
                    if (field[x][y + 1].state == true) {
                        field[x][y + 1].Button.setText("X");
                    }
                }
                if ((x + 1 < size) && (y - 1 >= 0)) {
                    if (field[x + 1][y - 1].state == true) {
                        field[x + 1][y - 1].Button.setText("X");
                    }
                }
                if ((x + 1 < size) && (y >= 0)) {
                    if (field[x + 1][y].state == true) {
                        field[x + 1][y].Button.setText("X");
                    }
                }
                if ((x + 1 < size) && (y + 1 < size)) {
                    if (field[x + 1][y + 1].state == true) {
                        field[x + 1][y + 1].Button.setText("X");
                    }

                }

            }
        }
    }
}
