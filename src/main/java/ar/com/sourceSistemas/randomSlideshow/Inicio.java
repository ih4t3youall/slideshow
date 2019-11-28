package ar.com.sourceSistemas.randomSlideshow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import ar.com.sourceSistemas.views.SlideView;
import ar.com.sourceSistemas.views.TimeAndRandomView;

public class 	Inicio {
    private SlideView slideView ;
    private int counter =0;
    Timer tm;
    int x = 0;
    private File[] list;
    public Inicio() {
        slideView = new SlideView(this);

        JFileChooser jfc;
    if (new File(System.getProperty("user.home") + "/Dropbox/Public").exists()) {
      jfc = new JFileChooser(System.getProperty("user.home") + "/Dropbox/Public");
    } else {
      jfc = new JFileChooser();
    }
    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    jfc.showOpenDialog(null);
    File file = jfc.getSelectedFile();
    list = file.listFiles();
    slideView.setList(list);
    new TimeAndRandomView(slideView);

    addButtonEvent();

  }

  public void slideshowTime(){
        System.out.println("slideshow");
    int duration = slideView.getTime();
    long endTime = duration + System.currentTimeMillis();
    boolean timeToChange = false;
    System.out.println("time: "+slideView.getTime());
      tm = new Timer(slideView.getTime(), new ActionListener() {

          public void actionPerformed(ActionEvent e) {
              try {
                  slideView.setImageSize(x);
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
              x += 1;
              if (x >= list.length)
                  x = 0;
          }
      });
      tm.start();




  }


    public void addButtonEvent(){
        slideView.next.addActionListener(new ActionListener()
        {
	  public void actionPerformed(ActionEvent e)
	  {
	    counter++;
	    try{
	      slideView.setImageSize(counter);
	    }catch(IOException ex){
	      ex.printStackTrace();
	    }

	  }
	});

  }
}
