package Mygame;
import java.awt.*;

public class Bullet {
    private int x, y;
    private int dx, dy;
    private boolean visible;

    public Bullet(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        visible = true;
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0 || x > 800 || y < 0 || y > 600) {
            visible = false;
        }
    }
	 public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        if (visible) {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, 20, 20);
        }
    }
}
