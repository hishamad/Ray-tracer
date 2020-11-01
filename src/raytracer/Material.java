package raytracer;

public abstract class Material {
    public abstract boolean scatter(Ray ray, Record record, Vec3D attenuation, Ray scattered);
}
