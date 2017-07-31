import java.util.Vector;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	
	private final Point[] points;
	private int numberOfSegements=0;
	
	public BruteCollinearPoints(Point[] points) {
		if (points==null){
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < points.length; i++) {
			if (points[i]==null){
				throw new IllegalArgumentException();
			}
		}
		this.points=points;
	}

	public int numberOfSegments() {
		return numberOfSegements;
	}

	public LineSegment[] segments() {
		Vector<LineSegment> lineSegments = new Vector<>();
		int length=points.length;
		for (int i = 0; i < length; i++) {
			for (int j = i+1; j < length; j++) {
				for (int k = j+1; k < length; k++) {
					if(points[i].slopeTo(points[j]) != points[j].slopeTo(points[k])) {
						continue;
					}
					for (int l = k+1; l < length; l++) {
						if (points[k].slopeTo(points[l])==points[i].slopeTo(points[k])){
							lineSegments.addElement(new LineSegment(points[i], points[l]));
							numberOfSegements++;
						}
					}
				}
			}
		}
		LineSegment[] array = new LineSegment[lineSegments.size()];
		lineSegments.toArray(array);
		return array;
	}
		
	public static void main(String[] args) {
		In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}

