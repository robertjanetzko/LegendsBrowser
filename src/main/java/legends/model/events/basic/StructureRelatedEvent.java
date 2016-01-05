package legends.model.events.basic;

public interface StructureRelatedEvent {
	boolean isRelatedToStructure(int structureId, int siteId);
}
