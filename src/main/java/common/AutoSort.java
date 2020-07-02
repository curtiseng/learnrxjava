package common;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author yangzifeng
 */
public class AutoSort {

    private static List<Entity> entities = new ArrayList<>(5);

    private static List<Integer> sorts = new ArrayList<>(5);

    private static final Integer INIT_VALUE = 100000;

    private static void init(Entity... entity) {
        entities.add(entity[0]);
        entities.add(entity[1]);
        entities.add(entity[2]);
        entities.add(entity[3]);
        entities.add(entity[4]);
    }

    private static void showList() {
        entities.sort((e1, e2) -> {
            if (e1.getSort() > e2.getSort()) {
                //return -1:即为正序排序
                return -1;
            } else if (e1.getSort().equals(e2.getSort())) {
                return 0;
            } else {
                //return 1: 即为倒序排序
                return 1;
            }
        });
        entities.forEach(System.out::println);
        System.out.println("---------------------------");
    }


    public static void main(String[] args) {
        Entity entity1 = new Entity("一", getSort());
        Entity entity2 = new Entity("二", getSort());
        Entity entity3 = new Entity("三", getSort());
        Entity entity4 = new Entity("四", getSort());
        Entity entity5 = new Entity("五", getSort());
        init(entity1, entity2, entity3, entity4, entity5);
        showList();
        move(entity5, null, entity1, entity2);
        showList();
        move(entity5, null, null, entity1);
        showList();
        move(entity5, null, entity4, null);
        showList();
    }

    /**
     * (pre_item.pos + after_item.pos）/ 2 = pos 移动到两个节点之间
     * after_item.pos + 1000 = pos 移动到最前面
     * pre_item.pos - 1000 = pos 移动到最后面
     * 100000 = pos 移动到初始节点
     * @param movedDept 被移动的部门
     * @param destinationParentDept 目标父部门，这里暂时不需要，只是为了更新父id
     * @param prevDept 移动后的前置部门
     * @param afterDept 移动后的后置部门
     */
    private static void move(Entity movedDept, @Nullable Entity destinationParentDept, @Nullable Entity prevDept, @Nullable Entity afterDept) {
        if (prevDept != null && afterDept != null) {
            // 精度丢失处理
            if ((prevDept.getSort() - afterDept.getSort()) < 2) {
                reset();
            }
            movedDept.setSort((prevDept.getSort() + afterDept.getSort()) / 2);
            sorts.add((prevDept.getSort() + afterDept.getSort()) / 2);
        }
        if (prevDept == null && afterDept != null) {
            // TODO 如果大于整型最大值，重排(基本不可能)
            movedDept.setSort(afterDept.getSort() + 1000);
            sorts.add(afterDept.getSort() + 1000);
        }
        if (prevDept != null && afterDept == null) {
            // TODO 如果小于1000，重排(初始值设置为1_000_000_000)
            movedDept.setSort(prevDept.getSort() - 1000);
            sorts.add(prevDept.getSort() - 1000);
        }
        if (prevDept == null && afterDept == null) {
            movedDept.setSort(INIT_VALUE);
            sorts.add(INIT_VALUE);
        }
        sorts.remove(movedDept.getSort());
    }

    private static void reset() {
        int i = entities.size();
        for (Entity entity : entities) {
            entity.setSort(INIT_VALUE + 1000 * i);
            i --;
        }
    }

    /**
     * 模拟某一级菜单下最小的sort值
     * @return miniSort
     */
    private static Integer miniSort() {
        if (sorts.size() > 0) {
            return Collections.min(sorts);
        }
        return null;
    }

    /**
     * 新建实体时的sort值,默认添加到最后
     * @return sort
     */
    private static Integer getSort() {
        Integer miniSort = miniSort();
        if (miniSort == null) {
            sorts.add(INIT_VALUE);
            return INIT_VALUE;
        } else {
            sorts.add(miniSort - 1000);
            return miniSort - 1000;
        }
    }

    static class Entity {
        private String name;

        private Integer sort;

        Entity(String name, Integer sort) {
            this.name = name;
            this.sort = sort;
        }

        Integer getSort() {
            return sort;
        }

        void setSort(Integer sort) {
            this.sort = sort;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "name='" + name + '\'' +
                    ", sort=" + sort +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Entity entity = (Entity) o;
            return Objects.equals(name, entity.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

}
