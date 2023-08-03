package rip.diamond.practice.titles.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import rip.diamond.practice.kits.Kit;
import rip.diamond.practice.titles.config.Config;
import rip.diamond.practice.util.ItemBuilder;
import rip.diamond.practice.util.menu.Button;
import rip.diamond.practice.util.menu.pagination.PaginatedMenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TitlesMenu extends PaginatedMenu {
    @Override
    public String getPrePaginatedTitle(Player player) {
        return Config.MENU_TITLE.toString();
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        for (Kit kit : Kit.getKits()) {
            String kitName = kit.getName();
            if (Config.DISABLED_KITS.toStringList().contains(kitName)) {
                continue;
            }


            String permission = "eden.titles." + kit.getName();
            Material material = Material.getMaterial(Config.MENU_ICON_MATERIAL.toString());
            String name = Config.MENU_ICON_NAME.toString().replace("{kit-display-name}", kit.getDisplayName());
            List<String> lore = Config.MENU_ICON_LORE.toStringList().stream().map(str -> str
                    //.replace("{title}", ) TODO
                    .replace("{kit-display-name}", kitName)
            ).collect(Collectors.toList());
            List<String> noPermissionLore = Config.MENU_ICON_LORE_NO_PERMISSION.toStringList().stream().map(str -> str
                    //.replace("{title}", ) TODO
                    .replace("{kit-display-name}", kitName)
            ).collect(Collectors.toList());

            buttons.put(buttons.size(), new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    return new ItemBuilder(material)
                            .name(name)
                            .lore(player.hasPermission(permission) ? lore : noPermissionLore)
                            .build();
                }

                @Override
                public void clicked(Player player, ClickType clickType) {
                    // TODO: 28/7/2023  
                }
            });
        }

        return buttons;
    }
}
