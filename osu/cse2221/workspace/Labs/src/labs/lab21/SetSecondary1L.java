package labs.lab21;

import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> removed = this.newInstance();
        Set<T> kept = this.newInstance();

        while (this.size() != 0) {
            T t = this.removeAny();
            if (s.contains(t)) {
                removed.add(t);
            } else {
                kept.add(t);
            }
        }
        this.transferFrom(kept);

        return removed;
    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> notAdded = s.newInstance();

        while (s.size() != 0) {
            T t = s.removeAny();
            if (this.contains(t)) {
                notAdded.add(t);
            } else {
                this.add(t);
            }
        }
        s.transferFrom(notAdded);
    }

}
