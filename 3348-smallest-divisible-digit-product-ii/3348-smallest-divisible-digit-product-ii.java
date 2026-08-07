class Solution {
    int[] PRIMES = {2, 3, 5, 7};

    public String smallestNumber(String num, long t) {
        long temp = t;
        for (int p : PRIMES) while (temp % p == 0) temp /= p;
        if (temp > 1) return "-1";

        long[] needs = computeNeeds(t);
        int n = num.length();

        {
            long[] rem = needs.clone();
            boolean valid = true;
            for (char c : num.toCharArray()) {
                int d = c - '0';
                if (d == 0) { valid = false; break; }
                reduceBy(rem, d);
            }
            if (valid && isCovered(rem)) return num;
        }

        long[][] prefRems = new long[n + 1][4];
        prefRems[0] = needs.clone();
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            if (d == 0) { firstZero = i; break; }
            prefRems[i + 1] = prefRems[i].clone();
            reduceBy(prefRems[i + 1], d);
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i > firstZero) continue;
            long[] prefRem = prefRems[i];
            int startD = (num.charAt(i) - '0') + 1;

            for (int d = startD; d <= 9; d++) {
                long[] rem = prefRem.clone();
                reduceBy(rem, d);
                if (canCover(rem, n - 1 - i)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append(d);
                    fillGreedy(sb, rem, n - 1 - i);
                    return sb.toString();
                }
            }
        }

        int targetLen = Math.max(n + 1, minDigits(needs));
        StringBuilder sb = new StringBuilder();
        fillGreedy(sb, needs.clone(), targetLen);
        return sb.toString();
    }

    long[] computeNeeds(long t) {
        long[] needs = new long[4];
        for (int i = 0; i < 4; i++)
            while (t % PRIMES[i] == 0) { needs[i]++; t /= PRIMES[i]; }
        return needs;
    }

    void reduceBy(long[] rem, int d) {
        for (int i = 0; i < 4; i++)
            while (rem[i] > 0 && d % PRIMES[i] == 0) { rem[i]--; d /= PRIMES[i]; }
    }

    boolean isCovered(long[] rem) {
        for (long r : rem) if (r > 0) return false;
        return true;
    }

    int minDigits(long[] rem) {
        long a = rem[0], b = rem[1], c = rem[2], e = rem[3];
        int count = (int)(c + e);
        count += a / 3; a %= 3;
        count += b / 2; b %= 2;
        if (a == 2) { count++; a = 0; }
        if (a == 1 && b == 1) { count++; a = 0; b = 0; }
        else if (a == 1) { count++; }
        if (b == 1) count++;
        return count;
    }

    boolean canCover(long[] rem, int L) {
        return L >= minDigits(rem);
    }

    void fillGreedy(StringBuilder sb, long[] rem, int L) {
        for (int pos = 0; pos < L; pos++) {
            for (int d = 1; d <= 9; d++) {
                long[] newRem = rem.clone();
                reduceBy(newRem, d);
                if (canCover(newRem, L - pos - 1)) {
                    sb.append(d);
                    rem = newRem;
                    break;
                }
            }
        }
    }
}