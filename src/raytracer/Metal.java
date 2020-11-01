package raytracer;

public class Metal extends Material {
    Vec3D color;
    public Metal(Vec3D c) {
        color = c;
    }
    public boolean scatter(Ray ray, Record record, Vec3D attenuation, Ray scattered) {
        Vec3D ref = ray.get_direction().unit_vector().reflect(record.normal);
        scattered.direction = ref;
        scattered.origin = record.p;
        attenuation.p[0] = color.p[0];
        attenuation.p[1] = color.p[1];
        attenuation.p[2] = color.p[2];
        return (scattered.get_direction().dot(record.normal) > 0);
    }
}
