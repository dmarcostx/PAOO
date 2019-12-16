package Ex3;

import java.util.HashSet;

public class LifetimeBuffer {
    private final HashSet<LifetimeItem> hashSet;

    public LifetimeBuffer() {
        this.hashSet = new HashSet<>();
    }

    public HashSet<LifetimeItem> getHashSet() {
        return new HashSet<>(hashSet);
    }

    private LifetimeBuffer incrementTime() {
        hashSet.forEach(LifetimeItem::incrementTime);
        return this;
    }

    public LifetimeBuffer insert(final int lifetime, final Object object) {
        incrementTime();
        hashSet.add(new LifetimeItem(lifetime, object));
        return this;
    }

    public HashSet<Object> remove() {
        final HashSet<Object> result = new HashSet<>();
        final HashSet<LifetimeItem> copyHashSet = new HashSet<>(hashSet);
        copyHashSet.forEach(lifetimeItem -> {
            if(lifetimeItem.isExpired()) {
                result.add(lifetimeItem.getItem());
                hashSet.remove(lifetimeItem);
            }
        });
        incrementTime();
        return result;
    }

    public static void main(String[] args) {
        LifetimeBuffer lifetimeBuffer = new LifetimeBuffer();
        lifetimeBuffer.insert(3,"a");
        lifetimeBuffer.insert(2,"b");
        lifetimeBuffer.insert(5,"c");
        System.out.println("1:");
        System.out.println(lifetimeBuffer.remove()); //nil
        System.out.println("2:");
        System.out.println(lifetimeBuffer.remove()); //a,b
        System.out.println("3:");
        System.out.println(lifetimeBuffer.remove());
        System.out.println("4:");
        System.out.println(lifetimeBuffer.remove());
        System.out.println("5:");
        System.out.println(lifetimeBuffer.remove());
        System.out.println("6:");
        System.out.println(lifetimeBuffer.remove());
        System.out.println("7:");
        System.out.println(lifetimeBuffer.remove());
    }
}
