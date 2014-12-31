package none.wjg.multiblockmechanisms.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import none.wjg.multiblockmechanisms.reference.Reference;

public class MbmBlock extends Block {

	public MbmBlock(Material material){
		super(material);	
	}
	public MbmBlock(){
		super(Material.rock);
	}
	
	
	@Override
	public String getUnlocalizedName(){
			
		return String.format("tile.%s%s", Reference.MODID.toLowerCase()+":",getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

		
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}


