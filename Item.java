package Mygame;
import java.awt.*;

public class Item {
    private int x, y;
	private boolean visible;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
		visible = true;
    }

    public void draw(Graphics g) {
		if(visible){
        	g.setColor(Color.GREEN);
        	g.fillRect(x, y, 20, 20);
		}
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
}
