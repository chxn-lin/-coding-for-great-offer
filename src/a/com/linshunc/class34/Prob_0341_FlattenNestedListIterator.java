package a.com.linshunc.class34;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Prob_0341_FlattenNestedListIterator {

    public static void main(String[] args) {
        // [1, [2, 3], [4, [5, 6], 7]]
        MyNestedInteger ne1 = new MyNestedInteger(1);
        MyNestedInteger ne2 = new MyNestedInteger(2);
        MyNestedInteger ne3 = new MyNestedInteger(3);
        MyNestedInteger ne4 = new MyNestedInteger(4);
        MyNestedInteger ne5 = new MyNestedInteger(5);
        MyNestedInteger ne6 = new MyNestedInteger(6);
        MyNestedInteger ne7 = new MyNestedInteger(7);

        List<NestedInteger> list1 = new ArrayList<>();
        List<NestedInteger> list2 = new ArrayList<>();
        List<NestedInteger> list3 = new ArrayList<>();
        List<NestedInteger> list4 = new ArrayList<>();
        list4.add(ne5);
        list4.add(ne6);
        list2.add(ne2);
        list2.add(ne3);

        MyNestedInteger sub22 = new MyNestedInteger(null);
        sub22.nest = list4;
        MyNestedInteger sub3 = new MyNestedInteger(null);
        sub3.nest = list3;
        list3.add(ne4);
        list3.add(sub22);
        list3.add(ne7);

        MyNestedInteger sub2 = new MyNestedInteger(null);
        sub2.nest = list2;
        list1.add(ne1);
        list1.add(sub2);
        list1.add(sub3);

        NestedIterator nestedIterator = new NestedIterator(list1);
        System.out.println();
        while (nestedIterator.hasNext) {
            System.out.print(nestedIterator.next() + " -> ");
        }
        System.out.println();
    }

    public static class MyNestedInteger implements NestedInteger{
        Integer value;
        List<NestedInteger> nest;
        public MyNestedInteger(Integer value) {
            this.value = value;
        }
        @Override
        public boolean isInteger() {
            return value != null;
        }
        @Override
        public Integer getInteger() {
            return value;
        }
        @Override
        public List<NestedInteger> getList() {
            return nest;
        }
    }

    // 不要提交这个接口类
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a
        // nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a
        // single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested
        // list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public static class NestedIterator implements Iterator<Integer> {
        List<NestedInteger> nestedList;
        Stack<Integer> stack;
        boolean hasNext;
        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedList = nestedList;
            this.stack = new Stack<>();
            if (this.nestedList != null && this.nestedList.size() > 0) {
                hasNext = true;
                // 往下扎
                stack.add(0);
                in(stack, nestedList.get(0));
            }
            else {
                hasNext = false;
            }
        }
        private void in(Stack<Integer> inStack, NestedInteger cur){
            if (cur.isInteger()) {
                return;
            }
            else {
                List<NestedInteger> list = cur.getList();
                in(inStack, list.get(0));
                inStack.add(0);
            }
        }
        @Override
        public boolean hasNext() {
            return hasNext;
        }
        @Override
        public Integer next() {
            Object[] res = getAndReturn(stack, nestedList);
            if ((boolean)res[1]) {
                hasNext = false;
            }
            return (Integer) res[0];
        }

        // Object[0] 表示值，Object[1]表示是否需要到下一步
        private Object[] getAndReturn(Stack<Integer> inStack, List<NestedInteger> nestedList) {
            int index = inStack.pop();
            NestedInteger sub = nestedList.get(index);
            if (sub.isInteger()) {
                index++;
                boolean needSkip = false;
                if (nestedList.size() == index) {
                    needSkip = true;
                    inStack.add(0);
                }
                else {
                    if (nestedList.get(index).isInteger()) {
                    }
                    else {
                        in(inStack, nestedList.get(index));
                    }
                    inStack.add(index);
                }
                return new Object[]{sub.getInteger(), needSkip};
            }
            else {
                Object[] res = getAndReturn(inStack, sub.getList());
                boolean needSkip = false;
                if ((boolean)res[1]) {
                    index++;
                    if (index == nestedList.size()) {
                        needSkip = true;
                    }
                }
                inStack.add(index);
                return new Object[]{res[0], needSkip};
            }
        }
    }

}
