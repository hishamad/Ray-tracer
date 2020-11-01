package raytracer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Picture {
    public static double infinity = Double.POSITIVE_INFINITY;

    public void draw() throws IOException {
        double ratio = 16.0 / 9.0;
        int width = 500;
        int height = (int) (width / ratio);
        int samples = 100;
        int d = 50;

        SurfaceList sl = new SurfaceList();
        Lambertian mg = new Lambertian(new Vec3D(0.5, 0.3, 0.4));
        Lambertian mc = new Lambertian(new Vec3D(0.6, 0.4, 0.4));
        Lambertian mt = new Lambertian(new Vec3D(0.6, 0.2, 0.2));
        Metal mr  = new Metal(new Vec3D(0.7, 0.5, 0.4));
        Metal ml   = new Metal(new Vec3D(0.5, 0.5, 0.5));
        Metal ms   = new Metal(new Vec3D(0.3, 0.3, 0.3));

        sl.push(new Sphere(new Vec3D(0,-100.5,-1), 100, mg));
        sl.push(new Sphere(new Vec3D(-1,-0.1,-1.3), 0.4, mr));
        sl.push(new Sphere(new Vec3D(0,-0.2,-1.2), 0.3, mc));
        sl.push(new Sphere(new Vec3D(0.3,-0.4,-.65), 0.1, mt));
        sl.push(new Sphere(new Vec3D(-0.3,-0.35,-.65), 0.15, ms));
        sl.push(new Sphere(new Vec3D(.95,0,-1.3), 0.5, ml));

        Camera camera = new Camera();

        File file = new File("raytracer.ppm");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        pw.println("P3\n" + width + ' ' + height + "\n255\n");
        for(int i = height-1; i >= 0; --i) {
            for(int j = 0; j < width; ++j) {
                Vec3D p_color = new Vec3D(0,0,0);
                for(int k = 0; k < samples; ++k){
                    double u = (double) j / (width-1);
                    double v = (double) i / (height-1);
                    Ray ray = new Ray(camera.origin, camera.corner.add(camera.horizontal.multiply(u)).add(camera.vertical.multiply(v)).subtract(camera.origin));
                    p_color = p_color.add(r_color(ray, sl, d));
                }

                double s = 1 / (double) samples;

                double r = p_color.get_x();
                double g = p_color.get_y();
                double b = p_color.get_z();

                r = Math.sqrt(s*r);
                g = Math.sqrt(s*g);
                b = Math.sqrt(s*b);

                pw.println((256 * fix(r,0.0,0.999)) + " " + (256 * fix(g,0.0,0.999)) + " " + (256 * fix(b,0.0,0.999)));
            }
        }
        pw.close();
    }

    public Vec3D r_color(Ray ray, Surface w, int d){
        Record record = new Record();

        if (d<=0){return new Vec3D(0,0,0);}

        if (w.hit(ray,0.001, infinity, record)) {
            Ray scatt = new Ray();
            Vec3D atten = new Vec3D();
            if (record.material.scatter(ray,record,atten,scatt)) {
                return atten.multiply(r_color(scatt, w, d-1));
            }
            return new Vec3D(0,0,0);
        }

        Vec3D u_direction = ray.get_direction().unit_vector();
        double q = 0.5 * (u_direction.get_y() + 1);
        return new Vec3D(1, 1, 1).multiply(1 - q).add(new Vec3D(0.5, 0.7, 1).multiply(q));
    }

    public double fix(double s, double min, double max) {
       if (s<min) return min;
       if (s>max) return max;
       return s;
    }


}
