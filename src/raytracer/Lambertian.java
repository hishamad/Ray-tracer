package raytracer;

public class Lambertian extends Material {
    public Vec3D color;
    public Lambertian(Vec3D c) {
        color = c;
    }
    public boolean scatter(Ray ray, Record record, Vec3D attenuation, Ray scattered) {
        Vec3D scatter_dir = record.normal.add(new Vec3D().random_unit_vec());
        scattered.direction = scatter_dir;
        scattered.origin = record.p;
        attenuation.p[0] = color.p[0];
        attenuation.p[1] = color.p[1];
        attenuation.p[2] = color.p[2];
        return true;
    }
}
