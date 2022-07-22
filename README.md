# AddStar
A LifeCounter addon that allows you to add hearts on right click with your chosen item.
(I know, i'm not supposed to put everything in one class...)
Requires LifeCounter: https://www.spigotmc.org/resources/102947/

config.yml:
```# Addon for LifeCounter, which allows you to add hearts on right click on item.
# LifeCounter must be installed! Made by AbeTGT 2022.
# Credit to Xodesito for making the original LifeCounter. https://www.spigotmc.org/resources/102947/

ITEM:
  # The item name. Supports Minecraft color codes (§), but not recommended.
  # Don't name it something like "a". The addon detects if the object right clicked CONTAINS the item name.
  ItemName: "§bLife Item"
  # The item description/lore. Supports Minecraft color codes (§).
  ItemDescription: "§dAdd a life to your current count."
  # The material of the item to be used. Make sure this is correct or the plugin will error!
  Material: NETHER_STAR
  # Set to true if you want the item to appear enchanted. Set to false if you don't want it to look enchanted.
  AppearEnchanted: true

RECIPE:
  # Spigot Recipe Example: https://www.spigotmc.org/wiki/recipe-example/
  # Default Recipe Ingredients: A = Diamond, B = Netherite Ingot, C = Gold Ingot, D = Diamond Block, - = Air
  Shape1: "-B-"
  Shape2: "ADA"
  Shape3: "-C-"

RECIPE_INGREDIENTS:
  # You can change the ingredients if you don't want them (use Bukkit/Spigot materials).
  # Make sure they are correct or the plugin will error!
  Ingredient_A: DIAMOND
  Ingredient_B: NETHERITE_INGOT
  Ingredient_C: GOLD_INGOT
  Ingredient_D: DIAMOND_BLOCK
  Ingredient_E: AIR```
