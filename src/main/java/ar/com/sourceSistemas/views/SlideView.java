package ar.com.sourceSistemas.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

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

    public SlideView() {
        super("Java SlideShow");
        JFileChooser jfc;
        if (new File(System.getProperty("user.home") + "/Dropbox/Public").exists()) {
            jfc = new JFileChooser(System.getProperty("user.home") + "/Dropbox/Public");
        } else {
            jfc = new JFileChooser();
        }
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        jfc.showOpenDialog(null);
        String timer = JOptionPane.showInputDialog(null, "cuanto tiempo duran ? ","4500");
        time = Integer.valueOf(timer);
        File file = jfc.getSelectedFile();
        list = file.listFiles();
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        pic = new JLabel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = Integer.valueOf((int) screenSize.getWidth());
        height = Integer.valueOf((int) screenSize.getHeight());
        System.out.println("window height: " + height);
        System.out.println("window width: " + width);
        pic.setBounds(40, 30, width, height);

        // Call The Function SetImageSize
        SetImageSize();

        // set a timer
        tm = new Timer(time, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    SetImageSize();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                x += 1;
                if (x >= list.length)
                    x = 0;
            }
        });
        add(pic);
        tm.start();
        setLayout(null);
        setSize(width , height );
        repaint();
        getContentPane().setBackground(Color.decode("#bdb67b"));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    // create a function to resize the image
    public void SetImageSize() throws IOException {
        int randomNumber = random(list.length);
        BufferedImage buffImg = ImageIO.read(new File(list[randomNumber].toString()));
        Integer[] arr = reduceImage(buffImg);
        picwidth = arr[1];
        picheight = arr[0];
        Image newImg = buffImg.getScaledInstance(picwidth, picheight, Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
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

    private Integer parseNumber(double doubleNumber) {
        String number = String.valueOf(doubleNumber);
        int punto = number.indexOf(".");
        String subnumber = number.substring(0, punto);
        Integer integer = Integer.parseInt(subnumber);
        return integer;
    }

}
