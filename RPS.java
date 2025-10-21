import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// This is the main class. This class extends JFrame and implements Action and Implement listener.
class RPS extends JFrame implements ActionListener, ComponentListener {
    JButton b1 = new JButton("ROCK");
    JButton b2 = new JButton("PAPER");
    JButton b3 = new JButton("SCISSORS");
    JButton rst = new JButton("RESET");

    JButton jdjb = new JButton("OK");
    JDialog jd = new JDialog(this, "System Message", true);
    JLabel jdjl = new JLabel("SYSTEM REBOOT COMPLETE", SwingConstants.CENTER);

    JTextField tf = new JTextField("START!", 20);
    JLabel l1 = new JLabel();
    JLabel l2 = new JLabel();
    JLabel l3 = new JLabel();

    JPanel mp = new JPanel();
    JPanel bp = new JPanel();
    JPanel sp = new JPanel();

    int ctr = -1, w = 0, l = 0, d = 0, mw = 0, ml = 0, md = 0;
    boolean maximized = false;

    void jf() {
        setTitle("RPS: Clash of Champions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        addComponentListener(this);
    }

// The Dialog box appears upon clicking RESET and disables the buttons unless the user clicks OK.
    void jdia() {
        jd.setSize(400, 200); 
        jd.setLayout(new BorderLayout());
        jd.setLocationRelativeTo(this);

        jdjl.setFont(new Font("Arial", Font.BOLD, 20)); 
        jdjl.setAlignmentX(Component.CENTER_ALIGNMENT);
        jd.add(jdjl, BorderLayout.CENTER);

        jdjb.setFont(new Font("Arial", Font.BOLD, 16)); 
        jdjb.setPreferredSize(new Dimension(100, 40)); 
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jdjb);
        jd.add(buttonPanel, BorderLayout.SOUTH);
        jdjb.addActionListener(this);
        // Added the AcrionListener to the OK button.

        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);

        jd.setVisible(true);
    }

    void buildInterface(boolean max) {
        getContentPane().removeAll();
        mp = new JPanel();
        bp = new JPanel();
        sp = new JPanel();

        // Set interface layout based on maximized or normal.
        Font titleFont, btnFont, labFont, tfFont, highFont;
        Dimension btnSize, tfSize;

        if (max) {
            mp.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            titleFont = new Font("Arial", Font.BOLD, 22);
            btnFont = new Font("Arial", Font.BOLD, 18);
            btnSize = new Dimension(200, 60);
            tfFont = new Font("Arial", Font.PLAIN, 16);
            highFont = new Font("Arial", Font.BOLD, 16);
            labFont = new Font("Arial", Font.PLAIN, 16);
            tfSize = new Dimension(300, 40);
        } else {
            titleFont = new Font("Arial", Font.BOLD, 19);
            btnFont = new Font("Arial", Font.BOLD, 17);
            btnSize = new Dimension(154, 48);
            tfFont = new Font("Arial", Font.PLAIN, 15);
            highFont = new Font("Arial", Font.BOLD, 16);
            labFont = new Font("Arial", Font.PLAIN, 16);
            tfSize = new Dimension(260, 32);
        }

        mp.setLayout(new BoxLayout(mp, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("*****CHOOSE YOUR CHOICE*****");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(titleFont);
        mp.add(title);
        mp.add(Box.createVerticalStrut(max ? 30 : 20));

        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        bp.setOpaque(false);
        bp.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton[] buttons = {b1, b2, b3};
        Color[] bcolors = {
            new Color(135,135,135),
            new Color(130,168,210),
            new Color(140,173,110)
        };
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setPreferredSize(btnSize);
            buttons[i].setMaximumSize(btnSize);
            buttons[i].setFont(btnFont);
            buttons[i].setBackground(bcolors[i]);
        }

        if(max) {
            bp.add(Box.createHorizontalGlue());
        }
        bp.add(b1);
        bp.add(Box.createHorizontalStrut(max ? 20 : 14));
        bp.add(b2);
        bp.add(Box.createHorizontalStrut(max ? 20 : 14));
        bp.add(b3);
        if (max) {
            bp.add(Box.createHorizontalGlue());
        }
        mp.add(bp);
        mp.add(Box.createVerticalStrut(max ? 30 : 22));

        tf.setMaximumSize(tfSize);
        tf.setEditable(false);
        tf.setAlignmentX(Component.CENTER_ALIGNMENT);
        tf.setFont(tfFont);
        mp.add(tf);
        mp.add(Box.createVerticalStrut(max ? 30 : 18));

        sp.setLayout(new BoxLayout(sp, BoxLayout.Y_AXIS));
        sp.setOpaque(false);
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);
        l3.setAlignmentX(Component.CENTER_ALIGNMENT);
        l3.setFont(highFont);
        l1.setFont(labFont);
        l2.setFont(labFont);
        l3.setText("HIGHEST: wins- " + mw + " / loses- " + ml + " / draws- " + md);
        sp.add(l1);
        sp.add(l2);
        sp.add(l3);

        mp.add(sp);

        add(mp, BorderLayout.CENTER);

        rst.setBackground(new Color(255, 102, 102));
        JPanel tp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tp.setOpaque(false);
        tp.add(rst);
        add(tp, BorderLayout.NORTH);

// The Top Panel containing the RESET button with FlowLayout.
        revalidate();
        repaint();
    }

    void bt() {
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        rst.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int a = 0;
        int r = (int) (Math.random() * 3) + 1;
        if(e.getSource()== jdjb){
            jd.dispose();
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(true);
            return;
        }
        if (e.getSource() == b1) a = 1;
        else if (e.getSource() == b2) a = 2;
        else if (e.getSource() == b3) a = 3;
        else if (e.getSource() == rst) {
            ctr = -1;
            tf.setText("START!");
            if (w > mw) mw = w;
            if (l > ml) ml = l;
            if (d > md) md = d;
            l3.setText("HIGHEST: wins- " + mw + " / loses- " + ml + " / draws- " + md);
            w = l = d = 0;
            l2.setText("CURRENT: wins- " + w + " / loses- " + l + " / draws- " + d);
            jdia();
            return;
        }
        if (a == r) {
            tf.setText("DRAW!!!");
            d++;
        } else {
            switch (a) {
                case 1:
                    if (r == 2) { tf.setText("YOU LOSE, I choose paper"); l++; }
                    else { tf.setText("YOU WIN, I choose scissors"); w++; }
                    break;
                case 2:
                    if (r == 3) { tf.setText("YOU LOSE, I choose scissors"); l++; }
                    else { tf.setText("YOU WIN, I choose rock"); w++; }
                    break;
                case 3:
                    if (r == 1) { tf.setText("YOU LOSE, I choose rock"); l++; }
                    else { tf.setText("YOU WIN, I choose paper"); w++; }
                    break;
            }
        }
        ctr++;
        l1.setText("Games Played: " + (ctr + 1));
        l2.setText("CURRENT: wins- " + w + " / loses- " + l + " / draws- " + d);
    }

    // Auto switch UI on maximize/restore
    @Override
    public void componentResized(ComponentEvent e) {
        boolean max = (getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH;
        if (max != maximized) {
            maximized = max;
            buildInterface(maximized);
        }
    }
    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentShown(ComponentEvent e) {}
    @Override
    public void componentHidden(ComponentEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RPS r = new RPS();
            r.jf();
            r.buildInterface(false);
            r.bt();
            r.setVisible(true);
        });
    }
}