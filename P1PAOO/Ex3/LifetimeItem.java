package Ex3;

import java.util.Optional;

class LifetimeItem {
    private final int lifetime;
    private final Object item;
    private int permanency;

    LifetimeItem(final int lifetime, final Object item) {
        this.lifetime = lifetime;
        this.item = item;
        this.permanency = 0;
    }

    int getPermanency() {
        return permanency;
    }

    LifetimeItem incrementTime() {
        permanency += 1;
        return this;
    }

    Boolean isExpired() {
        return permanency >= lifetime;
    }

    Object getItem() {
        return item;
    }
}
