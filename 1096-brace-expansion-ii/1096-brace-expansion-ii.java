class Solution {
    int i;

    public List<String> braceExpansionII(String expression) {
        i = 0;
        Set<String> set = parse(expression);
        List<String> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }

    Set<String> parse(String s) {
        List<Set<String>> groups = new ArrayList<>();
        groups.add(new HashSet<>());
        groups.get(0).add("");

        while (i < s.length() && s.charAt(i) != '}') {
            if (s.charAt(i) == '{') {
                i++;
                Set<String> inner = parse(s);
                i++; // skip '}'
                groups.set(groups.size()-1, product(groups.get(groups.size()-1), inner));
            } else if (s.charAt(i) == ',') {
                i++;
                groups.add(new HashSet<>());
                groups.get(groups.size()-1).add("");
            } else {
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(s.charAt(i)));
                i++;
                groups.set(groups.size()-1, product(groups.get(groups.size()-1), letter));
            }
        }

        Set<String> res = new HashSet<>();
        for (Set<String> g : groups) res.addAll(g);
        return res;
    }

    Set<String> product(Set<String> a, Set<String> b) {
        Set<String> res = new HashSet<>();
        for (String x : a) for (String y : b) res.add(x + y);
        return res;
    }
}