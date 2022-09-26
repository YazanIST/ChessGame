package chessgame;

public class ModularArithmetic {
    static private final int MOD = (int) 1e9 + 7;

    static public int add(int a, int b) {
        return (a + b) % MOD;
    }

    static public int sub(int a, int b) {
        return (a - b + MOD) % MOD;
    }

    static public int mul(int a, int b) {
        return (int)((long) a * b % MOD);
    }

    static public int power(int x, int p) {
        int ret = 1;
        while (p > 0) {
            if (p % 2 == 1) {
                ret = mul(ret, x);
            }
            p /= 2;
            x = mul(x, x);
        }
        return ret;
    }

    static public int inverse(int x) {
        return power(x, MOD - 2);
    }

    static public int divide(int a, int b) {
        return mul(a, inverse(b));
    }
}
