package common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 先按父亲id进行分组，并建hash表，再循环menu索引
 *
 * 1. {@link Collectors} groupingBy方法返回的是HashMap,存取时间复杂度为O(1)
 * 2. menuDTOS 使用LinkedList,插入时间复杂度为O(1)
 * 3. groupingBy操作和forEach操作都可以使用多线程来继续提高速度
 *
 * @author yangzifeng
 */
public class MenuTree {

    public static void main(String[] args) {
        List<Menu> menus = newMenus();
        List<MenuDTO> menuDTOS = new LinkedList<>();
        Map<String,List<Menu>> menuMap =  menus.stream().collect(Collectors.groupingBy(Menu::getPid,Collectors.mapping((Menu menu)->menu,Collectors.toList())));
        System.out.println(menuMap);
        menus.forEach(menu -> {
            if (menuMap.get(menu.getId()) != null) {
                MenuDTO menuDTO = new MenuDTO(menu.getId(), menu.getName(), menuMap.get(menu.getId()));
                menuDTOS.add(menuDTO);
            }
        });
        System.out.println(menuDTOS);
    }

    private static List<Menu> newMenus() {
        List<Menu> menus = new ArrayList<>(5);
        menus.add(new Menu("1", "1", "0"));
        menus.add(new Menu("2", "2", "0"));
        menus.add(new Menu("3", "1-1", "1"));
        menus.add(new Menu("4", "1-2", "1"));
        menus.add(new Menu("5", "2-1", "2"));
        return menus;
    }
}

class MenuDTO {

    private String id;

    private String name;

    private List<Menu> kids;

    MenuDTO(String id, String name, List<Menu> kids) {
        this.id = id;
        this.name = name;
        this.kids = kids;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", kids=" + kids +
                '}';
    }
}

class Menu {

    private String id;

    private String name;

    private String pid;

    Menu(String id, String name, String pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getPid() {
        return pid;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                '}';
    }
}
