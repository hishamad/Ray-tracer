package raytracer;

public class Camera {
    Vec3D origin;
    Vec3D vertical;
    Vec3D horizontal;
    Vec3D corner;

    public Camera() {
        double ratio = 16.0 / 9.0;
        double viewport_h = 2;
        double viewport_w = ratio * viewport_h;
        double focal_length = 1;

        origin = new Vec3D(0, 0, 0);
        vertical = new Vec3D(0, viewport_h, 0);
        horizontal = new Vec3D(viewport_w, 0, 0);
        corner = origin.subtract(horizontal.multiply(0.5)).subtract(vertical.multiply(0.5)).subtract(new Vec3D(0, 0, focal_length));
    }



}
