package legends.model.events.basic;

public interface LocalEvent extends SiteRelatedEvent, RegionRelatedEvent {
	EventLocation getLocation();
	
	@Override
	default boolean isRelatedToRegion(int regionId) {
		return getLocation().getSubregionId() == regionId;
	}
	
	@Override
	default boolean isRelatedToSite(int siteId) {
		return getLocation().getSiteId() == siteId;
	}
}
