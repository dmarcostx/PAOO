import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

class FP<T> {
    public final T fst;
    public final T snd;
    private final UnaryOperator<T> f;

    private FP(T fst, UnaryOperator<T> f) {
        this.fst=fst;
        this.snd=f.apply(fst);
        this.f=f;
    }

    public static <T> FP<T> of(T fst, UnaryOperator<T> f) {
        return new FP<T>(fst,f);
    }

    public FP<T> next() {
        return new FP<T>(snd,f);
    }

    private boolean isFixedPoint() {
        return fst == snd;
    }

    public static <T> T fixPoint(T x, UnaryOperator <T> f) {
        return Stream.iterate(FP.of(x,f), FP::next).filter(FP::isFixedPoint).findFirst().get().fst;
    }

    static <T> int find(T key, List<T> ls) {
        return fixPoint(0, i -> (i == ls.size() || ls.get(i) == key) ? i : i+1);
    }

    static int sum(List<Integer> xs) {
        return fixPoint(new AccumulatorAndIndex<>(0), a -> (a.index == xs.size()) ? a : new AccumulatorAndIndex<>(a.accumulator+xs.get(a.index),a.index+1)).accumulator;
    }

    static <T, U> U foldLeft(U acc, BiFunction<U, T, U> op, List<T> xs) {
        return fixPoint(new AccumulatorAndIndex<>(acc), a -> (a.index == xs.size()) ? a : new AccumulatorAndIndex<>(op.apply(a.accumulator,xs.get(a.index)),a.index+1)).accumulator;
    }

    @Override
    public String toString () {
        return "FP{"+"fst="+fst+", snd="+snd+'}';
    }

    public static void main(String[] args) {
        var a = new ArrayList<Integer>(5);
        a.add(0); a.add(1); a.add(2); a.add(3); a.add(4);
        System.out.println(find(4,a));
        System.out.println(sum(a));
        System.out.println(foldLeft(0, Integer::sum,a));
    }
}