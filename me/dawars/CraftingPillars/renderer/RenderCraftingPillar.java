package me.dawars.CraftingPillars.renderer;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.util.Random;

import javax.swing.Renderer;

import org.lwjgl.opengl.GL11;

import com.google.common.primitives.SignedBytes;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import me.dawars.CraftingPillars.CraftingPillars;
import me.dawars.CraftingPillars.tiles.TileEntityCraftingPillar;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class RenderCraftingPillar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	private ResourceLocation TEXTURE_WORKPILLAR;
	
	public static ModelBase model = new ModelBase()
	{
		
	};
	
	private ModelRenderer bottom;
	private ModelRenderer pillarbottom;
	private ModelRenderer pillar;
	private ModelRenderer pillartop;
	private ModelRenderer top;
	
	private ModelRenderer Icicle1A;
	private ModelRenderer Icicle1B;
    private ModelRenderer Icicle1C;
    private ModelRenderer Icicle2A;
    private ModelRenderer Icicle2B;
    private ModelRenderer Icicle2C;
    private ModelRenderer Icicle3A;
    private ModelRenderer Icicle3B;
    private ModelRenderer Icicle4A;
    private ModelRenderer Icicle4B;
    private ModelRenderer Icicle5A;
    private ModelRenderer Icicle5B;
    private ModelRenderer Icicle5C;
    private ModelRenderer Icicle6A;
    private ModelRenderer Icicle6B;
    private ModelRenderer Icicle6C;
    private ModelRenderer Icicle7A;
    private ModelRenderer Icicle7B;
    private ModelRenderer Icicle7C;
    private ModelRenderer Icicle8A;
    private ModelRenderer Icicle8B;
    private ModelRenderer Icicle8C;
    private ModelRenderer Icicle8D;
    private ModelRenderer WreathA;
    private ModelRenderer WreathB;
    private ModelRenderer WreathC;
    private ModelRenderer WreathD;
    private ModelRenderer WreathE;
    private ModelRenderer WreathF;
    private ModelRenderer WreathG;
    private ModelRenderer WreathH;
    private ModelRenderer WreathI;
    private ModelRenderer WreathJ;
    private ModelRenderer Bow;
	
	private ModelRenderer pillarBottom;
	
	private Random random;
	private RenderingHelper.ItemRender itemRenderer;
	private RenderingHelper.ItemRender resultRenderer;
	
	public RenderCraftingPillar()
	{
		if(CraftingPillars.winter)
			TEXTURE_WORKPILLAR = new ResourceLocation(CraftingPillars.id + ":textures/models/craftingPillarFrozen.png");
		else
			TEXTURE_WORKPILLAR = new ResourceLocation(CraftingPillars.id + ":textures/models/craftingPillar.png");
		random = new Random();
		itemRenderer = new RenderingHelper.ItemRender(false, true);
		resultRenderer = new RenderingHelper.ItemRender(true, true);
		
		model.textureWidth = 128;
		model.textureHeight = 64;
		
		pillarBottom = new ModelRenderer(model, 0, 33);
		pillarBottom.addBox(0F, 0F, 0F, 12, 3, 12);
		pillarBottom.setRotationPoint(-6F, 21F, 6F);
		pillarBottom.setTextureSize(128, 64);
		pillarBottom.mirror = true;
		setRotation(pillarBottom, 0F, 1.570796F, 0F);
		
		bottom = new ModelRenderer(model, 0, 0);
		bottom.addBox(-8F, -1F, -8F, 16, 2, 16);
		bottom.setRotationPoint(0F, 23F, 0F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		pillarbottom = new ModelRenderer(model, 0, 18);
		pillarbottom.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillarbottom.setRotationPoint(0F, 21F, 0F);
		pillarbottom.setTextureSize(128, 64);
		pillarbottom.mirror = true;
		setRotation(pillarbottom, 0F, 0F, 0F);
		pillar = new ModelRenderer(model, 0, 33);
		pillar.addBox(-6F, 0F, -6F, 12, 10, 12);
		pillar.setRotationPoint(0F, 11F, 0F);
		pillar.setTextureSize(128, 64);
		pillar.mirror = true;
		setRotation(pillar, 0F, 0F, 0F);
		pillartop = new ModelRenderer(model, 0, 18);
		pillartop.addBox(-7F, 0F, -7F, 14, 1, 14);
		pillartop.setRotationPoint(0F, 10F, 0F);
		pillartop.setTextureSize(128, 64);
		pillartop.mirror = true;
		setRotation(pillartop, 0F, 0F, 0F);
		top = new ModelRenderer(model, 64, 0);
		top.addBox(-8F, -1F, -8F, 16, 2, 16);
		top.setRotationPoint(0F, 9F, 0F);
		top.setTextureSize(128, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		
		//Winter
		Icicle1A = new ModelRenderer(model, 122, 60);
		Icicle1A.addBox(0F, 0F, 0F, 1, 2, 2);
		Icicle1A.setRotationPoint(6F, 11F, -5F);
		Icicle1A.setTextureSize(128, 64);
		Icicle1A.mirror = true;
		setRotation(Icicle1A, 0F, 0F, 0F);
		Icicle1B = new ModelRenderer(model, 124, 58);
		Icicle1B.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle1B.setRotationPoint(6F, 11F, -3F);
		Icicle1B.setTextureSize(128, 64);
		Icicle1B.mirror = true;
		setRotation(Icicle1B, 0F, 0F, 0F);
		Icicle1C = new ModelRenderer(model, 124, 56);
		Icicle1C.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle1C.setRotationPoint(6F, 13F, -4F);
		Icicle1C.setTextureSize(128, 64);
		Icicle1C.mirror = true;
		setRotation(Icicle1C, 0F, 0F, 0F);
		Icicle2A = new ModelRenderer(model, 122, 50);
		Icicle2A.addBox(0F, 0F, 0F, 1, 2, 2);
		Icicle2A.setRotationPoint(6F, 11F, 0F);
		Icicle2A.setTextureSize(128, 64);
		Icicle2A.mirror = true;
		setRotation(Icicle2A, 0F, 0F, 0F);
		Icicle2B = new ModelRenderer(model, 124, 47);
		Icicle2B.addBox(0F, 0F, 0F, 1, 2, 1);
		Icicle2B.setRotationPoint(6F, 13F, 0F);
		Icicle2B.setTextureSize(128, 64);
		Icicle2B.mirror = true;
		setRotation(Icicle2B, 0F, 0F, 0F);
		Icicle2C = new ModelRenderer(model, 124, 54);
		Icicle2C.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle2C.setRotationPoint(6F, 11F, -1F);
		Icicle2C.setTextureSize(128, 64);
		Icicle2C.mirror = true;
		setRotation(Icicle2C, 0F, 0F, 0F);
		Icicle3A = new ModelRenderer(model, 120, 43);
		Icicle3A.addBox(0F, 0F, 0F, 1, 1, 3);
		Icicle3A.setRotationPoint(6F, 11F, 3F);
		Icicle3A.setTextureSize(128, 64);
		Icicle3A.mirror = true;
		setRotation(Icicle3A, 0F, 0F, 0F);
		Icicle3B = new ModelRenderer(model, 124, 40);
		Icicle3B.addBox(0F, 0F, 0F, 1, 2, 1);
		Icicle3B.setRotationPoint(6F, 12F, 4F);
		Icicle3B.setTextureSize(128, 64);
		Icicle3B.mirror = true;
		setRotation(Icicle3B, 0F, 0F, 0F);
		Icicle4A = new ModelRenderer(model, 122, 38);
		Icicle4A.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle4A.setRotationPoint(3F, 11F, 6F);
		Icicle4A.setTextureSize(128, 64);
		Icicle4A.mirror = true;
		setRotation(Icicle4A, 0F, 0F, 0F);
		Icicle4B = new ModelRenderer(model, 124, 36);
		Icicle4B.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle4B.setRotationPoint(4F, 12F, 6F);
		Icicle4B.setTextureSize(128, 64);
		Icicle4B.mirror = true;
		setRotation(Icicle4B, 0F, 0F, 0F);
		Icicle5A = new ModelRenderer(model, 114, 61);
		Icicle5A.addBox(0F, 0F, 0F, 3, 2, 1);
		Icicle5A.setRotationPoint(-1F, 11F, 6F);
		Icicle5A.setTextureSize(128, 64);
		Icicle5A.mirror = true;
		setRotation(Icicle5A, 0F, 0F, 0F);
		Icicle5B = new ModelRenderer(model, 116, 59);
		Icicle5B.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle5B.setRotationPoint(-1F, 13F, 6F);
		Icicle5B.setTextureSize(128, 64);
		Icicle5B.mirror = true;
		setRotation(Icicle5B, 0F, 0F, 0F);
		Icicle5C = new ModelRenderer(model, 120, 56);
		Icicle5C.addBox(0F, 0F, 0F, 1, 2, 1);
		Icicle5C.setRotationPoint(0F, 14F, 6F);
		Icicle5C.setTextureSize(128, 64);
		Icicle5C.mirror = true;
		setRotation(Icicle5C, 0F, 0F, 0F);
		Icicle6A = new ModelRenderer(model, 114, 54);
		Icicle6A.addBox(0F, 0F, 0F, 4, 1, 1);
		Icicle6A.setRotationPoint(-5F, 11F, 6F);
		Icicle6A.setTextureSize(128, 64);
		Icicle6A.mirror = true;
		setRotation(Icicle6A, 0F, 0F, 0F);
		Icicle6B = new ModelRenderer(model, 116, 52);
		Icicle6B.addBox(0F, 0F, 0F, 2, 1, 1);
		Icicle6B.setRotationPoint(-4F, 12F, 6F);
		Icicle6B.setTextureSize(128, 64);
		Icicle6B.mirror = true;
		setRotation(Icicle6B, 0F, 0F, 0F);
		Icicle6C = new ModelRenderer(model, 118, 50);
		Icicle6C.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle6C.setRotationPoint(-4F, 13F, 6F);
		Icicle6C.setTextureSize(128, 64);
		Icicle6C.mirror = true;
		setRotation(Icicle6C, 0F, 0F, 0F);
		Icicle7A = new ModelRenderer(model, 104, 59);
		Icicle7A.addBox(0F, 0F, 0F, 1, 1, 4);
		Icicle7A.setRotationPoint(-7F, 11F, 1F);
		Icicle7A.setTextureSize(128, 64);
		Icicle7A.mirror = true;
		setRotation(Icicle7A, 0F, 0F, 0F);
		Icicle7B = new ModelRenderer(model, 114, 46);
		Icicle7B.addBox(0F, 0F, 0F, 1, 2, 2);
		Icicle7B.setRotationPoint(-7F, 12F, 2F);
		Icicle7B.setTextureSize(128, 64);
		Icicle7B.mirror = true;
		setRotation(Icicle7B, 0F, 0F, 0F);
		Icicle7C = new ModelRenderer(model, 116, 44);
		Icicle7C.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle7C.setRotationPoint(-7F, 14F, 2F);
		Icicle7C.setTextureSize(128, 64);
		Icicle7C.mirror = true;
		setRotation(Icicle7C, 0F, 0F, 0F);
		Icicle8A = new ModelRenderer(model, 104, 54);
		Icicle8A.addBox(0F, 0F, 0F, 1, 1, 4);
		Icicle8A.setRotationPoint(-7F, 11F, -5F);
		Icicle8A.setTextureSize(128, 64);
		Icicle8A.mirror = true;
		setRotation(Icicle8A, 0F, 0F, 0F);
		Icicle8B = new ModelRenderer(model, 106, 50);
		Icicle8B.addBox(0F, 0F, 0F, 1, 1, 3);
		Icicle8B.setRotationPoint(-7F, 12F, -4F);
		Icicle8B.setTextureSize(128, 64);
		Icicle8B.mirror = true;
		setRotation(Icicle8B, 0F, 0F, 0F);
		Icicle8C = new ModelRenderer(model, 108, 46);
		Icicle8C.addBox(0F, 0F, 0F, 1, 2, 2);
		Icicle8C.setRotationPoint(-7F, 13F, -4F);
		Icicle8C.setTextureSize(128, 64);
		Icicle8C.mirror = true;
		setRotation(Icicle8C, 0F, 0F, 0F);
		Icicle8D = new ModelRenderer(model, 112, 44);
		Icicle8D.addBox(0F, 0F, 0F, 1, 1, 1);
		Icicle8D.setRotationPoint(-7F, 15F, -3F);
		Icicle8D.setTextureSize(128, 64);
		Icicle8D.mirror = true;
		setRotation(Icicle8D, 0F, 0F, 0F);
		WreathA = new ModelRenderer(model, 86, 62);
		WreathA.addBox(0F, 0F, 0F, 4, 1, 1);
		WreathA.setRotationPoint(-2F, 12F, -7F);
		WreathA.setTextureSize(128, 64);
		WreathA.mirror = true;
		setRotation(WreathA, 0F, 0F, 0F);
		WreathB = new ModelRenderer(model, 82, 60);
		WreathB.addBox(0F, 0F, 0F, 6, 1, 1);
		WreathB.setRotationPoint(-3F, 13F, -7F);
		WreathB.setTextureSize(128, 64);
		WreathB.mirror = true;
		setRotation(WreathB, 0F, 0F, 0F);
		WreathC = new ModelRenderer(model, 88, 51);
		WreathC.addBox(0F, 0F, 0F, 2, 4, 1);
		WreathC.setRotationPoint(-4F, 14F, -7F);
		WreathC.setTextureSize(128, 64);
		WreathC.mirror = true;
		setRotation(WreathC, 0F, 0F, 0F);
		WreathD = new ModelRenderer(model, 90, 54);
		WreathD.addBox(0F, 0F, 0F, 2, 4, 1);
		WreathD.setRotationPoint(2F, 14F, -7F);
		WreathD.setTextureSize(128, 64);
		WreathD.mirror = true;
		setRotation(WreathD, 0F, 0F, 0F);
		WreathE = new ModelRenderer(model, 82, 46);
		WreathE.addBox(0F, 0F, 0F, 6, 1, 1);
		WreathE.setRotationPoint(-3F, 18F, -7F);
		WreathE.setTextureSize(128, 64);
		WreathE.mirror = true;
		setRotation(WreathE, 0F, 0F, 0F);
		WreathF = new ModelRenderer(model, 86, 49);
		WreathF.addBox(0F, 0F, 0F, 4, 1, 1);
		WreathF.setRotationPoint(-2F, 19F, -7F);
		WreathF.setTextureSize(128, 64);
		WreathF.mirror = true;
		setRotation(WreathF, 0F, 0F, 0F);
		WreathG = new ModelRenderer(model, 88, 38);
		WreathG.addBox(0F, 0F, 0F, 1, 1, 1);
		WreathG.setRotationPoint(-2F, 14F, -7F);
		WreathG.setTextureSize(128, 64);
		WreathG.mirror = true;
		setRotation(WreathG, 0F, 0F, 0F);
		WreathH = new ModelRenderer(model, 88, 38);
		WreathH.addBox(0F, 0F, 0F, 1, 1, 1);
		WreathH.setRotationPoint(1F, 14F, -7F);
		WreathH.setTextureSize(128, 64);
		WreathH.mirror = true;
		setRotation(WreathH, 0F, 0F, 0F);
		WreathI = new ModelRenderer(model, 88, 38);
		WreathI.addBox(0F, 0F, 0F, 1, 1, 1);
		WreathI.setRotationPoint(-2F, 17F, -7F);
		WreathI.setTextureSize(128, 64);
		WreathI.mirror = true;
		setRotation(WreathI, 0F, 0F, 0F);
		WreathJ = new ModelRenderer(model, 88, 38);
		WreathJ.addBox(0F, 0F, 0F, 1, 1, 1);
		WreathJ.setRotationPoint(1F, 17F, -7F);
		WreathJ.setTextureSize(128, 64);
		WreathJ.mirror = true;
		setRotation(WreathJ, 0F, 0F, 0F);
		Bow = new ModelRenderer(model, 120, 28);
		Bow.addBox(0F, -1F, -2F, 2, 2, 2);
		Bow.setRotationPoint(-1F, 14F, -6F);
		Bow.setTextureSize(128, 64);
		Bow.mirror = true;
		setRotation(Bow, 0F, 0F, 0F);
	}
	
	public void render(float f)
	{
		if(CraftingPillars.winter)
		{
			Icicle1A.render(f);
		    Icicle1B.render(f);
		    Icicle1C.render(f);
		    Icicle2A.render(f);
		    Icicle2B.render(f);
		    Icicle2C.render(f);
		    Icicle3A.render(f);
		    Icicle3B.render(f);
		    Icicle4A.render(f);
		    Icicle4B.render(f);
		    Icicle5A.render(f);
		    Icicle5B.render(f);
		    Icicle5C.render(f);
		    Icicle6A.render(f);
		    Icicle6B.render(f);
		    Icicle6C.render(f);
		    Icicle7A.render(f);
		    Icicle7B.render(f);
		    Icicle7C.render(f);
		    Icicle8A.render(f);
		    Icicle8B.render(f);
		    Icicle8C.render(f);
		    Icicle8D.render(f);
		    WreathA.render(f);
		    WreathB.render(f);
		    WreathC.render(f);
		    WreathD.render(f);
		    WreathE.render(f);
		    WreathF.render(f);
		    WreathG.render(f);
		    WreathH.render(f);
		    WreathI.render(f);
		    WreathJ.render(f);
		    Bow.render(f);
		}
		
		bottom.render(f);
		pillarbottom.render(f);
		pillar.render(f);
		pillartop.render(f);
		top.render(f);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		glPushMatrix();
		glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		glRotatef(180F, 1F, 0F, 0F);
		glRotatef(90F * (tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) - 2), 0F, 1F, 0F);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_WORKPILLAR);
		render(0.0625F);
		glPopMatrix();
		
		TileEntityCraftingPillar workTile = (TileEntityCraftingPillar) tile;
		EntityItem citem = new EntityItem(tile.worldObj);
		citem.hoverStart = CraftingPillars.floatingItems ? workTile.rot : 0F;
		
		glPushMatrix();
		glTranslated(x, y, z);
		for(int i = 0; i < 3; i++)
		{
			for(int k = 0; k < 3; k++)
			{
				if(workTile.getStackInSlot(i * 3 + k) != null)
				{
					citem.setEntityItemStack(workTile.getStackInSlot(i * 3 + k));
					glPushMatrix();
						glTranslated(0.1875D + i * 0.3125D, 1D + 0.1875D / 3D, 0.1875D + k * 0.3125D);
						glScalef(0.5F, 0.5F, 0.5F);
						itemRenderer.render(citem, 0F, 0F, 0F, workTile.showNum);
					glPopMatrix();
				}
			}
		}
		
		if(workTile.getStackInSlot(workTile.getSizeInventory()) != null)
		{
			glPushMatrix();
			citem.hoverStart = -workTile.rot;
			citem.setEntityItemStack(workTile.getStackInSlot(workTile.getSizeInventory()));
			resultRenderer.render(citem, 0.5F, 1.5F, 0.5F, workTile.showNum);
			glPopMatrix();
		}
		glPopMatrix();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		glPushMatrix();
		glTranslated(0, 1.0D, 0);
		glRotatef(180F, 1F, 0F, 0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURE_WORKPILLAR);
		render(0.0625F);
		glPopMatrix();
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}
	
	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}
	
	@Override
	public int getRenderId()
	{
		return CraftingPillars.craftingPillarRenderID;
	}
}
