package raytracer;

public abstract class Surface {
    public abstract boolean hit(Ray ray, double min, double max, Record record);
}
