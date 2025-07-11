import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI janela = new GUI();
            janela.janelaPrincipal();
            janela.setVisible(true);
        });
    }
}
