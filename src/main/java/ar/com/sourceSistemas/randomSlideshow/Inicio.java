package ar.com.sourceSistemas.randomSlideshow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import ar.com.sourceSistemas.views.SlideView;
import ar.com.sourceSistemas.views.TimeAndRandomView;
import javax.swing.*;

public class 	Inicio {


  public boolean stop = false;
    private SlideView slideView ;
    private int counter =0;
    Timer tm;
    int x = 1;
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
    Arrays.sort(list);
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
		System.out.println("stop is: "+stop);
		if(!stop)
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
	    x++;

	    if (x == list.length)
	      x=0;
	    try{
	      if(!stop)
		slideView.setImageSize(x);
	    }catch(IOException ex){
	      ex.printStackTrace();
	    }

	  }
	});

	slideView.previous.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
		x--;

		if (x < 0)
		  x=0;
		try{
		  if(!stop)
		    slideView.setImageSize(x);
		}catch(IOException ex){
		  ex.printStackTrace();
		}

	      }
	    });
    }
}
