class AccumulatorAndIndex<T> {
    final T accumulator;
    final int index;

    AccumulatorAndIndex(T accumulator) {
        this.accumulator = accumulator;
        this.index = 0;
    }

    AccumulatorAndIndex(T accumulator, int index) {
        this.accumulator = accumulator;
        this.index = index;
    }
}
