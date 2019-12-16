package Ex1;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Factors {
    private final HashMap<BigInteger, HashSet<BigInteger>> hashMap;

    public Factors() {
        this.hashMap = new HashMap<>();
    }

    public HashSet<BigInteger> factorization (BigInteger n) {
        final HashSet<BigInteger> hashSet = new HashSet<>(primeFactors(n));
        hashMap.put(n,hashSet);
        return hashSet;
    }

    private Collection<BigInteger> primeFactors(BigInteger n) {
        Collection<BigInteger> factors = new HashSet<>( );
        BigInteger d = new BigInteger.TWO;
        while (!n.equals(BigInteger.ONE) ) {
            if (n.mod(d) == BigInteger.ZERO) {
                factors.add(d);
                n = n.divide(d);
            } else {
                d = d.add(BigInteger.ONE);
            }
        }
        return Collections.unmodifiableCollection(factors);
    }
}
