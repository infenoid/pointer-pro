package PointerPro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PointerXY extends JFrame {
    private JTextField inputField;
    private DrawingPanel drawingPanel;

    public PointerXY() {
        setTitle("Pointer XY");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(400, 400));

        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel("Enter coordinates (e.g., (x,y)):");
        inputPanel.add(inputLabel, BorderLayout.WEST);

        inputField = new JTextField();
        inputPanel.add(inputField, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String edu = inputField.getText().trim();
                int X = parsingX(edu);
                int Y = parsingY(edu);
                drawingPanel.setCoordinates(X, Y);
                drawingPanel.repaint();
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(submitButton, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
    }

    public static int parsingX(String str) {
        try {
            int index = str.indexOf(",", 0);
            String str1 = str.substring(1, index);
            return Integer.parseInt(str1);
        } catch (Exception e) {
            return 0; // Default value or handle error appropriately
        }
    }

    public static int parsingY(String str) {
        try {
            int index = str.indexOf(",", 0);
            String str2 = str.substring(index + 1, str.length() - 1);
            return Integer.parseInt(str2);
        } catch (Exception e) {
            return 0; // Default value or handle error appropriately
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PointerXY().setVisible(true);
            }
        });
    }

    class DrawingPanel extends JPanel {
        private int x = 0;
        private int y = 0;

        public void setCoordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            int centerX = width / 2;
            int centerY = height / 2;

            // Draw the axes
            g.drawLine(centerX, 0, centerX, height);
            g.drawLine(0, centerY, width, centerY);

            // Draw the point
            int pointX = centerX + x * 10; // Scale the coordinates for better visibility
            int pointY = centerY - y * 10; // Invert Y-axis for correct orientation
            g.fillOval(pointX - 2, pointY - 2, 4, 4);

            // Mark the axes with values at an interval of 5
            g.setFont(new Font("Arial", Font.PLAIN, 10));
            for (int i = -width / 20; i <= width / 20; i++) {
                if (i % 5 == 0) {
                    g.drawString(String.valueOf(i), centerX + i * 10, centerY + 15);
                }
            }
            for (int i = -height / 20; i <= height / 20; i++) {
                if (i % 5 == 0) {
                    g.drawString(String.valueOf(i), centerX + 5, centerY - i * 10);
                }
            }
        }
    }
}