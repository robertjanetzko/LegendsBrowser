package legends.model.events;

import legends.model.HistoricalFigure;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.model.events.basic.IdentityRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("assume identity")
public class AssumeIdentityEvent extends Event implements HfRelatedEvent, EntityRelatedEvent, IdentityRelatedEvent {
	@Xml("trickster_hfid,trickster")
	private int tricksterHfId = -1;
	@Xml("identity_id")
	private int identityId = -1;
	@Xml("target_enid,target")
	private int targetEnId = -1;

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return targetEnId == entityId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return tricksterHfId == hfId;
	}

	@Override
	public boolean isRelatedToIdentity(int identityId) {
		return this.identityId == identityId;
	}

	@Override
	public String getShortDescription() {
		HistoricalFigure hf = World.getHistoricalFigure(tricksterHfId);
		if(targetEnId == -1)
			return hf.getLink() + " assumed the identity &quot;" + World.getIdentity(identityId).getLink() + "&quot;"; 
		return hf.getLink() + " fooled " + World.getEntity(targetEnId).getLink() + " into believing " + hf.getPronoun()
				+ " was &quot;" + World.getIdentity(identityId).getLink() + "&quot;";
	}
}
