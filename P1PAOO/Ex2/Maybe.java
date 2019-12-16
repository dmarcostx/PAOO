package Ex2;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Maybe<T> {
    private static Maybe empty = nothing( );
    public abstract boolean isEmpty( );
    public boolean isPresent( ) { return !isEmpty( ); }
    public abstract T get( );
    public abstract T orElseGet(Supplier<T> d);
    public abstract <U> Maybe<U> map(Function<T, U> f);
    public abstract <U> Maybe<U> flatMap(Function<T, Maybe<U>> f);
    public abstract <U> Maybe<U> app(Maybe<Function<T, U>> mfn);
    public static <E> Maybe<E> empty( ) { return (Maybe<E>) empty; }
    private static <E> Maybe<E> nothing( ) {
        return new Maybe<E>( ) {
            public boolean isEmpty( ) { return true; }
            public E get( ) { throw new IllegalCallerException( ); }
            public E orElseGet(Supplier<E> d) { return d.get( ); }
            public <U> Maybe<U> map(Function<E, U> f) { return empty( ); }
            public <U> Maybe<U> flatMap(Function<E, Maybe<U>> f) { return empty( ); }
            public <U> Maybe<U> app(Maybe<Function<E, U>> mfn) { return Maybe.empty(); }
        };
    }
    public static <E> Maybe<E> some(E value) {
        if (value == null) throw new IllegalCallerException( );
        return new Maybe<E>( ) {
            public boolean isEmpty( ) { return false; }
            public E get( ) { return value; }
            public E orElseGet(Supplier<E> d) { return value; }
            public <U> Maybe<U> map(Function<E, U> f) { return some(f.apply(value) ); }
            public <U> Maybe<U> flatMap(Function<E, Maybe<U>> f) { return f.apply(get( ) ); }
            public <U> Maybe<U> app(Maybe<Function<E, U>> mfn) {
                if(mfn.isPresent())
                    return Maybe.some(mfn.get().apply(value));
                return Maybe.empty();
            }
        };
    }
}
