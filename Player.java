package Mygame;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y, dx, dy;
    private final int SPEED = 5;
    private Game game;
    private int lastDx, lastDy; // Last movement direction
	private boolean visible;

    public Player(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
		visible = true;
    }

    public void move() {
        x += dx;
        y += dy;
        if (dx != 0 || dy != 0) {
            lastDx = dx;
            lastDy = dy;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -SPEED;
            dy = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = SPEED;
            dy = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -SPEED;
            dx = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = SPEED;
            dx = 0;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
        }

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
    }

    public void fire() {
        game.addBullet(new Bullet(x, y, lastDx * 2, lastDy * 2));
    }
	 public boolean isVisible() {
        return visible;
    }

    public Rectangle getBounds() {//衝突判定メソッド
        return new Rectangle(x, y, 20, 20);
    }

    public void setVisible(boolean visible) {//リストから削除
        this.visible = visible;
    }

    public void draw(Graphics g) {
		if(visible){
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, 20, 20);
		}
    }
}
