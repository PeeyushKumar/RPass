package rpass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Generator extends JFrame implements ActionListener {

    static final int WIDTH = 400;
    static final int HEIGHT = 300;

    Random random = new Random(System.currentTimeMillis());

    JLabel label;
    JButton generate;
    JPanel panel_controls;
    JSlider slide_length;

    JCheckBox check_numeric, check_symbols;

    Font font_tiny = new Font("Helvetica", Font.BOLD, 20);
    Font font_small = new Font("Helvetica", Font.BOLD, 25);
    Font font_medium = new Font("Helvetica", Font.BOLD, 30);
    Font font_large = new Font("Helvetica", Font.BOLD, 40);

    public Generator() {
        label = new JLabel("YourPassword", SwingConstants.CENTER);
        label.setFont(font_large);

        slide_length = new JSlider(JSlider.HORIZONTAL, 8, 25, 15);

        check_numeric = new JCheckBox("Digits");
        check_numeric.setFont(font_small);
        check_numeric.setFocusable(false);

        check_symbols = new JCheckBox("Symbols");
        check_symbols.setFont(font_small);
        check_symbols.setFocusable(false);
        
        panel_controls = new JPanel();
        panel_controls.add(check_numeric);
        panel_controls.add(check_symbols);

        generate = new JButton("Generate");
        generate.setFont(font_medium);
        generate.setFocusable(false);
        generate.setBorderPainted(false);
        generate.addActionListener(this);

        this.setLayout(new GridLayout(4,1));
        this.add(label);
        this.add(slide_length);
        this.add(panel_controls);
        this.add(generate);

        this.setTitle("RPass");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    String getPass() {

        String pool = "abcdefghijklmnopqrstuvwxyzABCDEFGNIJKLMNOPQRSTUVWXYZ_";
        String pool_numeric = "1234567890123456789012345678901234567890";
        String pool_symbols = "!@#$%^&*()/*-+.[]{}|,<>?'\";:`~";

        int length = slide_length.getValue();

        if (check_numeric.isSelected()) pool += pool_numeric;
        if (check_symbols.isSelected()) pool += pool_symbols;

        String pass = "";
        for (int i=0; i<length; i++) pass += pool.charAt(random.nextInt(pool.length()));
        return pass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int length = slide_length.getValue();
        
        label.setFont(font_large);
        if (length > 10) label.setFont(font_medium);
        if (length > 14) label.setFont(font_small);
        if (length > 20) label.setFont(font_tiny);
        
        label.setText(getPass());
    }
}