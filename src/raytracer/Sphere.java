package raytracer;

public class Sphere extends Surface {
    public Vec3D center;
    public double radius;
    public Material material;

    public Sphere(Vec3D c, double r, Material m) {
        center = c;
        radius = r;
        material = m;
    }

    public boolean hit(Ray ray, double min, double max, Record record){
        Vec3D o = ray.get_origin().subtract(center);
        double a = ray.get_direction().length_squared();
        double hb  = o.dot(ray.get_direction());
        double c = o.length_squared() - Math.pow(radius, 2);
        double dis = Math.pow(hb ,2) - (a*c);
        if (dis > 0) {
            double root = Math.sqrt(dis);
            double temp = (- hb - root) / a;

            if (temp < max && temp > min) {
                record.q = temp;
                record.p = ray.point_at(record.q);
                Vec3D o_normal = (record.p.subtract(center)).multiply(1/radius);
                record.face_normal(ray, o_normal);
                record.material = this.material;
                return true;
            }

            temp = ( - hb + root) / a;

            if(temp < max&& temp > min) {
                record.q = temp;
                record.p = ray.point_at(record.q);
                Vec3D o_normal = (record.p.subtract(center)).multiply(1/radius);
                record.face_normal(ray, o_normal);
                record.material = this.material;
                return true;
            }
        }
        return false;
    }
}
