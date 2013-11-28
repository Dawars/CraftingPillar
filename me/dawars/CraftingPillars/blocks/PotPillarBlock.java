package me.dawars.CraftingPillars.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.dawars.CraftingPillars.CraftingPillars;
import me.dawars.CraftingPillars.tiles.TileEntityCraftingPillar;
import me.dawars.CraftingPillars.tiles.TileEntityPotPillar;
import me.dawars.CraftingPillars.world.gen.ChristmasTreeGen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class PotPillarBlock extends BaseBlockContainer
{
	public PotPillarBlock(int id, Material mat)
	{
		super(id, mat);
        this.setTickRandomly(true);
	}
	
	@Override
	public int getRenderType()
	{
		return CraftingPillars.potPillarRenderID;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	/**
     * Used during tree growth to determine if newly generated leaves can replace this block.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @return true if this block can be replaced by growing leaves.
     */
	@Override
    public boolean canBeReplacedByLeaves(World world, int x, int y, int z)
    {
        return false;
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Returns true only if block is flowerPot
	 */
	public boolean isFlowerPot()
	{
		return true;
	}
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			TileEntityPotPillar pillarTile = (TileEntityPotPillar)world.getBlockTileEntity(x, y, z);
			if(pillarTile.getStackInSlot(0) != null)
				pillarTile.dropItemFromSlot(0, pillarTile.getStackInSlot(0).stackSize, player);
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote)
			return true;
		
		TileEntityPotPillar pillarTile = (TileEntityPotPillar) world.getBlockTileEntity(x, y, z);

		if(hitY < 1F && !player.isSneaking() && player.getCurrentEquippedItem() == null)
		{
			pillarTile.showNum = !pillarTile.showNum;
			pillarTile.onInventoryChanged();
		}
		
		if(hitY == 1F)
		{
			if(player.isSneaking())//pick out
			{
				pillarTile.dropItemFromSlot(0, 1, player);
			}
			else if(player.getCurrentEquippedItem() != null)
			{//put in
				if(player.getCurrentEquippedItem().itemID == this.blockID)
					player.addStat(CraftingPillars.achievementShowoff, 1);
				if(pillarTile.getStackInSlot(0) == null)
				{//slot empty
					if(pillarTile.isItemValidForSlot(0, player.getCurrentEquippedItem()))
					{
						if(!player.capabilities.isCreativeMode)
							player.getCurrentEquippedItem().stackSize--;
						
						ItemStack in = new ItemStack(player.getCurrentEquippedItem().itemID, 1, player.getCurrentEquippedItem().getItemDamage());
						pillarTile.setInventorySlotContents(0, in);
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		if(!world.isRemote)
		{
			TileEntityPotPillar workTile = (TileEntityPotPillar) world.getBlockTileEntity(x, y, z);
			
			if(workTile.getStackInSlot(0) != null)
			{
				EntityItem itemDropped = new EntityItem(world, x + 0.1875D, y + 1D, z + 0.1875D, workTile.getStackInSlot(0));
				itemDropped.motionX = itemDropped.motionY = itemDropped.motionZ = 0D;
				
				if(workTile.getStackInSlot(0).hasTagCompound())
					itemDropped.getEntityItem().setTagCompound((NBTTagCompound) workTile.getStackInSlot(0).getTagCompound().copy());
				
				world.spawnEntityInWorld(itemDropped);
			}
		}
		
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		TileEntityPotPillar tile = new TileEntityPotPillar();
		return tile;
	}
	
	 /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
        	super.updateTick(world, x, y, z, rand);
        	
        	TileEntityPotPillar tile = (TileEntityPotPillar) world.getBlockTileEntity(x, y, z);
	
	        if (tile.getStackInSlot(0) != null && tile.getStackInSlot(0).itemID == CraftingPillars.blockChristmasTreeSapling.blockID && tile.christmasTreeState <= 5)
	        {
	        	int l = world.getBlockMetadata(x, y, z);
	        	int randNum = rand.nextInt(4);
//	        	System.out.println("Xmas tree pot metadata: " + l + " rand: " + randNum + " Stage: " + tile.christmasTreeState);
			
	        	if(randNum == 0)
	        	{
	        		if (l < 8)
	                {
	                	world.setBlockMetadataWithNotify(x, y, z, l+1, 2);
	                }
	                else
	                {
						WorldGenerator tree = new ChristmasTreeGen(false, tile.christmasTreeState);
						if(tile.christmasTreeState == 0)
						{
							tree.generate(world, rand, x, y, z);
						}
						else 
						{
							if(tile.christmasTreeState >= 1 && CraftingPillars.treeState1)
							if(tile.christmasTreeState >= 2 && CraftingPillars.treeState2)
							if(tile.christmasTreeState >= 3 && CraftingPillars.treeState3)
							if(tile.christmasTreeState >= 4 && CraftingPillars.treeState4)
							{
								tree.generate(world, rand, x, y, z);
							}
						}
						tile.christmasTreeState+=1;
	                	world.setBlockMetadataWithNotify(x, y, z, 0, 4);
						Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(x, y, z);
			            world.updateAllLightTypes(x, y, z);
	                }
	        	}
	        }
        }
    }


    @SideOnly(Side.CLIENT)
	@Override
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
    	if(rand.nextInt(100) == 0)
    	{
			TileEntityPotPillar tile = (TileEntityPotPillar) world.getBlockTileEntity(x, y, z);
			if(tile.getStackInSlot(0) != null && tile.getStackInSlot(0).itemID == CraftingPillars.blockChristmasTreeSapling.blockID && tile.christmasTreeState == 0)
			{
				for (int i = 0; i < 5; ++i)
		        {
		            double d0 = rand.nextGaussian() * 0.02D;
		            double d1 = rand.nextGaussian() * 0.02D;
		            double d2 = rand.nextGaussian() * 0.02D;
		            float width = 0.3F;
		            float height = 0.5F;
					world.spawnParticle("happyVillager", x + 0.6F + (double)(rand.nextFloat() * width  * 2.0F) - (double)width, y + 1.2D + (double)(rand.nextFloat() * height), z + 0.6F + (double)(rand.nextFloat() * width * 2.0F) - (double)width, d0, d1, d2);
		        }
			}
    	}
	}

    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister itemIcon)
	{
		this.blockIcon = itemIcon.registerIcon(CraftingPillars.id + ":craftingPillar_side");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return this.blockIcon;
	}
}
