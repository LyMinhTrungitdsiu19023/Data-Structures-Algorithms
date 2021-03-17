package Geom;
import java.awt.*;

public abstract class GeomObject {

    protected Color edgeColor;
    protected Color faceColor;
    protected int line_weight = 1;

    public GeomObject(){
        edgeColor = new Color(20, 200, 20);
        faceColor = Color.RED;
    }

    public abstract void draw(Graphics g, SpaceMapping mapper);
    }
