package legends.model.events.basic;

public interface LocalEvent extends SiteRelatedEvent, RegionRelatedEvent, StructureRelatedEvent {
	EventLocation getLocation();

	@Override
	default boolean isRelatedToRegion(int regionId) {
		return getLocation().getSubregionId() == regionId;
	}

	@Override
	default boolean isRelatedToSite(int siteId) {
		return getLocation().getSiteId() == siteId;
	}

	@Override
	default boolean isRelatedToStructure(int structureId, int siteId) {
		return isRelatedToSite(siteId) && getLocation().getStructureId() == structureId;
	}

}
