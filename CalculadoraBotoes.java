package br.com.ufpr.tads.calculadora.tela;

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author User
 */
public class CalculadoraBotoes implements ActionListener {

    private CalculadoraTela frame = null;

    enum EstadoCalculadora {
        INICIAL,
        IGNORADO,
        ENTRADA1,
        OPERADOR,
        ENTRADA2,
        CALCULANDO
    };

    private EstadoCalculadora estadoCalc = EstadoCalculadora.INICIAL;
    private int num1 = 0, num2 = 0;
    private char sinal;

    public CalculadoraBotoes(CalculadoraTela frame) {

        this.frame = frame;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if (cmd != null) {

            switch (estadoCalc) {

                case INICIAL:

                    if ("X".equals(cmd) || "/".equals(cmd) || "=".equals(cmd)) {
                        estadoCalc = EstadoCalculadora.IGNORADO;
                    } else if ("C".equals(cmd)) {
                        frame.setText("0");
                    } else {

                        estadoCalc = EstadoCalculadora.ENTRADA1;
                        frame.setText(cmd);

                    }

                    break;

                case ENTRADA1:

                    if ("X".equals(cmd) || "/".equals(cmd)
                            || "-".equals(cmd) || "+".equals(cmd)) {
                        estadoCalc = EstadoCalculadora.OPERADOR;
                        num1 = Integer.parseInt(frame.getText());
                        sinal = cmd.toCharArray()[0];
                        frame.setText(frame.getText() + cmd);
                    } else if ("C".equals(cmd)) {
                        frame.setText("0");

                    } else if ("=".equals(cmd)) {
                      

                    } else {

                        estadoCalc = EstadoCalculadora.ENTRADA1;
                        frame.setText(frame.getText() + cmd);

                    }

                    break;

                case OPERADOR:
                    if ("X".equals(cmd) || "/".equals(cmd)) {
                       JOptionPane.showMessageDialog(frame, "Entrada inválida!");
                    } else if ("C".equals(cmd)) {
                        frame.setText("0");
                        
                        
                    } else if ("=".equals(cmd)) {
                        JOptionPane.showMessageDialog(frame, "Não é possível calcular sua operação!");
                    }
                    
                    else {

                        estadoCalc = EstadoCalculadora.ENTRADA2;
                        frame.setText(frame.getText() + cmd);

                    }
                     break;
                     
                     
                      case ENTRADA2:
                    if ("X".equals(cmd) || "/".equals(cmd) || "+".equals(cmd) || "-".equals(cmd) || "+".equals(cmd)) {
                        String texto = frame.getText();
                        num2 = Integer.parseInt(texto.replace(String.valueOf(num1) +  String.valueOf(sinal), ""));
                        
                         Integer result = getResult();
                          num1 = result;
                         sinal = cmd.toCharArray()[0];
                       frame.setText(String.valueOf(result) + cmd);
                     
                        estadoCalc = EstadoCalculadora.OPERADOR;
                       
                    } else if ("C".equals(cmd)) {
                        frame.setText("0");
                        
                        
                    } else if ("=".equals(cmd)) {
                        String texto = frame.getText();
                        num2 = Integer.parseInt(texto.replace(String.valueOf(num1) +  String.valueOf(sinal), ""));
                        
                         Integer result = getResult();
                          
                       frame.setText(String.valueOf(result));
                     
                        estadoCalc = EstadoCalculadora.ENTRADA1;
                    }
                    
                    else {

                        estadoCalc = EstadoCalculadora.ENTRADA2;
                        frame.setText(frame.getText() + cmd);

                    }
                     break;
            }

        }

        if (estadoCalc == EstadoCalculadora.IGNORADO) {
            estadoCalc = EstadoCalculadora.INICIAL;
        }

    }
    
    
    public Integer getResult(){
    
         switch (sinal) {
             
             case '+':
                 
                 return num1 + num2;
              
              case '-':
                  return num1 - num2;
                  
                  
                   case '/':
                  return num1 / num2;
                  
                   case 'X':
                  return num1 * num2;
              
             default:
                 return -1;
         }
    
    }

}
