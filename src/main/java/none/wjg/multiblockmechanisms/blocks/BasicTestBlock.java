package none.wjg.multiblockmechanisms.blocks;

import none.wjg.multiblockmechanisms.MultiBlockMechanisms;
import none.wjg.multiblockmechanisms.reference.BlockNames;
import none.wjg.multiblockmechanisms.reference.ModCreativeTabs;

public class BasicTestBlock extends MbmBlock {

	public BasicTestBlock(){
		
		super();
		this.setUnlocalizedName(BlockNames.BASIC_TEST_BLOCK_NAME);
		this.setCreativeTab(ModCreativeTabs.TEST_TAB);
	}
}
