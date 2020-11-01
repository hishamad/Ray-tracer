package raytracer;


public class Ray {
    public Vec3D origin, direction;
    public Ray(){}
    public Ray(Vec3D or, Vec3D dir) {
        origin = or;
        direction = dir;
    }

    public Vec3D get_origin() {
        return origin;
    }
    public Vec3D get_direction() {
        return direction;
    }
    public Vec3D point_at(double x) {
        return origin.add(direction.multiply(x));
    }
}
