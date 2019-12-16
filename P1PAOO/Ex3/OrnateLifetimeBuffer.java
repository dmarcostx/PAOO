package Ex3;

/*
 * OrnateLifetimeBuffer não pode ser uma herança pois acrescenta um novo método que
 * contradiz o comportamento do método remove da classe pai.
 * Isso acontece pois elementos expirados podem ser retornados prematuramente pelo novo método.
 */

import java.util.HashSet;

public class OrnateLifetimeBuffer {
    private final LifetimeBuffer lifetimeBuffer;

    public OrnateLifetimeBuffer() {
        lifetimeBuffer = new LifetimeBuffer();
    }

    public HashSet<LifetimeItem> getHashSet() {
        return lifetimeBuffer.getHashSet();
    }

    public OrnateLifetimeBuffer insert(final int lifetime, final Object object) {
        lifetimeBuffer.insert(lifetime,object);
        return this;
    }

    public HashSet<Object> remove() {
        return lifetimeBuffer.remove();
    }

    public HashSet<Object> getGreatest() {
        int max = 0;
        HashSet<Object> result = new HashSet<>();
        for (LifetimeItem lifetimeItem : lifetimeBuffer.getHashSet()) {
            if (lifetimeItem.getPermanency() > max) {
                max = lifetimeItem.getPermanency();
                result = new HashSet<>();
                result.add(lifetimeItem.getItem());
            }
            else if(lifetimeItem.getPermanency() == max)
                result.add(lifetimeItem.getItem());
        }
        return result;
    }

    public static void main(String[] args) {
        OrnateLifetimeBuffer ornateLifetimeBuffer = new OrnateLifetimeBuffer();
        ornateLifetimeBuffer.insert(3,"a");
        ornateLifetimeBuffer.insert(2,"b");
        ornateLifetimeBuffer.insert(5,"c");
        System.out.println("Maior:");
        System.out.println(ornateLifetimeBuffer.getGreatest());
        System.out.println("1:");
        System.out.println(ornateLifetimeBuffer.remove()); //nil
        System.out.println("2:");
        System.out.println(ornateLifetimeBuffer.remove()); //a,b
        System.out.println("3:");
        System.out.println(ornateLifetimeBuffer.remove());
        System.out.println("4:");
        System.out.println(ornateLifetimeBuffer.remove());
        System.out.println("Maior:");
        System.out.println(ornateLifetimeBuffer.getGreatest());
        System.out.println("5:");
        System.out.println(ornateLifetimeBuffer.remove());
        System.out.println("6:");
        System.out.println(ornateLifetimeBuffer.remove());
        System.out.println("7:");
        System.out.println(ornateLifetimeBuffer.remove());
    }
}