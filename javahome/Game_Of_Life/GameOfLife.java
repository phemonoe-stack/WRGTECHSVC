import java.awt.*;
import java.awt.event.*;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import javax.swing.*; 
import java.text.NumberFormat;


public class GameOfLife extends JFrame implements ActionListener{
    
    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu, submenu;
    private JMenuItem startItem, nextItem, quitItem, aboutItem, state1, state2;
    private JTextArea out;
    private JLabel gen;
    private JTextField gen_out;
    private JPanel control, display, tracker;
    private JButton start, next;
    private Graphics page;
    private CellX life;
    private int generation = 0, init_state = 0;
    
    public GameOfLife(){
        //set up frame
        super("Game of Life");
        getContentPane().setLayout(new BorderLayout(5,5));
        // setup initial location on screen
        Dimension bounds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension abounds = getSize();
        setLocation((bounds.width - abounds.width) / 3,
            (bounds.height - abounds.height) / 4);
        // initialize structures, ie... buttons, panels, text areas...        
        control = new JPanel();
        control.setLayout(new GridLayout(1,2));
        getContentPane().add(control, BorderLayout.NORTH);
        start = new JButton("Start");
        start.setMnemonic(KeyEvent.VK_S);
        start.setToolTipText("Click this button to establish a colony of cells");
        start.addActionListener(this);
        next = new JButton("Next");
        next.setMnemonic(KeyEvent.VK_N);
        next.setToolTipText("Click this button to view the next generation.");
        next.addActionListener(this);
        control.add(start);
        control.add(next);
        control.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        
        display = new JPanel();
        display.setLayout(new GridLayout(1,1));
        getContentPane().add(display, BorderLayout.CENTER);
        out = new JTextArea(10, 10);
        out.setLineWrap(false);
        out.setEditable(false);
        out.setFont(new Font("courier new", Font.BOLD, 14));
        display.add(out);
        display.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        
        tracker = new JPanel();
        tracker.setLayout(new GridLayout(1,2)); 
        getContentPane().add(tracker, BorderLayout.SOUTH); 
        gen = new JLabel("Generation:");
        gen_out = new JTextField(Integer.toString(generation), 10);
        gen_out.setEditable(false);
        tracker.add(gen);
        tracker.add(gen_out);
        tracker.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        
    }
      
    //Creates an icon-worthy Image from scratch.
    protected static Image createGoLImage() {
        //Create a 16x16 pixel image.
        BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        
        //Draw into it.
        Graphics g = bi.getGraphics();
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 15, 15);
        g.setColor(Color.magenta);
        g.fillOval(0, 0, 15, 15);
        g.setColor(Color.blue);
        g.fillOval(2, 2, 13, 13);
        g.setColor(Color.green);
        g.fillOval(4, 4, 11, 11);
        g.setColor(Color.yellow);
        g.fillOval(6, 6, 9, 9);
        g.setColor(Color.orange);
        g.fillOval(8, 8, 7, 7);
        g.setColor(Color.red);
        g.fillOval(10, 10, 5, 5);
        
        //Clean up.
        g.dispose();
        
        //Return it.
        return bi;
    }
    
    protected void createMenus(){
        //Create the menu bar.
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //Build menu.
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(helpMenu);
        // build menu items
        startItem = new JMenuItem("Start", KeyEvent.VK_S);
        startItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_S, ActionEvent.ALT_MASK));
        fileMenu.add(startItem);
        startItem.addActionListener(this);
        
        nextItem = new JMenuItem("Next", KeyEvent.VK_N);
        nextItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_N, ActionEvent.ALT_MASK));
        fileMenu.add(nextItem);
        nextItem.addActionListener(this);
               
        aboutItem = new JMenuItem("About...", KeyEvent.VK_A);
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_A, ActionEvent.ALT_MASK));
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(this);
        
        submenu = new JMenu("Cell Pattern");
        submenu.setMnemonic(KeyEvent.VK_C);        

        state1 = new JMenuItem("10 Cell Row");
        state1.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_1, ActionEvent.ALT_MASK));
        submenu.add(state1);

        state1.addActionListener(this);        
        state2 = new JMenuItem("Small Exploder");
        state2.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(state2);
        state2.addActionListener(this);
        fileMenu.add(submenu);

        fileMenu.addSeparator();
        
        quitItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        quitItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        fileMenu.add(quitItem);
        quitItem.addActionListener(this);
         
    }
    
    public void actionPerformed(ActionEvent e){
        // method executes actions when buttons are clicked

        if(e.getSource() == start)
            start();
        else if(e.getSource() == next)
            next();
        else if(e.getSource() == startItem)
            start();
        else if(e.getSource() == nextItem)
            next(); 
        else if(e.getSource() == aboutItem)
            doAbout();
        else if(e.getSource() == quitItem)
            dispose(); 
        else if(e.getSource() == state1){
            init_state = 1; 
            start();
        }
        else if(e.getSource() == state2){
            init_state = 2;
            start();
        }
        
    } // method actionPerformed()
    
    protected void start(){ // method to initialize cells
        generation = 0;
        if (init_state == 0)
            init_state = 1;
        life = new CellX(init_state);
        out.setText(life.getCells());
        gen_out.setText(Integer.toString(generation));
    }
     
    protected void next(){  // method to create next cell generation
        generation++;   
        life.nextGen();  
        out.setText(life.getCells());
        gen_out.setText(Integer.toString(generation));
    }
    
    protected void doAbout() {  // displays about box
        JOptionPane.showMessageDialog(this, "Written by: Gannon\\Kuskowski", 
            "About...", JOptionPane.PLAIN_MESSAGE);
    }
       
    public static void main(String[] args){  // runs application
         
        GameOfLife window = new GameOfLife();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane();
        window.setDefaultLookAndFeelDecorated(true);
        window.setIconImage(createGoLImage());
        window.createMenus();
        window.start();
        window.pack();
        window.show();
        
    }
    
    
}
