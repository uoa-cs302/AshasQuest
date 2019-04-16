

public class Camera{
    private float x,y;
    public Camera(float x, float y){
        this.x=x;
        this.y=y;
    }

    public void tick(GameObject object){
        x+= ((object.getX() - x)-1000/2) * 0.05f;
        y+= ((object.getY() - y)-563/2) * 0.05f;

        //Setting Camera boundaries so that the game world isn't exceeded past

        if (x <= 0) x=0;
        if (x >= 1640) x=1640;
        if (y <= 0)y=0;
        //563 + 48
        if (y >= 1000) y= 1000;

    }

    public float getX(){
        return x;

    }
    public float getY(){
        return y;

    }
    public void setX(float x){
        this.x = x;

    }
    public void setY(float y){
        this.y = y;

    }
}