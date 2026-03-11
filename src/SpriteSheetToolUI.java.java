package src;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class ui {

    public static JCheckBox[] I = new JCheckBox[6];
    public static JCheckBox[] Z = new JCheckBox[6];

    public static void main(String... args) {

        FlatDarkLaf.setup();

        JFrame frame = new JFrame("Sprite Sheet Cutting Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(12, 12));

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(12, 16, 0, 16));

        JLabel title = new JLabel("Sprite Sheet Cutting Tool");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        JLabel subtitle = new JLabel("Split Emerald and FireRed sprite sheets into output folders.");
        subtitle.setFont(subtitle.getFont().deriveFont(Font.PLAIN, 13f));

        JPanel titleWrap = new JPanel();
        titleWrap.setLayout(new BoxLayout(titleWrap, BoxLayout.Y_AXIS));
        titleWrap.add(title);
        titleWrap.add(Box.createVerticalStrut(4));
        titleWrap.add(subtitle);

        header.add(titleWrap, BorderLayout.WEST);
        frame.add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 16, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        mainPanel.add(createEmeraldPanel());
        mainPanel.add(createFireRedPanel());

        frame.add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 16, 16, 16));

        JButton emeraldBtn = new JButton("Split Emerald Sprites");
        JButton fireRedBtn = new JButton("Split FireRed Sprites");

        emeraldBtn.setFocusPainted(false);
        fireRedBtn.setFocusPainted(false);

        emeraldBtn.addActionListener(new C(frame));
        fireRedBtn.addActionListener(new B(frame));

        buttonPanel.add(emeraldBtn);
        buttonPanel.add(fireRedBtn);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static JPanel createEmeraldPanel() {
        JPanel outer = new JPanel(new BorderLayout(8, 8));
        outer.setBorder(BorderFactory.createTitledBorder("Emerald"));

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.add(new JLabel("Input folder: inputEM"));
        info.add(Box.createVerticalStrut(4));
        info.add(new JLabel("Output folder: outputEM"));
        info.add(Box.createVerticalStrut(4));
        info.add(new JLabel("File format: [Moemon name].png"));

        outer.add(info, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 3, 10, 10));
        grid.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));

        grid.add(makeHeaderLabel("Category"));
        grid.add(makeHeaderLabel("Normal"));
        grid.add(makeHeaderLabel("Shiny"));

        I[4] = new JCheckBox();
        I[5] = new JCheckBox();
        grid.add(new JLabel("Front Anim"));
        grid.add(Zcenter(I[4]));
        grid.add(Zcenter(I[5]));

        I[0] = new JCheckBox();
        I[1] = new JCheckBox();
        grid.add(new JLabel("Front"));
        grid.add(Zcenter(I[0]));
        grid.add(Zcenter(I[1]));

        I[2] = new JCheckBox();
        I[3] = new JCheckBox();
        grid.add(new JLabel("Back"));
        grid.add(Zcenter(I[2]));
        grid.add(Zcenter(I[3]));

        outer.add(grid, BorderLayout.CENTER);
        return outer;
    }

    private static JPanel createFireRedPanel() {
        JPanel outer = new JPanel(new BorderLayout(8, 8));
        outer.setBorder(BorderFactory.createTitledBorder("FireRed"));

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.add(new JLabel("Input folder: inputFR"));
        info.add(Box.createVerticalStrut(4));
        info.add(new JLabel("Output folder: outputFR"));
        info.add(Box.createVerticalStrut(4));
        info.add(new JLabel("File format: [Moemon id].png"));

        outer.add(info, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 3, 10, 10));
        grid.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));

        grid.add(makeHeaderLabel("Category"));
        grid.add(makeHeaderLabel("Normal"));
        grid.add(makeHeaderLabel("Shiny"));

        Z[4] = new JCheckBox();
        Z[5] = new JCheckBox();
        grid.add(new JLabel("Front Anim"));
        grid.add(Zcenter(Z[4]));
        grid.add(Zcenter(Z[5]));

        Z[0] = new JCheckBox();
        Z[1] = new JCheckBox();
        grid.add(new JLabel("Front"));
        grid.add(Zcenter(Z[0]));
        grid.add(Zcenter(Z[1]));

        Z[2] = new JCheckBox();
        Z[3] = new JCheckBox();
        grid.add(new JLabel("Back"));
        grid.add(Zcenter(Z[2]));
        grid.add(Zcenter(Z[3]));

        outer.add(grid, BorderLayout.CENTER);
        return outer;
    }

    private static JLabel makeHeaderLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        return label;
    }

    private static JPanel Zcenter(JCheckBox checkBox) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.add(checkBox);
        return panel;
    }
}
