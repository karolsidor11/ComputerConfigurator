package frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Windows extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu Plik, Edycja, Pomoc;
    private JMenuItem Otwórz, Zapisz, Zamknij, Kopiuj, Wklej, Ustawienia, Help;


    public Windows() {
        setTitle("Konfigurator komputerowy");
        setLayout(null);
        setBounds(400, 100, 450, 500);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        Plik = new JMenu("Plik");
        Edycja = new JMenu("Edycja");
        Pomoc = new JMenu("Pomoc");
        menuBar.add(Plik);
        menuBar.add(Edycja);
        menuBar.add(Pomoc);

        Otwórz = new JMenuItem("Otwórz");
        Otwórz.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        Otwórz.addActionListener(this);
        Zapisz = new JMenuItem("Zapisz");
        Zapisz.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        Zapisz.addActionListener(this);
        Zamknij = new JMenuItem("Zamknij");
        Zamknij.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));

        Plik.add(Otwórz);
        Plik.add(Zapisz);
        Plik.addSeparator();
        Plik.add(Zamknij);

        Kopiuj = new JMenuItem("Kopiuj");
        Kopiuj.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        Wklej = new JMenuItem("Wklej");
        Wklej.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
        Ustawienia = new JMenuItem("Ustawienia");
        Ustawienia.setAccelerator(KeyStroke.getKeyStroke("ctrl alt S"));
        Edycja.add(Kopiuj);
        Edycja.add(Wklej);
        Edycja.addSeparator();
        Edycja.add(Ustawienia);
        Help = new JMenuItem("O Programie");
        Help.setAccelerator(KeyStroke.getKeyStroke("F1"));
        Pomoc.add(Help);
        Help.addActionListener(this);

        Zamknij.addActionListener(this);
        // ustawienie skrótu klawiszowego do opcji

    }

    public static void main(String[] args) {
        Windows computerConfigurator = new Windows();
        computerConfigurator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        computerConfigurator.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == Otwórz) {
            JFileChooser jFileChooser = new JFileChooser();
        }

        if (obj == Zamknij) {
            int option = JOptionPane.showConfirmDialog(this, "Czy napewno chcesz wyjść",
                    "Pytanie", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                dispose();
            } else if (option == JOptionPane.CANCEL_OPTION) {

            } else if (option == JOptionPane.CLOSED_OPTION) {

            }
        }
        if (obj == Help) {
            JOptionPane.showMessageDialog(this, " Program do konfigurowania zestawów " +
                    "komputerowych\n Wersja 1.0", "Wiadomość", JOptionPane.INFORMATION_MESSAGE);

        }

    }
}


