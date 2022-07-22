package me.abetgt.addstar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.bukkit.Bukkit.getLogger;

public final class AddStar extends JavaPlugin {
    private static AddStar configInstance;
    PluginDescriptionFile pdf = this.getDescription();
    @Override
    public void onEnable() {
        // Plugin startup logic
        // Loading config files
        getLogger().info("Loading Config Files");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        FileConfiguration configInstance = getConfig();

        Bukkit.getServer().getPluginManager().registerEvents(new ActivateItemThing(), this);

        // REROLL ITEM RECIPE
        // Reroll Item
        getLogger().info("Loading Item");
        ItemStack item1 = new ItemStack(Material.matchMaterial(getConfig().getString("ITEM.Material")));
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName("§f" + getConfig().getString("ITEM.ItemName"));
        List<String> lore1 = new ArrayList<>();
        lore1.add(getConfig().getString("ITEM.ItemDescription"));
        meta1.setLore(lore1);
        if (getConfig().getBoolean("ITEM.AppearEnchanted") == true){
            getLogger().info("[RECIPE] Got boolean for AppearEnchanted: true.");
            getLogger().info("[RECIPE] Applying enchant to item...");
            meta1.addEnchant(Enchantment.LUCK, 1, false);
            meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            getLogger().info("[RECIPE] Finished applying enchant.");
        } else if (getConfig().getBoolean("ITEM.AppearEnchanted") != true) {
            getLogger().info("[RECIPE] AppearEnchanted is false. Skipping enchant system");
            getLogger().info("[RECIPE] Skipped enchant system.");
        }
        item1.setItemMeta(meta1);

        //Shaped Recipe
        getLogger().info("Loading Recipe - 1/1");
        ShapedRecipe sr1 = new ShapedRecipe(NamespacedKey.minecraft("rerollitem"), item1);
        String shape1_1 = getConfig().getString("RECIPE.Shape1");
        String shape2_1 = getConfig().getString("RECIPE.Shape2");
        String shape3_1 = getConfig().getString("RECIPE.Shape3");
        sr1.shape(shape1_1,
                shape2_1,
                shape3_1);
        // A = Diamond, B = Netherite Ingot, C = Gold Ingot, D = Diamond Block
        sr1.setIngredient('A', Material.matchMaterial(getConfig().getString("RECIPE_INGREDIENTS.Ingredient_A")));
        sr1.setIngredient('B', Material.matchMaterial(getConfig().getString("RECIPE_INGREDIENTS.Ingredient_B")));
        sr1.setIngredient('C', Material.matchMaterial(getConfig().getString("RECIPE_INGREDIENTS.Ingredient_C")));
        sr1.setIngredient('D', Material.matchMaterial(getConfig().getString("RECIPE_INGREDIENTS.Ingredient_D")));
        sr1.setIngredient('-', Material.matchMaterial(getConfig().getString("RECIPE_INGREDIENTS.Ingredient_E")));

        //Adding Recipes
        getLogger().info("Adding Recipe(s)");
        Bukkit.getServer().addRecipe(sr1);
        getLogger().info("Finished! You are currently running version " + pdf.getVersion());
    }

    public class ActivateItemThing implements Listener {
        @EventHandler
        public void openCustomItem(PlayerInteractEvent e){
            Player player = e.getPlayer();
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
                getLogger().info("[DEBUG] Right clicked block");
                try{
                    if (Objects.requireNonNull(player.getEquipment().getItemInMainHand().getItemMeta()).getDisplayName().contains(getConfig().getString("ITEM.ItemName"))){
                        getLogger().info("[DEBUG] Found item");
                        if (e.getItem().getType() == Material.matchMaterial(getConfig().getString("ITEM.Material"))){
                            player.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lifecounter add " + player.getDisplayName() + " 1");
                        }
                    }
                }catch (Exception ignore){
                    getLogger().info(player.getDisplayName() + " has redeemed a heart!");
                }
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutting down AddStar / LifeCounter addon");
    }
}
