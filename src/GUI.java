import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JTextField campoValor;
    private JTextField campoMoedaOrigem;
    private JTextField campoMoedaDestino;

    public void janelaPrincipal() {
        setTitle("Conversor de Moedas com cotação em tempo real");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout simples usando FlowLayout
        JPanel painel = new JPanel();

        //Criando os componentes
        JLabel labelValor = new JLabel("Valor a ser convertido");
        campoValor = new JTextField(10);

        JLabel labelMoedaOrigem = new JLabel("Digite a moeda de origem (ex: USD): ");
        campoMoedaOrigem = new JTextField(5);

        JLabel labelMoedaDestino = new JLabel("Digite a moeda de destiono (ex: BRL): ");
        campoMoedaDestino = new JTextField(5);

        JButton botaoConverter = new JButton("Converter");
        JLabel resultado = new JLabel("");

        //Adicionar os componetes no painel
        painel.add(labelValor);
        painel.add(campoValor);

        painel.add(labelMoedaOrigem);
        painel.add(campoMoedaOrigem);

        painel.add(labelMoedaDestino);
        painel.add(campoMoedaDestino);

        painel.add(botaoConverter);
        painel.add(resultado);

        Font fonte = new Font("SansSerif", Font.PLAIN, 14);
        labelValor.setFont(fonte);
        labelMoedaOrigem.setFont(fonte);
        labelMoedaDestino.setFont(fonte);
        campoValor.setFont(fonte);
        campoMoedaOrigem.setFont(fonte);
        campoMoedaDestino.setFont(fonte);
        resultado.setFont(new Font("SansSerif", Font.BOLD, 16));
        botaoConverter.setFont(new Font("SansSerif", Font.BOLD, 14));

        add(painel);


        // Ação do botão
       botaoConverter.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   Double valor = Double.parseDouble(campoValor.getText());
                   String origem = campoMoedaOrigem.getText().trim().toUpperCase();
                   String destino = campoMoedaDestino.getText().trim().toUpperCase();

                   double convertido = ConversorService.converterMoeda(origem, destino, valor);
                   resultado.setText("Convertido:" + convertido + " " + destino);

               }catch (NumberFormatException ex){
                   resultado.setText("Valor Invalido");
               }catch (Exception ex){
                   resultado.setText("Erro: " + ex.getMessage());
               }
           }
       });
    }
}
