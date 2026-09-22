class Solution {
    static class Node {
        int prod;
        int[] pref;
        int[] suff;
        int[] sub;

        Node(int k) {
            pref = new int[k];
            suff = new int[k];
            sub = new int[k];
        }
    }

    int n, k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            ans[i] = res.pref[x];
        }

        return ans;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            tree[node] = createLeaf(nums[l]);
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node createLeaf(int value) {
        Node res = new Node(k);

        int rem = value % k;

        res.prod = rem;
        res.pref[rem] = 1;
        res.suff[rem] = 1;
        res.sub[rem] = 1;

        return res;
    }

    private Node merge(Node a, Node b) {
        Node res = new Node(k);

        res.prod = (a.prod * b.prod) % k;

        for (int r = 0; r < k; r++) {
            res.pref[r] += a.pref[r];
        }

        for (int r = 0; r < k; r++) {
            int newRem = (a.prod * r) % k;
            res.pref[newRem] += b.pref[r];
        }

        for (int r = 0; r < k; r++) {
            res.suff[r] += b.suff[r];
        }

        for (int r = 0; r < k; r++) {
            int newRem = (r * b.prod) % k;
            res.suff[newRem] += a.suff[r];
        }
        for (int r = 0; r < k; r++) {
            res.sub[r] += a.sub[r];
        }

        for (int r = 0; r < k; r++) {
            res.sub[r] += b.sub[r];
        }

        for (int x = 0; x < k; x++) {
            for (int y = 0; y < k; y++) {
                int rem = (x * y) % k;

                res.sub[rem] += a.suff[x] * b.pref[y];
            }
        }

        return res;
    }

    private void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            tree[node] = createLeaf(value);
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}