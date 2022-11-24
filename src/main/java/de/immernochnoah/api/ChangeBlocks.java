package de.immernochnoah.api;

import de.immernochnoah.listeners.OnPlayerMoveEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ChangeBlocks {

    static ArrayList<Material> materialList = new ArrayList<>();
    static Material getBlock = Material.AIR;
    static Map<String, Long> randomBlockCooldown = new HashMap<String, Long>();

    public static void changeBlocks(Player player) {
        BlockRadius br = new BlockRadius();
        Location loc = player.getLocation();
        loc.setY(loc.getY() -1);
        ArrayList<Block> blocks = br.getBlocks(player.getLocation().getWorld().getBlockAt(loc), 20);
        Material randomMaterial = ChangeBlocks.randomMaterial(player);
        for (int i = 0; i < blocks.size() - 1; i++) {
            Block block = blocks.get(i);
            if (!blocks.get(i).getType().equals(randomMaterial)) {
                switch (block.getType()) {
                    case AIR, CHEST, TRAPPED_CHEST, FURNACE, BLAST_FURNACE, WATER:
                        break;
                    default:
                        block.setType(randomMaterial);
                        break;
                }
            }
        }
    }

    public static Material randomMaterial(Player player) {
        if (randomBlockCooldown.containsKey(player.getName())) {
            if (randomBlockCooldown.get(player.getName()) > System.currentTimeMillis()) {
                return getBlock;
            }
        }
        randomBlockCooldown.put(player.getName(), System.currentTimeMillis() + (10000));

        Random rand = new Random();
        int random = rand.nextInt(materialList.size() - 1);
        getBlock = materialList.get(random);
        return getBlock;
    }



    public static void setMaterialList() {
        materialList.add(Material.GLOWSTONE);
        materialList.add(Material.COBBLESTONE);
        materialList.add(Material.ACACIA_WOOD);
        materialList.add(Material.OAK_WOOD);
        materialList.add(Material.BIRCH_WOOD);
        materialList.add(Material.DARK_OAK_WOOD);
        materialList.add(Material.CAKE);
        materialList.add(Material.COPPER_ORE);
        materialList.add(Material.OAK_LEAVES);
        materialList.add(Material.ANDESITE);
        materialList.add(Material.ICE);
        materialList.add(Material.SAND);
        materialList.add(Material.SPONGE);
        materialList.add(Material.WATER);
        materialList.add(Material.PRISMARINE);
        materialList.add(Material.GOLD_BLOCK);
        materialList.add(Material.DIAMOND_BLOCK);
        materialList.add(Material.IRON_BLOCK);
        materialList.add(Material.CRAFTING_TABLE);
        materialList.add(Material.COAL_BLOCK);
        materialList.add(Material.GLASS_PANE);
    }
}
