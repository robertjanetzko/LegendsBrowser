package legends.model.events;

import legends.model.HistoricalFigure;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("assume identity")
public class AssumeIdentityEvent extends Event implements HfRelatedEvent, EntityRelatedEvent {
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
	public String getShortDescription() {
		HistoricalFigure hf = World.getHistoricalFigure(tricksterHfId);
		return hf.getLink() + " fooled "
				+ World.getEntity(targetEnId).getLink()+" into believing "+hf.getPronoun()+" was "+identityId;
	}
}
