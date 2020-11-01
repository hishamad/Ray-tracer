package raytracer;

public class Vec3D {
    public double[] p = new double[3];
    Vec3D() {}
    Vec3D(double x, double y, double z) {
        p[0] = x;
        p[1] = y;
        p[2] = z;
    }

    public double get_x() {return p[0];}
    public double get_y() {return p[1];}
    public double get_z() {return p[2];}

    public double length_squared() {
        return Math.pow(p[0], 2) + Math.pow(p[1], 2) + Math.pow(p[2], 2);
    }

    public Vec3D add(Vec3D vec) {
        return new Vec3D(p[0] + vec.p[0], p[1] + vec.p[1], p[2] + vec.p[2]);
    }

    public Vec3D subtract(Vec3D vec) {
        return new Vec3D(p[0] - vec.p[0], p[1] - vec.p[1], p[2] - vec.p[2]);
    }

    public Vec3D multiply(double q) {
        return new Vec3D(p[0] * q, p[1] * q, p[2] * q);
    }

    public Vec3D multiply(Vec3D vec) {
        return new Vec3D(p[0] * vec.p[0], p[1] * vec.p[1], p[2] * vec.p[2]);
    }

    public double length() {
        return Math.sqrt(length_squared());
    }

    public double dot(Vec3D vec)
    {
        return (p[0]* vec.p[0] + p[1] * vec.p[1] + p[2] * vec.p[2]);
    }

    public Vec3D cross(Vec3D vec)
    {
        return new Vec3D(p[1] * vec.p[2] - p[2] * vec.p[1],
                         p[2] * vec.p[0] - p[0] * vec.p[2],
                         p[0] * vec.p[1] - p[1] * vec.p[0]);
    }
    public Vec3D normalize()
    {
        return new Vec3D(p[0] / this.length(), p[1] / this.length(), p[2] / this.length());
    }

    public Vec3D unit_vector() {
        return this.multiply(1/this.length());
    }

    public double random(double min, double max) {
        return min+(max-min)*Math.random();
    }

    public Vec3D random_vec() {
        return new Vec3D(Math.random(), Math.random(), Math.random());
    }

    public Vec3D random_vec(double min, double max) {
        return new Vec3D(random(max, min), random(max, min), random(max, min));
    }

    public Vec3D random_unit_vec() {
        double a = random(0, 2*Math.PI);
        double b = random(-1,1);
        double c = Math.sqrt(1 - Math.pow(b,2));

        return new Vec3D(c*Math.cos(a), c*Math.sin(a), b);
    }

    public Vec3D random_vec_in_sphere() {
        while(true) {
            Vec3D v = random_vec(-1,1);
            if (v.length_squared() >= 1) continue;
            return v;
        }
    }

    public Vec3D random_vec_in_hemisphere(Vec3D n) {
        Vec3D vec =  new Vec3D().random_vec_in_sphere();
        if(vec.dot(n) > 0) {
            return vec;
        } else {
            return (new Vec3D(0,0,0).subtract(vec));
        }
    }

    public Vec3D reflect(Vec3D n) {
        return this.subtract(n.multiply(this.dot(n) * 2));
    }
}
