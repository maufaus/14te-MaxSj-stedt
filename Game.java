import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Game extends JPanel{

	Ball ball = new Ball(this);
	Racket racket = new Racket(this);
	Font scoreFont = new Font("sansSerif", Font.PLAIN, 30);
	private int timeLeft;
	
	public Game(){
			addKeyListener(new KeyListener() {

				
				public void keyPressed(KeyEvent e) {
					racket.keyPressed(e);
					
				}

				
				public void keyReleased(KeyEvent e) {
					racket.keyReleased(e);
					
				}

				
				public void keyTyped(KeyEvent e) {
					
					
				}
	
			
			
			
			});
			setFocusable(true);
	}
	
	private void move(){
		ball.moveBall();
		racket.move();
	}
	
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        ball.paint(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(new Color(0x008080));
        racket.paint(g2d);
        g2d.setFont(scoreFont);
        g2d.drawString("Score: " +ball.getScore(), 15, 30);
        g2d.drawString("Spelet startar om: "+timeLeft, 230, 30);
    }

    public void gameOver(){
    	JOptionPane.showMessageDialog(this, "Spelet är över för dig gubben", "GGWP" ,JOptionPane.YES_NO_OPTION);
    	System.exit(ABORT);
    }

    public void countDown(){
    	try {
    		for(timeLeft = 3; timeLeft > 0; timeLeft--){
    			repaint();
    			TimeUnit.SECONDS.sleep(1);
    		}
    	}catch(InterruptedException e){}
    }
    
    public static void main(String args[]) {
        JFrame frame = new JFrame();
        Game game = new Game();

        frame.setSize(500, 500); //x,y
        frame.setLocation(300, 300); //x,y
        frame.setDefaultCloseOperation(3); //stänger programmet
        frame.add(game);
        frame.setTitle("Ez bois");
        frame.setResizable(false);
        frame.setVisible(true);
        
        game.countDown();

        while (true) {
            game.move();
            game.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
