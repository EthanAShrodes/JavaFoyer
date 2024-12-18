import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.*;  
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.plaf.ColorUIResource;
import java.lang.ProcessBuilder;


public class FoyerLightMode2 extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel statusLabel;
    private BufferedImage[] allImages;
    private String dir = "";
    ArrayList<String> images = new ArrayList<String>();
    boolean hi = true;
    String file = "";
    //Pi@/172.16.0.157
    //pi@172.16.0.44
    //change all the /pi/ back to /Pi/

    public FoyerLightMode2() {
        //JFrame properties
        setTitle("Foyer System");//name of app
        setSize(1050, 800);
        // setMaximumSize(1050,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // define the functions to add to the main frame
        
        JScrollPane scrollJPane = DisplayPic();
        JPanel buttPan = buttoPanel();

        JLabel statusLabel = new JLabel("Your Foyer:");
        statusLabel.setPreferredSize(new Dimension(1000, 100));
        statusLabel.setFont(new Font("Dialog", Font.PLAIN, 69));
        
        
        
        
        //for small edge space on the main frame 
        JLabel statusLabel6 = new JLabel("");
        statusLabel6.setPreferredSize(new Dimension(10, 10));
        JLabel statusLabel2 = new JLabel("");
        statusLabel2.setPreferredSize(new Dimension(10, 100));
        JLabel statusLabel3 = new JLabel("");
        statusLabel3.setPreferredSize(new Dimension(10, 10));
        JLabel statusLabel4 = new JLabel("");
        statusLabel4.setPreferredSize(new Dimension(10, 100));
        JLabel statusLabel5 = new JLabel("");
        statusLabel5.setPreferredSize(new Dimension(20, 100));
        
        //condense functions down to a single panel 
        JPanel topPane = new JPanel();
        topPane.setLayout(new BorderLayout());  
        
        topPane.add(statusLabel, BorderLayout.CENTER);
        topPane.add(buttPan, BorderLayout.EAST);
        topPane.add(statusLabel6, BorderLayout.SOUTH);
        topPane.add(statusLabel5, BorderLayout.WEST);
        

        
        //top part
        add(topPane, BorderLayout.NORTH);
        
        // bottum part 
        add(scrollJPane, BorderLayout.CENTER);
        add(statusLabel3, BorderLayout.SOUTH);
        add(statusLabel4, BorderLayout.WEST);

        
        scrollJPane.setBorder(BorderFactory.createEmptyBorder());
        topPane.setBorder(BorderFactory.createEmptyBorder());
        // pi@172.16.044//235, 240, 245

        
        
        this.toFront();
        this.isFocusOwner();

        // gui making going here 
        statusLabel.setForeground(new java.awt.Color(0, 0, 0,255));
        topPane.setBackground(new java.awt.Color(235, 240, 245,255));
        buttPan.setBackground(new java.awt.Color(235, 240, 245,255));
        getContentPane().setBackground(new java.awt.Color(235, 240, 245,255));
        // gui stoping going here
        
        
        setVisible(true);
        
        
    }
    
    public static void main(String[] args) {
        File res = new File("res");
        res.mkdir();
        

        new FoyerLightMode2();
        
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        UIManager UI=new UIManager();
        //Gui making here (general)
        
        

        UI.put("OptionPane.background",new ColorUIResource(235, 240, 245));// background of option pane 
        UI.put("Panel.background",new ColorUIResource(235, 240, 245));// background of option pane (should be the same as the above)
        UI.put("OptionPane.messageForeground", new ColorUIResource(0, 0, 0)); // Set text color (text above the buttons)
        UI.put("OptionPane.buttonForeground", new ColorUIResource(0, 0, 0)); // Set button text color
        
        // UI.put("FileChooser.background", new ColorUIResource(255, 0, 0)); // JFileChooser background
        // UI.put("FileChooser.foreground", new ColorUIResource(255, 0, 255)); // FileChooser text color
        // UI.put("List.foreground", Color.RED); // text color for FILE_CHOOSER
        // //testing gui her 

        // UIManager.put("ScrollPane.background", Color.RED);
        // UIManager.put("List.background", Color.RED);

        
       
    }

    //Frames below this 

    private JPanel buttoPanel(){
        JPanel place = new JPanel();
        
        JButton uploadButton = new JButton("Upload");
        uploadButton.setPreferredSize(new Dimension(200, 100));
     
        uploadButton.addActionListener(e ->{
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                UIManager.put("Panel.background",new ColorUIResource(Color.WHITE));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            JFileChooser fileChooser = new JFileChooser();             
            fileChooser.setMultiSelectionEnabled(true);
            int result = fileChooser.showOpenDialog(this);
            
            
            if (result == JFileChooser.APPROVE_OPTION) {
                File[] selectedFiles = fileChooser.getSelectedFiles();
                if (selectedFiles.length > 0) {
                    
                //new 
                    for (File selectedFile : selectedFiles) {
                    try {// Copy the file locally 
                       Path sourcePath = selectedFile.toPath();
                       Path destinationPath = Paths.get("res", selectedFile.getName());
                       Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                       
                     } catch (IOException j) {
                       j.printStackTrace();
                        }
                       
                }
                for(int i = 0;i<selectedFiles.length;i++){
                    dir = selectedFiles[i].getAbsolutePath();
                    String finalDir = dir.replace("\\", "/");

                    try {
                    ProcessBuilder processBuilder = new ProcessBuilder("scp", finalDir, "pi@172.16.0.44:Documents/Pictures"); //IP 
                    Process process = processBuilder.start();
                    
        
                    } catch (IOException j) {
                    j.printStackTrace();
                    }
                
                }
                    
                    cleanFrame();
                    try {
                        Thread.sleep(2000); // Sleep for 2 seconds (2000 milliseconds)
                    } catch (InterruptedException r) {
                        System.out.println("Thread interrupted!");
                    }loadingBar();refreshGUI();
                    
                    
                }}

        });
        uploadButton.setToolTipText("You can hold CTRL to select multiple files or Shift to select all files in between the two files");

        
        JButton reButton = new JButton("Refresh");
        reButton.setPreferredSize(new Dimension(200, 100));
        reButton.addActionListener(e -> confirmationPopUpRefresh());

        JButton rapButton = new JButton("Rapid Delete Mode");
        rapButton.setPreferredSize(new Dimension(200, 100));
        rapButton.addActionListener(e-> {
            hi = false;
            instantlyRefreshGUI();
            instantlyRefreshGUI();});
        
        JButton normalButton = new JButton("Normal Mode");
        normalButton.setPreferredSize(new Dimension(200, 100));
        normalButton.addActionListener(e-> {
            hi = true;
            instantlyRefreshGUI();
        });

        if (hi == true){
            place.add(rapButton, BorderLayout.CENTER);
        }else if (hi == false){
            place.add(normalButton, BorderLayout.CENTER);
        }

        place.add(reButton, BorderLayout.WEST);
        place.add(rapButton, BorderLayout.CENTER);
        place.add(uploadButton, BorderLayout.EAST);

        // gui making going here
        uploadButton.setForeground(new java.awt.Color(0, 0, 0,255));
        reButton.setForeground(new java.awt.Color(0, 0, 0,255));
        normalButton.setForeground(new java.awt.Color(0, 0, 0,255));
        rapButton.setForeground(new java.awt.Color(0, 0, 0,255));
        rapButton.setBackground(new java.awt.Color(235, 240, 245,255));
        reButton.setBackground(new java.awt.Color(235, 240, 245,255));
        uploadButton.setBackground(new java.awt.Color(235, 240, 245,255));
        hoverColorizeButton(reButton);
        hoverColorizeButton(uploadButton);
        hoverColorizeButton(normalButton);
        hoverColorizeButton(rapButton);

            
        //gui stoping going here
        return place;
    }


    private JScrollPane DisplayPic(){
 

        File path = new File("res");
    
    
        File[] allFiles = path.listFiles();
    
        allImages = new BufferedImage[allFiles.length];
        
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(0, 2, 10, 10)); 


        JScrollPane scrollPane = new JScrollPane(imagePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(30);
        scrollPane.getVerticalScrollBar().setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVisible(true);

        //gui making going here //gui for the scrollpane + scrollbar 
        scrollPane.getViewport().getView().setBackground(new java.awt.Color(235, 240, 245,255));

        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new java.awt.Color(184, 194, 204); // scrollbar 
                this.trackColor = new Color(235, 240, 245,255); // Track for scrollbar
                
            }
        });

        scrollPane.getHorizontalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new java.awt.Color(100, 150, 200); // scrollbar 
                this.trackColor = new java.awt.Color(235, 240, 245,255); // Track for scrollbar
                
            }
        });

        //gui making stoping here 
        

        for (int i = 0; i < allFiles.length; i++) {
            try {
                allImages[i] = ImageIO.read(allFiles[i]);
    
                JLabel imageLabel = new JLabel(new ImageIcon(allImages[i]));
                JButton delButton = new JButton(allFiles[i].getName());
                images.add(allFiles[i].getName());
                delButton.setLayout(new GridBagLayout());
                delButton.add(imageLabel, new GridBagConstraints());
                delButton.addActionListener(this);
                imagePanel.add(delButton);

                //gui making going on here 
                delButton.setBackground(new java.awt.Color(235, 240, 245,255));
                hoverColorizeButton(delButton);
                // gui stoping here 
    
            } catch (IOException e) {
                System.out.println("Error loading image: " + allFiles[i].getName());
            }
        }

        // ImageIcon image = new ImageIcon("Logo.jpg");
        // setIconImage(image.getImage());
        
        
        return scrollPane;

    }


    private void confirmationPopUpDeletion() {
        //make frame
        JFrame frame = new JFrame();
        frame.setTitle("Confirmation");//name of buton
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        
        //the popup that appears 
        int response = JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete this item?", "Confirm Deletion",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        
        //when button is click "yes" then is runs this 
        if (response ==JOptionPane.YES_OPTION) {
           
            try {
                Process process = Runtime.getRuntime()
                        .exec("ssh pi@172.16.0.44 rm /home/pi/Documents/Pictures/'" +file+"'"); //IP 
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                Files.delete(FileSystems.getDefault().getPath("res/", file));
            } catch (IOException e){
                e.printStackTrace();
            }instantlyRefreshGUI();instantlyRefreshGUI();

        } else {
        frame.dispose();
        instantlyRefreshGUI();
        instantlyRefreshGUI();
        
        }   
    }


    private void confirmationPopUpRefresh() {
        //make frame
        JFrame frame = new JFrame();
        frame.setTitle("Confirmation");//name of app
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        //the popup that appears 
        int response = JOptionPane.showConfirmDialog(frame,"Are you sure you want to refresh the foyer?\nWill take a long time for larger foyers", "Confirm Deletion",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        
        //when button is click "yes" then is runs this 
        if (response == JOptionPane.YES_OPTION) {
            File path = new File("res");
            deleteFilesInDirectory(path);
            try{
            Process process = Runtime.getRuntime()
                .exec("scp pi@172.16.0.44:Documents/Pictures/*.* "+path);//IP 
                try {
                    Thread.sleep(2000); // Sleep for 2 seconds (2000 milliseconds)
                } catch (InterruptedException r) {
                    System.out.println("Thread interrupted!");
                }loadingBar();refreshGUI();
            }
            catch(IOException e){
                e.printStackTrace();}
            } else {//when no is clikced
        frame.dispose();
        }   
        
        instantlyRefreshGUI();
        instantlyRefreshGUI();
        
    }


    private void optionsForImplement(String f) {
        // Create frame
        JFrame frame = new JFrame();
        frame.setBackground(new java.awt.Color(235, 240, 245,255));
        frame.setTitle("Confirmation"); // name of the app
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        

        // The popup that appears
        Object[] options = {"View", "Delete", "Cancel"};
        int response = JOptionPane.showOptionDialog(frame,
                "What would you like to do?", 
                "Confirm Action",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,// the array with the choices 
                options[2]); // what the deafult action is 
                
                if (response == 0) {
                    try {
                        Process process = Runtime.getRuntime()
                            
                                .exec("powershell.exe Start-Process 'res\\" + f + "'");//opens up the view button // this line make it only work if you have powershell 
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                }else if (response == 1){
                    confirmationPopUpDeletion();
                    instantlyRefreshGUI();
                    
                }else {
                    frame.dispose();
                    instantlyRefreshGUI();
                }
    }


    public static void loadingBar() {

        
        JFrame frame = new JFrame("ProgressBar");
        frame.setUndecorated(true);
        frame.setBackground(new java.awt.Color(235, 240, 245,255));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 50);
        frame.setLocationRelativeTo(null);

        
        JProgressBar progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        
        frame.add(progressBar, BorderLayout.CENTER);
        frame.setVisible(true);

        
        new Thread(() -> {
            int i = 0;
            try {
                while (i <= 100) {
                    Thread.sleep(100);
                    progressBar.setValue(i);
                    i += 10;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.dispose();
        }).start();

        // JDialog dialog
    }

    //verb action functions

    private JButton hoverColorizeButton(JButton button){//gui functiopn
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                //when mouse is over button
                button.setBackground(new java.awt.Color(173, 172, 172,255));
                UIManager UI=new UIManager();
                UI.put("Panel.background",new ColorUIResource(235, 240, 245));
                
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                // when mouse leaves button
                button.setBackground(new java.awt.Color(235, 240, 245,255));
                
            }

        });
        return button; 
    }

    private void cleanFrame(){
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void instantlyRefreshGUI() {

       getContentPane().removeAll();
      
       
       
       JScrollPane scrollJPanel = DisplayPic();
       JPanel buttPan = buttoPanel();


       
       if(hi==true){
       

       
        JLabel statusLabel = new JLabel(" Your Foyer:");     
        statusLabel.setPreferredSize(new Dimension(1000, 100));     
        statusLabel.setFont(new Font("Dialog", Font.PLAIN, 69));
        statusLabel.setForeground(new java.awt.Color(0, 0, 0,255));
        JLabel statusLabel7= new JLabel("");
        statusLabel7.setPreferredSize(new Dimension(10, 10));
        JLabel statusLabel6= new JLabel("");
        statusLabel6.setPreferredSize(new Dimension(10, 10));
        
        JPanel topPane = new JPanel();
        topPane.setLayout(new BorderLayout()); 
         
        topPane.add(statusLabel, BorderLayout.CENTER);       
        topPane.add(buttPan, BorderLayout.EAST);
        topPane.add(statusLabel6,BorderLayout.SOUTH);
        topPane.add(statusLabel7,BorderLayout.WEST);

        JLabel statusLabel4 = new JLabel("");
        statusLabel4.setPreferredSize(new Dimension(10, 10));
        JLabel statusLabel5 = new JLabel("");
        statusLabel5.setPreferredSize(new Dimension(10, 10));

        JPanel botPane = new JPanel();
        botPane.setLayout(new BorderLayout());
        botPane.add(scrollJPanel,BorderLayout.CENTER);
        botPane.add(statusLabel4,BorderLayout.SOUTH);
        botPane.add(statusLabel5,BorderLayout.WEST);
       
        add(topPane, BorderLayout.NORTH);
        add(botPane, BorderLayout.CENTER);

        topPane.setOpaque(false);
        topPane.setBackground(new java.awt.Color(235, 240, 245,255));
        botPane.setOpaque(false);
        botPane.setBackground(new java.awt.Color(235, 240, 245,255));
        scrollJPanel.setOpaque(false);
        scrollJPanel.setBackground(new java.awt.Color(235, 240, 245,255));
        buttPan.setOpaque(false);
        buttPan.setBackground(new java.awt.Color(235, 240, 245,255));

        scrollJPanel.setBorder(BorderFactory.createEmptyBorder());
        topPane.setBorder(BorderFactory.createEmptyBorder());
        botPane.setBorder(BorderFactory.createEmptyBorder());

        

        
        
        getContentPane().setBackground(new java.awt.Color(235, 240, 245,255));
       
        cleanFrame();
        revalidate();
        repaint();
    
       
    } else if (hi==false){
           
    
        JLabel statusLabel2 = new JLabel("  Rapid Delete Mode:"); 
        statusLabel2.setForeground(new java.awt.Color(0, 0, 0,255)); 
        statusLabel2.setPreferredSize(new Dimension(650, 75));
        statusLabel2.setFont(new Font("Dialog", Font.PLAIN, 59));
        
        JLabel statusLabel3 = new JLabel("      Clicking on buttons will Delete the image with no warning or undo");  
        statusLabel3.setPreferredSize(new Dimension(100, 20));
        statusLabel3.setFont(new Font("Dialog", Font.PLAIN, 20));
        statusLabel3.setForeground(new java.awt.Color(0, 0, 0,255));
           
    
        JPanel topPane = new JPanel();   
        topPane.setLayout(new BorderLayout()); 
        topPane.add(statusLabel2, BorderLayout.WEST);
        topPane.add(statusLabel3, BorderLayout.SOUTH);

        JLabel statusLabel4 = new JLabel("");
        statusLabel4.setPreferredSize(new Dimension(10, 10));
        JLabel statusLabel5 = new JLabel("");
        statusLabel5.setPreferredSize(new Dimension(10, 10));

        JPanel botPane = new JPanel();
        botPane.setLayout(new BorderLayout());
        botPane.add(scrollJPanel,BorderLayout.CENTER);
        botPane.add(statusLabel4,BorderLayout.SOUTH);
        botPane.add(statusLabel5,BorderLayout.WEST);

        JButton normalButton = new JButton("Normal Mode");
        normalButton.setForeground(new java.awt.Color(0, 0, 0,255));
        normalButton.setPreferredSize(new Dimension(200, 100));
        normalButton.addActionListener(e-> {
            hi = true;
            instantlyRefreshGUI();});

        JButton nukeFilesBTN = new JButton("Delete all files");
        nukeFilesBTN.setForeground(new java.awt.Color(0, 0, 0,255));
        nukeFilesBTN.setPreferredSize(new Dimension(200, 100));
        nukeFilesBTN.addActionListener(e-> {
            JFrame frame = new JFrame();
            frame.setTitle("Confirmation");//name of app
            frame.setSize(700, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setLocationRelativeTo(null);
            
            int response = JOptionPane.showConfirmDialog(frame,"Are you sure you want delete all files in the foyer?\nWill not be able to undo", "Confirm Deletion",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            
            if (response == JOptionPane.YES_OPTION) {
                File path = new File("res");
                deleteFilesInDirectory(path);
                try{
                Process process = Runtime.getRuntime()
                    .exec("ssh pi@172.16.0.44:Documents/Pictures/rm *");//IP 
                    File path2 = new File("res");
                    deleteFilesInDirectory(path2);
                    try {
                        Thread.sleep(2000); // Sleep for 2 seconds (2000 milliseconds)
                    } catch (InterruptedException r) {
                        System.out.println("Thread interrupted!");
                    }loadingBar();refreshGUI();
                }
                catch(IOException j){
                    j.printStackTrace();}
                } else {//when no is clikced
                    frame.dispose();
                } 
        });
        topPane.add(nukeFilesBTN, BorderLayout.CENTER);
        topPane.add(normalButton, BorderLayout.EAST);
           
        getContentPane().setBackground(new java.awt.Color(235, 240, 245,255));
        
        add(topPane, BorderLayout.NORTH);
        add(botPane, BorderLayout.CENTER);

        topPane.setOpaque(false);
        topPane.setBackground(new java.awt.Color(235, 240, 245,255));
        botPane.setOpaque(false);
        botPane.setBackground(new java.awt.Color(235, 240, 245,255));
        scrollJPanel.setOpaque(false);
        scrollJPanel.setBackground(new java.awt.Color(235, 240, 245,255));
        buttPan.setOpaque(false);
        buttPan.setBackground(new java.awt.Color(235, 240, 245,255));

        scrollJPanel.setBorder(BorderFactory.createEmptyBorder());
        topPane.setBorder(BorderFactory.createEmptyBorder());
        botPane.setBorder(BorderFactory.createEmptyBorder());

        normalButton.setBackground(new java.awt.Color(235, 240, 245,255));
        nukeFilesBTN.setBackground(new java.awt.Color(235, 240, 245,255));
        hoverColorizeButton(normalButton);
        hoverColorizeButton(nukeFilesBTN);
        
        getContentPane().setBackground(new java.awt.Color(235, 240, 245,255));
           
        cleanFrame();       
        revalidate();       
        repaint();}
       }

    private void refreshGUI() {
        
            
        loadingBar();
            
        getContentPane().removeAll();
      
       
       
        JScrollPane scrollJPanel = DisplayPic();
        JPanel buttPan = buttoPanel();
 
 
        
        if(hi==true){
        
 
        
         JLabel statusLabel = new JLabel(" Your Foyer:");     
         statusLabel.setPreferredSize(new Dimension(1000, 100));     
         statusLabel.setFont(new Font("Dialog", Font.PLAIN, 69));
         statusLabel.setForeground(new java.awt.Color(0, 0, 0,255));
         JLabel statusLabel7= new JLabel("");
         statusLabel7.setPreferredSize(new Dimension(10, 10));
         JLabel statusLabel6= new JLabel("");
         statusLabel6.setPreferredSize(new Dimension(10, 10));
         
         JPanel topPane = new JPanel();
         topPane.setLayout(new BorderLayout()); 
          
         topPane.add(statusLabel, BorderLayout.CENTER);       
         topPane.add(buttPan, BorderLayout.EAST);
         topPane.add(statusLabel6,BorderLayout.SOUTH);
         topPane.add(statusLabel7,BorderLayout.WEST);
 
         JLabel statusLabel4 = new JLabel("");
         statusLabel4.setPreferredSize(new Dimension(10, 10));
         JLabel statusLabel5 = new JLabel("");
         statusLabel5.setPreferredSize(new Dimension(10, 10));
 
         JPanel botPane = new JPanel();
         botPane.setLayout(new BorderLayout());
         botPane.add(scrollJPanel,BorderLayout.CENTER);
         botPane.add(statusLabel4,BorderLayout.SOUTH);
         botPane.add(statusLabel5,BorderLayout.WEST);
        
         add(topPane, BorderLayout.NORTH);
         add(botPane, BorderLayout.CENTER);
 
         topPane.setOpaque(false);
         topPane.setBackground(new java.awt.Color(235, 240, 245,255));
         botPane.setOpaque(false);
         botPane.setBackground(new java.awt.Color(235, 240, 245,255));
         scrollJPanel.setOpaque(false);
         scrollJPanel.setBackground(new java.awt.Color(235, 240, 245,255));
         buttPan.setOpaque(false);
         buttPan.setBackground(new java.awt.Color(235, 240, 245,255));
 
         scrollJPanel.setBorder(BorderFactory.createEmptyBorder());
         topPane.setBorder(BorderFactory.createEmptyBorder());
         botPane.setBorder(BorderFactory.createEmptyBorder());
 
         
 
         
         
         getContentPane().setBackground(new java.awt.Color(235, 240, 245,255));
        
         cleanFrame();
         revalidate();
         repaint();
         instantlyRefreshGUI();
     
        
     } else if (hi==false){
            
     
         JLabel statusLabel2 = new JLabel("  Rapid Delete Mode:"); 
         statusLabel2.setForeground(new java.awt.Color(0, 0, 0,255)); 
         statusLabel2.setPreferredSize(new Dimension(650, 75));
         statusLabel2.setFont(new Font("Dialog", Font.PLAIN, 59));
         
         JLabel statusLabel3 = new JLabel("      Clicking on buttons will Delete the image with no warning or undo");  
         statusLabel3.setPreferredSize(new Dimension(100, 20));
         statusLabel3.setFont(new Font("Dialog", Font.PLAIN, 20));
         statusLabel3.setForeground(new java.awt.Color(0, 0, 0,255));
            
     
         JPanel topPane = new JPanel();   
         topPane.setLayout(new BorderLayout()); 
         topPane.add(statusLabel2, BorderLayout.WEST);
         topPane.add(statusLabel3, BorderLayout.SOUTH);
 
         JLabel statusLabel4 = new JLabel("");
         statusLabel4.setPreferredSize(new Dimension(10, 10));
         JLabel statusLabel5 = new JLabel("");
         statusLabel5.setPreferredSize(new Dimension(10, 10));
 
         JPanel botPane = new JPanel();
         botPane.setLayout(new BorderLayout());
         botPane.add(scrollJPanel,BorderLayout.CENTER);
         botPane.add(statusLabel4,BorderLayout.SOUTH);
         botPane.add(statusLabel5,BorderLayout.WEST);
 
         JButton normalButton = new JButton("Normal Mode");
         normalButton.setForeground(new java.awt.Color(0, 0, 0,255));
         normalButton.setPreferredSize(new Dimension(200, 100));
         normalButton.addActionListener(e-> {
             hi = true;
             instantlyRefreshGUI();});
         topPane.add(normalButton, BorderLayout.EAST);
            
         getContentPane().setBackground(new java.awt.Color(235, 240, 245,255));
         
         add(topPane, BorderLayout.NORTH);
         add(botPane, BorderLayout.CENTER);
 
         topPane.setOpaque(false);
         topPane.setBackground(new java.awt.Color(235, 240, 245,255));
         botPane.setOpaque(false);
         botPane.setBackground(new java.awt.Color(235, 240, 245,255));
         scrollJPanel.setOpaque(false);
         scrollJPanel.setBackground(new java.awt.Color(235, 240, 245,255));
         buttPan.setOpaque(false);
         buttPan.setBackground(new java.awt.Color(235, 240, 245,255));
 
         scrollJPanel.setBorder(BorderFactory.createEmptyBorder());
         topPane.setBorder(BorderFactory.createEmptyBorder());
         botPane.setBorder(BorderFactory.createEmptyBorder());
 
         normalButton.setBackground(new java.awt.Color(235, 240, 245,255));
 
         
         
         getContentPane().setBackground(new java.awt.Color(235, 240, 245,255));
            
         cleanFrame();       
         revalidate();       
         repaint();}
         
    
    }
             
    public static void deleteFilesInDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFilesInDirectory(file);
                    } else {
                        boolean deleted = file.delete();
                        if (deleted) {
                        } else {
                        }
                    }
                }
            }
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent evt) {  // this is the "this" you see referenced  
        String command = evt.getActionCommand();
        

        if (command.equals("Delete")) {// when button is clicked runs through this if statement.      // if buttons name matches then it runs the function or commands
            try {
                Process process = Runtime.getRuntime()
                        .exec("scp " + dir + " pi@172.16.0.44:Documents/Files");//IP 
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(hi == true){
            for(int i = 0;i<images.size();i++){
                if(command.equals(images.get(i))){
                    file = images.get(i);
                    optionsForImplement(file);
                    break;
                }
            }
        }else if(hi == false){
            for(int i = 0;i<images.size();i++){
                if(command.equals(images.get(i))){
                    file = images.get(i);
                    
                    try {
                        Process process = Runtime.getRuntime()
                                .exec("ssh pi@172.16.0.44 rm /home/pi/Documents/Pictures/'" +file+"'");//IP 
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try{
                        Files.delete(FileSystems.getDefault().getPath("res/", file));
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    instantlyRefreshGUI();
                    break;
                }
            }
        }
    }
                
        
    
            
}