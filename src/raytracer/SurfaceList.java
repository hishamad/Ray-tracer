package raytracer;

import java.util.Vector;

public class SurfaceList extends Surface {
    public Vector<Surface> vec;

    public SurfaceList() {
        vec = new Vector<Surface>();
    }
    public SurfaceList(Surface s) {
        push(s);
    }

    public void push(Surface s) {
        vec.add(s);
    }

    public boolean hit(Ray ray, double min, double max, Record record){
        Record temp_rec = new Record();
        boolean hit = false;
        double close = max;

        for (Surface surface : vec) {
            if (surface.hit(ray, min, close, temp_rec)) {
                hit = true;
                close = temp_rec.q;
                record.q = temp_rec.q;
                record.p = temp_rec.p;
                record.normal = temp_rec.normal;
                record.material = temp_rec.material;
            }
        }
        return hit;
    }

    }
