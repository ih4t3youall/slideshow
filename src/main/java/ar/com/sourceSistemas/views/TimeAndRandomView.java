package ar.com.sourceSistemas.views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.com.sourceSistemas.randomSlideshow.Inicio;

public class TimeAndRandomView extends JFrame{

    private SlideView slideView;
    private Inicio inicio;

    public void start(){

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new FlowLayout());

        final JButton aceptar = new JButton("aceptar");
        final JCheckBox checkBox = new JCheckBox();
        final JTextField textField = new JTextField(10);
        textField.setText("4500");

        jpanel.add(textField);
        jpanel.add(checkBox);
        jpanel.add(aceptar);

        add(jpanel);
        setSize(200,200);
        setLocation(200,200);
        setVisible(true);

        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                slideView.time = Integer.valueOf(textField.getText());
                slideView.random = checkBox.isSelected();
                try {
                    slideView.init();
                    setVisible(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public TimeAndRandomView(SlideView slideView){
        this.slideView =  slideView;
        start();
    }
}
