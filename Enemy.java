package Mygame;
import java.awt.*;

public abstract class Enemy {
    protected int x, y;
    protected boolean visible = true;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void move();

    public boolean isVisible() {
        return visible;
    }
	public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
	public void draw(Graphics g) {
        if (visible) {
            g.setColor(Color.RED);
            g.fillRect(x, y, 20, 20);
        }
    }
    
}
class EnemyType1 extends Enemy {
    public EnemyType1(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
        x += 5;
		
    }

	 
	
	
}

class EnemyType2 extends Enemy {
    public EnemyType2(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
        y += 5;
    }
	
	
	

}

class EnemyType3 extends Enemy{
	private Player player;
	
	public EnemyType3(int x, int y,Player player){
		super(x,y);
		this.player = player;
	}
	
	@Override
	public void move(){
		if (x < player.getX()){
			x += 1;
		}else if(x > player.getX()){
			x -= 1;
		}
		
		if (y < player.getY()){
			y += 1;
		}else if(y > player.getY()){
			y -= 1;
		}
	}

	

	
}
