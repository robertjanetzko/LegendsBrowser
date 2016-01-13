package legends.model;

import legends.xml.annotation.Xml;

public class LegendsXml {
	World world;

	public World getWorld() {
		return world;
	}

	@Xml("df_world")
	public void setWorld(World world) {
		this.world = world;
	}
	
	
}
