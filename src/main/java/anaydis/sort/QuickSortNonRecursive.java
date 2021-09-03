package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class QuickSortNonRecursive extends AbstractSorter{

    public QuickSortNonRecursive() {
        super(SorterType.QUICK_NON_RECURSIVE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        int l = 0;
        int r = list.size() - 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(l);
        stack.push(r);
        while (!stack.empty()) {
            r = stack.pop();
            l = stack.pop();
            if (r <= l) {
                continue;
            }
            int i = partition(comparator, list, l, r);
            if (i - l > r - i) {
                stack.push(l);
                stack.push(i - 1);
            }
            stack.push(i + 1);
            stack.push(r);
            if (r - i >= i - l) {
                stack.push(l);
                stack.push(i - 1);
            }
        }
    }
}
