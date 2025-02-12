import java.awt.*;

public class Projectile {
    private int x, y;
    private int speed = 5;  // ‹…‚Ì‘¬“x

    public Projectile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y -= speed;  // ‹…‚ğã•ûŒü‚ÉˆÚ“®‚³‚¹‚é
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10);  // ‹…‚ğ•`‰æ‚·‚é
    }

    public int getY() {
        return y;
    }
}
