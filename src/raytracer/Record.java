package raytracer;

public class Record {
    public Vec3D p;
    public Vec3D normal;
    public double q;
    public boolean f_f;
    public Material material;

    public void face_normal(Ray ray, Vec3D o_normal){
        f_f = (ray.get_direction().dot(o_normal) < 0);
        if (f_f) {
            normal = o_normal;
        } else {
            normal = new Vec3D(0,0,0).subtract(o_normal);
        }
    }
}
