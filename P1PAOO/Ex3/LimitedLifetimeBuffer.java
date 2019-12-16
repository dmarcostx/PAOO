package Ex3;

/*
 * LimitedLifetimeBuffer não pode ser uma herança pois modifica o comportamento do método insert
 * da classe pai, que passa a ignorar novos elementos que extrapolem o limite.
 */

import java.util.HashSet;

public class LimitedLifetimeBuffer {
    private final int limit;
    private final OrnateLifetimeBuffer ornateLifetimeBuffer;

    public LimitedLifetimeBuffer(final int limit) {
        this.limit = limit;
        ornateLifetimeBuffer = new OrnateLifetimeBuffer();
    }

    public LimitedLifetimeBuffer insert(final int lifetime, final Object object) {
        if(ornateLifetimeBuffer.getHashSet().size() < limit)
            ornateLifetimeBuffer.insert(lifetime,object);
        return this;
    }

    public HashSet<Object> remove() {
        return ornateLifetimeBuffer.remove();
    }

    public HashSet<Object> getGreatest() {
        return ornateLifetimeBuffer.getGreatest();
    }

    public static void main(String[] args) {
        LimitedLifetimeBuffer limitedLifetimeBuffer = new LimitedLifetimeBuffer(1);
        limitedLifetimeBuffer.insert(3,"a");
        limitedLifetimeBuffer.insert(2,"b");
        limitedLifetimeBuffer.insert(5,"c");
        System.out.println("Maior:");
        System.out.println(limitedLifetimeBuffer.getGreatest());
        System.out.println("1:");
        System.out.println(limitedLifetimeBuffer.remove()); //nil
        System.out.println("2:");
        System.out.println(limitedLifetimeBuffer.remove()); //a,b
        System.out.println("3:");
        System.out.println(limitedLifetimeBuffer.remove());
        System.out.println("4:");
        System.out.println(limitedLifetimeBuffer.remove());
        System.out.println("Maior:");
        System.out.println(limitedLifetimeBuffer.getGreatest());
        System.out.println("5:");
        System.out.println(limitedLifetimeBuffer.remove());
        System.out.println("6:");
        System.out.println(limitedLifetimeBuffer.remove());
        System.out.println("7:");
        System.out.println(limitedLifetimeBuffer.remove());
    }
}
