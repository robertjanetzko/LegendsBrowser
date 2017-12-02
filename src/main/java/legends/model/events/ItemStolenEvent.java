package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfEvent;
import legends.model.events.basic.Item;
import legends.model.events.basic.SiteRelatedEvent;
import legends.model.events.basic.StructureRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlComponent;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("item stolen")
public class ItemStolenEvent extends HfEvent implements SiteRelatedEvent, StructureRelatedEvent, ArtifactRelatedEvent {
	@Xml("site")
	private int calcSiteId = -1;
	@Xml("structure")
	private int structureId = -1;
	@Xml("entity")
	private int entityId = -1;
	@Xml(value = "circumstance", track = true)
	private String circumstance;
	@Xml("circumstance_id")
	private int circumstanceId;
	@XmlComponent
	Item item = new Item();

	private int calcLootedFromHfId = -1;
	private int calcLootedByHfId = -1;
	private int calcLootArtifactId = -1;

	public int getCalcSiteId() {
		return calcSiteId;
	}

	public void setCalcSiteId(int calcSiteId) {
		this.calcSiteId = calcSiteId;
	}

	public int getStructureId() {
		return structureId;
	}

	public void setStructureId(int structureId) {
		this.structureId = structureId;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getCircumstance() {
		return circumstance;
	}

	public void setCircumstance(String circumstance) {
		this.circumstance = circumstance;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return super.isRelatedToHf(hfId) || this.calcLootedByHfId == hfId || this.calcLootedFromHfId == hfId;
	}

	@Override
	public boolean isRelatedToSite(int siteId) {
		return this.calcSiteId == siteId;
	}

	@Override
	public boolean isRelatedToStructure(int structureId, int siteId) {
		return this.structureId == structureId && this.calcSiteId == siteId;
	}

	@Override
	public boolean isRelatedToArtifact(int artifactId) {
		return this.calcLootArtifactId == artifactId;
	}

	@Override
	public void process() {
		if ("defeated hf".equals(circumstance)) {
			Event pre = World.getHistoricalEvent(id - 1);
			if (pre != null && pre instanceof HfDiedEvent) {
				HfDiedEvent e = (HfDiedEvent) pre;
				calcLootedFromHfId = e.getHfId();
				calcLootedByHfId = e.getSlayerHfId();
			}
			Event post = World.getHistoricalEvent(id + 1);
			if (post != null && post instanceof ArtifactClaimFormedEvent) {
				ArtifactClaimFormedEvent e = (ArtifactClaimFormedEvent) post;
				calcLootArtifactId = e.getArtifactId();
			}
		}
	}

	@Override
	public String getShortDescription() {
		String structure = "";
		if (structureId != -1)
			structure = World.getStructure(structureId, calcSiteId).getLink() + " in ";

		String site = "UNKNOWN SITE";
		if (calcSiteId != -1)
			site = World.getSite(calcSiteId).getLink();

		String hf = "UNKNOWN HISTORICAL FIGURE";
		if (hfId != -1)
			hf = World.getHistoricalFigure(hfId).getLink();

		if ("defeated hf".equals(circumstance)) {
			return World.getArtifact(calcLootArtifactId).getLink() + " was looted from "
					+ World.getHistoricalFigure(calcLootedFromHfId).getLink() + " by "
					+ World.getHistoricalFigure(calcLootedByHfId).getLink();
		}

		String item = this.item.getText("");
		if (item.isEmpty())
			item = "UNKNOWN ITEM";

		return item + " was stolen from " + structure + site + " by " + hf;
	}

}
