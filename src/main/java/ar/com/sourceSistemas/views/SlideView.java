package ar.com.sourceSistemas.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import ar.com.sourceSistemas.randomSlideshow.Inicio;
import javax.swing.*;

public class SlideView extends JFrame {




    JLabel pic;
    Timer tm;
    int x = 0;
    int time = 0;

    File[] list;
    int width = 0;
    int height = 0;
    int picwidth = 0;
    int picheight = 0;
    List<Integer> alreadyTaken = new LinkedList<Integer>();
    boolean random;
    public JButton next = new JButton("Next");
    public JButton stop = new JButton("stop");
    public JButton start = new JButton("start");
    private Inicio inicio;

    public SlideView(Inicio inicio) {
        super("Java SlideShow");
        this.inicio = inicio;
    }

    public int getTime(){
      return time;
    }

   public void setList(File[] list){
      this.list = list;
    }

    public void init() throws IOException {
        pic = new JLabel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = Integer.valueOf((int) screenSize.getWidth());
        height = Integer.valueOf((int) screenSize.getHeight());
        System.out.println("window height: " + height);
        System.out.println("window width: " + width);
        pic.setBounds(0, 0, width, height);
        setLayout(new FlowLayout());

        // Call The Function SetImageSize
        setImageSize(0);

	add(stop);
        add(start);
        add(next);
        add(pic);

        setSize(width , height );
        repaint();
        getContentPane().setBackground(Color.decode("#bdb67b"));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startSlideshow();
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                inicio.stop = !inicio.stop;
		System.out.println("stop changed to: "+inicio.stop);
            }
        });
    }

    public void startSlideshow(){
        this.inicio.slideshowTime();
    }

    // create a function to resize the image
    public void setImageSize(int index) throws IOException {
        
        BufferedImage buffImg = ImageIO.read(new File(list[index].toString()));
        Integer[] arr = reduceImage(buffImg);
        picwidth = arr[1];
        picheight = arr[0];
        Image newImg = buffImg.getScaledInstance(picwidth, picheight, Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
    }


    public void removeall(){
        pic.removeAll();
    }

    public Integer[] reduceImage(BufferedImage image) {
        Integer[] newsize = new Integer[2];
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Integer screenWidth = Integer.valueOf((int) screenSize.getWidth());
        Integer screenHeight = Integer.valueOf((int) screenSize.getHeight());

        newsize[0] = image.getHeight();
        newsize[1] = image.getWidth();

        while (newsize[0] > screenHeight) {
            newsize[0] = parseNumber((newsize[0] * 0.80));
            newsize[1] = parseNumber((newsize[1] * 0.80));
        }
        return newsize;

    }


    public int random(int max) {
        boolean noValid = true;
        int result = 0;
        while (noValid) {
            Random r = new Random();
            int low = 0;

            result = r.nextInt(max - low) + low;
            if (!alreadyTaken.contains(result)) {
                alreadyTaken.add(result);
                noValid = false;
            } else {

                if (alreadyTaken.size() == list.length)
                    alreadyTaken.clear();
            }

        }
        return result;

    }
    int cont = -1;
    int nonRandom(int max){
        Arrays.sort(list);
        if(cont < max-1){
            cont++;
            return cont;
        }else{
            cont = 0;
            return cont;
        }


    }

    private Integer parseNumber(double doubleNumber) {
        String number = String.valueOf(doubleNumber);
        int punto = number.indexOf(".");
        String subnumber = number.substring(0, punto);
        Integer integer = Integer.parseInt(subnumber);
        return integer;
    }

}
