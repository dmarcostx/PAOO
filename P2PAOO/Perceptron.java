import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Perceptron {
    private final List<List<Double>> xss;
    private final List<Double> ys;
    
    private Perceptron(List<List<Double>> xss, List<Double> ys) {
        this.xss = xss;
        this.ys = ys;
    }
    
    static List<Double> scalar(double v, List<Double> xs) {
        return xs.stream().map(x -> v*x).collect(Collectors.toUnmodifiableList());
    }

    static double inner(List<Double> xs, List<Double> ys) {
        return IntStream.range(0, xs.size()).mapToDouble(x -> xs.get(x) * ys.get(x)).sum();
    }

    private Optional<Integer> wrong(List<Double> ws) {
        return IntStream.range(0,xss.size()).boxed().filter(i -> ys.get(i)*inner(ws,xss.get(i)) <= 0).findFirst();
    }
    
    static List<Double> sum(List<Double> xs, List<Double> ys) {
        return IntStream.range(0,xs.size()).mapToDouble(i -> xs.get(i) + ys.get(i)).boxed().collect(Collectors.toUnmodifiableList());
    }

    private List<Double> step(List<Double> ws) {
        return wrong(ws).map(i -> sum(ws, scalar(ys.get(i), xss.get(i)))).orElse(ws);
    }
    
    private List<Double> getSeparator() {
        return FP.fixPoint(xss.get(0).stream().map(x -> 0.0).collect(Collectors.toUnmodifiableList()), this::step);
    }
    
    public static Function<List<Double>, Double> predictor(List<List<Double>> xss, List<Double> ys) {
        return p -> inner(p, new Perceptron(xss,ys).getSeparator());
    }

    public static void main(String[] args) {
        System.out.println(inner(Arrays.asList(1.0,2.0,3.0,4.0,5.0),Arrays.asList(6.0,7.0,8.0,9.0,10.0)));
        var p = predictor(Arrays.asList(
        Arrays.asList(1.0,1.0), Arrays.asList(1.5,0.5), Arrays.asList(4.0,2.0), Arrays.asList(5.0,14.0), Arrays.asList(7.0,19.0),
        Arrays.asList(8.0,4.0), Arrays.asList(11.0,13.0), Arrays.asList(12.0,2.0), Arrays.asList(15.0,6.0), Arrays.asList(18.0,11.0),
        Arrays.asList(-1.0,-36.0), Arrays.asList(-3.0,-31.0), Arrays.asList(-6.0,-39.0), Arrays.asList(-8.0,-31.0), Arrays.asList(-10.0,-37.0),
        Arrays.asList(-11.0,-32.0),Arrays.asList(-13.0,-22.0),Arrays.asList(-15.0,-29.0),Arrays.asList(-16.0,-38.0),Arrays.asList(-17.0,-19.0)), Arrays.asList(
        1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
        -1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0));
        System.out.println(p.apply(Arrays.asList(1.0,1.0)));
        System.out.println(p.apply(Arrays.asList(18.0,40.0)));
        System.out.println(p.apply(Arrays.asList(8.0,21.0)));
        System.out.println(p.apply(Arrays.asList(15.0,20.0)));
        System.out.println(p.apply(Arrays.asList(0.1,0.1)));
        System.out.println(p.apply(Arrays.asList(-1.0,-1.0)));
        System.out.println(p.apply(Arrays.asList(-18.0,-40.0)));
        System.out.println(p.apply(Arrays.asList(-8.0,-21.0)));
        System.out.println(p.apply(Arrays.asList(-15.0,-20.0)));
        System.out.println(p.apply(Arrays.asList(-0.1,-0.1)));
    }
}
